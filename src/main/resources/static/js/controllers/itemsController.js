/**
 * Контроллер продуктов. Запускается при урл #!/products
 */
internetShop.controller('itemsController', function ($scope, $http, $routeParams) {
    const contextPath = '/api/v1/items';

    // Шаблон полей. Чтобы не писать несколько раз одно и то же
    const newItemTemplate = {
        id: null,
        name: null,
        price: null
    };

    $scope.currentDate = new Date().getFullYear();

    $scope.ItemsList = [];

    $scope.totalItems = 0;

    $scope.totalPages = 0;

    $scope.viewItems = 0;

    $scope.page = $routeParams.page ? parseInt($routeParams.page, 10) : 1;

    $scope.pages = [];

    $scope.search = [];

    $scope.newItem = JSON.parse(JSON.stringify(newItemTemplate));

    $scope.fillItemTable = function() {
        const params = {
            page: $scope.page
        };

        $http({
            url: contextPath,
            method: 'GET',
            params
        }).then(function (response) {
            $scope.ItemsList = response.data.content;
            $scope.totalPages = response.data.totalPages;
            $scope.totalItems = response.data.totalElements;
            $scope.viewItems = response.data.numberOfElements;
        }).catch((e) => {
            console.log(e);
        });
    };

    $scope.clearItem = function() {
        $scope.newItem = JSON.parse(JSON.stringify(newItemTemplate));
    };

    $scope.paginate = function(page) {
        $scope.page = page;
        $scope.fillItemTable();
    }

    $scope.deleteItem = function(item) {
        if (confirm(`Удалить продукт '${item.name}' с ценой '${item.price}'?`)) {
            $http.delete(`${contextPath}/${item.id}`)
                .then(function (response) {
                    console.log(response.data)
                    alert("Продукт успешно удален!")
                    $scope.fillItemTable();
                });
        }
    };

    $scope.saveItem = function(item) {
        if (confirm(`Сохранить продукт '${item.name}' с ценой '${item.price}' рублей?`)) {
            $http.post(`${contextPath}`, item)
                .then(function (response) {
                    console.log(response.data);
                    alert("Продукт успешно добавлен!")
                    $scope.clearItem();
                    $scope.fillItemTable();
                });
        }

    };

    $scope.updateItem = function(item) {
        if (confirm(`Изменить продукт '${item.name}' с ценой '${item.price}' рублей?`)) {
            $http.put(`${contextPath}/${item.id}`, item)
                .then(function (response) {
                    console.log(response.data);
                    alert("Продукт успешно отредактирован!")
                    item.edit = false;
                });
        }
    };


    $scope.fillItemTable();
});