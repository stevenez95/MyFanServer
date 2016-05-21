myMusic.controller('editDiscCtrl',["$scope","$routeParams", function ($scope,$routeParams) {
        $scope.type = 'edit';
        $scope.discId = $routeParams.discId;
}]);


myMusic.controller('discCtrl',["$scope","$routeParams", function ($scope,$routeParams) {
        $scope.type = 'view';
        $scope.userType='band';
        $scope.discId=$routeParams.discId;
        $scope.bandId=$routeParams.bandId;
        
        $scope.viewDisc = function (/* ID */){
            $scope.discInfo={};
        };
        
        $scope.deleteDisc = function (/* ID */){
            $scope.discInfo=null;
        };  
        
        $scope.editSong = function (/* ID */){
        };  
        
        
        $scope.deleteSong = function (/* ID */){
        }; 
        
        
}]);
