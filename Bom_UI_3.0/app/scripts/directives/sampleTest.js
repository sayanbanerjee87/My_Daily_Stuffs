var app = angular.module("testApp", []);
app.directive("saTestSample", [function(){
	  return{
      restrict : "E",
      templateUrl : "testExample.html"
    };
}])
