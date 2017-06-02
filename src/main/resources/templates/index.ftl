<!DOCTYPE html>

<html lang="en" ng-app="crudApp">
<head>
<title>${title}</title>
<link href="/MySpringBootStarterApp/webjars/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="/MySpringBootStarterApp/webjars/angular-ui-grid/ui-grid.min.css" rel="stylesheet" />
<link href="css/app.css" rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" ui-sref="home">Home Page</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li ng-show="authenticated && authority == 'ROLE_ADMIN'"><a ui-sref="users">Users</a></li>
					<li ng-show="authenticated"><a ui-sref="products">Products</a></li>
                    <li ng-show="authenticated && authority == 'ROLE_USER'"><a ui-sref="cart">Cart</a></li>
					<li ng-show="!authenticated"><a ui-sref="login">Login</a></li>
					<li ng-show="authenticated"><a ui-sref="logout">Logout</a></li>
				</ul>
			</div>

		</div>
	</nav>

	<div class="container">
		<div ui-view></div>
	</div>

	<script src="/MySpringBootStarterApp/webjars/angularjs/angular.min.js"></script>
	<script src="/MySpringBootStarterApp/webjars/angularjs/angular-route.js"></script>
    <script src="/MySpringBootStarterApp/webjars/angularjs/angular-touch.js"></script>
    <script src="/MySpringBootStarterApp/webjars/angularjs/angular-animate.js"></script>
	<script src="/MySpringBootStarterApp/webjars/angular-ui-router/angular-ui-router.min.js"></script>
	<script src="/MySpringBootStarterApp/webjars/angular-ui-grid/ui-grid.min.js"></script>
	<script src="/MySpringBootStarterApp/webjars/ngstorage/ngStorage.js"></script>
	<script src="/MySpringBootStarterApp/js/app/app.js"></script>
	<script src="/MySpringBootStarterApp/js/app/UserService.js"></script>
	<script src="/MySpringBootStarterApp/js/app/UserController.js"></script>
	<script src="/MySpringBootStarterApp/js/app/ProductController.js"></script>
	<script src="/MySpringBootStarterApp/js/app/ProductService.js"></script>
    <script src="/MySpringBootStarterApp/js/app/CartController.js"></script>
    <script src="/MySpringBootStarterApp/js/app/CartService.js"></script>
    <script src="/MySpringBootStarterApp/js/app/SecureController.js"></script>
    <script src="/MySpringBootStarterApp/js/app/SecureService.js"></script>
</body>
</html>