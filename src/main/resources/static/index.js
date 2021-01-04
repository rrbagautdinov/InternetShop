angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = '/shop';
    $scope.currentDate = new Date().getFullYear();

    $scope.ProductsList = [];

    $scope.totalProducts = 0;

    $scope.totalPages = 0;

    $scope.viewProducts = 0;

    $scope.page = 0;

    $scope.pages = [];

    $scope.search = [];

    $scope.newProduct = {
        id: null,
        name: null,
        price: null
    };

    $scope.pageRange = function() {
        const range = [];
        for (let i = 0; i < $scope.totalPages; i++) {
            range.push(i);
        }
        return range;
    };

    $scope.fillTable = function() {
        const params = {
            page: $scope.page
        };

        $http({
            url: contextPath,
            method: 'GET',
            params
        }).then(function (response) {
            $scope.ProductsList = response.data.content;
            $scope.totalPages = response.data.totalPages;
            $scope.totalProducts = response.data.totalElements;
            $scope.viewProducts = response.data.numberOfElements;
        }).catch((e) => {
            console.log(e);
        });
    };

    $scope.clearProduct = function() {
        $scope.newProduct = {
            id: null,
            name: null,
            price: null
        };
    };

    $scope.paginate = function(page) {
        $scope.page = page;
        $scope.fillTable();
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
                alert("Продукт успешно добавлен!")
                $scope.clearProduct();
                $scope.fillTable();
            });
    };

    $scope.updateProduct = function(product) {
        $http.put(`${contextPath}/edit/${product.id}`, product)
            .then(function (response) {
                console.log(response.data);
                alert("Продукт успешно отредактирован!")
                product.edit = false;
            });
    };


    $scope.fillTable();
});