'use strict';
 
App.factory('UserLinksService', ['$http', '$q', function($http, $q){
 
    return {
         
        fetchAllLinks: function() {
            return $http.get('http://localhost:8080/webapp/links/')
                .then(
                        function(response){
                            return response.data;
                        }, 
                        function(errResponse){
                            console.error('Error while fetching links');
                            return $q.reject(errResponse);
                        }
                );
        },
            
        fetchLinkById: function(id) {
            return $http.get('http://localhost:8080/webapp/link/'+id)
                .then(
                        function(response){
                            return response.data;
                        }, 
                        function(errResponse){
                            console.error('Error while fetching link by id');
                            return $q.reject(errResponse);
                        }
                );
        },
             
        createLink: function(link){
            return $http.post('http://localhost:8080/webapp/links/', link)
                .then(
                        function(response){
                            return response.data;
                        }, 
                        function(errResponse){
                            console.error('Error while creating link');
                            return $q.reject(errResponse);
                        }
                );
        },
         
        updateLink: function(link){
            return $http.put('http://localhost:8080/webapp/links/', link)
                .then(
                        function(response){
                            return response.data;
                        }, 
                        function(errResponse){
                            console.error('Error while updating link');
                            return $q.reject(errResponse);
                        }
                );
        },
         
        deleteLink: function(id){
            return $http.delete('http://localhost:8080/webapp/links/'+id)
                .then(
                        function(response){
                            return response.data;
                        }, 
                        function(errResponse){
                            console.error('Error while deleting link');
                            return $q.reject(errResponse);
                        }
                );
        },
        
        fetchLinksByTag: function(id){
        	return $http.get('http://localhost:8080/webapp/tag/'+id)
        		.then(
        				function(response){
        					return response.data;
        				},
        				function(errResponse){
        					console.error('Error while fetch links by tag');
                            return $q.reject(errResponse);            					
        				}  
        		);
        }
         
    };
 
}]);