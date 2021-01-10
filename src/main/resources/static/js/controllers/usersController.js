/**
 * Контроллер пользователей. Запускается при урл #!/users
 */
internetShop.controller('usersController', function ($scope, $http) {
    const contextPath = '/users';
    $scope.currentDate = new Date().getFullYear();

    $scope.UsersList = [];

    $scope.totalUsers = 0;

    $scope.totalPages = 0;

    $scope.viewUsers = 0;

    $scope.page = 0;

    $scope.pages = [];

    $scope.search = [];

    $scope.newUser = {
        id: null,
        login: null,
        password: null,
        name: null,
        surname: null,
        email: null
    };

    $scope.pageRange = function() {
        const range = [];
        for (let i = 0; i < $scope.totalPages; i++) {
            range.push(i);
        }
        return range;
    };

    $scope.fillUserTable = function() {
        const params = {
            page: $scope.page
        };

        $http({
            url: contextPath,
            method: 'GET',
            params
        }).then(function (response) {
            $scope.UsersList = response.data.content;
            $scope.totalPages = response.data.totalPages;
            $scope.totalUsers = response.data.totalElements;
            $scope.viewUsers = response.data.numberOfElements;
        }).catch((e) => {
            console.log(e);
        });
    };

    $scope.clearUser = function() {
        $scope.newProduct = {
            id: null,
            login: null,
            password: null,
            name: null,
            surname: null,
            email: null
        };
    };

    $scope.paginate = function(page) {
        $scope.page = page;
        $scope.fillUserTable();
    }

    $scope.deleteUser = function(user) {
        if (confirm(`Удалить пользователя '${user.name}' с ценой '${user.price}'?`)) {
            $http.delete(`${contextPath}/delete/${user.id}`)
                .then(function (response) {
                    console.log(response.data)
                    alert("Пользователь успешно удален!")
                    $scope.fillUserTable();
                });
        }
    };

    $scope.saveUser = function(user) {
        if (confirm(`Сохранить пользователя '${user.name}'?`)) {
            $http.post(contextPath + '/add', user)
                .then(function (response) {
                    console.log(response.data);
                    alert("Пользователь успешно добавлен!")
                    $scope.clearUser();
                    $scope.fillUserTable();
                });
        }

    };

    $scope.updateUser = function(user) {
        if (confirm(`Изменить пользователя '${user.name}'?`)) {
            $http.put(`${contextPath}/edit/${user.id}`, user)
                .then(function (response) {
                    console.log(response.data);
                    alert("Пользователь успешно отредактирован!")
                    user.edit = false;
                });
        }
    };


    $scope.fillUserTable();
});