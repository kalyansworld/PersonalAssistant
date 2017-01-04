app.controller('usersController', function($scope) {
    $scope.headingTitle = "User List";
});

app.controller('rolesController', function($scope) {
    $scope.headingTitle = "Roles List";
});

app.controller('notificationController', function($scope, $http) {

    $http.get('http://localhost:8080/getNotifications?userName='+1293).
        then(function(response) {
            $scope.notificationList = response.data;
            $scope.notificationCount = response.data.length;
        });
});