/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global myMusic */

myMusic.factory('Auth', ["$http","AuthToken",function($http, AuthToken) {

	// create auth factory object
	var authFactory = {};

	// log a user in
	authFactory.login = function(user) {
            return $http.post('http://localhost:8080/MyFanServer/api/v1/autenticar/login', user)
                    .success(function(data) {
                        AuthToken.setToken(data.mensaje,data.tipo,data.id);
                return data;
            });
	};

	// log a user out by clearing the token
	authFactory.logout = function() {
		AuthToken.setToken(null,null,null);
	};

	authFactory.isLoggedIn = function() {
		if (AuthToken.getToken()) 
			return true;
		else
			return false;	
	};



	// return auth factory object
	return authFactory;

}]);

myMusic.factory('AuthToken',["$cookies", function($cookies) {

	var authTokenFactory = {};

	authTokenFactory.getToken = function() {
		return $cookies.getObject('token');
	};

	authTokenFactory.setToken = function(token,tipo,id) {
		if (token){
                    $cookies.putObject('token', token);
                    $cookies.putObject('tipo',tipo);
                    $cookies.putObject('id',id);
                }
	 	else{
			$cookies.remove('token');
                        $cookies.remove('tipo');
                        $cookies.remove('id');
                    }
	};

	return authTokenFactory;

}]);

myMusic.factory('AuthInterceptor',["$q","$location","AuthToken", function($q, $location, AuthToken) {

	var interceptorFactory = {};

	// this will happen on all HTTP requests
	interceptorFactory.request = function(config) {

		// grab the token
		var token = AuthToken.getToken();
               // console.log("config: " + token);

		// if the token exists, add it to the header as x-access-token
		if (token) 
			config.headers['x-access-token'] = token;
		
		return config;
	};

	// happens on response errors
	interceptorFactory.responseError = function(response) {
                        

            // if our server returns a 403 forbidden response
            if (response.status === 403) {
                AuthToken.setToken(null,null,null);
                $location.path('/');
            }

		// return the errors from the server as a promise
		return $q.reject(response);
	};

	return interceptorFactory;
	
}]);