/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var myMusic = angular.module('myMusic', ['ngRoute','ngCookies','naif.base64']);

myMusic.config(["$httpProvider",function($httpProvider) {

	$httpProvider.interceptors.push('AuthInterceptor');

}]);