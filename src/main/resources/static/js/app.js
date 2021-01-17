var internetShop = angular.module('internet_shop', ['ngRoute', 'ngResource']).config(function ($routeProvider) {
    $routeProvider
        .when('/', {templateUrl: '/templates/index.html', controller: 'indexController'})
        .when('/products', {templateUrl: '/templates/products.html', controller: 'productsController'})
        .when('/users', {templateUrl: '/templates/users.html', controller: 'usersController'})
        .otherwise({redirectTo: '/'});
});
