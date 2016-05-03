'use strict';
 
App.controller('UserLinksController', ['$scope', 'UserLinksService', '$state', '$window', function($scope, UserLinksService, $state, $window) {
      var self = this;
      
      $scope.tagsStringArr = [];
      self.link ={id:null};
      self.linkDetails={description:'', tags:[]};
      self.links=[];            
      self.createdLink = null;
      
      $scope.$watch('tagsStringArr', function() {
          self.tagsObjArr = $scope.tagsStringArr.map(function(s){
            return {
              name: s,
              toString: function() { return s }
            }
          });
        })     
            
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
 
     self.updateLink = function(link){
    	 UserLinksService.updateLink(link)
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
    	  self.createdLink=null;
          if(self.link.id===null){
        	  self.linkDetails.tags = self.tagsObjArr;
        	  self.link.linkDetails = self.linkDetails;
              self.createLink(self.link);
          }else{
        	  self.linkDetails.tags = self.tagsObjArr;
        	  self.link.linkDetails = self.linkDetails;
              self.updateLink(self.link);
          }
          self.reset();
          self.fetchAllLinks();
      };
       
      self.edit = function(id){
    	  self.createdLink=null;
    	  self.reset();
          for(var i = 0; i < self.links.length; i++){
              if(self.links[i].id === id) {
            	 self.link.id = angular.copy(self.links[i].id);
                 self.link.url = angular.copy(self.links[i].url);
                 self.linkDetails.description = angular.copy(self.links[i].linkDetails.description);
                 angular.forEach(self.links[i].linkDetails.tags, function(value, key){
                	 $scope.tagsStringArr.push(value.name);
                 });
                 $window.scrollTo(0, 0);
                 break;
              }
          }
      };
           
      self.remove = function(id){
    	  self.createdLink=null;
              if(self.link.id === id) {
                 self.reset();
              }
              self.deleteLink(id);
          };
 
           
          self.reset = function(){
        	  self.createdLink=null;
	          self.link={id:null};
	          self.linkDetails={description:'', tags:[]};
	          $scope.tagsStringArr = [];
	          $scope.linkForm.$setPristine();
          };
      
      self.details = function(id){
    	  $state.go("details", { id: id});
      };
 
}]);