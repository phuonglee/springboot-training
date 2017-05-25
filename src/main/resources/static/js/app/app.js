var app = angular.module('crudApp', [ 'ui.router', 'ngStorage', 'ui.grid',
		'ui.grid.pagination' ]);

app.constant('urls', {
	BASE : 'http://localhost:8088/MySpringBootStarterApp',
	USER_SERVICE_API : 'http://localhost:8088/MySpringBootStarterApp/api/user/',
	PRODUCT_SERVICE_API : 'http://localhost:8088/MySpringBootStarterApp/api/product/'
});

app.config([
		'$stateProvider',
		'$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {

			$stateProvider
			.state('users', {
				url : '/users',
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
				url : '/products',
				templateUrl : 'partials/products',
				controller : 'ProductController',
				controllerAs : 'ctrl',
				resolve : {
					users : function($q, ProductService) {
						console.log('Load all products');
						var deferred = $q.defer();
						ProductService.loadAllProducts().then(
								deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
			});
			$urlRouterProvider.otherwise('/users');
		} ]);