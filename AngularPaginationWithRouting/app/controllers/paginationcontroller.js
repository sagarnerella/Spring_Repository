angular.module('app.controllers', []).controller('PaginationDemoCtrl', function($scope, $log) {

 var allCandidates =[{request_category_name: "Moroni", priority_name: 50},
                {request_category_name: "Simon", priority_name: 43},
                {request_category_name: "Jacob", priority_name: 27},
                {request_category_name: "Nephi", priority_name: 29},
                {request_category_name: "Christian", priority_name: 34},
                {request_category_name: "Tiancum", priority_name: 43},
                {request_category_name: "Jacob", priority_name: 27},
				{request_category_name: "Simon", priority_name: 43},
                {request_category_name: "Jacob", priority_name: 27},
                {request_category_name: "Nephi", priority_name: 29},
				{request_category_name: "Moroni", priority_name: 50},
                {request_category_name: "Simon", priority_name: 43},
                {request_category_name: "Jacob", priority_name: 27},
                {request_category_name: "Nephi", priority_name: 29},
                {request_category_name: "Christian", priority_name: 34},
                {request_category_name: "Tiancum", priority_name: 43},
                {request_category_name: "Jacob", priority_name: 27},
				{request_category_name: "Simon", priority_name: 43},
                {request_category_name: "Jacob", priority_name: 27},
                {request_category_name: "Nephi", priority_name: 29}
                
            ];

  $scope.totalItems = allCandidates.length;
  $scope.currentPage = 1;
  $scope.itemsPerPage = 5;

  $scope.$watch("currentPage", function() {
    setPagingData($scope.currentPage);
  });

  function setPagingData(page) {
    var pagedData = allCandidates.slice(
      (page - 1) * $scope.itemsPerPage,
      page * $scope.itemsPerPage
    );
    $scope.aCandidates = pagedData;
  }
    });