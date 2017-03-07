angular.module('ngBoilerplate.customers', ['ui.router', 'ngResource', 'hateoas'])
.config(function($stateProvider) {
	$stateProvider.state('customers', {
		url:'/customers',
		views: {
			'main':{
				templateUrl:"customers/customers.tpl.html",
				controller:"CustomerController"
			},
		},
		resolve: {
			customers: function(customerService) {
				return customerService.getAllCustomers();
			}
		},
		data : { pageTitle : "Customer Database" }		
	})
	.state('create', {
		url:'/customers/create',
		views: {
			'main':{
				templateUrl:"customers/create.tpl.html",
				controller:"CreateController"
			}
		},
		data : { pageTitle : "New Customer Form" }		
	})
	.state('update', {
		url:'/customers/update/:customerId',
		views: {
			'main':{
				templateUrl:"customers/update.tpl.html",
				controller:"UpdateController"
			}
		},
		resolve: {
	         customer:  function(customerService, $http, $stateParams){
	        	 return customerService.getCustomer($stateParams.customerId);
	          },
		},
		data : { pageTitle : "Customer Update Form" }		
	});
})
.factory('customerService', function($http, $resource) {
      var service = {};
      service.createCustomer = function(customer, success, failure) {
        var Customer = $resource("/rest/customer/");
        Customer.save({}, customer, success, failure);
      };
      service.updateCustomer = function(link, customer, success, failure) {
    	  customer.links = null;
    	  $http.put(link, customer).then(success, failure);
        };
      service.getAllCustomers = function() {
        var Customers = $resource("/rest/customer/");
        return Customers.get().$promise.then(function(data) {
            return data.customers;
        });
      };
      service.getCustomer = function(customerId){
    	  var Customer = $resource("/rest/customer/" + customerId);
    	  return Customer.get().$promise.then(function(data){
    		  return data;
    	  }, function(error){
    		  alert(error.status);
    	  });
      };
      service.deleteCustomer = function(customer, success, failure) {
    	  $http.delete(customer.links.self).then(success, failure);
      };
      return service;
})
.controller('CreateController', function($scope, $state, customerService) {
	$scope.create = function() {
		customerService.createCustomer($scope.customer,
		function(){
			$state.go("customers");
		},
		function(error) {
			if (error.status == 409){
					alert("409 - CONFLICT\nError creating customer. \nA customer with the provided email or telephone number already exists.");
				}
			else{
					alert(error.status + " - Error creating customer");
			}
		});		
	};
})
.controller('UpdateController', function($scope, $state, customer, customerService) {
	$scope.link = customer.links.self;
	$scope.customer = customer;
	$scope.update = function() {
		customerService.updateCustomer($scope.link, $scope.customer,
		function(){
			$state.go("customers");
		},
		function(error) {
			if (error.status == 404){
				alert("404 - NOT FOUND\nError editing customer. \nCustomer does not exist.");
			}
			else if (error.status == 409){
				alert("409 - CONFLICT\nError editing customer. \nA customer with the provided email or telephone number already exists.");
			}
			else{
					alert(error.status + " - Error editing customer");
			}
		});		
	};
})
.controller('CustomerController', function($state, $scope, customerService, customers) {
	$scope.customers = customers;
	$scope.update = function(customer){
		var url = customer.links.self;
		var id = url.split('/').pop();
		$state.go("update", {customerId:id});
	};
	$scope.remove = function(index, customer) {
		var result = confirm("Are you sure you want to delete " + customer.firstName + " " + customer.lastName + "?");
		if (result) {
			customerService.deleteCustomer(customer,
					function(){
						$scope.customers.splice(index, 1);
					},
					function(error) {
						if (error.status == 404){
							alert("404 - NOT FOUND.\nError deleteing customer. \nThe customer does not exist.");
						}
						else{
							alert(error.status + " - Error deleting user");
						}
					});		
		}
	};
})		
;