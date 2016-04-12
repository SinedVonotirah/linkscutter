'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){
 
    return {
             
            createUser: function(user){
                return $http.post('http://localhost:8080/webapp/registration', user)
	                .then(
	                    function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        return $q.reject(errResponse);
	                    }
                    );
            }
         
    };
 
}]);