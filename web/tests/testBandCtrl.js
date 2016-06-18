/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
describe('Controller: BandCtrl', function () {

  // load the controller's module
  beforeEach(module('myMusic'));

  var BandCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BandCtrl = $controller('bandCtrl', {
      $scope: scope
    });
  }));

  it('Deber ser tipo banda', function () {
//      BandCtrl.getBandInfo(7);
    expect(scope.type).toBeDefined('banda');
  });

  it('Debe existir metodo crearNoticia', function () {
    expect(scope.crearNoticia).toBeDefined();
  });
  
  it('Debe de haber pasado', function () {
    expect(scope.isPast('1995-12-12')).toEqual(true);
  });

  it('debe de agregar un item a la lista', function () {
    scope.toggleSelectionB(1);
    expect(scope.genreSelectionB.length).toBe(1);
  });
  
  it('debe de agregar y despues eliminar un item a la lista', function () {
      scope.toggleSelectionB(1);
      scope.toggleSelectionB(1);
    expect(scope.genreSelectionB.length).toBe(0);
  });
  
  

});