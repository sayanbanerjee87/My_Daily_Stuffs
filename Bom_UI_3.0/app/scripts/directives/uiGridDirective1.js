angular.module("bomGridApp",['ui.grid', 'ui.grid.exporter', 'ui.grid.treeView']).directive("seBomUiGrid", ['uiGridTreeViewConstants','uiGridExporterConstants',function(){
	return{
        restrict : 'E',
        scope : {
        	  bomGridData : '@',
            bomConfigOption : '@',
            exportName : '@'
        },
        template : '<div class="grid" ui-grid="gridOptions" ui-grid-exporter ui-grid-tree-view></div>',
        link: function($scope){
               var bomConfigOption = angular.fromJson($scope.bomConfigOption);
               var defaultConfigOption = {
                    'enableGridMenu' : false,
                    'enableCellEditOnFocus' : false,
                    "enableSelectAll" : true,
                    "exporterMenuCsv" : true,
                    "exporterMenuPdf" : false,
                    "exporterCsvFilename" : 'BomGrid.csv',
                    "exporterCsvLinkElement" : angular.element(document.querySelectorAll(".custom-csv-link-location"))
                };
                if(bomConfigOption){
                    angular.extend(defaultConfigOption,bomConfigOption);
                    bomConfigOption = defaultConfigOption;
                }else{
                    bomConfigOption = defaultConfigOption;
                }

        	$scope.gridOptions = {
        		enableCellEditOnFocus : bomConfigOption.enableCellEditOnFocus,
                enableFiltering: bomConfigOption.enableFiltering,
                enableGridMenu : bomConfigOption.enableGridMenu,
                enableSelectAll: bomConfigOption.enableSelectAll,
                exporterMenuCsv : bomConfigOption.exporterMenuCsv,
                exporterMenuPdf : bomConfigOption.exporterMenuPdf,
                exporterCsvFilename: $scope.exportName,
                exporterCsvLinkElement: bomConfigOption.exporterCsvLinkElement ,
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
            var bomGridData = angular.fromJson($scope.bomGridData);
        		if(angular.isDefined(bomGridData)){
        			angular.extend($scope.gridOptions, {'columnDefs': bomGridData.header.columns, 'data' : bomGridData.bom});
        		}
            if(angular.isDefined(bomGridData) && bomGridData.header.isExpandRows){
              $scope.gridApi.treeBase.expandAllRows();
              //angular.element(".ui-grid-tree-base-row-header-buttons").trigger("click");
            }
        	});
        }
    }
}]);
