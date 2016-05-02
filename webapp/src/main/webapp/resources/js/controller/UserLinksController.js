'use strict';
 
App.controller('UserLinksController', ['$scope', 'UserLinksService', '$state', '$window', function($scope, UserLinksService, $state, $window) {
      var self = this;
      self.link={id:null,url:'',description:'', tags:[]};
      self.links=[];      
      self.createdLink={id:null,url:'',description:'',tags:[]};
            
      $scope.totalItems = 0;
      $scope.currentPage = 1;
      $scope.itemsPerPage = 10;
      $scope.maxSize = 5; //Number of pager buttons to show

      $scope.setPage = function (pageNo) {
        $scope.currentPage = pageNo;
      };      
      
      $scope.pageChanged = function() {
    	  	console.log("pageChange");
    	    self.fetchAllLinks();
      };
      
      
           
      self.fetchAllLinks = function(){
    	  UserLinksService.fetchAllLinks($scope.currentPage)
              .then(
	                   function(d) {
	                	    console.log(d.count);
	                        self.links = d.links;
	                        $scope.totalItems = d.count;
	                   },
	                    function(errResponse){
	                        console.error('Error while fetching Currencies');
	                        if (errResponse.status === 404){
	                        	self.links =null;
	                        }
	                    }
	                   );
      };
      
      
      self.fetchAllLinks();
    
  	  self.createLink = function(link){
		  UserLinksService.createLink(link)
	          .then(                      
	          		  function(data){
	        	  			self.fetchAllLinks();
	        	  			self.createdLink = data;
	          		  },
	                  function(errResponse){
	                       console.error('Error while creating Link.');
		                      } 
	              );
      };
 
         self.updateLink = function(link, id){
        	 UserLinksService.updateLink(link, id)
                  .then(
                          self.fetchAllLinks, 
                          function(errResponse){
                               console.error('Error while updating Link.');
                          } 
                  );
          };
 
         self.deleteLink = function(id){
        	 UserLinksService.deleteLink(id)
                  .then(
                          self.fetchAllLinks, 
                          function(errResponse){
                               console.error('Error while deleting Link.');
                          } 
                  );
          };
 
          
 
      self.submit = function() {
    	  self.createdLink={id:null,url:'',description:'',tags:[]};
          if(self.link.id===null){
              console.log('Saving New Link', self.link);
              self.createLink(self.link);
          }else{
              self.updateLink(self.link, self.link.id);
              console.log('Link updated with id ', self.link.id);
          }
          self.reset();
          self.fetchAllLinks();
      };
       
      self.edit = function(id){
    	  self.createdLink={id:null,url:'',description:'',tags:[]};
    	  self.reset();
          console.log('id to be edited', id);
          for(var i = 0; i < self.links.length; i++){
              if(self.links[i].id === id) {
            	 self.link.id = angular.copy(self.links[i].id);
                 self.link.url = angular.copy(self.links[i].url);
                 self.link.description = angular.copy(self.links[i].linkDetails.description);
                 angular.forEach(self.links[i].linkDetails.tags, function(value, key){
                	 self.link.tags.push(value.name);
                	 console.error(value.name);
                 });
                 $window.scrollTo(0, 0);
                 break;
              }
          }
      };
           
      self.remove = function(id){
    	  self.createdLink={id:null,url:'',description:'',tags:[]};
          console.log('id to be deleted', id);
              if(self.link.id === id) {
                 self.reset();
              }
              self.deleteLink(id);
          };
 
           
          self.reset = function(){
        	  self.createdLink={id:null,url:'',description:'',tags:[]};
          self.link={id:null,url:'',description:'',tags:[]};
          $scope.linkForm.$setPristine();
      };
      
      self.details = function(id){
    	  $state.go("details", { id: id});
      };
 
}]);