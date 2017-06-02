'use strict';
 
angular.module('crudApp').controller('SecureController', 
	['SecureService', '$rootScope', '$scope', '$location', '$state', 
		function(SecureService, $rootScope, $scope, $location, $state) {
		    var self = this;
		    self.account = {};
		    
		    self.submit = submit;
	        self.doLogin = doLogin;
	        self.reset = reset;
		    
	        self.errorMessage = '';
	        self.done = false;
	        
	        $rootScope.authority = '';
	        $rootScope.authenticated = false;
		    
		    function submit() {
	            console.log('Submitting');
                doLogin(self.account);
	        }
	 
	        function doLogin(account) {
	            console.log('About to login');
	            SecureService.doLogin(account)
	                .then(
	                    function (response) {
                            console.log('Login successfully');
	                        self.errorMessage='';
	                        self.done = true;
	                        self.account = {};
	                        $rootScope.authority = angular.fromJson(response.data.authorities[0]).authority;
	                        $rootScope.authenticated = true;
	                        $scope.myForm.$setPristine();
	                        $state.go('home');
	                    },
	                    function (errResponse) {
	                        console.error('Error while login');
	                        self.errorMessage = 'Error while doing login: ' + errResponse.data.errorMessage;
	                        $rootScope.authenticated = false;
	                    }
	                );
	        }

	        function doLogout() {
		    	console.log('About to logout');
		    	SecureService.doLogout();
			}
	        
	        function reset(){
	            self.errorMessage='';
	            self.account = {};
	            $scope.myForm.$setPristine(); //reset Form
	        }
	        
		}
	]
);