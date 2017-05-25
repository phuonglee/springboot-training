'use strict';
 
angular.module('crudApp').factory('ProductService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllProducts: loadAllProducts,
                loadAllProductsPaginated: loadAllProductsPaginated,
                getAllProducts: getAllProducts,
                getProduct: getProduct,
                createProduct: createProduct,
                updateProduct: updateProduct,
                removeProduct: removeProduct
            };
 
            return factory;
 
            function loadAllProducts() {
                console.log('Fetching all products');
                var deferred = $q.defer();
                $http.get(urls.PRODUCT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all products');
                            $localStorage.products = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading products');
                            $localStorage.products = null;
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function loadAllProductsPaginated(pageNumber, size) {
            	console.log('Load all products in page : ' + pageNumber + ', with size: ' + size);
            	pageNumber = pageNumber > 0 ? pageNumber - 1 : 0;
            	var deferred = $q.defer();
            	$http.get(urls.PRODUCT_SERVICE_API + 'get?page=' + pageNumber + '&size=' + size)
            		.then(
            				function(response) {
            					console.log('Successfully load products in page : ' + pageNumber + ', with size: ' + size);
            					deferred.resolve(response.data);
            				},
            				function(errResponse) {
            					console.log('Error while getting products in page : ' + pageNumber + ', with size: ' + size);
            					deferred.reject(errResponse);
            				}
    				);
            	return deferred.promise;
            }
 
            function getAllProducts(){
                return $localStorage.products;
            }
            
            function getProduct(id) {
                console.log('Fetching Product with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PRODUCT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Product with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading product with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function createProduct(product) {
                console.log('Creating Product');
                var deferred = $q.defer();
                $http.post(urls.PRODUCT_SERVICE_API, product)
                    .then(
                        function (response) {
                            loadAllProducts();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Product : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function updateProduct(product, id) {
                console.log('Updating Product with id '+id);
                var deferred = $q.defer();
                $http.put(urls.PRODUCT_SERVICE_API + id, product)
                    .then(
                        function (response) {
                            loadAllProducts();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Product with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removeProduct(id) {
                console.log('Removing Product with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PRODUCT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllProducts();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Product with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);