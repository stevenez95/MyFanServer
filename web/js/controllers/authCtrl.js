/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global myMusic */

myMusic.controller('authCtrl',["$scope", "$http","$location","$rootScope", function ($scope,$http,$location,$rootScope) {
        //alert("authCtrl");
        $scope.newFan = {};
        $scope.newBand = {};
        $scope.newFan.img = 'http://i.imgur.com/G3C6CE0.png';
        $scope.logUser = {};
        $scope.error;
        
//        var check =  function(){
//          if(authFact.getUserObj()){
//              $rootScope.authenticated = true;
//              $location.path('/fan');
//          }else{
//              console.log("not Logged In")
//          }  
//        };
//        
//        check();
        
        $scope.registerBand = function (){
            console.log($scope.newBand);  
        };
        
        $scope.registerFan = function (){
            console.log($scope.newFan); 
            console.log($scope.newFan.bday);
        };
        
        $scope.login = function (){
            $rootScope.authenticated = true;
            console.log($scope.logUser);
            $location.path('/fan');
            $http.post('http://localhost:8080/prueba/webresources/generic',{
                image:"asda",
                username:"stevenez",
                password:"pass"
            })
                    .then(function success(data){
                        console.log(data);
            },function error(data){
                console.log(data);
            });
            //$scope.$apply();
        };
        
        $scope.openLogIn = function (name) {
            if (name === 'Fan') $scope.isFan = true;
            else $scope.isFan = false;
        };
        
        $scope.signout = function() {
            $rootScope.authenticated=false;
        };
    }]);