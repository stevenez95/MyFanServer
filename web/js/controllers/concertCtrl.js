
/* global myMusic */

myMusic.controller('concertCtrl',["$scope","$routeParams","$http", function ($scope,$routeParams,$http) {
        if($routeParams.fanId)$scope.type='fan';
        else $scope.type='banda'; 
        
        $scope.newComment;

        var url = 'http://localhost:8080/MyFanServer/api/v1/';
        $scope.eventId=$routeParams.eventId;
        
        $scope.date2005 = new Date("2016-12-12").getTime(); 
        $scope.today = Date.now();
        
        var getEventInfo = function (){
            $http.get(url+'evento/'+$scope.eventId).then(function mySucces(response) {
                $scope.eventInfo=response.data;
                $scope.fechaEv = new Date($scope.eventInfo.fechaEvento).getTime();
                verificar();
            }, function myError(response) {
                console.log(response);
                $scope.error = response.mensaje;
            });
        };
        
        var verificar = function (){
            if($scope.today >= $scope.fechaEv + 3600000)
                $scope.puedeCalificar = true;
            else
                $scope.puedeCalificar = false;
        };
        
        getEventInfo();
        
        var getBandRate = function (){
            $http.get(url+'evento/getEventRate/'+$scope.eventId).then(function mySucces(response) {
                $scope.eventRate = response.data;
                getBandComments();
            }, function myError(response) {
                console.log(response);
                $scope.error = response.data.mensaje;
            });
            
        };
        
        var getBandComments = function (){
            $http.get(url+'evento/getEventComments/'+$scope.eventId).then(function mySucces(response) {
                $scope.eventComments = response.data;
            }, function myError(response) {
                console.log(response);
                $scope.error = response.data.mensaje;
            });
        };
        
        getBandRate();
        
        $scope.calificar = function (){
            $scope.newComment.fecha = Date.now();
            $scope.newComment.id= $scope.eventId;
            $scope.newComment.idFan = $routeParams.fanId;
            $http.post(url+'fan/rateEvent', $scope.newComment).then(function mySucces(response) {
                $scope.newComment = '';
                getBandRate();
            }, function myError(response) {
                $scope.error = response.data.mensaje;
            });
        };
                
        
}]);


