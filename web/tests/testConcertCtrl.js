/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
describe('Controller: ConcertCtrl', function () {

  // load the controller's module
  beforeEach(module('myMusic'));

  var ConcertCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ConcertCtrl = $controller('concertCtrl', {
      $scope: scope
    });
  }));

  it('Debe existir metodo verificar', function () {
    expect(scope.verificar).toBeDefined();
  });

  
  it('Debe de tener retornar true si la fecha es mayor a la fecha actual', function () {
      scope.fechaEv = new Date('1995*12-12').getTime();
      scope.verificar();
    expect(scope.puedeCalificar).toEqual(true);
  });
  
  
  
  

});