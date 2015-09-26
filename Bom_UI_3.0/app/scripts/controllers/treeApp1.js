var module = angular.module('app', ['bomGridApp','addCostToBom']);

module.controller('gridController', ['$scope','$http', function($scope,$http) {
	//$scope.gridData = newJson;
  /*$http.get("http://localhost:8080/bom-service/api/v1/import?UiGridReq=true").success(function(data){
      console.log(data);
  }).error(function(err){
      console.log("Error Message : : "+err);
  });*/
  var textContent = angular.element(jsonContainer);
      textContent.val(JSON.stringify(newJson));
  var elem = angular.element(jsonContainer).val();
   //jsonData = JSON.parse(elem);
    $scope.gridData = elem;
    $scope.showTextContainer = false;
    if(textContent.val()){
      $scope.showTextContainer = true;
    }
  $scope.bomConfigOption = {
    "uiGridReq" : true,
    "enableCellEditOnFocus" : true,
    "enableFiltering" : true,
    "enableGridMenu" : true,
    "enableSelectAll" : true,
    "exporterMenuCsv" : true,
    "exporterMenuPdf" : false,
    "exporterCsvFilename" : 'BomGrid.csv',
  };

  $scope.submitJson = function(){
        var elem = angular.element(jsonContainer).val(),
        jsonData = JSON.parse(elem);
        $scope.gridData = jsonData;
       $scope.exportName = "SampleCSV";
  }

  $scope.addToProduct = function(){
    var gridElem = {},
        formElem = event.target,
        inputElem = event.target.getElementsByTagName("input"),
        length = inputElem.length,
        columns = $scope.gridData.header.columns,
        headerLength = $scope.gridData.header.columns.length,
        count,
        loop;
        for(count = 0;count < length; count++){
          for(loop = 0; loop < headerLength; loop++){
             gridElem[inputElem[count].getAttribute("data-id")] = inputElem[count].value ?  inputElem[count].value : "";
          }
        }
        $scope.gridData.bom.push(gridElem);
  }
}]);
