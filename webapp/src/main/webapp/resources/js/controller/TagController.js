'use strict';
 
App.controller('TagController', ['$state', '$scope', 'UserLinksService', '$stateParams', function($state, $scope, UserLinksService, $stateParams) {
      var self = this;
      self.tagId = $stateParams.tagId;
      
      
      
      self.details = function(id){
    	  $state.go("details", { id: id});
      };
      
      $scope.totalItems = 0;
      $scope.currentPage = 1;
      $scope.itemsPerPage = 10;
      $scope.maxSize = 5; //Number of pager buttons to show

      $scope.setPage = function (pageNo) {
        $scope.currentPage = pageNo;
      };      
      
      $scope.pageChanged = function() {
    	  	console.log("pageChange");
    	    self.fetchAllLinksByTag();
      };   
      
      self.fetchAllLinksByTag = function(){
    	  UserLinksService.fetchLinksByTag(self.tagId, $scope.currentPage)
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
      self.fetchAllLinksByTag();
}]);