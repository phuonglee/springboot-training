'use strict';

angular.module('crudApp').controller('CartController',
    ['CartService', '$scope',  function( CartService, $scope) {

        var self = this;
        self.product = {};
        self.products=[];
        self.carts = [];

        self.checkOut = checkOut;
        self.submitCart = submitCart;
        self.getAllProductsInCart = getAllProductsInCart;
        self.removeProductInCart = removeProductInCart;
        self.reset = reset;

        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        // FUNCTION DEFINITIONS
        function checkOut() {
            console.log('Check out products...');
        }

        function submitCart() {
            console.log('Submitting');
        }

        function removeProductInCart(id){
            console.log('About to remove Product with id ' + id + ' in Cart ');
            CartService.removeProductInCart(id)
                .then(
                    function(){
                        console.log('Product '+id + ' removed successfully from Cart');
                    },
                    function(errResponse){
                        console.error('Error while removing product '+id +', Error :'+errResponse.data);
                    }
                );
        }

        function getAllProductsInCart(){
            return CartService.getAllProductsInCart();
        }

        function reset(){
            console.log('Resetting');
        }
    }
    ]);