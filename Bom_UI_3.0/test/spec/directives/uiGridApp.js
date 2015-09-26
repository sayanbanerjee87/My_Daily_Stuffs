/**
 * Created by SESA379886 on 6/15/2015.
 */

describe("Test UI Grid App", function(){
  var element,scope, compile;
  beforeEach(module("bomGridApp"))
  describe("BOM Grid Directive Test Configuration for se-bom-ui-grid directive", function(){
    beforeEach(inject(function($compile,$rootScope){
      scope = $rootScope;
      compile = $compile;
      element = angular.element('<se-bom-ui-grid bom-grid-data="gridData" bom-config-option="bomConfigOption" export-name="exportName"></se-bom-ui-grid>');
      element = $compile(element)($rootScope);
    }))
    it("Test Bom Config Option", function(){
      scope.bomConfigOption = {
        'enableGridMenu' : false,
        'enableCellEditOnFocus' : false,
        "enableSelectAll" : true,
        "exporterMenuCsv" : true,
        "exporterMenuPdf" : false,
        "exporterCsvFilename" : 'BomGrid.csv',
        "exporterCsvLinkElement" : angular.element(document.querySelectorAll(".custom-csv-link-location"))
      };
      scope.$digest();
      expect(angular.isUndefined(scope.bomConfigOption)).toBe(false);
    });

    it("Test BOM default Config Option Available", function(){
      scope.$digest();
      expect(angular.isUndefined(scope.bomConfigOption)).toBe(false);
    });
    it("Checks Grid options availability", function(){
      scope.bomConfigOption = {
        'enableGridMenu' : false,
        'enableCellEditOnFocus' : false,
        "enableSelectAll" : true,
        "exporterMenuCsv" : true,
        "exporterMenuPdf" : false,
        "exporterCsvFilename" : 'BomGrid.csv',
        "exporterCsvLinkElement" : angular.element(document.querySelectorAll(".custom-csv-link-location"))
      };

      scope.gridOptions = {
        enableCellEditOnFocus : scope.bomConfigOption.enableCellEditOnFocus,
        enableFiltering: scope.bomConfigOption.enableFiltering,
        enableGridMenu : scope.bomConfigOption.enableGridMenu,
        enableSelectAll: scope.bomConfigOption.enableSelectAll,
        exporterMenuCsv : scope.bomConfigOption.exporterMenuCsv,
        exporterMenuPdf : scope.bomConfigOption.exporterMenuPdf,
        exporterCsvFilename: scope.exportName,
        exporterCsvLinkElement: scope.bomConfigOption.exporterCsvLinkElement ,
        onRegisterApi: function(gridApi) {
          scope.gridApi = gridApi;
        }
      };

      scope.$digest();
      expect(angular.isUndefined(scope.gridOptions)).toBe(false);
    })

    it("Checks Default Grid Options Present", function(){
      scope.gridOptions = {};
      scope.$digest();
      expect(element.isolateScope().gridOptions).not.toBe({});
    })

    it("Checks Default Grid Options Present", function(){
      scope.gridOptions = {};
      scope.$digest();
      expect(scope.gridOptions).toEqual({});
    })

    it("Check Export For Dynamic File name", function(){
      scope.gridOptions = {
        exporterCsvFilename: "San.csv"
      };
      scope.$digest();
      expect(scope.gridOptions.exporterCsvFilename).toBe("San.csv");
    })

    it("check for export file name as .csv extension is added or not", function(){
      scope.exportName = "ABC";
      scope.$digest();
      expect(element.isolateScope().gridOptions.exporterCsvFilename).toBe("ABC.csv");
    });

    it("Takes default .csv extension if provided", function(){
      scope.exportName = "ABC.csv";
      scope.$digest();
      expect(element.isolateScope().gridOptions.exporterCsvFilename).toBe("ABC.csv");
    });

    it("Render Grid To verify all scenarios", function(){
      scope.gridOptions = {
      };
      scope.$digest();
      //expect((scope.$digest())).toThrow(TypeError("Cannot set property"));
    })


    describe("Bom Grid data and Bom config option", function(){
      it("check configured bomConfigOption", function(){
        scope.bomConfigOption = {
          "exporterMenuCsv" : false
        };
        scope.$digest();
        expect(element.isolateScope().bomConfigOption.exporterMenuCsv).toBe(false);
      })

      it("Takes default properties if not configured", function(){
        scope.bomConfigOption = {
          'enableGridMenu' : false,
          'enableCellEditOnFocus' : false,
          "enableSelectAll" : true,
          "exporterMenuCsv" : true,
          "exporterMenuPdf" : false,
          "exporterCsvLinkElement" : angular.element(document.querySelectorAll(".custom-csv-link-location"))
        };
        angular.extend(element.isolateScope().bomConfigOption,scope.bomConfigOption);
        expect(element.isolateScope().bomConfigOption.exporterCsvFilename).toBe('BomGrid.csv');
      })

      it("Takes Configured Property if Defined", function(){
        scope.bomConfigOption = {
          exporterCsvFilename : "MyFile.csv"
        };
        angular.extend(element.isolateScope().bomConfigOption,scope.bomConfigOption);
        expect(element.isolateScope().bomConfigOption.exporterCsvFilename).toBe('MyFile.csv');
      })

      it("Takes Configurable ExporterMenuCsv",function(){
        scope.bomConfigOption = {
          exporterMenuCsv : false
        };
        angular.extend(element.isolateScope().bomConfigOption,scope.bomConfigOption);
        expect(element.isolateScope().bomConfigOption.exporterMenuCsv).toBe(false);
      })

      it("Initially BOM Grid Data will be empty",function(){
        scope.$digest();
        expect(element.isolateScope().gridOptions.data.length).toEqual(0);
      })

      it("Initially Column Definition will be empty",function(){
        scope.$digest();
        expect(element.isolateScope().gridOptions.columnDefs.length).toEqual(0);
      })

      it("Check grid with modified bomConfig Option", function(){
        scope.bomConfigOption = {
          "uiGridReq" : true,
          "enableCellEditOnFocus" : true,
          "enableFiltering" : true,
          "enableGridMenu" : true,
          "enableSelectAll" : true,
          "exporterMenuCsv" : true,
          "exporterMenuPdf" : false,
          "exporterCsvFilename" : 'Sayan.csv'
        };
        var tempOption = element.isolateScope().bomConfigOption;
        element = angular.element('<se-bom-ui-grid bom-grid-data="gridData" bom-config-option="bomConfigOption" export-name="exportName"></se-bom-ui-grid>');
        element = compile(element)(scope);
        expect(angular.equals(tempOption,scope.bomConfigOption)).toBe(false);
      })

      it("Check BOM grid data changes", function(){
        scope.gridData = {
          "header": {
            "columns": [
              {
                "field": "cubicle",
                "name": "Cubicle"
              },

              {
                "field": "fu",
                "name": "Functional Unit"
              },
              {
                "field": "efu",
                "name": "eFU reference"
              },
              {
                "field": "productDefinition",
                "name": "Product Definition"
              },
              {
                "field": "part",
                "name": "Part reference"
              },
              {
                "field": "type",
                "name": "Type"
              },
              {
                "field": "material",
                "name": "Material"
              },
              {
                "field": "description",
                "name": "Description"
              },
              {
                "field": "quantity",
                "name": "Quantity",
                "type": "Number"
              }
            ],
            "groups": [
              "cubicles",
              "fus",
              "efus",
              "productDefinitions",
              "parts"
            ]},
          "bom":
          [{"cubicle":"Cubicle CUB-A","$$treeLevel":0}]
        };

        var tempOption = element.isolateScope().gridOptions.columnDefs;
        scope.$digest();
        expect(angular.equals(tempOption,scope.gridData.header.columns)).toBe(false);
      })
    })


  });
});
