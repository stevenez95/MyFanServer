/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


myMusic.controller('mainCtrl', ['$scope','$location','$anchorScroll',function ($scope,$location,$anchorScroll) {
    //alert("working"); 
    $location.path('/');
    $scope.scrollTo = function(id) {
      $location.hash(id);
      $anchorScroll();
   };

}]);