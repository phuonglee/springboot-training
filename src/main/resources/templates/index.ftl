<!DOCTYPE html>
 
<html lang="en" ng-app="crudApp">
    <head>
        <title>${title}</title>
        <link href="/MySpringBootStarterApp/webjars/bootstrap/css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>
 
        <div ui-view></div>
        <script src="/MySpringBootStarterApp/webjars/angularjs/angular.min.js" ></script>
        <script src="/MySpringBootStarterApp/webjars/angular-ui-router/angular-ui-router.min.js" ></script>
        <script src="https://cdn.rawgit.com/mozilla/localForage/master/dist/localforage.js"></script>
        <script src="https://rawgithub.com/gsklee/ngStorage/master/ngStorage.js"></script>
        <script src="/MySpringBootStarterApp/js/app/app.js"></script>
        <script src="/MySpringBootStarterApp/js/app/UserService.js"></script>
        <script src="/MySpringBootStarterApp/js/app/UserController.js"></script>
    </body>
</html>