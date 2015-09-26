/**
 * Created by SESA379886 on 6/19/2015.
 */

angular.module("addCostToBom",[]).directive("seAddLaborCost", function(){
  return{
    restrict  : 'A',
    scope : {
      triggerAddEvent : '&',
    },
    templateUrl : 'addLaborCost.html',

  }
})
