/**
 * Created by SESA379886 on 9/8/2015.
 */
angular.module('angularPocApp').directive('saUserRegistration',[function(){
    return{
        restrict: "EA",
        scope : {
              submitForm : '&',
              resetForm :  '&'
        },
        templateUrl : "views/UserRegistration.html",
        link: function(scope, element, attrs){

        }
    }
}]);
