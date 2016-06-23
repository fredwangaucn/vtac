<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<html>
<head>

<title></title>

    <link href="resources/css/kendo.common.min.css" rel="stylesheet" />
    <link href="resources/css/kendo.default.min.css" rel="stylesheet" />
    <link href="resources/css/kendo.dataviz.min.css" rel="stylesheet" />
    <link href="resources/css/kendo.dataviz.default.min.css" rel="stylesheet" />
    <link href="resources/css/kendo.rtl.min.css" rel="stylesheet" />

	<!--script src="resources/js/jquery-1.9.1.min.js"></script-->

    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/kendo.all.min.js"></script>
</head>
<body>
	
	<div id="mainContent" >
 
			<h2>Classes V1</h2>
            <div id="classesListGrid" style="width:750"></div>

			<h2>Students V1</h2>
            <div id="studentsListGrid" style="width:780"></div>
            
	
	<script type="text/javascript">

	var theFirstClassId=0;
	<c:if  test="${!empty classes.theFirstClassId}">
		theFirstClassId=${classes.theFirstClassId};
	</c:if>
	
    var allClasses = [
   	<c:if  test="${!empty classes.classList}">	
   		{	"value": 0,
   			"text": "Select"
   		},
		<c:forEach items="${classes.classList}" var="classObj">
		{
	        "value": ${classObj.id},
	        "text": "${classObj.name}"
	    },
		</c:forEach>
    </c:if>	
    ];

                 
	$(document).ready(function () {
	$("#classesListGrid").kendoGrid({
	    dataSource : {
        	transport: {
				read: {
					url: "/vtac/api/ajax/fetchClasses.html",
					dataType: "json",
					type: "POST"
				}, 
				update: {
                    url: "/vtac/api/ajax/classUpdate.html",
                    dataType: "json",
                    type: "POST"
                },
                destroy: {
                    url: "/vtac/api/ajax/classDestroy.html",
                    dataType: "json",
                    type: "POST"
                },
                create: {
                    url: "/vtac/api/ajax/classCreate.html",
                    dataType: "json",
                    type: "POST"
                },
                
                batch: true, 
                pageSize: 20,

                parameterMap: function(options, operation) {
                	 if (operation == "read"){
                		 return {
 							pageNumber : '1'
 						};
                	 }
                     if (operation !== "read" && operation != "create" && options ) { //&& options.models
                    	 //alert("options:"+options+"	"+kendo.stringify(options));
                         //return {options: kendo.stringify(options)};
                         return {
                        	 id : options.id,
                        	 name : options.name,
                        	 location : options.location,
                        	 teacher : options.teacher
                         };
                     }
                     if (operation == "create"){
                		 return {
                        	 name : options.name,
                        	 location : options.location,
                        	 teacher : options.teacher
 						};
                	 }                     
                 }
                 
			},
			schema: {
                model: {
                    id: "id",
                    fields: {
                    	id: { editable: false, nullable: true },
                        name: { validation: { required: true } },
                        location: { validation: { required: true } },
                        teacher: { validation: { required: true } }
                    }
                },
				data: function(response) {
					return response.classList;
				}
            }
		},
        groupable: false,
        selectable: true,
        sortable: true,
        filterable : {
            messages : {
                info : "Filter",
                filter : "Filter",
                clear : "Clear",
                attributes: { tabindex: "999999" }
            },
            extra : false,
            operators : {
                string : {
                    startswith : "Starts with",
                    eq : "Is equal to",
                    neq : "Is not equal to"
                }
            }
        },
        pageable: false,
        scrollable: false,
        change: onChange,
        columns: [
   			{ 
   				title : 'ID', 
   				field: 'id', 
   				width: 70
   			},
			{ 
				title : 'Name', 
				field: 'name', 
				width: 150
			},
			{ 
				title : 'Location', 
				field: 'location', 
				width: 200
			},
			{ 
				title : 'Teacher', 
				field: 'teacher', 
				width: 150
			},
			{ command: ["edit", "destroy"], title: "Action", width: "180px" }
 		],
        editable: "popup",
        toolbar: ["create"]
    });
	
	/////////////////////////////////////////////
	
	//initStudentsList(theFirstClassId);
	
		$("#studentsListGrid").kendoGrid({
        dataSource: dataSrc,

		groupable: false,
	    selectable: false,
	    sortable: true,
	    filterable : {
	        messages : {
	            info : "Filter",
	            filter : "Filter",
	            clear : "Clear",
	            attributes: { tabindex: "999999" }
	        },
	        extra : false,
	        operators : {
	            string : {
	                startswith : "Starts with",
	                eq : "Is equal to",
	                neq : "Is not equal to"
	            }
	        }
	    },
	    pageable: false,
	    scrollable: false,
	    columns: [
  			{ 
   				title : 'ID', 
   				field: 'id', 
   				width: 70
   			},
			{ 
				title : 'First Name', 
				field: 'firstName', 
				template: $("#firstName-template").html(),
				width: 100
			},
			{ 
				title : 'Last Name', 
				field: 'lastName', 
				template: $("#lastName-template").html(),
				width: 100
			},
			{ 
				title : 'Age', 
				field: 'age', 
				width: 100
			},
			{ 
				title : 'GPA', 
				field: 'gpa', 
				width: 100
			},
			{ 
   				title : 'Class', 
   				field: 'classId', 
   				values: allClasses,
   				width: 70
   			},
			{ command: ["edit", "destroy"], title: "Action", width: "180px" }
			],
	    editable: "popup",
	    toolbar: ["create"]
	});

	
	
	//////////////////////////////////////////////
	
	
	});

	
	
	///////////////////////////
	var dataSrc = new kendo.data.DataSource(
			{
	        	transport: {
					read: {
						url: "/vtac/api/ajax/fetchStudentsByClassId.html",
						dataType: "json",
						type: "POST"
					}, 
					update: {
	                    url: "/vtac/api/ajax/studentUpdate.html",
	                    dataType: "json",
	                    type: "POST"
	                },
	                destroy: {
	                    url: "/vtac/api/ajax/studentDestroy.html",
	                    dataType: "json",
	                    type: "POST"
	                },
	                create: {
	                    url: "/vtac/api/ajax/studentCreate.html",
	                    dataType: "json",
	                    type: "POST"
	                },
	                
	                batch: true,
	                pageSize: 20,

	                parameterMap: function(options, operation) {
	                	 if (operation == "read"){
	                		 return {
	 							pageNumber : '1',
	 							classId : theFirstClassId
	 						};
	                	 }
	                     if (operation !== "read" && operation != "create" && options ) { 
	                         return {
	                        	 id : options.id,
	                        	 firstName : options.firstName,
	                        	 lastName : options.lastName,
	                        	 age : options.age,
	                        	 gpa : options.gpa,
	                        	 classId : options.classId
	                         };
	                     }
	                     if (operation == "create"){
	                		 return {
	                        	 firstName : options.firstName,
	                        	 lastName : options.lastName,
	                        	 age : options.age,
	                        	 gpa : options.gpa,
	                        	 classId : options.classId
	 						};
	                	 }                     
	                 }
	                 
				},
				schema: {
	                model: {
	                    id: "id",
	                    fields: {
	                    	id: { editable: false, nullable: true },
	                    	firstName: { validation: { required: true } },
	                    	lastName: { validation: { required: true } },
	                    	age: { validation: { required: false } },
	                    	gpa: { validation: { required: false } },
	                    	classId: { validation: { required: false } }
	                    }
	                },
					data: function(response) {
						return response.studentList;
					}
	            }
			}
	);		
	///////////////////////////

	function onChange(arg) {
	var data = this.dataItem(this.select()); 
	var selectedClassId = data.id;
	//alert(selectedClassId);
	refreshStudentsList(selectedClassId);
	}

	function refreshStudentsList(selectedClassId){
		theFirstClassId=selectedClassId;
		dataSrc.read({ page: 1, skip: 0 });
		$("#studentsListGrid").data("kendoGrid").refresh();
	}
	//////////////////////////////////


	</script>	

	<script type="text/x-kendo-tmpl" id="firstName-template">
    <div>	
			# if(gpa>=3.2){ #
			<font color="red"> #= firstName # </font>
			# }else{ #
			#= firstName #
			# } #
    </div>
	</script>

	<script type="text/x-kendo-tmpl" id="lastName-template">
    <div>	
			# if(gpa>=3.2){ #
			<font color="red"> #= lastName # </font>
			# }else{ #
			#= lastName #
			# } #
    </div>
	</script>

	</div>
	
</body>
</html>