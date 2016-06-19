/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
describe('Controller: DiscCtrl', function () {

  // load the controller's module
  beforeEach(module('myMusic'));

  var DiscCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DiscCtrl = $controller('discCtrl', {
      $scope: scope
    });
  }));

  it('Debe existir metodo editDisc', function () {
    expect(scope.editDisc).toBeDefined();
  });
  
  
  it('Debe ser de tipo ver', function () {
    expect(scope.tipo).toEqual('view');
  });
  
  it('Debe cambier el link', function () {
      scope.viewVid('linkPrueba')
    expect(scope.detailFrame).toEqual('linkPrueba');
  });

  
  it('Debe de cambiar el tipo a editar', function () {
      scope.editDisc();
    expect(scope.tipo).toEqual('edit');
  });
  
  
  
  

});