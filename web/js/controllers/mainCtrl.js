/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


myMusic.controller('mainCtrl', ['$scope','$location','$anchorScroll','Auth','$cookies','$route',function ($scope,$location,$anchorScroll,Auth,$cookies,$route) {
    //alert("working"); 
    //$location.path('/');
    $scope.scrollTo = function(id) {
      $location.hash(id);
      $anchorScroll();
   };
   
   $scope.authenticated = Auth.isLoggedIn();
   
   $scope.home = function (){
       $scope.authenticated = Auth.isLoggedIn();
       if($scope.authenticated){
           if($cookies.getObject('tipo') === 'banda') $location.path('/band/'+$cookies.getObject('id'));
           else if ($cookies.getObject('tipo') === 'fan') $location.path('/fan/'+$cookies.getObject('id'));
       }
       else{
           $location.path('/');
       }
       //$route.reload();
       console.log("home");
   };
   
   $scope.home();
   
   
   $scope.cerrarSesion = function (){
       Auth.logout();
       $scope.authenticated = false;
   };

}]);