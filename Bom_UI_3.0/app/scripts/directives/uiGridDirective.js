angular.module("bomGridApp",['ui.grid', 'ui.grid.exporter', 'ui.grid.treeView']).directive("seBomUiGrid", ['uiGridTreeViewConstants','uiGridExporterConstants',function(){
	return{
        restrict : 'E',
        scope : {
        	  bomGridData : '=',
            bomConfigOption : '=',
            exportName : '='
        },
        template : '<div class="grid" ui-grid="gridOptions" ui-grid-exporter ui-grid-tree-view></div>',
        link: function($scope){
               var defaultConfigOption = {
                    'enableGridMenu' : false,
                    'enableCellEditOnFocus' : false,
                    "enableSelectAll" : true,
                    "exporterMenuCsv" : true,
                    "exporterMenuPdf" : false,
                    "exporterCsvFilename" : 'BomGrid.csv',
                    "exporterCsvLinkElement" : angular.element(document.querySelectorAll(".custom-csv-link-location"))
                };
                if($scope.bomConfigOption){
                    angular.extend(defaultConfigOption,$scope.bomConfigOption);
                    $scope.bomConfigOption = defaultConfigOption;
                }else{
                    $scope.bomConfigOption = defaultConfigOption;
                }

        	$scope.gridOptions = {
        		enableCellEditOnFocus : $scope.bomConfigOption.enableCellEditOnFocus,
                enableFiltering: $scope.bomConfigOption.enableFiltering,
                enableGridMenu : $scope.bomConfigOption.enableGridMenu,
                enableSelectAll: $scope.bomConfigOption.enableSelectAll,
                exporterMenuCsv : $scope.bomConfigOption.exporterMenuCsv,
                exporterMenuPdf : $scope.bomConfigOption.exporterMenuPdf,
                exporterCsvFilename: $scope.exportName,
                exporterCsvLinkElement: $scope.bomConfigOption.exporterCsvLinkElement ,
                onRegisterApi: function(gridApi) {
                    $scope.gridApi = gridApi;
                }
            };
             $scope.$watch('exportName', function(){
                if ($scope.exportName) {
                  $scope.gridOptions.exporterCsvFilename = $scope.exportName.indexOf(".csv") !== -1 ? $scope.exportName : $scope.exportName + '.csv';

                }
            });
        	$scope.$watch('bomGridData', function(){
        		if(angular.isDefined($scope.bomGridData)){
        			angular.extend($scope.gridOptions, {'columnDefs': $scope.bomGridData.header.columns, 'data' : $scope.bomGridData.bom});
        		}
            if(angular.isDefined($scope.bomGridData) && $scope.bomGridData.header.isExpandRows){
              $scope.gridApi.treeBase.expandAllRows();
              //angular.element(".ui-grid-tree-base-row-header-buttons").trigger("click");
            }
        	});
        }
    }
}]);
