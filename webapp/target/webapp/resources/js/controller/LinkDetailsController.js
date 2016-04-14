'use strict';
 
App.controller('LinkDetailsController', ['$scope', 'UserLinksService', '$stateParams', function($scope, UserLinksService, $stateParams) {
      var self = this;
      self.link={id:null,url:'',description:'',tags:''};
      self.links=[];
      self.linkid = $stateParams.id; 
      $scope.linkid = self.linkid;
      
      self.fetchLinkById = function(id){
    	  UserLinksService.fetchLinkById(id)
    	  		.then(
                       function(d) {
                            self.link = d;
                       },
                       function(errResponse){
                            console.error('Error while fetching Currencies');
                       }       	  		
    	  		);    	  
      };      
      self.fetchLinkById(self.linkid);
}]);