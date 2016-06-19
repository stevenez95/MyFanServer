/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
describe('Controller: FanBandCtrl', function () {

  // load the controller's module
  beforeEach(module('myMusic'));

  var FanBandCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    FanBandCtrl = $controller('fanBandCtrl', {
      $scope: scope
    });
  }));
});