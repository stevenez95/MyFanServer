/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
myMusic.factory('Auth', ["$http","AuthToken",function($http, AuthToken) {

	// create auth factory object
	var authFactory = {};

	// log a user in
	authFactory.login = function(username, password) {

		// return the promise object and its data
//		return $http.post('/api/authenticate', {
//			username: username,
//			password: password
//		})
//			.success(function(data) {
//				AuthToken.setToken(data.token);
//       			return data;
//			});
            console.log('loginnnn!!');
	};

	// log a user out by clearing the token
	authFactory.logout = function() {
		// clear the token
		AuthToken.setToken();
	};

	// check if a user is logged in
	// checks if there is a local token
	authFactory.isLoggedIn = function() {
		if (AuthToken.getToken()) 
			return true;
		else
			return false;	
	};

	// get the logged in user
//	authFactory.getUser = function() {
//		if (AuthToken.getToken())
//			return $http.get('/api/me', { cache: true });
//		else
//			return $q.reject({ message: 'User has no token.' });		
//	};


	// return auth factory object
	return authFactory;

}]);

myMusic.factory('AuthToken',["$cookies", function($cookies) {

	var authTokenFactory = {};

	authTokenFactory.getToken = function() {
		return $cookies.getObject('token');
	};

	authTokenFactory.setToken = function(token) {
		if (token)
			$cookies.putObject('token', token);
	 	else
			$cookies.remove('token');
	};

	return authTokenFactory;

}]);

myMusic.factory('AuthInterceptor',["$q","$location","AuthToken", function($q, $location, AuthToken) {

	var interceptorFactory = {};

	// this will happen on all HTTP requests
	interceptorFactory.request = function(config) {

		// grab the token
		var token = AuthToken.getToken();

		// if the token exists, add it to the header as x-access-token
		if (token) 
			config.headers['x-access-token'] = token;
		
		return config;
	};

	// happens on response errors
	interceptorFactory.responseError = function(response) {

		// if our server returns a 403 forbidden response
		if (response.status === 403) {
			AuthToken.setToken();
			$location.path('/login');
		}

		// return the errors from the server as a promise
		return $q.reject(response);
	};

	return interceptorFactory;
	
}]);