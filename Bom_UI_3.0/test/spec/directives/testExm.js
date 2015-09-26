/**
 * Created by SESA379886 on 6/17/2015.
 */


describe("Sample test case for external template url", function(){
   var $scope,
   element,
   $rootScope,
   $body =  $('body');
  beforeEach(module('testApp', 'views/testExample.html'))

  describe("testing The Directive", function(){
    beforeEach(inject(function($compile, $rootScope){
      //$rootScope = $rootScope;
      $scope = $rootScope.$new();
      element = angular.element('<sa-test-sample></sa-test-sample>');
      element = $compile(element)($rootScope);
    }));

    it("Test Dom Element's Class", function(){
      $scope.$digest();
    })
  })
})
