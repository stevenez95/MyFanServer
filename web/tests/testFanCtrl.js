/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
describe('Controller: FanCtrl', function () {

  // load the controller's module
  beforeEach(module('myMusic'));

  var FanCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    FanCtrl = $controller('fanCtrl', {
      $scope: scope
    });
  }));

  it('Debe existir metodo buscar', function () {
    expect(scope.buscar).toBeDefined();
  });

  it('Debe de agregar un item a la lista', function () {
    scope.toggleSelectionF(1);
    expect(scope.genreSelectionF.length).toBe(1);
  });
  
  it('Debe de agregar y despues eliminar un item a la lista', function () {
      scope.toggleSelectionF(1);
      scope.toggleSelectionF(1);
    expect(scope.genreSelectionF.length).toBe(0);
  });
  
  it('Debe de tener un error, si no se agrega el nombre de banda en la busqueda', function () {
      scope.busqueda.nombre = null;
      scope.buscar();
    expect(scope.error).toEqual("Debe ingresar un nombre de banda");
  });
  
  
  
  

});