var internetShop = angular.module('internet_shop', ['ngRoute', 'ngResource']).config(function ($routeProvider) {
    $routeProvider
        .when('/', {templateUrl: '/templates/index.html', controller: 'indexController'})
        .when('/items', {templateUrl: '/templates/items.html', controller: 'itemsController'})
        .when('/users', {templateUrl: '/templates/users.html', controller: 'usersController'})
        .otherwise({redirectTo: '/'});
});
