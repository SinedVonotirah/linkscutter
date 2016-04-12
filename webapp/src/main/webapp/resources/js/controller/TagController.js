'use strict';
 
App.controller('TagController', ['async', '$state', function(async, $state) {
          var self = this;
          self.links=async;
          
          self.details = function(id){
        	  $state.go("details", { id: id});
          };
}]);