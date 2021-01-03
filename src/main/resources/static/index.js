angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/shop';
    $scope.currentDate = (new Date()).getFullYear();

    $scope.newProduct = {
        id: null,
        name: null,
        price: null
    }

    $scope.fillTable = function() {
        $http({
            url: contextPath,
            method: 'GET',
            // params: {
            //     min_price: $scope.filter ? $scope.filter.min_price : null,
            //     max_price: $scope.filter ? $scope.filter.max_price : null
            // }
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    };

    $scope.clearProduct = function() {
        $scope.newProduct = {
            id: null,
            name: null,
            price: null
        }
    }

    $scope.deleteProduct = function(product) {
        if (confirm(`Удалить продукт ${product.name}?`)) {
            $http.delete(`${contextPath}/delete/${product.id}`)
                .then(function (response) {
                    console.log(response.data)
                    alert("Продукт успешно удален!")
                    $scope.fillTable();
                });
        }
    };

    $scope.saveProduct = function(product) {
        $http.post(contextPath + '/add', product)
            .then(function (response) {
                console.log(response.data);
                $scope.clearProduct();
                $scope.fillTable();
            });
    };

    $scope.updateProduct = function(product) {
        $http.put(`${contextPath}/edit/${product.id}`, product)
            .then(function (response) {
                console.log(response.data);
                product.edit = false;
            });
    };


    $scope.fillTable();
});