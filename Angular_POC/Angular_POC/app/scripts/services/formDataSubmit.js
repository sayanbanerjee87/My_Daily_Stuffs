/**
 * Created by SESA379886 on 9/13/2015.
 */
angular.module("angularPocApp").service("dataSubmit", ['$http','configurationModule', function($http,config){
    this.submitFormData = function(formData){
          var configObj = new config.returnConfigData(),
              configParam = configObj.returnConfig();
              /*$http({
                    url : configParam.url+"/api/users",
                    method : 'POST',
                    data : formData,
                    transformRequest: angular.identity
                      })*/
              $http.post(configParam.url+"/api/users",formData,
                {headers : {'Content-Type' : 'application/json'}
              }).success(function(status,data){
                      console.log(status);
                }).error(function(err){

                });
    }
}]);
