taskManagerModule.factory('fileUploadFactory',function($http){  

return{

fileUpload : function(file, uploadUrl){
               var fd = new FormData();
               fd.append('file', file);
            alert("fd "+fd);
               $http.get(uploadUrl, fd, {
                  transformRequest: angular.identity,
                  headers: {'Content-Type': undefined}
               })
            
               .success(function(){
               })
            
               .error(function(){
               });
            }
}

});