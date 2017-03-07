angular.module( 'ngBoilerplate', [
  'templates-app',
  'templates-common',
  'ngBoilerplate.customers',
  'ui.router'
])

.config( function myAppConfig ( $stateProvider, $urlRouterProvider, HateoasInterceptorProvider ) {
  $urlRouterProvider.otherwise( '/customers' );
  HateoasInterceptorProvider.transformAllResponses();
  $urlRouterProvider.rule(function($injector, $location) {

	    var path = $location.path();
	    var hasTrailingSlash = path[path.length-1] === '/';

	    if(hasTrailingSlash) {

	      //if last character is a slash, return the same url without the slash  
	      var newPath = path.substr(0, path.length - 1); 
	      return newPath; 
	    } 

	  });
})

.run(function($trace) {
  $trace.enable('TRANSITION');
})

.controller( 'AppCtrl', function AppCtrl ( $transitions, $scope, $location ) {
	$transitions.onSuccess({}, function($transitions){
		  var toState = $transitions.$to();
		    if ( angular.isDefined( toState.data.pageTitle ) ) {
		        $scope.pageTitle = toState.data.pageTitle + ' | Customer Accounts' ;
		      }
		});
})

;

