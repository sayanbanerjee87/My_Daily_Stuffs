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


{"cubicle":"Cubicle CUB-A","type":"Internal partitioning","efu":"EFU_01INTP_00043_WW","description":"RIGHT BARRIER 2350 & 2200 HIGH","quantity":1,"productDefinition":"GHQ87300","material":"Steel","part":"04690478"}

]};
