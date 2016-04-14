'use strict';

App.controller('UserController', ['$scope', 'UserService', '$location', function($scope, UserService, $location) {
      var self = this;
      $scope.user={id:null,login:'',mail:'',password:''};
        
      $scope.createUser = function(){
          UserService.createUser($scope.user)
              .then(
                      function(d) {
                    	  $location.path('/login');
                      },
                       function(errResponse){
                    	  alert('Login or Email already exists');
                       }
              );
      };
}]);