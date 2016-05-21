
myMusic.controller('concertCtrl',["$scope","$routeParams", function ($scope,$routeParams) {
        $scope.userType='band';
        $scope.discId=$routeParams.eventId;
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


