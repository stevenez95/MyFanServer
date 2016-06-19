describe('Prueba config rutas', function() {
  var $scope = null;
  var ctrl = null;
 
  it('should test routeProvider', function() {
    module('myMusic');
  
    inject(function($route, $location, $rootScope, $httpBackend) {
  
      expect($route.current).toBeUndefined();
      
      $httpBackend.expectGET('templates/login.html').respond(200);
      $location.path('/');
      $rootScope.$digest();
  
      expect($route.current.templateUrl).toBe('templates/login.html');
      expect($route.current.controller).toBe('authCtrl');

      $httpBackend.expectGET('templates/signup.html').respond(200);
      $location.path('/register');
      $rootScope.$digest();
      expect($route.current.templateUrl).toBe('templates/signup.html');
      expect($route.current.controller).toBe('authCtrl');

      $httpBackend.expectGET('templates/login.html').respond(200);
      $location.path('/otherwise');
      $rootScope.$digest();

      expect($location.path()).toBe('/');
      expect($route.current.templateUrl).toEqual('templates/login.html');
      expect($route.current.controller).toBe('authCtrl');

    });
  }); 
});