var internetShop = angular.module('internet_shop', ['ngRoute', 'ngResource']).config(function ($routeProvider) {
    $routeProvider
        .when('/', {templateUrl: '/templates/index.html', controller: 'indexController'})
        .when('/admin/items', {templateUrl: '/templates/admin/items.html', controller: 'itemsController'})
        .when('/admin/items/page_:page', {templateUrl: '/templates/admin/items.html', controller: 'itemsController'})
        .when('/admin/users', {templateUrl: '/templates/admin/users.html', controller: 'usersController'})
        .when('/admin/users/page_:page', {templateUrl: '/templates/admin/users.html', controller: 'usersController'})
        .otherwise({redirectTo: '/'});
});
internetShop.config(['$routeProvider','$locationProvider', function($routeProvider, $locationProvider){
    if (window.history && window.history.pushState) { //проверка поддерживается ли бразуером html5 history API
        $locationProvider.html5Mode({
            enabled:     true,
            requireBase: false
        });
    }
}]);