'use strict';

App.controller('AuthController', ['$scope', '$location', '$http', function($scope, $location, $http) {
          
      $scope.SendData = function () {
           var data = $.param({
               username: $scope.user.username,
               password: $scope.user.password
           });
       
           var config = {
               headers : {
                   'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
               }
           }

           $http.post('http://localhost:8080/webapp/login', data, config)
           .then(
                  function(d) {
                	  localStorage.authenticated = true;
                	  $location.path('/links');
                  },
                  function(errResponse){
                       alert('Authorisation Error');
                  }
           );
     };
}]);