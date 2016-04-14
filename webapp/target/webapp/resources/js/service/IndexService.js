'use strict';

App.factory('IndexService', ['$http', '$q', function($http, $q){
 
	return {
		
		searchLink: function(link){
			return $http.post('http://localhost:8080/webapp/search/', link)
				.then(
						function(response){
							return response.data;
						}, 
	                    function(errResponse){
	                        return $q.reject(errResponse);
	                    }
					);
            },
            
        logout: function(){
        	return $http.post('http://localhost:8080/webapp/logout')
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