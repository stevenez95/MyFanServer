/* global myMusic */
myMusic.controller('discCtrl',["$scope","$routeParams","$http","$sce", function ($scope,$routeParams,$http,$sce) {
        $scope.tipo = 'view';
        if($routeParams.fanId)$scope.type='fan';
        else $scope.type='banda';
                
        $scope.bandId=$routeParams.bandId;
        
        $scope.newDisc={};
        $scope.newSong={};
        
        var url = "/MyFanServer/api/v1/";
        
        var viewDiscs = function (){
            $http.get(url+'disco/banda/'+$scope.bandId).then(function mySucces(response) {
                $scope.discs = response.data;
                console.log(response.data);
            }, function myError(response) {
                $scope.error = "error";
            });
        }; //done
        viewDiscs();
        
        $scope.viewDiscInfo = function (idDisco){
            $scope.idDisco = idDisco;
            $http.get(url+'disco/'+idDisco).then(function mySucces(response) {
                $scope.discInfo = response.data;
                viewSongs(idDisco);
            }, function myError(response) {
                $scope.error = "error";
            });
        }; //done
        
        var viewSongs = function (idDisco){
            $http.get(url+'disco/getSongs/'+idDisco).then(function mySucces(response) {
                $scope.discSongs = response.data;
                getDiscRate();
            }, function myError(response) {
                $scope.error = "error";
            });
        }; //done
        
        $scope.deleteDisc = function (){
            $http.delete(url+'disco/delete/'+$scope.idDisco).then(function mySucces(response) {
                $scope.discInfo=null;
                viewDiscs();
            }, function myError(response) {
                $scope.error = "error";
            });
        };  //done
        
        $scope.editDisc = function (){
            $scope.tipo = 'edit';
            //$scope.viewDiscInfo(idDisc);
        }; //done
        
        $scope.updateDisc = function (){
            $http.put(url+'disco/edit/'+$scope.discInfo.idDiscografia,$scope.discInfo ).then(function mySucces(response) {
                $scope.tipo = 'view';
                refresh();
            }, 
            function myError(response) {
                $scope.error = "error";
            });
        }; //done
        
        $scope.createDisc = function (){
            $scope.newDisc.idBanda = $scope.bandId;
            $http.post(url+'disco/new', $scope.newDisc).then(function mySucces(response) {
                viewDiscs();
            }, function myError(response) {
                $scope.error = "error";
            });
            $scope.newDisc = '';
        };
        
        $scope.getSong= function (idCancion){
            $scope.isEditSong = true ;
            $http.get(url+'disco/getSong/'+idCancion ).then(function mySucces(response) {
                $scope.newSong = response.data;
            }, function myError(response) {
                $scope.error = "error";
            });
        };
        
        $scope.updateSong = function (){
            console.log($scope.newSong.idCancion);
            $http.put(url+'disco/editSong/'+$scope.newSong.idCancion,$scope.newSong ).then(function mySucces(response) {
                refresh();
            }, function myError(response) {
                $scope.error = "error";
            });
            $scope.isEditSong = false;
            $scope.newSong = '';
        };  
        
        
        $scope.deleteSong = function (idSong){
            $http.delete(url+'disco/deleteSong/'+idSong ).then(function mySucces(response) {
                refresh();
            }, function myError(response) {
                $scope.error = "error";
            });
        }; //done
        
        $scope.createSong = function (){
            $scope.newSong.idDisco = $scope.idDisco;
            $http.post(url+'disco/newSong', $scope.newSong ).then(function mySucces(response) {
                refresh();
            }, function myError(response) {
                $scope.error = "error";
            });
            $scope.newSong = '';
        }; //done

        var refresh = function (){
            $scope.viewDiscInfo($scope.idDisco);
        };
        
        var getDiscRate = function (){
            $http.get(url+'disco/getDiscRate/'+$scope.idDisco).then(function mySucces(response) {
                $scope.discRate = response.data;
                getDiscComments();
            }, function myError(response) {
                console.log(response);
                $scope.error = response.data.mensaje;
            });
            
        };
        
        var getDiscComments = function (){
            $http.get(url+'disco/getDiscComments/'+$scope.idDisco).then(function mySucces(response) {
                $scope.discComments = response.data;
            }, function myError(response) {
                console.log(response);
                $scope.error = response.data.mensaje;
            });
        };
        
        
        $scope.newComment;
        
        $scope.calificar = function (){
            $scope.newComment.fecha = Date.now();
            $scope.newComment.id= $scope.idDisco;
            $scope.newComment.idFan = $routeParams.fanId;
            $http.post(url+'fan/rateDisc', $scope.newComment).then(function mySucces(response) {
                $scope.newComment = '';
                getDiscRate();
            }, function myError(response) {
                $scope.error = response.data.mensaje;
            });
        };
        
        $scope.viewVid = function (link){
            $scope.detailFrame= $sce.trustAsResourceUrl(link);
        };
        
        
        
    }]);
