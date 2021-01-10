/**
 * Главный контроллер. Запускается всегда
 */
internetShop.controller('mainController', function ($scope) {
    // Такой себе костыль, чтобы записать текущее имя контроллера в переменную. Сделал для того чтобы определить текущую
    // страницу и сделать активной ссылку на эту страницу в главном меню
    $scope.controller = null;
    $scope.$on("$routeChangeSuccess", function(event, current){
        $scope.controller = current.$$route.controller;
    });
});
