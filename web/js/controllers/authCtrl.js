/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global myMusic */

myMusic.controller('authCtrl',["$scope", "$http","$location","Auth", function ($scope,$http,$location,Auth) {
        //alert("authCtrl");
        var url = "http://localhost:8080/MyFanServer/api/v1/";
        $scope.myfile={};
        $scope.myfile.filetype = "image/png";
        $scope.myfile.base64 = "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";
        $scope.newFan = {};
        $scope.newBand = {};
        $scope.logUser = {};
        $scope.error;
        $scope.generos;
        $scope.genreSelectionF = [];
        $scope.genreSelectionB = [];
        
        var getGeneros = function (){
            $http.get(url+'generos').then(function mySucces(response) {
                $scope.generos= response.data;
            }, function myError(response) {
                $scope.myWelcome = response.statusText;
            });
        };
        
        getGeneros();
        
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
            $scope.newFan.generos = $scope.genreSelectionF;
            console.log($scope.newFan);
        };
        
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
            $scope.newBand.generos = $scope.genreSelectionB;
            console.log($scope.newBand);
        };
        
        $scope.registerBand = function (){
            $scope.newBand.fotoPerfil = "data:"+$scope.myfile.filetype+";base64,"+$scope.myfile.base64;
            $scope.newBand.fechaCreacion = Date.now();
            console.log($scope.newBand);
            $http.post(url+'autenticar/signUpBanda', $scope.newBand).then(function mySucces(response) {
                $scope.myWelcome = response.data;
                $location.path('/');
            }, function myError(response) {
                $scope.error = response.data.mensaje;
            });
        };
        
        $scope.registerFan = function (){
            $scope.newFan.fotoPerfil = "data:"+$scope.myfile.filetype+";base64,"+$scope.myfile.base64;
            $scope.newFan.fechaCreacion = Date.now();
            console.log($scope.newFan);
            $http.post(url+'autenticar/signUpFan', $scope.newFan).then(function mySucces(response) {
            }, function myError(response) {
                console.log(response);
                $scope.error = response.data.mensaje;
            });
        };
        
        $scope.login = function (){
            Auth.login($scope.logUser).success(function (data){
                if(data.success){
                    if(data.tipo==='banda')
                        $location.path('/band/'+data.id);
                    else if (data.tipo==='fan')
                        $location.path('/fan/'+data.id);
                }
                else{
                    $scope.error = data.mensaje;
                }
            });
        };
        
        $scope.openLogIn = function (name) {
            if (name === 'Fan') $scope.isFan = true;
            else $scope.isFan = false;
        };
        
        $scope.signout = function() {
            Auth.logout();
        };
        
    }]);