/**
 * Контроллер продуктов. Запускается при урл #!/products
 */
internetShop.controller('productsController', function ($scope, $http) {
    const contextPath = '/products';
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

    $scope.fillProductTable = function() {
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
        $scope.fillProductTable();
    }

    $scope.deleteProduct = function(product) {
        if (confirm(`Удалить продукт '${product.name}' с ценой '${product.price}'?`)) {
            $http.delete(`${contextPath}/delete/${product.id}`)
                .then(function (response) {
                    console.log(response.data)
                    alert("Продукт успешно удален!")
                    $scope.fillProductTable();
                });
        }
    };

    $scope.saveProduct = function(product) {
        if (confirm(`Сохранить продукт '${product.name}' с ценой '${product.price}' рублей?`)) {
            $http.post(contextPath + '/add', product)
                .then(function (response) {
                    console.log(response.data);
                    alert("Продукт успешно добавлен!")
                    $scope.clearProduct();
                    $scope.fillProductTable();
                });
        }

    };

    $scope.updateProduct = function(product) {
        if (confirm(`Изменить продукт '${product.name}' с ценой '${product.price}' рублей?`)) {
            $http.put(`${contextPath}/edit/${product.id}`, product)
                .then(function (response) {
                    console.log(response.data);
                    alert("Продукт успешно отредактирован!")
                    product.edit = false;
                });
        }
    };


    $scope.fillProductTable();
});