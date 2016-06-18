describe('Factory: Auth', function() {
  var Auth;

  // Before each test load our api.users module
  beforeEach(angular.mock.module('myMusic')); 

  // Before each test set our injected Users factory (_Users_) to our local Users variable
  beforeEach(inject(function(_Auth_) { 
    Auth = _Auth_;
  }));

  // A simple test to verify the Users factory exists
  it('Debe existir el servicio Auth', function() {
    expect(Auth).toBeDefined(); 
  }); 
  
  it('Debe de tener el metodo Login', function() {
    expect(Auth.login).toBeDefined(); 
  }); 
  
  it('Debe de tener el metodo Logout', function() {
    expect(Auth.logout).toBeDefined(); 
  }); 
   
  it('Should return false', function() {
    expect(Auth.isLoggedIn()).toEqual(false); 
  }); 
});