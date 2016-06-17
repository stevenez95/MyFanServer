/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global myMusic */

myMusic.config(["$routeProvider",function ($routeProvider) {
    $routeProvider
        // AUTHENCTICATION
        .when('/', {
            templateUrl: 'templates/login.html'
            , controller: 'authCtrl'
        })
        .when('/register', {
            templateUrl: 'templates/signup.html'
            , controller: 'authCtrl'
        })
        // PROFILES
        .when('/fan/:fanId',{
            templateUrl:'templates/fan/fanDash.html'
            , controller:'fanCtrl'
        })
        .when('/band/:bandId',{
            templateUrl:'templates/banda/bandDash.html'
            , controller: 'bandCtrl'
        })
        .when('/fan/:fanId/band/:bandId',{/********************/
            templateUrl:'templates/banda/bandDash.html'
            , controller: 'fanBandCtrl'
        })
        .when('/fan/:fanId/edit',{/********************/
            templateUrl:'templates/fan/fanEditar.html'
            , controller: 'fanCtrl'
        })
        .when('/band/:bandId/edit',{/********************/
            templateUrl:'templates/banda/bandaEditar.html'
            , controller: 'bandCtrl'
        })
        .when('/fan/:fanId/misArtistas',{/********************/
            templateUrl:'templates/fan/misArtistas.html'
            , controller: 'fanCtrl'
        })
        .when('/fan/:fanId/buscar',{/********************/
            templateUrl:'templates/fan/busqueda.html'
            , controller: 'fanCtrl'
        })
        // DISCOGRAPHY
        .when('/band/:bandId/discos',{
            templateUrl:'templates/banda/discografia.html',
            controller:'discCtrl'
        })
        .when('/fan/:fanId/band/:bandId/discos',{/********************/
            templateUrl:'templates/banda/discografia.html',
            controller:'discCtrl'
        })
        // CONCERTS
        .when('/band/:bandId/conciertos/:eventId',{
            templateUrl:'templates/banda/concierto.html',
            controller:'concertCtrl'
        })
        .when('/fan/:fanId/conciertos/:eventId',{/************************/
            templateUrl:'templates/banda/concierto.html',
            controller:'concertCtrl'
        })
        .otherwise({ 
            redirectTo:'/'
        });
}]);