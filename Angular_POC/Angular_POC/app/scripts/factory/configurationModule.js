/**
 * Created by SESA379886 on 9/13/2015.
 */
angular.module("angularPocApp").factory('configurationModule', [function(){
    var config = {};
    config.returnConfigData = function(){
        var configParams = {
                url : "http://localhost:3000"
        };
        this.returnConfig = function(){
            return configParams;
        }
    }

    return config;
}]);
