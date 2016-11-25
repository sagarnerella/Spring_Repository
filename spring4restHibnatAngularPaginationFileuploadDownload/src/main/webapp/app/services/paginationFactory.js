taskManagerModule.factory('paginationFactory',function($http){ 

var editPersonArr=[];

return {
setEditPerson:function(editPerson){
editPersonArr=editPerson;
},
getEditPerson:function(){
return editPersonArr;
},

getPersonList:function(personalDetails){
return $http.get("http://localhost:8080/springapp/personlist").
	success(function(data, status, headers, config) {
        console.log("getTaskList", getPersonList);
    });
},
savePerson: function(personalDetails,method){
return $http({

	            method: 'POST',
	            url: 'http://localhost:8080/springapp/person/'+method,
	            data: {
	            	personId:personalDetails.personId,firstName:personalDetails.firstName,lastName:personalDetails.lastName,email:personalDetails.email
	            },
	            headers: {'Content-Type':'application/json'}
	        }).
	  success(function(data, status, headers, config) {
		    console.log("updateRecord", data);
	  });

}

};
});