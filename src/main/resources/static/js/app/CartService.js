'use strict';
 
angular.module('crudApp').factory('CartService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                getAllProductsInCart: getAllProductsInCart,
                loadAllProductsInCart: loadAllProductsInCart,
                removeProductInCart: removeProductInCart
            };
 
            return factory;
 
            function loadAllProductsInCart() {
                console.log('Fetching all products in cart');
                var deferred = $q.defer();
                $http.get(urls.CART_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all products in cart');
                            $localStorage.productsInCart = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading products in cart');
                            $localStorage.productsInCart = null;
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getAllProductsInCart(){
                return $localStorage.productsInCart;
            }
            
            function removeProductInCart(id) {
                console.log('Removing Product in Cart with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.CART_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllProductsInCart();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Product in Cart with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);