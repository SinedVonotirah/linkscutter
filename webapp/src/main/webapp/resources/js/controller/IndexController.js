'use strict';

App.controller('IndexController', ['IndexService', '$state', function(IndexService, $state) {
          var self = this;
          self.link={searchUrl:''};
          
            
          self.searchLink = function(){
        	  IndexService.searchLink(self.link.searchUrl)
	              .then(
	                      function(d) {
	                          $state.go("details", { id: d.id});
	                         
	                      },
	                       function(errResponse){
	                    	  if(errResponse.status == 400) alert('Link not found');
	                       }
	              );
          },
          
          self.logout = function(){
        	  IndexService.logout()
              .then(
                      function(d) {
                    	  localStorage.removeItem("authenticated");
                    	    $state.go("login");
                         
                      },
                       function(errResponse){
                    	  localStorage.$reset();
                       }
              );
        	  
          },
          
          self.home = function(){
        	  if (null !== localStorage.getItem("authenticated")){
        		  $state.go("links");
        	  } else {
        		  $state.go("login");
        	  }       	  
        	  
          }
          
          self.shouldShowLoginOverlay = function () {
        	  self.isAuthenticated  = (null !== localStorage.getItem("authenticated"));
              return self.isAuthenticated;
            }

}]);