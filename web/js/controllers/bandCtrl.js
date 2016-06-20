/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global myMusic */

myMusic.controller('fanBandCtrl',['$scope','$routeParams','$location','$http',function ($scope,$routeParams,$location,$http){
        var url = '/MyFanServer/api/v1/';
        
        $scope.newComment;
        
        $scope.type='fan';
        
        var isFoll = function (){
            $http.get(url+'fan/esSeguidor/'+$routeParams.fanId+'/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.isFollowing = response.data;
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            }); 
        };
        
        isFoll();
            
        var getBandInfo = function (){
            $http.get(url+'banda/me/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandInfo = response.data;
                getCantSeguidores();
                getBandRate();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        }; //done
        
        var getCantSeguidores = function (){
            $http.get(url+'banda/seguidores/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandSeg = response.data;
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        }; //done
        
        var getBandRate = function (){
            $http.get(url+'banda/getBandRate/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandRate = response.data;
                getBandComments();
            }, function myError(response) {
                console.log(response);
                $scope.error = response.data.mensaje;
            });
            
        };
        
        var getBandComments = function (){
            $http.get(url+'banda/getBandComments/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandComments = response.data;
            }, function myError(response) {
                console.log(response);
                $scope.error = response.data.mensaje;
            });
        };
        
        getBandRate();
        getBandInfo();
        
        var verNoticias = function (){
            $http.get(url+'noticias/banda/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandNews=response.data;
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        };
        
        var verEventos = function (){
            $http.get(url+'evento/banda/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandEvents=response.data;
                //console.log($scope.bandEvents);
                //                $scope.newsFeed= [].concat($scope.bandNews,$scope.bandEvents);
                //                console.log('sdfsdf '+ $scope.newsFeed);
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        };
        
        verNoticias();
        verEventos();
        
        $scope.verDiscos = function (){
            $location.path('/fan/'+$routeParams.fanId+'/band/'+$routeParams.bandId+'/discos');
        }; //done
        
        $scope.verConcierto = function (idEvento){
            $location.path('/fan/'+$routeParams.fanId+'/conciertos/'+idEvento);
        };
        
        $scope.seguir = function (){
            $http.post(url+'fan/seguir/'+$routeParams.fanId+'/'+$routeParams.bandId,{}).then(function mySucces(response) {
                $scope.isFollowing = true;
                getCantSeguidores();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });  
        };
        
        $scope.dejarSeguir = function (){
            $http.delete(url+'fan/dejarSeguir/'+$routeParams.fanId+'/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.isFollowing = false;
                getCantSeguidores();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });            
        };
        
        $scope.calificar = function (){
            $scope.newComment.fecha = Date.now();
            $scope.newComment.id= $routeParams.bandId;
            $scope.newComment.idFan = $routeParams.fanId;
            $http.post(url+'fan/rateBand', $scope.newComment).then(function mySucces(response) {
                $scope.newComment = '';
                getBandRate();
            }, function myError(response) {
                $scope.error = response.data.mensaje;
            });
        };
        
        $scope.borrarComentario = function (){
            $http.delete(url+'fan/deleteRate/'+$routeParams.bandId+'/'+$routeParams.fanId+'/banda').then(function mySucces(response) {
                getBandRate();
            }, function myError(response) {
                $scope.error = response.data.mensaje;
            });
        };
    }]);

myMusic.controller('bandCtrl',["$scope","$routeParams","$http","$location","$route", function ($scope,$routeParams,$http,$location,$route) {
        
        var url = '/MyFanServer/api/v1/';
        
        $scope.type='banda';
        $scope.bandInfo={};
        
        
        
        var getBandInfo = function (id){
            $http.get(url+'banda/me/'+id).then(function mySucces(response) {
                $scope.bandInfo = response.data;
                getCantSeguidores();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        }; //done
        
        var getCantSeguidores = function (){
            $http.get(url+'banda/seguidores/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandSeg = response.data;
                getBandRate();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        }; //done
        
        getBandInfo($routeParams.bandId);
        
        var verNoticias = function (){
            $http.get(url+'noticias/banda/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandNews=response.data;
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        };
        
        var verEventos = function (){
            $http.get(url+'evento/banda/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandEvents=response.data;
                //console.log($scope.bandEvents);
                //                $scope.newsFeed= [].concat($scope.bandNews,$scope.bandEvents);
                //                console.log('sdfsdf '+ $scope.newsFeed);
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        };
        
        verNoticias();
        verEventos();
               
        $scope.newNews;
        $scope.newEvent;
        
        $scope.crearNoticia = function (){
            $scope.newNews.idBanda = $routeParams.bandId;
            $scope.newNews.fechaCreacion = new Date().getTime();
            $http.post(url+'noticias/newNews',$scope.newNews).then(function mySucces(response) {
                $scope.newNews={};
                verNoticias();
                //refresh();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        }; //done
        
        $scope.crearEvento = function (){
            $scope.newEvent.idBanda = $routeParams.bandId;
            $scope.newEvent.fechaCreacion = new Date().getTime();
            $http.post(url+'evento/newEvent',$scope.newEvent).then(function mySucces(response) {
                $scope.newEvent={};
                verEventos();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        }; //done
        
        $scope.borrarNoticia = function (idNoticia){
            $http.delete(url+'noticias/'+idNoticia).then(function mySucces(response) {
                verNoticias();
                //refresh();
            }, function myError(response) {
                $scope.error = "error";
            });
        }; //done
        
        $scope.cancelarEvento = function (idEvento){
            $http.delete(url+'evento/'+idEvento).then(function mySucces(response) {
                verEventos();
                //refresh();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
        }; //done
        
        $scope.editarPerfil = function (){
            $location.path('/band/'+$routeParams.bandId+'/edit');
        }; //done
        
        $scope.verDiscos = function (){
            $location.path('/band/'+$routeParams.bandId+'/discos');
        }; //done
        
        $scope.actualizarDatos = function (){
            $http.put(url+'banda/actualizar/'+$routeParams.bandId,$scope.bandInfo).then(function mySucces(response) {
                $scope.newNews={};
                getBandInfo();
                //refresh();
            }, function myError(response) {
                console.log(response);
                $scope.error = "error";
            });
            
        }; //done
        
        $scope.desactivarCuenta = function () {
            if (confirm('¿Esta seguro que desea activar/desactivar su cuenta?')) {
                // Save it!
                $http.delete(url+'banda/desactivar/'+$routeParams.bandId).then(function mySucces(response) {
                    $location.path("/band/"+$routeParams.bandId);
                    $route.reload();
                }, function myError(response) {
                    console.log(response);
                    $scope.error = "error";
                });
            }
            else{}
        }; //done
        
        $scope.verConcierto = function (idEvento){
            $location.path('/band/'+$routeParams.bandId+'/conciertos/'+idEvento);
        };
        
        var getBandRate = function (){
            $http.get(url+'banda/getBandRate/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandRate = response.data;
                getBandComments();
            }, function myError(response) {
                console.log(response);
                $scope.error = response.data.mensaje;
            });
            
        };
        
        var getBandComments = function (){
            $http.get(url+'banda/getBandComments/'+$routeParams.bandId).then(function mySucces(response) {
                $scope.bandComments = response.data;
            }, function myError(response) {
                console.log(response);
                $scope.error = response.data.mensaje;
            });
        };
        
        getBandRate();
        
        $scope.isPast = function (fecha){
            var hoy = Date.now();
            var fechaEv = new Date(fecha);
            if(hoy >= fechaEv )
                return true;
            else
                return false;
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
        
        $scope.genreSelectionB = [];
        $scope.toggleSelectionB = function toggleSelection(idGenero) {
            var idx = $scope.genreSelectionB.indexOf(idGenero);
            
            // is currently selected
            if (idx > -1) {
                $scope.genreSelectionB.splice(idx, 1);
            }
            
            // is newly selected
            else {
                $scope.genreSelectionB.push(idGenero);
            }
            console.log($scope.genreSelectionB);
            $scope.bandInfo.generos = $scope.genreSelectionB;
            console.log($scope.bandInfo);
        };
        
    }]);
