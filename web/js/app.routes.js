/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
myMusic.config(["$routeProvider",function ($routeProvider) {
    $routeProvider
        // AUTHENCTICATION
        .when('/', {
            templateUrl: 'templates/login.html'
            , controller: 'authCtrl'
        })
        //the signup display
        .when('/register', {
            templateUrl: 'templates/signup.html'
            , controller: 'authCtrl'
        })
        // PROFILES
        .when('/fan',{
            templateUrl:'templates/fan/fanDash.html'
            , controller:'fanCtrl'
        })
        .when('/band',{
            templateUrl:'templates/banda/bandDash.html'
            , controller: 'bandCtrl'
        })
        // DISCOGRAPHY
        .when('/band/:bandId/discos/:discId',{
            templateUrl:'templates/banda/discografia.html',
            controller:'discCtrl'
        })
        .when('/band/:bandId/discos/edit/:discId',{
            templateUrl:'templates/banda/discografia.html',
            controller:'editDiscCtrl'
        })
        .when('/fan/discos/:discId',{
            templateUrl:'templates/banda/discografia.html',
            controller:'discCtrl'
        })
        // CONCERTS
        .when('/band/:bandId/conciertos/:eventId',{
            templateUrl:'templates/banda/concierto.html',
            controller:'concertCtrl'
        })
        .otherwise({ 
            redirectTo:'/'
        });
}]);