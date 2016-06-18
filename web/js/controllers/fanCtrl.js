/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global myMusic */

myMusic.controller('fanCtrl',["$scope","$location","$routeParams","$http","$route", function ($scope,$location,$routeParams,$http,$route) {
        
        $scope.fanId = $routeParams.fanId;
        var url = 'http://localhost:8080/MyFanServer/api/v1/';
        
        $scope.busqueda;
        
        var getFanInfo = function (){
            $http.get(url+'fan/me/'+$scope.fanId).then(function mySucces(response) {
                $scope.fanInfo = response.data;
                //console.log(response.data);
            }, function myError(response) {
                $scope.error = "error";
            });
        }; //done
        
        getFanInfo();
                
        var verNoticias = function (){
            $http.get(url+'noticias/fan/'+$scope.fanId).then(function mySucces(response) {
                $scope.fanNews=response.data;
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        }; //done
        
        var verEventos = function (){
            $http.get(url+'evento/fan/'+$scope.fanId).then(function mySucces(response) {
                $scope.fanEvents=response.data;
            }, function myError(response) {
                $scope.error = "error";
            });
        }; //done
        
        verNoticias();
        verEventos();
        
        
        $scope.editarPerfil = function (){
            $location.path('/fan/'+$scope.fanId+'/edit');
        }; //done

        $scope.verMisArtistas = function (){
            $location.path('/fan/'+$scope.fanId+'/misArtistas');
        }; //done
        
        $scope.actualizarDatos = function (){
            $http.put(url+'fan/actualizar/'+$scope.fanId,$scope.fanInfo).then(function mySucces(response) {
                $scope.newNews={};
                getFanInfo();
                //refresh();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
            
        }; //done
        
        $scope.desactivarCuenta = function () {
            $http.delete(url+'fan/desactivar/'+$scope.fanId).then(function mySucces(response) {
                $location.path("/fan/"+$scope.fanId);
                $route.reload();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        }; // done
        
        var verArtistas = function (){
            $http.get(url+'fan/verArtistas/'+$scope.fanId).then(function mySucces(response) {
                $scope.misArtistas = response.data;
            }, function myError(response) {
                $scope.error = "error";
            });
        };
        verArtistas();
        
        $scope.verPerfilArtista = function (bandId){
            $location.path('/fan/'+$scope.fanId+'/band/'+bandId);
        };
        
        $scope.dejarSeguir = function (bandId){
            $http.delete(url+'fan/dejarSeguir/'+$scope.fanId+'/'+bandId).then(function mySucces(response) {
                verArtistas();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        }; //done
        
        $scope.verConcierto = function (idEvento){
            $location.path('/fan/'+$routeParams.fanId+'/conciertos/'+idEvento);
        };
        
        $scope.buscar = function (){
            if(!$scope.busqueda.nombre) {
                $scope.error = "Debe ingresar un nombre de banda";
                return;
            }
            if(!$scope.busqueda.genero) {$scope.busqueda.genero=""; }
            if(!$scope.busqueda.pais) { $scope.busqueda.pais = "";}
            $http.get(url+'fan/buscarArtista/'+$scope.busqueda.nombre+'/'+$scope.busqueda.genero+'/'+$scope.busqueda.pais).then(function mySucces(response) {
                $scope.busqueda='';
                $scope.resBusqueda = response.data;
                $scope.error = "";
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        };
        
        $scope.verBusqueda = function (){
            $location.path('/fan/'+$scope.fanId+'/buscar');
        };
        
        var getGeneros = function (){
            $http.get(url+'generos').then(function mySucces(response) {
                $scope.generos= response.data;
            }, function myError(response) {
                $scope.myWelcome = response.statusText;
            });
        };
        
        var getPaises = function (){
            $http.get(url+'paises').then(function mySucces(response) {
                $scope.paises= response.data;
            }, function myError(response) {
                $scope.myWelcome = response.statusText;
            });
        };
        
        getGeneros();
        getPaises();
        
        $scope.genreSelectionF = [];
        $scope.toggleSelectionF = function toggleSelection(idGenero) {
            var idx = $scope.genreSelectionF.indexOf(idGenero);
            
            // is currently selected
            if (idx > -1) {
                $scope.genreSelectionF.splice(idx, 1);
            }
            
            // is newly selected
            else {
                $scope.genreSelectionF.push(idGenero);
            }
            console.log($scope.genreSelectionF);
            $scope.fanInfo.generos = $scope.genreSelectionF;
            console.log($scope.fanInfo);
        };

}]);