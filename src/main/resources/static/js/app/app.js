var app = angular.module('crudApp', [ 'ngRoute', 'ui.router', 'ngStorage', 'ui.grid', 'ui.grid.pagination' ]);

app.constant('urls', {
	BASE : 'http://localhost:8088/MySpringBootStarterApp',
	USER_SERVICE_API : 'http://localhost:8088/MySpringBootStarterApp/api/user/',
	PRODUCT_SERVICE_API : 'http://localhost:8088/MySpringBootStarterApp/api/product/',
	SECURE_SERVICE_API : 'http://localhost:8088/MySpringBootStarterApp/api/secure/'
});

app.run(
	['$rootScope', 'SecureService', function($rootScope, SecureService) {
		$rootScope.authenticated = (SecureService.checkAuthenticated()) ? true : false;
		console.log('Check authenticated : ' + $rootScope.authenticated);
		
		$rootScope.authority = (SecureService.checkAuthority()) ? SecureService.checkAuthority() : '';
		console.log('Get authority : ' + $rootScope.authority);
	}]
);

app.filter('htmlToPlaintext', function() {
    return function(text) {
    	return  text ? String(text).replace(/<[^>]+>/gm, '') : '';
      }
});

app.config([
	'$stateProvider',
	'$urlRouterProvider',
	'$httpProvider',
	function($stateProvider, $urlRouterProvider, $httpProvider) {

		$stateProvider
			.state('home', {
				url : '/home',
				templateUrl : 'partials/home'
			})
			.state('login', {
				url : '/login',
				templateUrl : 'partials/login',
				controller : 'SecureController',
				controllerAs : 'ctrl'
			})
			.state('logout', {
				url : '/logout',
				templateUrl : 'partials/login',
				controller : 'SecureController',
				controllerAs : 'ctrl',
				resolve : {
					logout : function($q, SecureService) {
						console.log('Logout');
						var deferred = $q.defer();
						SecureService.doLogout().then(
								deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
			})
			.state('users', {
				url : '/admin/users',
				templateUrl : 'partials/users',
				controller : 'UserController',
				controllerAs : 'ctrl',
				resolve : {
					users : function($q, UserService) {
						console.log('Load all users');
						var deferred = $q.defer();
						UserService.loadAllUsers().then(
								deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
			})
			.state('products', {
				url : '/admin/products',
				templateUrl : 'partials/products',
				controller : 'ProductController',
				controllerAs : 'ctrl',
				resolve : {
					products : function($q, ProductService) {
						console.log('Load all products');
						var deferred = $q.defer();
						ProductService.loadAllProducts().then(
								deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
			});
		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		$urlRouterProvider.otherwise('home');
	} 
]);