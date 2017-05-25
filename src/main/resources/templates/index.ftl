<!DOCTYPE html>

<html lang="en" ng-app="crudApp">
<head>
<title>${title}</title>
<link href="/MySpringBootStarterApp/webjars/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link href="css/app.css" rel="stylesheet" />
<link
	href="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css"
	rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" ui-sref="home">Admin Page</a>
			</div>
			<div class="collapse navbar-collapse" ng-controller="HeaderController">
				<ul class="nav navbar-nav">
					<li ng-class="{active: isActive('/users')}"><a ui-sref="users">Users</a></li>
					<li ng-class="{active: isActive('/products')}"><a ui-sref="products">Products</a></li>
				</ul>
			</div>
			
		</div>
	</nav>

	<div class="container">
    	<div ui-view></div>
    </div>
    
	<script src="/MySpringBootStarterApp/webjars/angularjs/angular.min.js"></script>
	<script
		src="/MySpringBootStarterApp/webjars/angular-ui-router/angular-ui-router.min.js"></script>
	<script
		src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
	<script
		src="https://cdn.rawgit.com/mozilla/localForage/master/dist/localforage.js"></script>
	<script
		src="https://rawgithub.com/gsklee/ngStorage/master/ngStorage.js"></script>
	<script src="/MySpringBootStarterApp/js/app/app.js"></script>
	<script src="/MySpringBootStarterApp/js/app/UserService.js"></script>
	<script src="/MySpringBootStarterApp/js/app/UserController.js"></script>
	<script src="/MySpringBootStarterApp/js/app/HeaderController.js"></script>
	<script src="/MySpringBootStarterApp/js/app/ProductController.js"></script>
	<script src="/MySpringBootStarterApp/js/app/ProductService.js"></script>
</body>
</html>