'use strict';
var App = angular.module('linksCutter',['ngResource','ui.router', 'ui.bootstrap']);

App.config(['$stateProvider', '$urlRouterProvider',  function($stateProvider, $urlRouterProvider){
	

	
	$urlRouterProvider.otherwise("/login")
    
	$stateProvider
	.state('login', {
		url: "/login",
		templateUrl: 'login',
		controller: "AuthController as ctrl"
        
	})
    
	.state('registration', {
        url: "/registration",
        templateUrl: 'registration',
        controller: "UserController as ctrl"
    })
    
    .state('links', {
        url: "/links",
        templateUrl: 'links',
        controller: "UserLinksController as ctrl"
    })
    
    .state('details',{
    	url: '/link/:id',
    	templateUrl: 'details',
    	controller: "LinkDetailsController as ctrl"
    })
    
    .state('details.linksbytag', {
	    url: '/{tagId:[0-9]{1,9}}',
	    templateUrl: function(params){ return 'link/2/'+params.tagId; },
	    controller : "TagController as ctrl"
    })



}])
	.factory('authHttpResponseInterceptor',['$q','$location',function($q,$location){
		return {
			response: function(response){
				if (response.status === 401) {
					console.log("Response 401");
	            }
	            return response || $q.when(response);
	        },
	        responseError: function(rejection) {
	            if (rejection.status === 401) {
	                console.log("Response Error 401",rejection);
	                $location.path('/login').search('returnTo', $location.path());
	            }
	            return $q.reject(rejection);
	        }
	    }
	}])
	
	.config(['$httpProvider',function($httpProvider) {
		$httpProvider.interceptors.push('authHttpResponseInterceptor');
	}]);