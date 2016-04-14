'use strict';
 
App.controller('TagController', ['async', '$state', '$scope', function(async, $state, $scope) {
      var self = this;
      self.links=async;
      
      self.details = function(id){
    	  $state.go("details", { id: id});
      };
      
      $scope.totalItems = self.links.length;
      $scope.currentPage = 1;
      $scope.itemsPerPage = 10;
      $scope.maxSize = 5; //Number of pager buttons to show

      $scope.setPage = function (pageNo) {
        $scope.currentPage = pageNo;
      };   
}]);