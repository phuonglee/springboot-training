'use strict';
 
angular.module('crudApp').controller('ProductController',
    ['ProductService', '$rootScope', '$scope',  function( ProductService, $rootScope, $scope) {

        var self = this;
        self.product = {};
        self.products = [];
        self.carts = [];

        self.submit = submit;
        self.getAllProducts = getAllProducts;
        self.createProduct = createProduct;
        self.updateProduct = updateProduct;
        self.removeProduct = removeProduct;
        self.editProduct = editProduct;
        self.reset = reset;
        self.addToCart = addToCart;
 
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        // PAGINATION
       var paginationOptions = {
       		pageNumber: 1,
       		pageSize: 5,
       		sort: null
       };

       getAllProductsPaginated();

       $scope.gridOptions = {
       		paginationPageSizes: [5, 10, 20, 30],
       		paginationPageSize: paginationOptions.pageSize,
       		enableColumnMenus: false,
       		useExternalPagination: true,
            enableRowSelection: true,
            enableSelectAll: true,
            multiSelect : true,
            selectionRowHeaderWidth: 35,
            rowHeight: 35,
            showGridFooter:true,
       		columnDefs: [
       			{name: 'id'},
       			{name: 'price'},
       			{name: 'productDetails'},
       			{name: 'productName'},
                {name: 'quantityThreshold'}
       		],
            onRegisterApi : function(gridApi) {
       			$scope.gridApi = gridApi;
       			gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
       				paginationOptions.pageNumber = newPage;
       				paginationOptions.pageSize = pageSize;
       				getAllProductsPaginated();
   		        });
                gridApi.selection.on.rowSelectionChanged($scope, function (row) {
                    var index = $scope.gridOptions.data.indexOf(row.entity);
                    var msg = 'rows ' + index + ' changed ' + row.isSelected;
                    $rootScope.selectedProducts = $scope.gridApi.selection.getSelectedRows()
                    console.log(msg);
                    console.log('selected rows: ' + $rootScope.selectedProducts);
                });
       			gridApi.selection.on.rowSelectionChangedBatch($scope, function (rows) {
                    var msg = 'rows changed ' + rows.length;
                    $rootScope.selectedProducts = $scope.gridApi.selection.getSelectedRows()
                    console.log(msg);
                    console.log('selected rows: ' + $rootScope.selectedProducts);
                });
       		}
       }
        
        // FUNCTION DEFINITIONS
        function submit() {
            console.log('Submitting');
            if (self.product.id === undefined || self.product.id === null) {
                console.log('Saving New Product', self.product);
                createProduct(self.product);
            } else {
                updateProduct(self.product, self.product.id);
                console.log('Product updated with id ', self.product.id);
            }
        }

        function addToCart() {
            console.log('Add products to Cart');
            ProductService.addToCart($rootScope.selectedProducts);
        }
 
        function createProduct(product) {
            console.log('About to create product');
            ProductService.createProduct(product)
                .then(
                    function (response) {
                        console.log('Product created successfully');
                        self.successMessage = 'Product created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.product={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Product');
                        self.errorMessage = 'Error while creating Product: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
        function updateProduct(product, id){
            console.log('About to update product');
            ProductService.updateProduct(product, id)
                .then(
                    function (response){
                        console.log('Product updated successfully');
                        self.successMessage='Product updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Product');
                        self.errorMessage='Error while updating Product '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
        function removeProduct(id){
            console.log('About to remove Product with id '+id);
            ProductService.removeProduct(id)
                .then(
                    function(){
                        console.log('Product '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing product '+id +', Error :'+errResponse.data);
                    }
                );
        }
 
        function getAllProducts(){
            return ProductService.getAllProducts();
        }
        
        function getAllProductsPaginated() {
        	console.log('Get all products paginated');
        	ProductService.loadAllProductsPaginated(paginationOptions.pageNumber, paginationOptions.pageSize)
            		.then(
            				function(response) {
            					console.log('Get all products paginated success');
            					$scope.gridOptions.data = response.content;
                    			$scope.gridOptions.totalItems = response.totalElements;
                    			self.products = response.content;
            				},
            				function(errResponse) {
            					console.log('Get all products paginated fail');
            				}
            		);
        }
 
        function editProduct(id) {
            self.successMessage='';
            self.errorMessage='';
            ProductService.getProduct(id).then(
                function (product) {
                    self.product = product;
                },
                function (errResponse) {
                    console.error('Error while removing product ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.product={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);