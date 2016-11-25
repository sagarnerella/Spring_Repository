taskManagerModule.controller('fileUploadController', function($scope,$http,fileUploadFactory,$window){

$scope.uploadFile = function(){
               /*var file = $scope.myFile;
               
               console.log('file is ' );
               console.dir(file);
               
               var uploadUrl = "http://localhost:8080/springapp/fileUpload";
               fileUploadFactory.fileUpload(file, uploadUrl);
			   */
			   
			   
			   
			   var file = $scope.myFile;
/* console.log('file is ' );
console.dir(file);*/
var uploadUrl = "http://localhost:8080/springapp/fileUpload";
var fd = new FormData();
fd.append('file', file);
$http.post(uploadUrl, fd, {
transformRequest : angular.identity,
headers : {
'Content-Type' : undefined
}
}).success(function() {
console.log('success');
}).error(function() {
console.log('error');
});
			   
			   
			   
			   
            };
			
		$scope.downloadAnyFile=function(){
		$http({

        method: 'GET',
        url: 'http://localhost:8080/springapp/download',
        responseType: 'arraybuffer'
    }).success(function (data, status, headers){
	
	 var filename = headers('fileName');
        var contentType = headers['content-type'];
 
        var linkElement = document.createElement('a');
        try {
            var blob = new Blob([data], { type: contentType });
            var url = window.URL.createObjectURL(blob);
 
            linkElement.setAttribute('href', url);
            linkElement.setAttribute("download", filename);
 
            var clickEvent = new MouseEvent("click", {
                "view": window,
                "bubbles": true,
                "cancelable": false
            });
            linkElement.dispatchEvent(clickEvent);
        } catch (ex) {
            console.log(ex);
        }
		
                    })

    .error(function(data, status){
       
    })
		
		}
			
			
			
			$scope.downloadFile=function(){
		$http({

        method: 'GET',
        url: 'http://localhost:8080/springapp/downloadfile?fileId='+'2',
        responseType: 'arraybuffer'
    }).success(function (data, status, headers){
	
	 var filename = headers('fileName');
        var contentType = headers['content-type'];
 
        var linkElement = document.createElement('a');
        try {
            var blob = new Blob([data], { type: contentType });
            var url = window.URL.createObjectURL(blob);
 
            linkElement.setAttribute('href', url);
            linkElement.setAttribute("download", filename);
 
            var clickEvent = new MouseEvent("click", {
                "view": window,
                "bubbles": true,
                "cancelable": false
            });
            linkElement.dispatchEvent(clickEvent);
        } catch (ex) {
            console.log(ex);
        }
		
                    })

    .error(function(data, status){
       
    })
		
		}
			
			
			
			
			
});

taskManagerModule.directive('fileModel', ['$parse', function ($parse) {
            return {
               restrict: 'A',
               link: function(scope, element, attrs) {
                  var model = $parse(attrs.fileModel);
                  var modelSetter = model.assign;
                  
                  element.bind('change', function(){
                     scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                     });
                  });
               }
            };
         }]);

