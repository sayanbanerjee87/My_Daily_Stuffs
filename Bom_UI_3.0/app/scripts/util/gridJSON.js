var newJson = {
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
        ]
    },
    "bom":

   [{"cubicle":"Cubicle CUB-A","$$treeLevel":0},

{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"INTP 115-1 N/D 2350 0+650+0 600","quantity":1,"$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"INTER CUB BARRIER H2350 D600","quantity":1,"productDefinition":"GHQ87015","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"INTER-COL PANEL 2350HI 600DEEP","quantity":1,"productDefinition":"GHQ87015","material":"Steel","part":"04690707"},


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"INTER-COL PANEL 2350HI 600DEEP","quantity":1,"productDefinition":"GHQ87015","material":"Steel","part":"04690707"},


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"2 FLANG FOR BB115<=3200A H2350","quantity":1,"productDefinition":"GHQ87279","material":"Steel","part":"04690707","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"FLASK 115 BB 2350 HIGH","quantity":2,"productDefinition":"GHQ87279","material":"Steel","part":"04690411"},


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"FLASK 115 BB 2350 HIGH","quantity":2,"productDefinition":"GHQ87279","material":"Steel","part":"04690411"},


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"1 RIGHT PARTITION   H2350&2200  115BB","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690411","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"RIGHT BARRIER 2350 & 2200 HIGH","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690478"},


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"RIGHT BARRIER 2350 & 2200 HIGH","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690478"},


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"RIGHT BARRIER 2350 & 2200 HIGH","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690478"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HRBB Copper 0+650+0 (3P+N)+PE;(3P+PEN) Single 4x40x10","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690478","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HBB W650 3P+N / 3P+PEN 4B/PH","quantity":1,"productDefinition":"GHQ10830","material":"Steel","part":"04690478","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HBB W 650","quantity":16,"productDefinition":"GHQ10830","material":"Copper","part":"04692008"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HBB W 650","quantity":16,"productDefinition":"GHQ10830","material":"Copper","part":"04692008"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"DEV CUB PLATE L650 FOR BB115","quantity":1,"productDefinition":"GHQ87020","material":"Copper","part":"04692008","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"TUSK BOTTOM PLATE","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690212"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"F2 TOP DC TOP ENTRY","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690416"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"BOTTOM TRAY 115 BB","quantity":1,"productDefinition":"GHQ87020","material":"Miscellaneous","part":"04690487"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"F2 115 BB 2 MOD","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690488"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB TRAY 650 WIDE 115 BB","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690492"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB TRAY SIDE MEMBER","quantity":2,"productDefinition":"GHQ87020","material":"Steel","part":"04690493"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB TRAY BAND 70/115 BB","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690494"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"SUPPORT FOR HBB SUPP 650 WIDE","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690516"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB REAR F2 650 WIDE IP3X","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"AAV17870"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB FRONT F2 650 WIDE IP3X","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"AAV17874"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB FRONT F2 650 WIDE IP3X","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"AAV17874"},


{"cubicle":"Cubicle CUB-A","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"1 HORIZ BB SUP ICW 100KA","quantity":3,"productDefinition":"GHQ87261","material":"Steel","part":"AAV17874","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"PE horizontal","efu":"EFU10PH0012","description":"PEH OKN  NS_#1/#2-1/#3 W6 PE/PEN_40x5","quantity":1,"productDefinition":"GHQ87261","material":"Steel","part":"AAV17874","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"PE horizontal","efu":"EFU10PH0012","description":"H PE 40X5 W650","quantity":1,"productDefinition":"GHQ10981","material":"Steel","part":"AAV17874","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"PE horizontal","efu":"EFU10PH0012","description":"PE BB W 650 40X5","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017"},


{"cubicle":"Cubicle CUB-A","type":"PE horizontal","efu":"EFU10PH0012","description":"PE BB W 650 40X5","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017"},


{"cubicle":"Cubicle CUB-A","type":"PE horizontal","efu":"EFU10PH0012","description":"PE BB W 650 40X5","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017"},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"CBRF 0+650+0 600 Without Natural","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"SEALING GASKET FOR JUNCTION ROOF","quantity":0.0143,"productDefinition":"GHQ87029","material":"Copper","part":"04692017","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"TOP PLATE  L650 D600","quantity":1,"productDefinition":"GHQ87033","material":"Copper","part":"04692017","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"FRONT ROOF P600 L650","quantity":1,"productDefinition":"GHQ87033","material":"Steel","part":"AAV12866"},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"BACK ROOF P600 L650","quantity":1,"productDefinition":"GHQ87033","material":"Miscellaneous","part":"AAV21207"},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"BACK ROOF P600 L650","quantity":1,"productDefinition":"GHQ87033","material":"Miscellaneous","part":"AAV21207"},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"50 METERS IP31 TOP PL GASKET","quantity":0.0132,"productDefinition":"GHQ87046","material":"Miscellaneous","part":"AAV21207","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"UPPER BAND L650","quantity":1,"productDefinition":"GHQ87063","material":"Miscellaneous","part":"AAV21207","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"200 WATER PROOF WASHERS  M6","quantity":0.02,"productDefinition":"GHQ87139","material":"Miscellaneous","part":"AAV21207","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"1000 SELF THR SCR/ M6X13","quantity":0.004,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"AAV21207","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"SELF THR SCR / M6X13","quantity":1000,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"04690002"},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"SELF THR SCR / M6X13","quantity":1000,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"04690002"},


{"cubicle":"Cubicle CUB-A","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"100 WATER PROOF WASHERS","quantity":0.04,"productDefinition":"GHQ87653","material":"Miscellaneous","part":"04690002","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Bottom plate","efu":"EFU_01BTPL_00001_WW","description":"BTPL Okken 0+650+0 600","quantity":1,"productDefinition":"GHQ87653","material":"Miscellaneous","part":"04690002","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Bottom plate","efu":"EFU_01BTPL_00001_WW","description":"BOTTOM PLATE L650 D600","quantity":1,"productDefinition":"GHQ87162","material":"Miscellaneous","part":"04690002","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Bottom plate","efu":"EFU_01BTPL_00001_WW","description":"BOTTOM PLATE 650-FP","quantity":1,"productDefinition":"GHQ87162","material":"Miscellaneous","part":"04691421"},


{"cubicle":"Cubicle CUB-A","type":"Bottom plate","efu":"EFU_01BTPL_00001_WW","description":"BOTTOM PLATE 650-MP","quantity":2,"productDefinition":"GHQ87162","material":"Aluminum","part":"04691439"},


{"cubicle":"Cubicle CUB-A","type":"Bottom plate","efu":"EFU_01BTPL_00001_WW","description":"BOTTOM PLATE 650-MP","quantity":2,"productDefinition":"GHQ87162","material":"Aluminum","part":"04691439"},


{"cubicle":"Cubicle CUB-A","type":"Bottom plate","efu":"EFU_01BTPL_00001_WW","description":"BOTTOM PLATE 650-MP","quantity":2,"productDefinition":"GHQ87162","material":"Aluminum","part":"04691439"},


{"cubicle":"Cubicle CUB-A","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"FRME H2350 W0+650+0 D600 None","quantity":1,"productDefinition":"GHQ87162","material":"Aluminum","part":"04691439","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"FRAMES H2350 D600","quantity":1,"productDefinition":"GHQ87000","material":"Aluminum","part":"04691439","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"1 PLINTH D600","quantity":1,"productDefinition":"GHQ87004","material":"Aluminum","part":"04691439","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"BASE POSITIONS WELDS STD P600","quantity":2,"productDefinition":"GHQ87004","material":"Miscellaneous","part":"AAV17225"},


{"cubicle":"Cubicle CUB-A","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"BASE POSITIONS WELDS STD P600","quantity":2,"productDefinition":"GHQ87004","material":"Miscellaneous","part":"AAV17225"},


{"cubicle":"Cubicle CUB-A","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"4 CROSS-MEMBERS L650","quantity":1,"productDefinition":"GHQ87007","material":"Miscellaneous","part":"AAV17225","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Fishplates","efu":"EFU_01HBFP_00013_WW","description":"HBFP Okken 4x40x10","quantity":1,"productDefinition":"GHQ87007","material":"Miscellaneous","part":"AAV17225","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Fishplates","efu":"EFU_01HBFP_00013_WW","description":"SLIDING BB JOINER/ 4 HORIZ B/P","quantity":4,"productDefinition":"GHQ87262","material":"Miscellaneous","part":"AAV17225","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Rear panelling","efu":"EFU_01RPNL_00105_WW","description":"RPNL CUB_G_P81_ IP31","quantity":1,"productDefinition":"GHQ87262","material":"Miscellaneous","part":"AAV17225","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Rear panelling","efu":"EFU_01RPNL_00105_WW","description":"REAR PANEL H2350 L650","quantity":1,"productDefinition":"GHQ87047","material":"Miscellaneous","part":"AAV17225","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Rear panelling","efu":"EFU_01RPNL_00105_WW","description":"REAR PANEL 650 WIDE 2350 HIGH","quantity":1,"productDefinition":"GHQ87047","material":"Steel","part":"04690661"},


{"cubicle":"Cubicle CUB-A","type":"Rear panelling","efu":"EFU_01RPNL_00105_WW","description":"REAR PANEL 650 WIDE 2350 HIGH","quantity":1,"productDefinition":"GHQ87047","material":"Steel","part":"04690661"},


{"cubicle":"Cubicle CUB-A","type":"Rear panelling","efu":"EFU_01RPNL_00105_WW","description":"REAR PANEL 650 WIDE 2350 HIGH","quantity":1,"productDefinition":"GHQ87047","material":"Steel","part":"04690661"},


{"cubicle":"Cubicle CUB-A","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"FPNL CUB_G_D9R_ IP31","quantity":1,"productDefinition":"GHQ87047","material":"Steel","part":"04690661","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"4 OFF-IP3X GRILL FOR L650 FRAM","quantity":1,"productDefinition":"GHQ87055","material":"Steel","part":"04690661","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"IP3X GRILL 650 WIDE","quantity":3,"productDefinition":"GHQ87055","material":"Steel","part":"04690717"},


{"cubicle":"Cubicle CUB-A","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"TOP REAR IP3X GRILL 650 WIDE","quantity":1,"productDefinition":"GHQ87055","material":"Steel","part":"04690718"},


{"cubicle":"Cubicle CUB-A","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"TOP REAR IP3X GRILL 650 WIDE","quantity":1,"productDefinition":"GHQ87055","material":"Steel","part":"04690718"},


{"cubicle":"Cubicle CUB-A","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MODULES","quantity":1,"productDefinition":"GHQ87081WP","material":"Steel","part":"04690718","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP"},


{"cubicle":"Cubicle CUB-A","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP"},


{"cubicle":"Cubicle CUB-A","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"FRDR 225 RAL9003 Plain","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"GHQ80049WP","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"PLAIN DOOR 9 MODULES","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"04473519VA","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"10 ANG BRACK / DO BOTTOM L650","quantity":0.1,"productDefinition":"GHQ87138","material":"Miscellaneous","part":"GHQ80049WP","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"ANG BRACK / DO BOTTOM W650","quantity":10,"productDefinition":"GHQ87138","material":"Steel","part":"04690778"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"ANG BRACK / DO BOTTOM W650","quantity":10,"productDefinition":"GHQ87138","material":"Steel","part":"04690778"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"50 FIXED HINGES WITH PIN","quantity":0.04,"productDefinition":"GHQ87142","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"200 DOOR STOPS","quantity":0.005,"productDefinition":"GHQ87150","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"DRAC No_key_or_insert_without_label","quantity":1,"productDefinition":"GHQ87150","material":"Steel","part":"04690778","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"HANDLES WITHOUT BARREL","quantity":1,"productDefinition":"GHQ87666","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORTS FOR LATCHING SYSTEM","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0"},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0"},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"FRDR 150 RAL9003 Plain","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Steel","part":"LVM23041X0","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"PLAIN DOOR 6 MODULES NP","quantity":1,"productDefinition":"GHQ87094WP","material":"Miscellaneous","part":"04473519VA","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"PLAIN DOOR 6 MOD ASM.","quantity":1,"productDefinition":"GHQ87094WP","material":"Miscellaneous","part":"GHQ80050WP"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"PLAIN DOOR 6 MOD ASM.","quantity":1,"productDefinition":"GHQ87094WP","material":"Miscellaneous","part":"GHQ80050WP"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"10 ANG BRACK / DO BOTTOM L650","quantity":0.1,"productDefinition":"GHQ87138","material":"Miscellaneous","part":"GHQ80050WP","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"ANG BRACK / DO BOTTOM W650","quantity":10,"productDefinition":"GHQ87138","material":"Steel","part":"04690778"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"ANG BRACK / DO BOTTOM W650","quantity":10,"productDefinition":"GHQ87138","material":"Steel","part":"04690778"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"50 FIXED HINGES WITH PIN","quantity":0.04,"productDefinition":"GHQ87142","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00068_WW","description":"200 DOOR STOPS","quantity":0.005,"productDefinition":"GHQ87150","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"DRAC No_key_or_insert_without_label","quantity":1,"productDefinition":"GHQ87150","material":"Steel","part":"04690778","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"HANDLES WITHOUT BARREL","quantity":1,"productDefinition":"GHQ87666","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORTS FOR LATCHING SYSTEM","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0"},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0"},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0"},


{"cubicle":"Cubicle CUB-A","type":"Space cover install","efu":"eFU_Space_Cover_instal_7","description":"eFU_Space_Cover_instal_7","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Space cover install","efu":"eFU_Space_Cover_instal_7","description":"CASE COVER 6 MODULES","quantity":1,"productDefinition":"GHQ87305","material":"Steel","part":"LVM23041X0","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Space cover install","efu":"eFU_Space_Cover_instal_7","description":"HORIZONTAL BARRIER 115 BB","quantity":1,"productDefinition":"GHQ87305","material":"Steel","part":"04690432"},


{"cubicle":"Cubicle CUB-A","type":"Space cover install","efu":"eFU_Space_Cover_instal_7","description":"F2 115 BB 6 MOD","quantity":2,"productDefinition":"GHQ87305","material":"Steel","part":"04690443"},


{"cubicle":"Cubicle CUB-A","type":"Space cover install","efu":"eFU_Space_Cover_instal_7","description":"F2 115 BB 6 MOD","quantity":2,"productDefinition":"GHQ87305","material":"Steel","part":"04690443"},


{"cubicle":"Cubicle CUB-A","type":"Space cover install","efu":"eFU_Space_Cover_instal_7","description":"F2 115 BB 6 MOD","quantity":2,"productDefinition":"GHQ87305","material":"Steel","part":"04690443"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"FRDR 475 RAL9003 Plain","quantity":1,"productDefinition":"GHQ87305","material":"Steel","part":"04690443","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Steel","part":"04690443","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"PLAIN DOOR 19 MOD ASM","quantity":1,"productDefinition":"GHQ87079WP","material":"Miscellaneous","part":"04473519VA","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"PLAIN DOOR 19 MOD ASM","quantity":1,"productDefinition":"GHQ87079WP","material":"Miscellaneous","part":"GHQ80045WP"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"PLAIN DOOR 19 MOD ASM","quantity":1,"productDefinition":"GHQ87079WP","material":"Miscellaneous","part":"GHQ80045WP"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"10 ANG BRACK / DO BOTTOM L650","quantity":0.1,"productDefinition":"GHQ87138","material":"Miscellaneous","part":"GHQ80045WP","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"ANG BRACK / DO BOTTOM W650","quantity":10,"productDefinition":"GHQ87138","material":"Steel","part":"04690778"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"ANG BRACK / DO BOTTOM W650","quantity":10,"productDefinition":"GHQ87138","material":"Steel","part":"04690778"},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"50 FIXED HINGES WITH PIN","quantity":0.04,"productDefinition":"GHQ87142","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Front door","efu":"EFU_01FRDR_00058_WW","description":"200 DOOR STOPS","quantity":0.005,"productDefinition":"GHQ87150","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"DRAC No_key_or_insert_without_label","quantity":1,"productDefinition":"GHQ87150","material":"Steel","part":"04690778","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"HANDLES WITHOUT BARREL","quantity":1,"productDefinition":"GHQ87666","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORTS FOR LATCHING SYSTEM","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"04690778","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0"},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0"},


{"cubicle":"Cubicle CUB-A","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0"},


{"cubicle":"Cubicle CUB-A","type":"AWA mandatory","efu":"AWA_Mandatory_ref_40","description":"AWA_Mandatory_code_designation_40","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"AWA mandatory","efu":"AWA_Mandatory_ref_40","description":"5 AUX. CABLE DUCTS BB115","quantity":0.2,"productDefinition":"GHQ87301","material":"Steel","part":"LVM23041X0","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LFTL Okken 230;70-2;70-F;115-1;115-2;115-3;115/70-2;PFC;SingleNW;SingleNT/NS;VSD-SS;S16-BT;S16-TBB;S","quantity":1,"productDefinition":"GHQ87301","material":"Steel","part":"LVM23041X0","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"4 LIFTING EYES","quantity":1,"productDefinition":"GHQ87011","material":"Steel","part":"LVM23041X0","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795"},


{"cubicle":"Cubicle CUB-A","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795"},


{"cubicle":"Cubicle CUB-A","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795"},


{"cubicle":"Cubicle CUB-A","type":"HVL","efu":"EFU_01HVLK_00719_WW","description":"HVLK Copper HBB (3P+N)+PE Single VBB (3P+N)+PE;(3P+N)+N+PE N/A","quantity":1,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"HVL","efu":"EFU_01HVLK_00719_WW","description":"H/V BB 115 3P+N / 3P+PEN 4B/PH","quantity":1,"productDefinition":"GHQ10948","material":"Steel","part":"04690795","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"HVL","efu":"EFU_01HVLK_00719_WW","description":"H-V DBA115 SPACER","quantity":4,"productDefinition":"GHQ10948","material":"Copper","part":"04692013"},


{"cubicle":"Cubicle CUB-A","type":"HVL","efu":"EFU_01HVLK_00719_WW","description":"H-V DBA115 SPACER","quantity":4,"productDefinition":"GHQ10948","material":"Copper","part":"04692013"},


{"cubicle":"Cubicle CUB-A","type":"HVL","efu":"EFU_01HVLK_00719_WW","description":"100 ANTI ROT BRACK","quantity":0.08,"productDefinition":"GHQ87188","material":"Copper","part":"04692013","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"HVL","efu":"EFU_01HVLK_00719_WW","description":"100 FLAT WASHERS DIA 11/38","quantity":0.08,"productDefinition":"GHQ87189","material":"Copper","part":"04692013","$$treeLevel":2},


/*{"cubicle":"Cubicle CUB-A","type":"HVL","efu":"EFU_01HVLK_00719_WW","description":"BB115/HORIZ BB JOINER","quantity":4,"productDefinition":"GHQ87408","material":"Copper","part":"04692013","$$treeLevel":0},


{"cubicle":"Cubicle CUB-A","type":"HVL","efu":"EFU_01HVLK_00719_WW","description":"BB115/HORIZ BB JOINER","quantity":4,"productDefinition":"GHQ87408","material":"Copper","part":"04692013","$$treeLevel":0},*/


{"cubicle":"Cubicle CUB-A","type":"HVL","efu":"EFU_01HVLK_00719_WW","description":"BB115/HORIZ BB JOINER","quantity":4,"productDefinition":"GHQ87408","material":"Copper","part":"04692013","fu":"QA-2","$$treeLevel":0},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"ACBP B475F_DP_001 IP31 RAL9003","quantity":1,"productDefinition":"GHQ87408","material":"Copper","part":"04692013","fu":"QA-2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Copper","part":"04692013","fu":"QA-2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA","fu":"QA-2"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-2"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-2"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"NW40 DOOR BB115 19 MODULES","quantity":1,"productDefinition":"GHQ87074WP","material":"Miscellaneous","part":"04473519VA","fu":"QA-2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"DOOR 19 MOD. NW ASM.","quantity":1,"productDefinition":"GHQ87074WP","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-2"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"DOOR 19 MOD. NW ASM.","quantity":1,"productDefinition":"GHQ87074WP","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-2"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"50 FIXED HINGES WITH PIN","quantity":0.04,"productDefinition":"GHQ87142","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"200 DOOR STOPS","quantity":0.005,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"ACB Device","efu":"NW10N1 3P WWW","description":"NW10N1 3P WWW neutral position left","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"ACB embedded protection","efu":"Micrologic 2.0A","description":"Micrologic 2.0A","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"ACB embedded protection","efu":"Micrologic 2.0A","description":"Micrologic 2.0A","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3","$$treeLevel":0},


{"cubicle":"Cubicle CUB-A","type":"ACB installation","efu":"EFU_ACBI_8","description":"ACB_installation_designation_8","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"ACB installation","efu":"EFU_ACBI_8","description":"NW 3200A INSTALLATION / 19 MOD","quantity":1,"productDefinition":"GHQ87304","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"ACB installation","efu":"EFU_ACBI_8","description":"MOUNTING SHELF NW","quantity":1,"productDefinition":"GHQ87304","material":"Steel","part":"04690430","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB installation","efu":"EFU_ACBI_8","description":"F2 115 BB 5 MOD","quantity":1,"productDefinition":"GHQ87304","material":"Steel","part":"04690442","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB installation","efu":"EFU_ACBI_8","description":"LIAISON INSUL SCREEN 115 BB","quantity":1,"productDefinition":"GHQ87304","material":"Miscellaneous","part":"04690445","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB installation","efu":"EFU_ACBI_8","description":"F2 115 BB 19 MOD","quantity":1,"productDefinition":"GHQ87304","material":"Steel","part":"04690446","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB installation","efu":"EFU_ACBI_8","description":"F2 COMPLEMENT 115BB NW 3PWDBLE","quantity":1,"productDefinition":"GHQ87304","material":"Steel","part":"04690447","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB installation","efu":"EFU_ACBI_8","description":"F2 COMPLEMENT 115BB NW 3PWDBLE","quantity":1,"productDefinition":"GHQ87304","material":"Steel","part":"04690447","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB installation","efu":"EFU_ACBI_8","description":"F2 COMPLEMENT 115BB NW 3PWDBLE","quantity":1,"productDefinition":"GHQ87304","material":"Steel","part":"04690447","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"ACBP B475F_DP_001 IP31 RAL9003","quantity":1,"productDefinition":"GHQ87304","material":"Steel","part":"04690447","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Steel","part":"04690447","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"NW40 DOOR BB115 19 MODULES","quantity":1,"productDefinition":"GHQ87074WP","material":"Miscellaneous","part":"04473519VA","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"DOOR 19 MOD. NW ASM.","quantity":1,"productDefinition":"GHQ87074WP","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"DOOR 19 MOD. NW ASM.","quantity":1,"productDefinition":"GHQ87074WP","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"50 FIXED HINGES WITH PIN","quantity":0.04,"productDefinition":"GHQ87142","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"ACB panelling","efu":"EFU_ACBP_156","description":"200 DOOR STOPS","quantity":0.005,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-A","type":"ACB Device","efu":"NW10N1 3P WWW","description":"NW10N1 3P WWW neutral position left","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-A","type":"ACB embedded protection","efu":"Micrologic 2.0A","description":"Micrologic 2.0A","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"ACB embedded protection","efu":"Micrologic 2.0A","description":"Micrologic 2.0A","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3","$$treeLevel":0},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"INTP SingleNW N/D 2350 0+650+0 600","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"INTER CUB BARRIER H2350 D600","quantity":1,"productDefinition":"GHQ87015","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"INTER-COL PANEL 2350HI 600DEEP","quantity":1,"productDefinition":"GHQ87015","material":"Steel","part":"04690707","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"INTER-COL PANEL 2350HI 600DEEP","quantity":1,"productDefinition":"GHQ87015","material":"Steel","part":"04690707","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"2 FLANG FOR BB115<=3200A H2350","quantity":1,"productDefinition":"GHQ87279","material":"Steel","part":"04690707","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"FLASK 115 BB 2350 HIGH","quantity":2,"productDefinition":"GHQ87279","material":"Steel","part":"04690411","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"FLASK 115 BB 2350 HIGH","quantity":2,"productDefinition":"GHQ87279","material":"Steel","part":"04690411","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"1 RIGHT PARTITION   H2350&2200  115BB","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690411","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"RIGHT BARRIER 2350 & 2200 HIGH","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690478","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"RIGHT BARRIER 2350 & 2200 HIGH","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690478","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Internal partitioning","efu":"EFU_01INTP_00242_WW","description":"RIGHT BARRIER 2350 & 2200 HIGH","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690478","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HRBB Copper 0+650+0 (3P+N)+PE;(3P+PEN) Single 4x40x10","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690478","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HBB W650 3P+N / 3P+PEN 4B/PH","quantity":1,"productDefinition":"GHQ10830","material":"Steel","part":"04690478","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HBB W 650","quantity":16,"productDefinition":"GHQ10830","material":"Copper","part":"04692008","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HBB W 650","quantity":16,"productDefinition":"GHQ10830","material":"Copper","part":"04692008","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"DEV CUB PLATE L650 FOR BB115","quantity":1,"productDefinition":"GHQ87020","material":"Copper","part":"04692008","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"TUSK BOTTOM PLATE","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690212","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"F2 TOP DC TOP ENTRY","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690416","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"BOTTOM TRAY 115 BB","quantity":1,"productDefinition":"GHQ87020","material":"Miscellaneous","part":"04690487","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"F2 115 BB 2 MOD","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690488","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB TRAY 650 WIDE 115 BB","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690492","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB TRAY SIDE MEMBER","quantity":2,"productDefinition":"GHQ87020","material":"Steel","part":"04690493","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB TRAY BAND 70/115 BB","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690494","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"SUPPORT FOR HBB SUPP 650 WIDE","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"04690516","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB REAR F2 650 WIDE IP3X","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"AAV17870","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB FRONT F2 650 WIDE IP3X","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"AAV17874","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"HORIZ BB FRONT F2 650 WIDE IP3X","quantity":1,"productDefinition":"GHQ87020","material":"Steel","part":"AAV17874","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"HBB","efu":"EFU_01HRBB_00772_WW","description":"1 HORIZ BB SUP ICW 100KA","quantity":3,"productDefinition":"GHQ87261","material":"Steel","part":"AAV17874","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"PE horizontal","efu":"EFU10PH0012","description":"PEH OKN  NS_#1/#2-1/#3 W6 PE/PEN_40x5","quantity":1,"productDefinition":"GHQ87261","material":"Steel","part":"AAV17874","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"PE horizontal","efu":"EFU10PH0012","description":"H PE 40X5 W650","quantity":1,"productDefinition":"GHQ10981","material":"Steel","part":"AAV17874","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"PE horizontal","efu":"EFU10PH0012","description":"PE BB W 650 40X5","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"PE horizontal","efu":"EFU10PH0012","description":"PE BB W 650 40X5","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"PE horizontal","efu":"EFU10PH0012","description":"PE BB W 650 40X5","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"CBRF 0+650+0 600 Without Natural","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"SEALING GASKET FOR JUNCTION ROOF","quantity":0.0143,"productDefinition":"GHQ87029","material":"Copper","part":"04692017","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"TOP PLATE  L650 D600","quantity":1,"productDefinition":"GHQ87033","material":"Copper","part":"04692017","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"FRONT ROOF P600 L650","quantity":1,"productDefinition":"GHQ87033","material":"Steel","part":"AAV12866","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"BACK ROOF P600 L650","quantity":1,"productDefinition":"GHQ87033","material":"Miscellaneous","part":"AAV21207","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"BACK ROOF P600 L650","quantity":1,"productDefinition":"GHQ87033","material":"Miscellaneous","part":"AAV21207","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"50 METERS IP31 TOP PL GASKET","quantity":0.0132,"productDefinition":"GHQ87046","material":"Miscellaneous","part":"AAV21207","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"UPPER BAND L650","quantity":1,"productDefinition":"GHQ87063","material":"Miscellaneous","part":"AAV21207","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"200 WATER PROOF WASHERS  M6","quantity":0.02,"productDefinition":"GHQ87139","material":"Miscellaneous","part":"AAV21207","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"1000 SELF THR SCR/ M6X13","quantity":0.004,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"AAV21207","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"SELF THR SCR / M6X13","quantity":1000,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"04690002","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"SELF THR SCR / M6X13","quantity":1000,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"04690002","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Roof","efu":"EFU_01CBRF_00001_WW","description":"100 WATER PROOF WASHERS","quantity":0.04,"productDefinition":"GHQ87653","material":"Miscellaneous","part":"04690002","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"FRME H2350 W0+650+0 D600 None","quantity":1,"productDefinition":"GHQ87653","material":"Miscellaneous","part":"04690002","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"FRAMES H2350 D600","quantity":1,"productDefinition":"GHQ87000","material":"Miscellaneous","part":"04690002","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"1 PLINTH D600","quantity":1,"productDefinition":"GHQ87004","material":"Miscellaneous","part":"04690002","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"BASE POSITIONS WELDS STD P600","quantity":2,"productDefinition":"GHQ87004","material":"Miscellaneous","part":"AAV17225","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"BASE POSITIONS WELDS STD P600","quantity":2,"productDefinition":"GHQ87004","material":"Miscellaneous","part":"AAV17225","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Frame","efu":"EFU_01FRME_00002_WW","description":"4 CROSS-MEMBERS L650","quantity":1,"productDefinition":"GHQ87007","material":"Miscellaneous","part":"AAV17225","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Fishplates","efu":"EFU_01HBFP_00013_WW","description":"HBFP Okken 4x40x10","quantity":1,"productDefinition":"GHQ87007","material":"Miscellaneous","part":"AAV17225","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Fishplates","efu":"EFU_01HBFP_00013_WW","description":"SLIDING BB JOINER/ 4 HORIZ B/P","quantity":4,"productDefinition":"GHQ87262","material":"Miscellaneous","part":"AAV17225","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Rear panelling","efu":"EFU_01RPNL_00057_WW","description":"RPNL CUB_G_D81L_ IP31","quantity":1,"productDefinition":"GHQ87262","material":"Miscellaneous","part":"AAV17225","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Rear panelling","efu":"EFU_01RPNL_00057_WW","description":"LHS LOCK COMP DOOR H2350L650","quantity":1,"productDefinition":"GHQ87111WP","material":"Miscellaneous","part":"AAV17225","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Rear panelling","efu":"EFU_01RPNL_00057_WW","description":"CABLEWAY DOOR 650 2350 HIGH ASM","quantity":1,"productDefinition":"GHQ87111WP","material":"Miscellaneous","part":"GHQ80051WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Rear panelling","efu":"EFU_01RPNL_00057_WW","description":"CABLEWAY DOOR 650 2350 HIGH ASM","quantity":1,"productDefinition":"GHQ87111WP","material":"Miscellaneous","part":"GHQ80051WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Rear panelling","efu":"EFU_01RPNL_00057_WW","description":"CABLEWAY DOOR 650 2350 HIGH ASM","quantity":1,"productDefinition":"GHQ87111WP","material":"Miscellaneous","part":"GHQ80051WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"FPNL CUB_G_D9R_ IP31","quantity":1,"productDefinition":"GHQ87111WP","material":"Miscellaneous","part":"GHQ80051WP","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"4 OFF-IP3X GRILL FOR L650 FRAM","quantity":1,"productDefinition":"GHQ87055","material":"Miscellaneous","part":"GHQ80051WP","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"IP3X GRILL 650 WIDE","quantity":3,"productDefinition":"GHQ87055","material":"Steel","part":"04690717","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"TOP REAR IP3X GRILL 650 WIDE","quantity":1,"productDefinition":"GHQ87055","material":"Steel","part":"04690718","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"TOP REAR IP3X GRILL 650 WIDE","quantity":1,"productDefinition":"GHQ87055","material":"Steel","part":"04690718","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MODULES","quantity":1,"productDefinition":"GHQ87081WP","material":"Steel","part":"04690718","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"FRDR 225 RAL9003 Plain","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"PLAIN DOOR 9 MODULES","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"04473519VA","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"10 ANG BRACK / DO BOTTOM L650","quantity":0.1,"productDefinition":"GHQ87138","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"ANG BRACK / DO BOTTOM W650","quantity":10,"productDefinition":"GHQ87138","material":"Steel","part":"04690778","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"ANG BRACK / DO BOTTOM W650","quantity":10,"productDefinition":"GHQ87138","material":"Steel","part":"04690778","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"50 FIXED HINGES WITH PIN","quantity":0.04,"productDefinition":"GHQ87142","material":"Steel","part":"04690778","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00065_WW","description":"200 DOOR STOPS","quantity":0.005,"productDefinition":"GHQ87150","material":"Steel","part":"04690778","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"DRAC No_key_or_insert_without_label","quantity":1,"productDefinition":"GHQ87150","material":"Steel","part":"04690778","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"HANDLES WITHOUT BARREL","quantity":1,"productDefinition":"GHQ87666","material":"Steel","part":"04690778","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORTS FOR LATCHING SYSTEM","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"04690778","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"FRDR 1100 RAL9003 Plain","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Steel","part":"LVM23041X0","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"PLAIN DOOR 44 MODULES","quantity":1,"productDefinition":"GHQ87076WP","material":"Miscellaneous","part":"04473519VA","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"PLAIN DOOR 44 MOD ASM","quantity":1,"productDefinition":"GHQ87076WP","material":"Miscellaneous","part":"GHQ80038WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"PLAIN DOOR 44 MOD ASM","quantity":1,"productDefinition":"GHQ87076WP","material":"Miscellaneous","part":"GHQ80038WP","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"10 ANG BRACK / DO BOTTOM L650","quantity":0.1,"productDefinition":"GHQ87138","material":"Miscellaneous","part":"GHQ80038WP","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"ANG BRACK / DO BOTTOM W650","quantity":10,"productDefinition":"GHQ87138","material":"Steel","part":"04690778","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"ANG BRACK / DO BOTTOM W650","quantity":10,"productDefinition":"GHQ87138","material":"Steel","part":"04690778","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"50 FIXED HINGES WITH PIN","quantity":0.04,"productDefinition":"GHQ87142","material":"Steel","part":"04690778","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Front door","efu":"EFU_01FRDR_00045_WW","description":"200 DOOR STOPS","quantity":0.005,"productDefinition":"GHQ87150","material":"Steel","part":"04690778","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"DRAC No_key_or_insert_without_label","quantity":1,"productDefinition":"GHQ87150","material":"Steel","part":"04690778","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"HANDLES WITHOUT BARREL","quantity":1,"productDefinition":"GHQ87666","material":"Steel","part":"04690778","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORTS FOR LATCHING SYSTEM","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"04690778","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"Door accessories","efu":"EFU_01DRAC_00001_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","fu":"QA-3"},


{"cubicle":"Cubicle CUB-B","type":"AWA mandatory","efu":"AWA_Mandatory_ref_40","description":"AWA_Mandatory_code_designation_40","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"AWA mandatory","efu":"AWA_Mandatory_ref_40","description":"5 AUX. CABLE DUCTS BB115","quantity":0.2,"productDefinition":"GHQ87301","material":"Steel","part":"LVM23041X0","fu":"QA-3","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LFTL Okken 230;70-2;70-F;115-1;115-2;115-3;115/70-2;PFC;SingleNW;SingleNT/NS;VSD-SS;S16-BT;S16-TBB;S","quantity":1,"productDefinition":"GHQ87301","material":"Steel","part":"LVM23041X0","fu":"QA-3","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"4 LIFTING EYES","quantity":1,"productDefinition":"GHQ87011","material":"Steel","part":"LVM23041X0","fu":"QA-3","$$treeLevel":2},


/*{"cubicle":"Cubicle CUB-B","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","fu":"QA-3","$$treeLevel":0},


{"cubicle":"Cubicle CUB-B","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","fu":"QA-3","$$treeLevel":0},*/


/*{"cubicle":"Cubicle CUB-B","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","fu":"QA-3","$$treeLevel":0},


{"cubicle":"Cubicle CUB-B","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","fu":"QA-3","$$treeLevel":0},*/


{"cubicle":"Cubicle CUB-B","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","fu":"QA-4","$$treeLevel":0},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"ACB_installation_designation_34","quantity":1,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"SINGLE NW 08-32  3P+PEN  BDC  INSTALLATION","quantity":1,"productDefinition":"AAV61742","material":"Steel","part":"04690795","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"F2 COMPLEMENT 115BB NW 3PWDBLE","quantity":1,"productDefinition":"AAV61742","material":"Steel","part":"04690447","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"FU PLATE SINGLE NW 08-32","quantity":1,"productDefinition":"AAV61742","material":"Steel","part":"AAV58906","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"FLASK 115 BB SINGLE NW 08-32","quantity":2,"productDefinition":"AAV61742","material":"Steel","part":"AAV58908","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"CROSS MEMBER 115 BB SINGLE NW 08-32","quantity":1,"productDefinition":"AAV61742","material":"Steel","part":"AAV58911","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"HORIZONTAL BARRIER SINGLE NW 08-32","quantity":1,"productDefinition":"AAV61742","material":"Steel","part":"AAV60877","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"CROSS MEMBER   BDC   SINGLE NW 08-32","quantity":2,"productDefinition":"AAV61742","material":"Steel","part":"AAV60891","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"FORM 2 DBA115-1  SINGLE NW 08-32  BDC / RC","quantity":1,"productDefinition":"AAV61742","material":"Steel","part":"AAV60972","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"F2   20 MOD. SINGLE NW 08-32  3P +PEN  RC / RDB","quantity":1,"productDefinition":"AAV61742","material":"Plastic & Insulation","part":"AAV61029","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"F2   20 MOD. SINGLE NW 08-32  3P +PEN  RC / RDB","quantity":1,"productDefinition":"AAV61742","material":"Plastic & Insulation","part":"AAV61029","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB installation","efu":"EFU_ACBI_34","description":"F2   20 MOD. SINGLE NW 08-32  3P +PEN  RC / RDB","quantity":1,"productDefinition":"AAV61742","material":"Plastic & Insulation","part":"AAV61029","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB panelling","efu":"EFU_ACBP_156","description":"ACBP B475F_DP_001 IP31 RAL9003","quantity":1,"productDefinition":"AAV61742","material":"Plastic & Insulation","part":"AAV61029","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"ACB panelling","efu":"EFU_ACBP_156","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Plastic & Insulation","part":"AAV61029","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"ACB panelling","efu":"EFU_ACBP_156","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB panelling","efu":"EFU_ACBP_156","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB panelling","efu":"EFU_ACBP_156","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB panelling","efu":"EFU_ACBP_156","description":"NW40 DOOR BB115 19 MODULES","quantity":1,"productDefinition":"GHQ87074WP","material":"Miscellaneous","part":"04473519VA","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"ACB panelling","efu":"EFU_ACBP_156","description":"DOOR 19 MOD. NW ASM.","quantity":1,"productDefinition":"GHQ87074WP","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB panelling","efu":"EFU_ACBP_156","description":"DOOR 19 MOD. NW ASM.","quantity":1,"productDefinition":"GHQ87074WP","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-4"},


{"cubicle":"Cubicle CUB-B","type":"ACB panelling","efu":"EFU_ACBP_156","description":"50 FIXED HINGES WITH PIN","quantity":0.04,"productDefinition":"GHQ87142","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"ACB panelling","efu":"EFU_ACBP_156","description":"200 DOOR STOPS","quantity":0.005,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-B","type":"ACB Device","efu":"NW10N1 3P WWW","description":"NW10N1 3P WWW neutral position left","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-B","type":"ACB embedded protection","efu":"Micrologic 2.0A","description":"Micrologic 2.0A","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"ACB embedded protection","efu":"Micrologic 2.0A","description":"Micrologic 2.0A","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-4","$$treeLevel":0},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"INTP 70-2 N/D 2350 0+650+0 1000","quantity":1,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"INTER CUB BARRIER H2350 D600","quantity":1,"productDefinition":"GHQ87015","material":"Miscellaneous","part":"GHQ80030WP","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"INTER-COL PANEL 2350HI 600DEEP","quantity":1,"productDefinition":"GHQ87015","material":"Steel","part":"04690707","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"INTER-COL PANEL 2350HI 600DEEP","quantity":1,"productDefinition":"GHQ87015","material":"Steel","part":"04690707","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"INTER CUB BARRIER H2350 D400","quantity":1,"productDefinition":"GHQ87016","material":"Steel","part":"04690707","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"INTER-COL PANEL 2350HI 400DEEP","quantity":1,"productDefinition":"GHQ87016","material":"Steel","part":"04690708","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"INTER-COL PANEL 2350HI 400DEEP","quantity":1,"productDefinition":"GHQ87016","material":"Steel","part":"04690708","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"ADDIT TO INTER-COL BARRIER D400","quantity":1,"productDefinition":"GHQ87026","material":"Steel","part":"04690708","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"INTER-COL COMPLEMENT 400DEEP","quantity":1,"productDefinition":"GHQ87026","material":"Miscellaneous","part":"04690716","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"INTER-COL COMPLEMENT 400DEEP","quantity":1,"productDefinition":"GHQ87026","material":"Miscellaneous","part":"04690716","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"2 BB70 FLANG FOR CUBICLE H2350","quantity":1,"productDefinition":"GHQ87273","material":"Miscellaneous","part":"04690716","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"70 BB RIGHT FLASK 2350 HIGH","quantity":1,"productDefinition":"GHQ87273","material":"Steel","part":"04690095","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"70 BB LEFT FLASK 2350 HIGH","quantity":1,"productDefinition":"GHQ87273","material":"Steel","part":"04690096","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"70 BB LEFT FLASK 2350 HIGH","quantity":1,"productDefinition":"GHQ87273","material":"Steel","part":"04690096","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Internal partitioning","efu":"EFU_01INTP_00416_WW","description":"70 BB LEFT FLASK 2350 HIGH","quantity":1,"productDefinition":"GHQ87273","material":"Steel","part":"04690096","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"HRBB Copper 0+650+0 (3P+N)+PE;(3P+PEN) Single 4x40x10","quantity":1,"productDefinition":"GHQ87273","material":"Steel","part":"04690096","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"HBB W650 3P+N / 3P+PEN 4B/PH","quantity":1,"productDefinition":"GHQ10830","material":"Steel","part":"04690096","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"HBB W 650","quantity":16,"productDefinition":"GHQ10830","material":"Copper","part":"04692008","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"HBB W 650","quantity":16,"productDefinition":"GHQ10830","material":"Copper","part":"04692008","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"DEV CUB PLATE L650 FOR BB70","quantity":1,"productDefinition":"GHQ87021","material":"Copper","part":"04692008","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"BOTTOM PLATE W650 DBA70","quantity":1,"productDefinition":"GHQ87021","material":"Miscellaneous","part":"04690137","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"HORIZ BB TRAY BAND 70/115 BB","quantity":1,"productDefinition":"GHQ87021","material":"Steel","part":"04690494","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"HORIZ BB TRAY 650 WIDE 70 BB","quantity":1,"productDefinition":"GHQ87021","material":"Steel","part":"04690495","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"HORIZ BB F2 FIXING 70 BB","quantity":1,"productDefinition":"GHQ87021","material":"Steel","part":"04690496","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"SUPPORT FOR HBB SUPP 650 WIDE","quantity":1,"productDefinition":"GHQ87021","material":"Steel","part":"04690516","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"BOTTOM TRAY 70 BB","quantity":1,"productDefinition":"GHQ87021","material":"Miscellaneous","part":"04690555","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"HORIZ BB REAR F2 650 WIDE IP3X","quantity":1,"productDefinition":"GHQ87021","material":"Steel","part":"AAV17870","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"HORIZ BB FRONT F2 650 WIDE IP3X","quantity":1,"productDefinition":"GHQ87021","material":"Steel","part":"AAV17874","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"HORIZ BB FRONT F2 650 WIDE IP3X","quantity":1,"productDefinition":"GHQ87021","material":"Steel","part":"AAV17874","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HBB","efu":"EFU_01HRBB_00961_WW","description":"1 HORIZ BB SUP ICW 100KA","quantity":3,"productDefinition":"GHQ87261","material":"Steel","part":"AAV17874","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PE horizontal","efu":"EFU10PH0012","description":"PEH OKN  NS_#1/#2-1/#3 W6 PE/PEN_40x5","quantity":1,"productDefinition":"GHQ87261","material":"Steel","part":"AAV17874","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"PE horizontal","efu":"EFU10PH0012","description":"H PE 40X5 W650","quantity":1,"productDefinition":"GHQ10981","material":"Steel","part":"AAV17874","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PE horizontal","efu":"EFU10PH0012","description":"PE BB W 650 40X5","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"PE horizontal","efu":"EFU10PH0012","description":"PE BB W 650 40X5","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"PE horizontal","efu":"EFU10PH0012","description":"PE BB W 650 40X5","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"PE PEN accessories","efu":"EFU_01PEAC_00001_WW","description":"PEAC 70-2 RC 1x40x10;1x50x10","quantity":1,"productDefinition":"GHQ10981","material":"Copper","part":"04692017","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"PE PEN accessories","efu":"EFU_01PEAC_00001_WW","description":"50 ANGLE BRACKET SUPPORT /PE","quantity":0.06,"productDefinition":"GHQ87348","material":"Copper","part":"04692017","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PE PEN accessories","efu":"EFU_01PEAC_00001_WW","description":"SQUARE FOR EARTHING BAR","quantity":50,"productDefinition":"GHQ87348","material":"Steel","part":"04690988","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"PE PEN accessories","efu":"EFU_01PEAC_00001_WW","description":"SQUARE FOR EARTHING BAR","quantity":50,"productDefinition":"GHQ87348","material":"Steel","part":"04690988","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"PE PEN accessories","efu":"EFU_01PEAC_00001_WW","description":"20 INSULATORS H35 M10","quantity":0.2,"productDefinition":"GHQ87643","material":"Steel","part":"04690988","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"CBRF 0+650+0 1000 RC Natural","quantity":1,"productDefinition":"GHQ87643","material":"Steel","part":"04690988","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"SEALING GASKET FOR JUNCTION ROOF","quantity":0.0286,"productDefinition":"GHQ87029","material":"Steel","part":"04690988","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"TOP PLATE  L650 D1000","quantity":1,"productDefinition":"GHQ87032","material":"Steel","part":"04690988","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"FRONT ROOF P600 L650","quantity":1,"productDefinition":"GHQ87032","material":"Steel","part":"AAV12866","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"ASSOCIATE ROOF P600 L650","quantity":1,"productDefinition":"GHQ87032","material":"Miscellaneous","part":"AAV21211","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"COMPLETE  ASSOCIATE  ROOF  P400 L650  ASSEMBLY","quantity":1,"productDefinition":"GHQ87032","material":"Miscellaneous","part":"AAV33164","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"COMPLETE  ASSOCIATE  ROOF  P400 L650  ASSEMBLY","quantity":1,"productDefinition":"GHQ87032","material":"Miscellaneous","part":"AAV33164","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"50 METERS IP31 TOP PL GASKET","quantity":0.022,"productDefinition":"GHQ87046","material":"Miscellaneous","part":"AAV33164","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"UPPER BAND L650","quantity":1,"productDefinition":"GHQ87063","material":"Miscellaneous","part":"AAV33164","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"200 WATER PROOF WASHERS  M6","quantity":0.02,"productDefinition":"GHQ87139","material":"Miscellaneous","part":"AAV33164","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"1000 SELF THR SCR/ M6X13","quantity":0.004,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"AAV33164","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"SELF THR SCR / M6X13","quantity":1000,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"04690002","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"SELF THR SCR / M6X13","quantity":1000,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"04690002","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Roof","efu":"EFU_01CBRF_00002_WW","description":"100 WATER PROOF WASHERS","quantity":0.08,"productDefinition":"GHQ87653","material":"Miscellaneous","part":"04690002","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Frame","efu":"EFU_01FRME_00010_WW","description":"FRME H2350 W0+650+0 D1000 None","quantity":1,"productDefinition":"GHQ87653","material":"Miscellaneous","part":"04690002","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Frame","efu":"EFU_01FRME_00010_WW","description":"FRAMES H2350 D600","quantity":1,"productDefinition":"GHQ87000","material":"Miscellaneous","part":"04690002","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Frame","efu":"EFU_01FRME_00010_WW","description":"FRAMES H2350 D400","quantity":1,"productDefinition":"GHQ87001","material":"Miscellaneous","part":"04690002","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Frame","efu":"EFU_01FRME_00010_WW","description":"1 PLINTH D600","quantity":1,"productDefinition":"GHQ87004","material":"Miscellaneous","part":"04690002","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Frame","efu":"EFU_01FRME_00010_WW","description":"BASE POSITIONS WELDS STD P600","quantity":2,"productDefinition":"GHQ87004","material":"Miscellaneous","part":"AAV17225","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Frame","efu":"EFU_01FRME_00010_WW","description":"BASE POSITIONS WELDS STD P600","quantity":2,"productDefinition":"GHQ87004","material":"Miscellaneous","part":"AAV17225","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Frame","efu":"EFU_01FRME_00010_WW","description":"1 PLINTH D400","quantity":1,"productDefinition":"GHQ87005","material":"Miscellaneous","part":"AAV17225","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Frame","efu":"EFU_01FRME_00010_WW","description":"BASE POSITIONS WELDS STD P400","quantity":2,"productDefinition":"GHQ87005","material":"Miscellaneous","part":"AAV17226","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Frame","efu":"EFU_01FRME_00010_WW","description":"BASE POSITIONS WELDS STD P400","quantity":2,"productDefinition":"GHQ87005","material":"Miscellaneous","part":"AAV17226","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Frame","efu":"EFU_01FRME_00010_WW","description":"4 CROSS-MEMBERS L650","quantity":1,"productDefinition":"GHQ87007","material":"Miscellaneous","part":"AAV17226","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Fishplates","efu":"EFU_01HBFP_00013_WW","description":"HBFP Okken 4x40x10","quantity":1,"productDefinition":"GHQ87007","material":"Miscellaneous","part":"AAV17226","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Fishplates","efu":"EFU_01HBFP_00013_WW","description":"SLIDING BB JOINER/ 4 HORIZ B/P","quantity":4,"productDefinition":"GHQ87262","material":"Miscellaneous","part":"AAV17226","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Rear panelling","efu":"EFU_01RPNL_00057_WW","description":"RPNL CUB_G_D81L_ IP31","quantity":1,"productDefinition":"GHQ87262","material":"Miscellaneous","part":"AAV17226","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Rear panelling","efu":"EFU_01RPNL_00057_WW","description":"LHS LOCK COMP DOOR H2350L650","quantity":1,"productDefinition":"GHQ87111WP","material":"Miscellaneous","part":"AAV17226","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Rear panelling","efu":"EFU_01RPNL_00057_WW","description":"CABLEWAY DOOR 650 2350 HIGH ASM","quantity":1,"productDefinition":"GHQ87111WP","material":"Miscellaneous","part":"GHQ80051WP","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Rear panelling","efu":"EFU_01RPNL_00057_WW","description":"CABLEWAY DOOR 650 2350 HIGH ASM","quantity":1,"productDefinition":"GHQ87111WP","material":"Miscellaneous","part":"GHQ80051WP","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Rear panelling","efu":"EFU_01RPNL_00057_WW","description":"CABLEWAY DOOR 650 2350 HIGH ASM","quantity":1,"productDefinition":"GHQ87111WP","material":"Miscellaneous","part":"GHQ80051WP","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"FPNL CUB_G_D9R_ IP31","quantity":1,"productDefinition":"GHQ87111WP","material":"Miscellaneous","part":"GHQ80051WP","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"4 OFF-IP3X GRILL FOR L650 FRAM","quantity":1,"productDefinition":"GHQ87055","material":"Miscellaneous","part":"GHQ80051WP","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"IP3X GRILL 650 WIDE","quantity":3,"productDefinition":"GHQ87055","material":"Steel","part":"04690717","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"TOP REAR IP3X GRILL 650 WIDE","quantity":1,"productDefinition":"GHQ87055","material":"Steel","part":"04690718","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"TOP REAR IP3X GRILL 650 WIDE","quantity":1,"productDefinition":"GHQ87055","material":"Steel","part":"04690718","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MODULES","quantity":1,"productDefinition":"GHQ87081WP","material":"Steel","part":"04690718","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Front panelling","efu":"EFU_01FPNL_00026_WW","description":"PLAIN DOOR 9 MOD ASM.","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"AWA optional","efu":"AWA_Optional_ref_1","description":"AWA_Optional_code_designation_1","quantity":1,"productDefinition":"GHQ87081WP","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"AWA optional","efu":"AWA_Optional_ref_1","description":"LATTICE TRUNKING / AUX WIRING","quantity":1,"productDefinition":"GHQ87178","material":"Miscellaneous","part":"GHQ80049WP","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"AWA optional","efu":"AWA_Optional_ref_1","description":"WIRE TRUNKING","quantity":1,"productDefinition":"GHQ87178","material":"Miscellaneous","part":"04690401","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"AWA optional","efu":"AWA_Optional_ref_1","description":"WIRE TRUNKING","quantity":1,"productDefinition":"GHQ87178","material":"Miscellaneous","part":"04690401","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"AWA optional","efu":"AWA_Optional_ref_1","description":"5AUX BUS DUCT 4X32A H2350/2200","quantity":0.2,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04690401","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"AWA optional","efu":"AWA_Optional_ref_1","description":"LIMIT END","quantity":10,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04690374","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"AWA optional","efu":"AWA_Optional_ref_1","description":"COLECTOR AMP","quantity":5,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04690383","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"AWA optional","efu":"AWA_Optional_ref_1","description":"FIXING ANGLE COLLECTOR","quantity":10,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04691043","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"AWA optional","efu":"AWA_Optional_ref_1","description":"FIXING ANGLE COLLECTOR","quantity":10,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04691043","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"AWA optional","efu":"AWA_Optional_ref_1","description":"FIXING ANGLE COLLECTOR","quantity":10,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04691043","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LFTL Okken 230;70-2;70-F;115-1;115-2;115-3;115/70-2;PFC;SingleNW;SingleNT/NS;VSD-SS;S16-BT;S16-TBB;S","quantity":1,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04691043","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"4 LIFTING EYES","quantity":1,"productDefinition":"GHQ87011","material":"Miscellaneous","part":"04691043","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"Lifting lugs","efu":"EFU_01LFTL_00001_WW","description":"LIFTING RING","quantity":4,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HVL","efu":"EFU_01HVLK_00393_WW","description":"HVLK Copper HBB (3P+N)+PE Single VBB (3P)+N+PE;(3P)+PE N/A","quantity":1,"productDefinition":"GHQ87011","material":"Steel","part":"04690795","fu":"QA-4","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"HVL","efu":"EFU_01HVLK_00393_WW","description":"H/V BB 70/185 3P/ 3P+PEN/PENR 4B/PH","quantity":1,"productDefinition":"GHQ10943","material":"Steel","part":"04690795","fu":"QA-4","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"HVL","efu":"EFU_01HVLK_00393_WW","description":"H-V DBA70 CONN. N-PH3","quantity":1,"productDefinition":"GHQ10943","material":"Copper","part":"04692014","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HVL","efu":"EFU_01HVLK_00393_WW","description":"H-V DBA70 SPACER ","quantity":3,"productDefinition":"GHQ10943","material":"Copper","part":"04692015","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HVL","efu":"EFU_01HVLK_00393_WW","description":"H-V DBA70 CONN. PH1-PH2","quantity":2,"productDefinition":"GHQ10943","material":"Copper","part":"04692016","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HVL","efu":"EFU_01HVLK_00393_WW","description":"H-V DBA70 CONN. PH1-PH2","quantity":2,"productDefinition":"GHQ10943","material":"Copper","part":"04692016","fu":"QA-4"},


{"cubicle":"Cubicle CUB-C","type":"HVL","efu":"EFU_01HVLK_00393_WW","description":"100 ANTI ROT BRACK","quantity":0.03,"productDefinition":"GHQ87188","material":"Copper","part":"04692016","fu":"QA-4","$$treeLevel":2},


/*{"cubicle":"Cubicle CUB-C","type":"HVL","efu":"EFU_01HVLK_00393_WW","description":"100 FLAT WASHERS DIA 11/38","quantity":0.03,"productDefinition":"GHQ87189","material":"Copper","part":"04692016","fu":"QA-4","$$treeLevel":0},


{"cubicle":"Cubicle CUB-C","type":"HVL","efu":"EFU_01HVLK_00393_WW","description":"100 FLAT WASHERS DIA 11/38","quantity":0.03,"productDefinition":"GHQ87189","material":"Copper","part":"04692016","fu":"QA-4","$$treeLevel":0},*/


{"cubicle":"Cubicle CUB-C","type":"HVL","efu":"EFU_01HVLK_00393_WW","description":"100 FLAT WASHERS DIA 11/38","quantity":0.03,"productDefinition":"GHQ87189","material":"Copper","part":"04692016","fu":"QA-1.1","$$treeLevel":0},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"PnMP_FP OKN70-2 STD/SLT K200F003 RC","quantity":1,"productDefinition":"GHQ87189","material":"Copper","part":"04692016","fu":"QA-1.1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"POLYFAST RC DRAWER/PLUG-IN NS630 3P","quantity":1,"productDefinition":"GHQ11027","material":"Copper","part":"04692016","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"PH 1-3 NS630 REAR CONNECTOR","quantity":2,"productDefinition":"GHQ11027","material":"Copper","part":"04692000","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"PH 2-N NS630 REAR CONNECTOR","quantity":1,"productDefinition":"GHQ11027","material":"Copper","part":"04692001","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"PH 2-N NS630 REAR CONNECTOR","quantity":1,"productDefinition":"GHQ11027","material":"Copper","part":"04692001","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"CONNECTION SUPPORT 630A 3P","quantity":2,"productDefinition":"GHQ87265","material":"Copper","part":"04692001","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"8 M FIXED PA POLYFAST NS630 3P","quantity":1,"productDefinition":"GHQ87354","material":"Copper","part":"04692001","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"THRU  SUP NS630 3P WITH/OUT CT","quantity":1,"productDefinition":"GHQ87354","material":"Steel","part":"04690143","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"PARTITION REM/DISC NS630 3P 8MOD","quantity":1,"productDefinition":"GHQ87354","material":"Steel","part":"04690229","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"DSTREAM BLANK. PLATE NS630 3P 8MOD","quantity":1,"productDefinition":"GHQ87354","material":"Steel","part":"04690236","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"STRUCTURE FOR NS630 3P 8 MOD","quantity":1,"productDefinition":"GHQ87354","material":"Steel","part":"04690251","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"STRUCTURE FOR NS630 3P 8 MOD","quantity":1,"productDefinition":"GHQ87354","material":"Steel","part":"04690251","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"UPSTREAM JAWS 3P 630A","quantity":1,"productDefinition":"GHQ87366","material":"Steel","part":"04690251","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"DOWNSTREAM JAWS 3P 630A","quantity":1,"productDefinition":"GHQ87370","material":"Steel","part":"04690251","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"DCBRACKET RC 630A 3P  8M","quantity":1,"productDefinition":"GHQ87534","material":"Steel","part":"04690251","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"BUSHING SUPPORT RCO 8MOD","quantity":1,"productDefinition":"GHQ87534","material":"Steel","part":"04690164","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"BUSHING SUPPORT RCO 8MOD","quantity":1,"productDefinition":"GHQ87534","material":"Steel","part":"04690164","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"BUSHING SUPPORT RCO 8MOD","quantity":1,"productDefinition":"GHQ87534","material":"Steel","part":"04690164","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP moving part","efu":"EFU_01PPMP_00016_WW","description":"PPMP Okken K200F003","quantity":1,"productDefinition":"GHQ87534","material":"Steel","part":"04690164","fu":"QA-1.1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"PnMP moving part","efu":"EFU_01PPMP_00016_WW","description":"NS400/630 3P DEVICE POLYFAST","quantity":1,"productDefinition":"GHQ87358","material":"Steel","part":"04690164","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP moving part","efu":"EFU_01PPMP_00016_WW","description":"POLYFAST ACC NS630 DISC AMM","quantity":1,"productDefinition":"GHQ87429","material":"Steel","part":"04690164","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"PnMP_FF OKN70-2 K200F003 IP22/31 RAL9003","quantity":1,"productDefinition":"GHQ87429","material":"Steel","part":"04690164","fu":"QA-1.1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Steel","part":"04690164","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"50 FIXED HINGES WITH PIN","quantity":0.04,"productDefinition":"GHQ87142","material":"Miscellaneous","part":"04473519VA","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"200 DOOR STOPS","quantity":0.005,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"04473519VA","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"ROTARY HANDLE DOOR 8M","quantity":1,"productDefinition":"GHQ87747WP","material":"Miscellaneous","part":"04473519VA","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"ROTARY HANDLE DOOR 8M ASM.","quantity":1,"productDefinition":"GHQ87747WP","material":"Miscellaneous","part":"GHQ11258WP","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"ROTARY HANDLE DOOR 8M ASM.","quantity":1,"productDefinition":"GHQ87747WP","material":"Miscellaneous","part":"GHQ11258WP","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"H. PLATE PLUG-IN / DISCON POLYF.","quantity":1,"productDefinition":"GHQ87752","material":"Miscellaneous","part":"GHQ11258WP","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"L SUPPORT FOR 1/2.1D SEPERATOR","quantity":1,"productDefinition":"GHQ87752","material":"Steel","part":"04690338","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"LEFT SUPPORT","quantity":1,"productDefinition":"GHQ87752","material":"Steel","part":"AAV16142","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"HORIZONTAL SEPARATOR","quantity":1,"productDefinition":"GHQ87752","material":"Steel","part":"AAV16143","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"HORIZONTAL SEPARATOR","quantity":1,"productDefinition":"GHQ87752","material":"Steel","part":"AAV16143","fu":"QA-1.1"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"SELF CLINCHING CAPTIVE STUDS M6","quantity":1,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Protection device","efu":"NSX630B","description":"NSX630B 3P","quantity":1,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Trip unit","efu":"Micrologic 5.3A","description":"Trip unit Micrologic 5.3A","quantity":1,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Rotary handle","efu":"LV432598","description":"Rotary handle LV432598","quantity":1,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Terminal shield","efu":"LV432591","description":"Terminal shield LV432591","quantity":2,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Terminal shield","efu":"LV432591","description":"Terminal shield LV432591","quantity":2,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.2","$$treeLevel":0},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"PnMP_FP OKN70-2 STD/SLT K200F003 RC","quantity":1,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"POLYFAST RC DRAWER/PLUG-IN NS630 3P","quantity":1,"productDefinition":"GHQ11027","material":"Steel","part":"AAV16143","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"PH 1-3 NS630 REAR CONNECTOR","quantity":2,"productDefinition":"GHQ11027","material":"Copper","part":"04692000","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"PH 2-N NS630 REAR CONNECTOR","quantity":1,"productDefinition":"GHQ11027","material":"Copper","part":"04692001","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"PH 2-N NS630 REAR CONNECTOR","quantity":1,"productDefinition":"GHQ11027","material":"Copper","part":"04692001","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"CONNECTION SUPPORT 630A 3P","quantity":2,"productDefinition":"GHQ87265","material":"Copper","part":"04692001","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"8 M FIXED PA POLYFAST NS630 3P","quantity":1,"productDefinition":"GHQ87354","material":"Copper","part":"04692001","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"THRU  SUP NS630 3P WITH/OUT CT","quantity":1,"productDefinition":"GHQ87354","material":"Steel","part":"04690143","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"PARTITION REM/DISC NS630 3P 8MOD","quantity":1,"productDefinition":"GHQ87354","material":"Steel","part":"04690229","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"DSTREAM BLANK. PLATE NS630 3P 8MOD","quantity":1,"productDefinition":"GHQ87354","material":"Steel","part":"04690236","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"STRUCTURE FOR NS630 3P 8 MOD","quantity":1,"productDefinition":"GHQ87354","material":"Steel","part":"04690251","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"STRUCTURE FOR NS630 3P 8 MOD","quantity":1,"productDefinition":"GHQ87354","material":"Steel","part":"04690251","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"UPSTREAM JAWS 3P 630A","quantity":1,"productDefinition":"GHQ87366","material":"Steel","part":"04690251","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"DOWNSTREAM JAWS 3P 630A","quantity":1,"productDefinition":"GHQ87370","material":"Steel","part":"04690251","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"DCBRACKET RC 630A 3P  8M","quantity":1,"productDefinition":"GHQ87534","material":"Steel","part":"04690251","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"BUSHING SUPPORT RCO 8MOD","quantity":1,"productDefinition":"GHQ87534","material":"Steel","part":"04690164","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"BUSHING SUPPORT RCO 8MOD","quantity":1,"productDefinition":"GHQ87534","material":"Steel","part":"04690164","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP fixed part","efu":"EFU10DB0047","description":"BUSHING SUPPORT RCO 8MOD","quantity":1,"productDefinition":"GHQ87534","material":"Steel","part":"04690164","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP moving part","efu":"EFU_01PPMP_00016_WW","description":"PPMP Okken K200F003","quantity":1,"productDefinition":"GHQ87534","material":"Steel","part":"04690164","fu":"QA-1.2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"PnMP moving part","efu":"EFU_01PPMP_00016_WW","description":"NS400/630 3P DEVICE POLYFAST","quantity":1,"productDefinition":"GHQ87358","material":"Steel","part":"04690164","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP moving part","efu":"EFU_01PPMP_00016_WW","description":"POLYFAST ACC NS630 DISC AMM","quantity":1,"productDefinition":"GHQ87429","material":"Steel","part":"04690164","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"PnMP_FF OKN70-2 K200F003 IP22/31 RAL9003","quantity":1,"productDefinition":"GHQ87429","material":"Steel","part":"04690164","fu":"QA-1.2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Steel","part":"04690164","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"50 FIXED HINGES WITH PIN","quantity":0.04,"productDefinition":"GHQ87142","material":"Miscellaneous","part":"04473519VA","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"200 DOOR STOPS","quantity":0.005,"productDefinition":"GHQ87150","material":"Miscellaneous","part":"04473519VA","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"ROTARY HANDLE DOOR 8M","quantity":1,"productDefinition":"GHQ87747WP","material":"Miscellaneous","part":"04473519VA","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"ROTARY HANDLE DOOR 8M ASM.","quantity":1,"productDefinition":"GHQ87747WP","material":"Miscellaneous","part":"GHQ11258WP","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"ROTARY HANDLE DOOR 8M ASM.","quantity":1,"productDefinition":"GHQ87747WP","material":"Miscellaneous","part":"GHQ11258WP","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"H. PLATE PLUG-IN / DISCON POLYF.","quantity":1,"productDefinition":"GHQ87752","material":"Miscellaneous","part":"GHQ11258WP","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"L SUPPORT FOR 1/2.1D SEPERATOR","quantity":1,"productDefinition":"GHQ87752","material":"Steel","part":"04690338","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"LEFT SUPPORT","quantity":1,"productDefinition":"GHQ87752","material":"Steel","part":"AAV16142","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"HORIZONTAL SEPARATOR","quantity":1,"productDefinition":"GHQ87752","material":"Steel","part":"AAV16143","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"HORIZONTAL SEPARATOR","quantity":1,"productDefinition":"GHQ87752","material":"Steel","part":"AAV16143","fu":"QA-1.2"},


{"cubicle":"Cubicle CUB-C","type":"PnMP front face","efu":"EFU10DN0108-RAL9003","description":"SELF CLINCHING CAPTIVE STUDS M6","quantity":1,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Protection device","efu":"NSX630B","description":"NSX630B 3P","quantity":1,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Trip unit","efu":"Micrologic 5.3A","description":"Trip unit Micrologic 5.3A","quantity":1,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Rotary handle","efu":"LV432598","description":"Rotary handle LV432598","quantity":1,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Terminal shield","efu":"LV432591","description":"Terminal shield LV432591","quantity":2,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"QA-1.2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Terminal shield","efu":"LV432591","description":"Terminal shield LV432591","quantity":2,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"Reserve 1","$$treeLevel":0},


{"cubicle":"Cubicle CUB-C","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_348","description":"eFU_Space_cover_paneling_348","quantity":1,"productDefinition":"GHQ88653","material":"Steel","part":"AAV16143","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-C","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_348","description":"SPACE FPLATE FOR DRAWER 8M","quantity":7,"productDefinition":"GHQ87403WP","material":"Steel","part":"AAV16143","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-C","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_348","description":"RESERVE FRONT PLATE 200","quantity":1,"productDefinition":"GHQ87403WP","material":"Miscellaneous","part":"04690774WP","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-C","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_348","description":"RESERVE FRONT PLATE BRACKET","quantity":4,"productDefinition":"GHQ87403WP","material":"Steel","part":"GHQ80450","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-C","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_348","description":"RESERVE FRONT PLATE BRACKET","quantity":4,"productDefinition":"GHQ87403WP","material":"Steel","part":"GHQ80450","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-C","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_348","description":"RESERVE FRONT PLATE BRACKET","quantity":4,"productDefinition":"GHQ87403WP","material":"Steel","part":"GHQ80450","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-C","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_348","description":"RESERVE FRONT PLATE BRACKET","quantity":4,"productDefinition":"GHQ87403WP","material":"Steel","part":"GHQ80450","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-C","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_348","description":"RESERVE FRONT PLATE BRACKET","quantity":4,"productDefinition":"GHQ87403WP","material":"Steel","part":"GHQ80450","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_348","description":"RESERVE FRONT PLATE BRACKET","quantity":4,"productDefinition":"GHQ87403WP","material":"Steel","part":"GHQ80450","fu":"Reserve 1","$$treeLevel":0},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"INTP 70-M RC 2350 600 1000","quantity":1,"productDefinition":"GHQ87403WP","material":"Steel","part":"GHQ80450","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"SUPPORTS FOR LATCHING SYSTEM","quantity":12,"productDefinition":"LVM00026X0","material":"Steel","part":"GHQ80450","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"SUPPORT FOR LATCHING INTERCOLUMN PART.","quantity":1,"productDefinition":"LVM00026X0","material":"Steel","part":"LVM23041X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"INTER COLUMN PARTITION H2350 RC D1000","quantity":1,"productDefinition":"LVM00092X0","material":"Steel","part":"LVM23041X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"INTERCOLUMN PARTITION COMP D300 H2200","quantity":2,"productDefinition":"LVM00092X0","material":"Steel","part":"LVM23022XD","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"INTERCOLUMN PARTITION D600 H2200","quantity":2,"productDefinition":"LVM00092X0","material":"Steel","part":"LVM23023XB","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"INTERCOLUMN PARTITION D600 H2200","quantity":2,"productDefinition":"LVM00092X0","material":"Steel","part":"LVM23023XB","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"INTER COLUMN PARTITION COMP RC D1000","quantity":1,"productDefinition":"LVM00094X0","material":"Steel","part":"LVM23023XB","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"INTERCOLUMN PARTITION TOP COMP RC D1000","quantity":1,"productDefinition":"LVM00094X0","material":"Miscellaneous","part":"LVM23025X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"INTERCOLUMN PARTITION TOP COMP RC D1000","quantity":1,"productDefinition":"LVM00094X0","material":"Miscellaneous","part":"LVM23025X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"SUPPORT COMPLEMENT PARTITIONNEMENT 2","quantity":1,"productDefinition":"LVM00106X0","material":"Miscellaneous","part":"LVM23025X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"IA PARTITIONNING SUPPORT 1","quantity":1,"productDefinition":"LVM00106X0","material":"Miscellaneous","part":"LVM23089X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"IA PARTITIONNING SUPPORT 1","quantity":1,"productDefinition":"LVM00106X0","material":"Miscellaneous","part":"LVM23089X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Internal partitioning","efu":"EFU_01INTP_00009_WW","description":"IA PARTITIONNING SUPPORT 1","quantity":1,"productDefinition":"LVM00106X0","material":"Miscellaneous","part":"LVM23089X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"HRBB Copper 600 (3P+N)+PE;(3P+PEN) Single 4x40x10","quantity":1,"productDefinition":"LVM00106X0","material":"Miscellaneous","part":"LVM23089X0","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"1 HORIZ BB SUP ICW 100KA","quantity":3,"productDefinition":"GHQ87261","material":"Miscellaneous","part":"LVM23089X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"F2 PARTITIONNING FOR HBB W600","quantity":1,"productDefinition":"LVM00530X0","material":"Miscellaneous","part":"LVM23089X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"HORIZONTAL BUSBAR PLATE","quantity":1,"productDefinition":"LVM00530X0","material":"Steel","part":"LVM21120X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"HORIZONTAL BUSBAR SUPPORT RC","quantity":1,"productDefinition":"LVM00530X0","material":"Miscellaneous","part":"LVM21165X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"TOP HORIZONTAL BUSBAR SUPPORT RC","quantity":1,"productDefinition":"LVM00530X0","material":"Steel","part":"LVM21166X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"SC BUSBARS FRONT PARTITION P600 L600","quantity":2,"productDefinition":"LVM00530X0","material":"Steel","part":"LVM25300X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"SC BUSBARS FRONT PARTITION P600 L600","quantity":2,"productDefinition":"LVM00530X0","material":"Steel","part":"LVM25300X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"HBB 4B/PH L 600 4P","quantity":1,"productDefinition":"LVM99074X0","material":"Steel","part":"LVM25300X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"HBB W 600","quantity":16,"productDefinition":"LVM99074X0","material":"Copper","part":"LVM25008X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"HBB W 600","quantity":16,"productDefinition":"LVM99074X0","material":"Copper","part":"LVM25008X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HBB","efu":"EFU_01HRBB_01184_WW","description":"HBB W 600","quantity":16,"productDefinition":"LVM99074X0","material":"Copper","part":"LVM25008X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"PE PEN accessories","efu":"EFU_01PEAC_00010_WW","description":"PEAC 70-M RC 1x40x10","quantity":1,"productDefinition":"LVM99074X0","material":"Copper","part":"LVM25008X0","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"PE PEN accessories","efu":"EFU_01PEAC_00010_WW","description":"20 INSULATORS H35 M10","quantity":0.2,"productDefinition":"GHQ87643","material":"Copper","part":"LVM25008X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"CBRF 600 1000 RC Natural","quantity":1,"productDefinition":"GHQ87643","material":"Copper","part":"LVM25008X0","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"SEALING GASKET FOR JUNCTION ROOF","quantity":0.024,"productDefinition":"GHQ87029","material":"Copper","part":"LVM25008X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"50 METERS IP31 TOP PL GASKET","quantity":0.022,"productDefinition":"GHQ87046","material":"Copper","part":"LVM25008X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"200 WATER PROOF WASHERS  M6","quantity":0.05,"productDefinition":"GHQ87139","material":"Copper","part":"LVM25008X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"1000 SELF THR SCR/ M6X13","quantity":0.006,"productDefinition":"GHQ87182","material":"Copper","part":"LVM25008X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"SELF THR SCR / M6X13","quantity":1000,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"04690002","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"SELF THR SCR / M6X13","quantity":1000,"productDefinition":"GHQ87182","material":"Miscellaneous","part":"04690002","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"UPPER BAND L600","quantity":1,"productDefinition":"GHQ88667","material":"Miscellaneous","part":"04690002","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"ROOF W600 D1000","quantity":1,"productDefinition":"LVM00051X0","material":"Miscellaneous","part":"04690002","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"RC FRONT ROOF","quantity":1,"productDefinition":"LVM00051X0","material":"Steel","part":"LVM23050X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"RC MIDDLE ROOF","quantity":1,"productDefinition":"LVM00051X0","material":"Steel","part":"LVM23051X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"RC D1000 BACK ROOF","quantity":1,"productDefinition":"LVM00051X0","material":"Steel","part":"LVM23052XA","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"RC D1000 BACK ROOF","quantity":1,"productDefinition":"LVM00051X0","material":"Steel","part":"LVM23052XA","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Roof","efu":"EFU_01CBRF_00053_WW","description":"RC D1000 BACK ROOF","quantity":1,"productDefinition":"LVM00051X0","material":"Steel","part":"LVM23052XA","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Bottom plate","efu":"EFU_01BTPL_00027_WW","description":"BTPL Okken 600 1000","quantity":1,"productDefinition":"LVM00051X0","material":"Steel","part":"LVM23052XA","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Bottom plate","efu":"EFU_01BTPL_00027_WW","description":"BOTTOM PLATE D1000 W600","quantity":1,"productDefinition":"LVM00036X0","material":"Steel","part":"LVM23052XA","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Bottom plate","efu":"EFU_01BTPL_00027_WW","description":"MAIN BOTTOM PLATE","quantity":1,"productDefinition":"LVM00036X0","material":"Aluminum","part":"LVM23030XA","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Bottom plate","efu":"EFU_01BTPL_00027_WW","description":"BACK BOTTOM PLATE","quantity":1,"productDefinition":"LVM00036X0","material":"Miscellaneous","part":"LVM23031X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Bottom plate","efu":"EFU_01BTPL_00027_WW","description":"RC BACK BOTTOM PLATE 1","quantity":1,"productDefinition":"LVM00036X0","material":"Aluminum","part":"S1B27481","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Bottom plate","efu":"EFU_01BTPL_00027_WW","description":"RC BACK BOTTOM PLATE 2","quantity":1,"productDefinition":"LVM00036X0","material":"Aluminum","part":"S1B27482","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Bottom plate","efu":"EFU_01BTPL_00027_WW","description":"RC BACK BOTTOM PLATE 2","quantity":1,"productDefinition":"LVM00036X0","material":"Aluminum","part":"S1B27482","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Bottom plate","efu":"EFU_01BTPL_00027_WW","description":"RC BACK BOTTOM PLATE 2","quantity":1,"productDefinition":"LVM00036X0","material":"Aluminum","part":"S1B27482","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"FRME H2350 W600 D1000 None","quantity":1,"productDefinition":"LVM00036X0","material":"Aluminum","part":"S1B27482","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"FIX PART POSITIONNING PIN","quantity":0.15,"productDefinition":"GHQ88669","material":"Aluminum","part":"S1B27482","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"SEPARATION PLATE POSITIONING AXIS","quantity":1,"productDefinition":"GHQ88669","material":"Steel","part":"LVM21016X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"SEPARATION PLATE POSITIONING AXIS","quantity":1,"productDefinition":"GHQ88669","material":"Steel","part":"LVM21016X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"MAIN FRAME D1000 H2350 W600","quantity":1,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM21016X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"UPRIGHT H2350 FLBR","quantity":2,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23002X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"UPRIGHT H2350 FRBL","quantity":2,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23003X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"INTERMEDIARY UPRIGHT RC H2350","quantity":2,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23005X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"CROSS MEMBER W600","quantity":5,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23006X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"CROSS MEMBER D1000","quantity":8,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23012X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"TRIPOD TOP FRONT LEFT","quantity":4,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23015X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"TRIPOD TOP FRONT RIGHT","quantity":4,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23016X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"LARGE SQUARE","quantity":6,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23017X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"SMALL SQUARE","quantity":8,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23018X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"GROUND SQUARE","quantity":4,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23019X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"GROUND SQUARE","quantity":4,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23019X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Frame","efu":"EFU_01FRME_00176_WW","description":"GROUND SQUARE","quantity":4,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23019X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Rear panelling","efu":"EFU_01RPNL_00323_WW","description":"RPNL CUB_G_D81L_ IP31","quantity":1,"productDefinition":"LVM00014X0","material":"Steel","part":"LVM23019X0","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Rear panelling","efu":"EFU_01RPNL_00323_WW","description":"LHS LOCK COMP DOOR H2350W600","quantity":1,"productDefinition":"LVM00022X0WP","material":"Steel","part":"LVM23019X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Rear panelling","efu":"EFU_01RPNL_00323_WW","description":"LHS LOCK COMP DOOR H2350W600P","quantity":1,"productDefinition":"LVM00022X0WP","material":"Miscellaneous","part":"LVM23101X0WP","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Rear panelling","efu":"EFU_01RPNL_00323_WW","description":"LHS LOCK COMP DOOR H2350W600P","quantity":1,"productDefinition":"LVM00022X0WP","material":"Miscellaneous","part":"LVM23101X0WP","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Rear panelling","efu":"EFU_01RPNL_00323_WW","description":"LHS LOCK COMP DOOR H2350W600P","quantity":1,"productDefinition":"LVM00022X0WP","material":"Miscellaneous","part":"LVM23101X0WP","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"AWA_Mandatory_code_designation_22","quantity":1,"productDefinition":"LVM00022X0WP","material":"Miscellaneous","part":"LVM23101X0WP","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"RC CABLE TIE SUPPORT D400","quantity":1,"productDefinition":"LVM00038X0","material":"Miscellaneous","part":"LVM23101X0WP","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"CABLE TIE BAR D400","quantity":4,"productDefinition":"LVM00038X0","material":"Steel","part":"LVM23061X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"CABLE TIE BAR D400","quantity":4,"productDefinition":"LVM00038X0","material":"Steel","part":"LVM23061X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"CABLING PATH HBB4P/VBB3P H2350 D1000","quantity":1,"productDefinition":"LVM05090X0","material":"Steel","part":"LVM23061X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"CABLE DUCT SUPPORT","quantity":1,"productDefinition":"LVM05090X0","material":"Steel","part":"LVM25277X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"CABLING SUPPORT HORIZONTAL CROSS MEMBER","quantity":5,"productDefinition":"LVM05090X0","material":"Steel","part":"LVM25403X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"RC CROSS-MEMBER D.1000","quantity":5,"productDefinition":"LVM05090X0","material":"Steel","part":"LVM25404X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"VERTICAL SUPPORT CROSSBEAM TERMINAL BLOC","quantity":1,"productDefinition":"LVM05090X0","material":"Steel","part":"LVM25406X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"VERTICAL SUPPORT AUX HBB4P / VBB3P 2350","quantity":1,"productDefinition":"LVM05090X0","material":"Steel","part":"LVM25600X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"VERTICAL SUPPORT AUX HBB4P / VBB3P 2350","quantity":1,"productDefinition":"LVM05090X0","material":"Steel","part":"LVM25600X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA mandatory","efu":"AWA_Mandatory_ref_22","description":"VERTICAL SUPPORT AUX HBB4P / VBB3P 2350","quantity":1,"productDefinition":"LVM05090X0","material":"Steel","part":"LVM25600X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"AWA_Optional_code_designation_12","quantity":1,"productDefinition":"LVM05090X0","material":"Steel","part":"LVM25600X0","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"LATTICE TRUNKING / AUX WIRING","quantity":1,"productDefinition":"GHQ87178","material":"Steel","part":"LVM25600X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"WIRE TRUNKING","quantity":1,"productDefinition":"GHQ87178","material":"Miscellaneous","part":"04690401","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"WIRE TRUNKING","quantity":1,"productDefinition":"GHQ87178","material":"Miscellaneous","part":"04690401","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"5AUX BUS DUCT 4X32A H2350/2200","quantity":0.2,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04690401","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"LIMIT END","quantity":10,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04690374","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"COLECTOR AMP","quantity":5,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04690383","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"FIXING ANGLE COLLECTOR","quantity":10,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04691043","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"FIXING ANGLE COLLECTOR","quantity":10,"productDefinition":"GHQ87187","material":"Miscellaneous","part":"04691043","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"BUSDUCTS SUPPORT","quantity":2,"productDefinition":"LVM05060X0","material":"Miscellaneous","part":"04691043","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"ISOSPEED SUPPORT","quantity":1,"productDefinition":"LVM05060X0","material":"Steel","part":"LVM25443X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"ISOSPEED SUPPORT","quantity":1,"productDefinition":"LVM05060X0","material":"Steel","part":"LVM25443X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"AWA optional","efu":"AWA_Optional_ref_12","description":"ISOSPEED SUPPORT","quantity":1,"productDefinition":"LVM05060X0","material":"Steel","part":"LVM25443X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"Lifting lugs","efu":"EFU_01LFTL_00002_WW","description":"LFTL Okken 70-M","quantity":1,"productDefinition":"LVM05060X0","material":"Steel","part":"LVM25443X0","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Lifting lugs","efu":"EFU_01LFTL_00002_WW","description":"4 LIFTING LUGS","quantity":1,"productDefinition":"GHQ88694","material":"Steel","part":"LVM25443X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"HVLK Copper HBB (3P+N)+PE Single VBB (3P)+N+PE;(3P)+PE Single","quantity":1,"productDefinition":"GHQ88694","material":"Steel","part":"LVM25443X0","fu":"Reserve 1","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"100 ANTI ROT BRACK","quantity":0.03,"productDefinition":"GHQ87188","material":"Steel","part":"LVM25443X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"100 FLAT WASHERS DIA 11/38","quantity":0.03,"productDefinition":"GHQ87189","material":"Steel","part":"LVM25443X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"FIBRE REINFORCED THREADED ROD M8 L=950","quantity":0.0521052631578947,"productDefinition":"GHQ88624","material":"Steel","part":"LVM25443X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"INSULATING BRACE L44","quantity":1,"productDefinition":"GHQ88627","material":"Steel","part":"LVM25443X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"HBB/VBB LINK CASING H2350 3P","quantity":1,"productDefinition":"LVM00589X0","material":"Steel","part":"LVM25443X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"BULKHEAD CONNECTION ENV. JDB 3P-H2350","quantity":1,"productDefinition":"LVM00589X0","material":"Steel","part":"LVM25143X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"BACK PARTITION F2 2350 4P/3P","quantity":1,"productDefinition":"LVM00589X0","material":"Steel","part":"LVM25601X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"FORWARD PARTITION 4P/3P 2350","quantity":1,"productDefinition":"LVM00589X0","material":"Steel","part":"LVM25611X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"COVER F2 4P/3P 2350","quantity":1,"productDefinition":"LVM00589X0","material":"Steel","part":"LVM25612X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"COVER F2 4P/3P 2350","quantity":1,"productDefinition":"LVM00589X0","material":"Steel","part":"LVM25612X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"INSULATING PLATE HBB 4P/VBB 3P 20X8","quantity":1,"productDefinition":"LVM00596X0","material":"Steel","part":"LVM25612X0","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"INSULATING PLATE HBB 4P/VBB 3P 20X8","quantity":1,"productDefinition":"LVM00596X0","material":"Plastic & Insulation","part":"LVM25604XC","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"INSULATING PLATE HBB 4P/VBB 3P 20X8","quantity":1,"productDefinition":"LVM00596X0","material":"Plastic & Insulation","part":"LVM25604XC","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"LINK HBB 4P / SINGLE VBB 3P 20X8","quantity":1,"productDefinition":"LVM99363X0","material":"Plastic & Insulation","part":"LVM25604XC","fu":"Reserve 1","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"HORIZONTAL CONNECTION 20X8 4P/3P","quantity":3,"productDefinition":"LVM99363X0","material":"Copper","part":"LVM25619X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"VERTICAL LINK P1 4P/3P","quantity":1,"productDefinition":"LVM99363X0","material":"Copper","part":"LVM25620X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"VERTICAL LINK P2 4P/3P","quantity":1,"productDefinition":"LVM99363X0","material":"Copper","part":"LVM25621X0","fu":"Reserve 1"},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"VERTICAL LINK P3 LEFT 4P/3P","quantity":1,"productDefinition":"LVM99363X0","material":"Copper","part":"LVM25622X0","fu":"Reserve 1"},


/*{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"VERTICAL / HORIZONTAL LINK 4P/3P","quantity":3,"productDefinition":"LVM99363X0","material":"Copper","part":"LVM25624X0","fu":"Reserve 1","$$treeLevel":0},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"VERTICAL / HORIZONTAL LINK 4P/3P","quantity":3,"productDefinition":"LVM99363X0","material":"Copper","part":"LVM25624X0","fu":"Reserve 1","$$treeLevel":0},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"VERTICAL / HORIZONTAL LINK 4P/3P","quantity":3,"productDefinition":"LVM99363X0","material":"Copper","part":"LVM25624X0","fu":"Reserve 1","$$treeLevel":0},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"VERTICAL / HORIZONTAL LINK 4P/3P","quantity":3,"productDefinition":"LVM99363X0","material":"Copper","part":"LVM25624X0","fu":"Reserve 1","$$treeLevel":0},


{"cubicle":"Cubicle CUB-D","type":"HVL","efu":"EFU_01HVLK_00051_WW","description":"VERTICAL / HORIZONTAL LINK 4P/3P","quantity":3,"productDefinition":"LVM99363X0","material":"Copper","part":"LVM25624X0","fu":"QA-5","$$treeLevel":0},*/


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"DWFP Okken C200Fxxx A_to_H","quantity":1,"productDefinition":"LVM99363X0","material":"Copper","part":"LVM25624X0","fu":"QA-5","$$treeLevel":1},


/*{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"OUTGOING BLOCK S-M","quantity":1,"productDefinition":"GHQ88606","material":"Copper","part":"LVM25624X0","fu":"QA-5","$$treeLevel":2},*/


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"RC OUTGOING PAD S/M","quantity":3,"productDefinition":"GHQ88644","material":"Copper","part":"LVM25624X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"SM DRAWER CONNECTION RC","quantity":1,"productDefinition":"GHQ88644","material":"Copper","part":"LVM21010XA","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"SM DRAWER CONNECTION RC","quantity":1,"productDefinition":"GHQ88644","material":"Copper","part":"LVM21010XA","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"BEARING ANTI-TIP","quantity":4,"productDefinition":"GHQ88660","material":"Copper","part":"LVM21010XA","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"DRAWER PROOF-FALLING","quantity":1,"productDefinition":"GHQ88660","material":"Steel","part":"LVM21004X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"DRAWER PROOF-FALLING","quantity":1,"productDefinition":"GHQ88660","material":"Steel","part":"LVM21004X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"DRAWER INDEXING MECHANISM STOP","quantity":2,"productDefinition":"GHQ88680","material":"Steel","part":"LVM21004X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"DRAWER POSITIONNING","quantity":1,"productDefinition":"GHQ88683","material":"Steel","part":"LVM21004X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"FP DRAWER C200F S/M PLUG","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM21004X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"NETWORK CABLE PARTITION FOR RC/SC","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"HRB85747","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"SEPARATOR PLUGS  TYPE S/M","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31000X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"DRAWER H=100 REINFORCEMENT SEPARATOR","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31001X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"CABLE DUCT DRAWER H=100","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31004X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"CABLE DUCT DRAWER H=200","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31066X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"DRAWER H=100  COVER DUCT","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31070XA","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"DRAWER H=100  COVER DUCT","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31070XB","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"H=200 DRAWER SEPARATOR","quantity":2,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31233X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"PROTECTION FOR CABLE ON CABLE DUCT","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31235X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"PROTECTION FOR CABLE ON CABLE DUCT","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31235X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer fixed part","efu":"EFU_01DWFP_00309_WW","description":"PROTECTION FOR CABLE ON CABLE DUCT","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31235X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DWMP Okken C200F011","quantity":1,"productDefinition":"LVM02004X0","material":"Steel","part":"LVM31235X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"S JAW BLOCK 3P","quantity":1,"productDefinition":"GHQ88600","material":"Steel","part":"LVM31235X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER SKATE","quantity":1,"productDefinition":"GHQ88663","material":"Steel","part":"LVM31235X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER SKATE","quantity":1,"productDefinition":"GHQ88663","material":"Steel","part":"LVM21005X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER SKATE","quantity":1,"productDefinition":"GHQ88663","material":"Steel","part":"LVM21005X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"100 CODING PINS","quantity":0.08,"productDefinition":"GHQ88668","material":"Steel","part":"LVM21005X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"MISMATCH-PROOF WASHER","quantity":100,"productDefinition":"GHQ88668","material":"Steel","part":"LVM21007X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"MISMATCH-PROOF WASHER","quantity":100,"productDefinition":"GHQ88668","material":"Steel","part":"LVM21007X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER WHEEL","quantity":3,"productDefinition":"GHQ88674","material":"Steel","part":"LVM21007X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER WHEEL","quantity":1,"productDefinition":"GHQ88674","material":"Steel","part":"LVM21013X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER WHEEL AXIS","quantity":1,"productDefinition":"GHQ88674","material":"Steel","part":"LVM21014X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER WHEEL FRAME","quantity":1,"productDefinition":"GHQ88674","material":"Steel","part":"LVM21015X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER WHEEL FRAME","quantity":1,"productDefinition":"GHQ88674","material":"Steel","part":"LVM21015X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER MECH. INDEXING SPRING","quantity":1,"productDefinition":"GHQ88677","material":"Steel","part":"LVM21015X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER MECH. INDEXING PIN AXIS","quantity":1,"productDefinition":"GHQ88678","material":"Steel","part":"LVM21015X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER INDEXING MECHANISM AXIS","quantity":1,"productDefinition":"GHQ88678","material":"Steel","part":"LVM21006X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER INDEXING MECHANISM AXIS","quantity":1,"productDefinition":"GHQ88678","material":"Steel","part":"LVM21006X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"INTERLOCKING SPRING","quantity":1,"productDefinition":"GHQ88679","material":"Steel","part":"LVM21006X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"HANDLE FOR GV2 DRAWER","quantity":2,"productDefinition":"GHQ88681","material":"Steel","part":"LVM21006X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"STRAIGHT DRAWER HANDLE GV2","quantity":1,"productDefinition":"GHQ88681","material":"Steel","part":"LVM21012XB","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"STRAIGHT DRAWER HANDLE GV2","quantity":1,"productDefinition":"GHQ88681","material":"Steel","part":"LVM21012XB","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"MP DRAWER C200F/C300F S/M PLUG","quantity":1,"productDefinition":"LVM02053X0","material":"Steel","part":"LVM21012XB","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"H=200 DRAWER","quantity":1,"productDefinition":"LVM02053X0","material":"Miscellaneous","part":"LVM31063X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"H=100 SUPPORT CONTROL PLUG","quantity":1,"productDefinition":"LVM02053X0","material":"Steel","part":"LVM31068X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"H=100 SUPPORT CONTROL PLUG","quantity":1,"productDefinition":"LVM02053X0","material":"Steel","part":"LVM31068X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"PLATES C200F GV2L LC1D38 TESYST","quantity":1,"productDefinition":"LVM02119X0","material":"Steel","part":"LVM31068X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER H=100-200/2 PAINTED PLATE","quantity":1,"productDefinition":"LVM02119X0","material":"Steel","part":"LVM31059X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"DRAWER H=200 MOUNTING PLATE GV2/GV3/GS2F","quantity":1,"productDefinition":"LVM02119X0","material":"Miscellaneous","part":"LVM31197X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"H=200 SUPPORT FOR AT","quantity":1,"productDefinition":"LVM02119X0","material":"Steel","part":"LVM31268X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"H=200 FIXATION FOR HANDLE","quantity":2,"productDefinition":"LVM02119X0","material":"Miscellaneous","part":"LVM31271X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"H=200 FIXATION FOR HANDLE","quantity":2,"productDefinition":"LVM02119X0","material":"Miscellaneous","part":"LVM31271X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"KIT INDEXING MECHANISM","quantity":1,"productDefinition":"LVM02902X0","material":"Miscellaneous","part":"LVM31271X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer moving part","efu":"EFU_01DWMP_00258_WW","description":"KIT LOCKING MECHANISM GV2 GV3 TESYS U","quantity":1,"productDefinition":"LVM02903X0","material":"Miscellaneous","part":"LVM31271X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"DWFB Okken C200Fxxx RC","quantity":1,"productDefinition":"LVM02903X0","material":"Miscellaneous","part":"LVM31271X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC NETWORK FOR MP FULL WIDTH (GV)","quantity":1,"productDefinition":"LVM05074X0","material":"Miscellaneous","part":"LVM31271X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"SECONDARY BUS CABLE DUCT 55>250KW","quantity":1,"productDefinition":"LVM05074X0","material":"Steel","part":"LVM21230X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC MAIN BUS CABLE DUCT 0>55KW","quantity":1,"productDefinition":"LVM05074X0","material":"Steel","part":"LVM25293X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC MAIN BUS CABLE DUCT 0>55KW","quantity":1,"productDefinition":"LVM05074X0","material":"Steel","part":"LVM25293X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC AUXILIARY OUTPUT MP FULL WIDTH GV","quantity":1,"productDefinition":"LVM05077X0","material":"Steel","part":"LVM25293X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC SECONDARY CABLE DUCT 55>250KW","quantity":1,"productDefinition":"LVM05077X0","material":"Miscellaneous","part":"LVM21231X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC MAIN CABLE DUCT 0>55KW","quantity":1,"productDefinition":"LVM05077X0","material":"Steel","part":"LVM25290X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC MAIN CABLE DUCT 0>55KW","quantity":1,"productDefinition":"LVM05077X0","material":"Steel","part":"LVM25290X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC F3 DEPTH 1000","quantity":1,"productDefinition":"LVM05084X0","material":"Steel","part":"LVM25290X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC F3 SIDE PARTITION D:1000 / H:100","quantity":1,"productDefinition":"LVM05084X0","material":"Steel","part":"LVM25440X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC F3 SIDE PARTITION D:1000 / H:100","quantity":1,"productDefinition":"LVM05084X0","material":"Steel","part":"LVM25440X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer form box","efu":"EFU_01DWFB_00050_WW","description":"RC F3 SIDE PARTITION D:1000 / H:100","quantity":1,"productDefinition":"LVM05084X0","material":"Steel","part":"LVM25440X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"DWFF Okken C200F_FF_001 RAL9003","quantity":1,"productDefinition":"LVM05084X0","material":"Steel","part":"LVM25440X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"GE EARTH CONNECTION 6MM2","quantity":1,"productDefinition":"GHQ07081A","material":"Steel","part":"LVM25440X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"EARTHING BRAID","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04470600VA","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"SACHET PE130 X 250 X 80","quantity":1,"productDefinition":"GHQ07081A","material":"Miscellaneous","part":"04473519VA","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"DRAWER INDEX PUSH BUTTON 70-M","quantity":1,"productDefinition":"GHQ88629","material":"Miscellaneous","part":"04473519VA","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"SELF CLINCHING CAPTIVE STUDS M6","quantity":1,"productDefinition":"GHQ88653","material":"Miscellaneous","part":"04473519VA","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"DRAWER HANDLE 8M & MORE","quantity":2,"productDefinition":"GHQ88659","material":"Miscellaneous","part":"04473519VA","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"DRAWER FRONT PLATE AXIS","quantity":2,"productDefinition":"GHQ88672","material":"Miscellaneous","part":"04473519VA","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"PIN FOR DOOR DRAWER 3M1/2W-3MFW-6M1/2W","quantity":1,"productDefinition":"GHQ88672","material":"Miscellaneous","part":"AAV31138","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"PIN FOR DOOR DRAWER 3M1/2W-3MFW-6M1/2W","quantity":1,"productDefinition":"GHQ88672","material":"Miscellaneous","part":"AAV31138","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"FRONT PLATE DRAWER LOCK","quantity":1,"productDefinition":"GHQ88673","material":"Miscellaneous","part":"AAV31138","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"FRONT FACE C200F GV2/GV3","quantity":1,"productDefinition":"LVM04320X0WP","material":"Miscellaneous","part":"AAV31138","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"FRONT FACE H200 GV2/GV3 HOLE D22","quantity":1,"productDefinition":"LVM04320X0WP","material":"Miscellaneous","part":"LVM20018XFWP","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"H=200/2 - H=200 LEFT SUPPORT FOR DOOR","quantity":1,"productDefinition":"LVM04320X0WP","material":"Miscellaneous","part":"LVM22503X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"H=200/2 - H=200 RIGHT SUPPORT FOR DOOR","quantity":1,"productDefinition":"LVM04320X0WP","material":"Miscellaneous","part":"LVM22504X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"DRAWER H=200 FULL COVER PLATE - NSX250","quantity":1,"productDefinition":"LVM04320X0WP","material":"Steel","part":"LVM32506X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"DRAWER H=200 FULL COVER PLATE - NSX250","quantity":1,"productDefinition":"LVM04320X0WP","material":"Steel","part":"LVM32506X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"Drawer front face","efu":"EFU_01DWFF_00545_WW","description":"DRAWER H=200 FULL COVER PLATE - NSX250","quantity":1,"productDefinition":"LVM04320X0WP","material":"Steel","part":"LVM32506X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"FP auxiliaries","efu":"EFU_01ABFP_00021_WW","description":"ABFP 70-M C200F B","quantity":1,"productDefinition":"LVM04320X0WP","material":"Steel","part":"LVM32506X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"FP auxiliaries","efu":"EFU_01ABFP_00021_WW","description":"AUXILIARIES SINGLE GUIDING RAMP","quantity":1,"productDefinition":"GHQ88631","material":"Steel","part":"LVM32506X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"FP auxiliaries","efu":"EFU_01ABFP_00021_WW","description":"AUXILIARIES SINGLE GUIDING RAMP","quantity":1,"productDefinition":"GHQ88631","material":"Steel","part":"LVM21000X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"FP auxiliaries","efu":"EFU_01ABFP_00021_WW","description":"AUXILIARIES SINGLE GUIDING RAMP","quantity":1,"productDefinition":"GHQ88631","material":"Steel","part":"LVM21000X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"FP auxiliaries","efu":"EFU_01ABFP_00021_WW","description":"MALE AUXILIARY CONNECTOR 24P","quantity":1,"productDefinition":"GHQ88646","material":"Steel","part":"LVM21000X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"FP auxiliaries","efu":"EFU_01ABFP_00021_WW","description":"2000 MALE CONTACTS","quantity":0.012,"productDefinition":"GHQ88648","material":"Steel","part":"LVM21000X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"MP auxiliaries","efu":"EFU_01ABMP_00021_WW","description":"ABMP 70-M C200F B","quantity":1,"productDefinition":"GHQ88648","material":"Steel","part":"LVM21000X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"MP auxiliaries","efu":"EFU_01ABMP_00021_WW","description":"FEMALE AUXILIARY CONNECTOR 24P","quantity":1,"productDefinition":"GHQ88647","material":"Steel","part":"LVM21000X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"MP auxiliaries","efu":"EFU_01ABMP_00021_WW","description":"2000 FEMALE CONTACTS","quantity":0.012,"productDefinition":"GHQ88649","material":"Steel","part":"LVM21000X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"MP auxiliaries","efu":"EFU_01ABMP_00021_WW","description":"CONTROL PLUG SUPPORT PLUG S/M","quantity":1,"productDefinition":"LVM02215X0","material":"Steel","part":"LVM21000X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"MP auxiliaries","efu":"EFU_01ABMP_00021_WW","description":"SUPPORT FOR CONTROL PLUGS","quantity":1,"productDefinition":"LVM02215X0","material":"Steel","part":"LVM31217X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"MP auxiliaries","efu":"EFU_01ABMP_00021_WW","description":"SUPPORT FOR CONTROL PLUGS","quantity":1,"productDefinition":"LVM02215X0","material":"Steel","part":"LVM31217X0","fu":"QA-5"},


{"cubicle":"Cubicle CUB-D","type":"MP auxiliaries","efu":"EFU_01ABMP_00021_WW","description":"KIT AUXILIARY CONTROL PLUG MECHANISM","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Protection device","efu":"GV2L32","description":"GV2L32 3P","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Contactor","efu":"LC1D38","description":"Contactor LC1D38","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Motor protection relay","efu":"LTMR100","description":"Motor protection relay LTMR100","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Rotary handle","efu":"GV2APK01","description":"Rotary handle GV2APK01","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Auxiliary transformer","efu":"ABL6TS06","description":"Auxiliary Transformer ABL6TS06","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Relay extension","efu":"LTMEV40**","description":"Relay extension LTMEV40**","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Relay extension chord","efu":"LTMCC004","description":"Relay extension chord LTMCC004","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"HMI","efu":"LTMCU","description":"HMI LTMCU","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Ammeter","efu":"Ammeter","description":"Ammeter","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Lamp-pushbutton","efu":"Lamp-pushbutton","description":"Lamp-pushbutton","quantity":3,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"DIN rail","efu":"DIN rail","description":"DIN rail (mm)","quantity":150,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"QA-5","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"DIN rail","efu":"DIN rail","description":"DIN rail (mm)","quantity":150,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"Reserve 2","$$treeLevel":0},


{"cubicle":"Cubicle CUB-D","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_89","description":"eFU_Space_cover_paneling_89","quantity":1,"productDefinition":"LVM02901X0","material":"Steel","part":"LVM31217X0","fu":"Reserve 2","$$treeLevel":1},


{"cubicle":"Cubicle CUB-D","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_89","description":"FRONT PLATE DRAWER L 100 H","quantity":13,"productDefinition":"LVM04702X0WP","material":"Steel","part":"LVM31217X0","fu":"Reserve 2","$$treeLevel":2},


{"cubicle":"Cubicle CUB-D","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_89","description":"FRONT PLATE 100 HEIGHT","quantity":1,"productDefinition":"LVM04702X0WP","material":"Miscellaneous","part":"LVM20502X0WP","fu":"Reserve 2"},


{"cubicle":"Cubicle CUB-D","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_89","description":"IP CROSS MEMBER 600 WIDTH","quantity":1,"productDefinition":"LVM04702X0WP","material":"Steel","part":"LVM21029X0","fu":"Reserve 2"},


{"cubicle":"Cubicle CUB-D","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_89","description":"IP CROSS MEMBER 600 WIDTH","quantity":1,"productDefinition":"LVM04702X0WP","material":"Steel","part":"LVM21029X0","fu":"Reserve 2"},


{"cubicle":"Cubicle CUB-D","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_89","description":"IP CROSS MEMBER 600 WIDTH","quantity":1,"productDefinition":"LVM04702X0WP","material":"Steel","part":"LVM21029X0","fu":"Reserve 2"},


{"cubicle":"Cubicle CUB-D","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_89","description":"IP CROSS MEMBER 600 WIDTH","quantity":1,"productDefinition":"LVM04702X0WP","material":"Steel","part":"LVM21029X0","fu":"Reserve 2"},


{"cubicle":"Cubicle CUB-D","type":"Space cover panelling","efu":"eFU_Space_cover_paneling_89","description":"IP CROSS MEMBER 600 WIDTH","quantity":1,"productDefinition":"LVM04702X0WP","material":"Steel","part":"LVM21029X0","fu":"Reserve 2"}]


}
