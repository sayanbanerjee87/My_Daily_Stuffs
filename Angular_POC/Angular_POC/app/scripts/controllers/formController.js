/**
 * Created by SESA379886 on 9/12/2015.
 */

angular.module("angularPocApp").controller("formController", ['$scope',  'dataSubmit', function($scope,dataSubmit){
  $scope.submitData = function(){
      var inputs = angular.element("form[name = 'registrationForm'] input"),
          formData = {};
      angular.forEach(inputs , function(val , key){
         /* formData.push(angular.element(val).data("model"), angular.element(val).val());*/
        formData[angular.element(val).data("model")] = angular.element(val).val();
      });
      dataSubmit.submitFormData(formData);
  };
  $scope.resetData = function(){

  };
}]);
