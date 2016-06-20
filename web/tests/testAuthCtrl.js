/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
describe('Controller: AuthCtrl', function () {

  // load the controller's module
  beforeEach(module('myMusic'));

  var AuthCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AuthCtrl = $controller('authCtrl', {
      $scope: scope
    });
  }));

  it('debe de agregar un item a la lista', function () {
    scope.toggleSelectionB(1);
    expect(scope.genreSelectionB.length).toBe(1);
  });
  
  it('debe de agregar y despues eliminar un item a la lista', function () {
      scope.toggleSelectionB(1);
      scope.toggleSelectionB(1);
    expect(scope.genreSelectionB.length).toBe(0);
  });
  
  it('debe de retornar error si hay mas de 5 generos seleccionados', function () {
      scope.newBand.generos = [1,2,3,4,5,6,7];
      scope.registerBand();
    expect(scope.error).toEqual("No puede seleccionar mas de 5 generos");
  });
  

});