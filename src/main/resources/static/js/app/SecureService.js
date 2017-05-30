'use strict';
 
angular.module('crudApp').factory('SecureService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
            		doLogin: doLogin,
            		doLogout: doLogout,
            		checkAuthenticated: checkAuthenticated,
            		checkAuthority : checkAuthority
            };
            
            return factory;
 
            function doLogin(account) {
                console.log('Do login');
                var headers = account ? {Authorization : "Basic "
	                + btoa(account.userName + ":" + account.password)
	            } : {};
                var deferred = $q.defer();
                $http.post(urls.SECURE_SERVICE_API, account, {headers : headers})
                    .then(
                        function (response) {
                        	console.log('Do login successfully');
                        	$localStorage.authority = angular.fromJson(response.data.authorities[0]).authority;
                        	$localStorage.authenticated = true;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                           console.error('Error while loging with account : ' + errResponse.data.errorMessage);
                           $localStorage.authenticated = false;
                           $localStorage.authority = '';
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function doLogout() {
            	console.log('Do logout');
            	var deferred = $q.defer();
            	$http.post('logout', {})
	            	.then(
                        function (response) {
                        	console.log('Logout successfully');
                        	$localStorage.authenticated = false;
                        	$localStorage.authority = '';
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                           console.error('Error while logout with account : '+errResponse.data.errorMessage);
                           $localStorage.authenticated = true;
                           deferred.reject(errResponse);
                        }
                    );
            	return deferred.promise;
            }
            
            function checkAuthenticated() {
            	return $localStorage.authenticated;
            }
 
            function checkAuthority() {
            	return $localStorage.authority;
            }
        }
    ]);