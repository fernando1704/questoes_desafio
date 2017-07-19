var app = angular.module('campanhaApp', ['ngResource']);

app.controller('campanhaController', function ($scope, $resource) {
    $scope.salvarCampanha = function (campanha) {

        var campanhaRecurso = $resource('services/campanha');

        campanhaRecurso.save(campanha).$promise.then(
                function (campanha) {
                    $scope.clear();
                    $scope.listarCampanhas();
                }, function (error) {
            $scope.mensagemError = error.data;
        });

    };

    $scope.listarCampanhas = function () {

        var campanhaRecurso = $resource('services/campanha');

        campanhaRecurso.query().$promise.then(
                function (_campanhas) {
                    $scope.campanhas = _campanhas;
                }, function (error) {
            $scope.mensagemError = error.data;
        });

    };

    $scope.removerCampanha = function (campanha) {

        var campanhaResource = $resource('services/campanha/:id', {
            id: campanha.id,
        });

        campanhaResource.delete().$promise.then(
                function (campanhas) {
                    $scope.listarCampanhas();
                }, function (error) {
            $scope.mensagemError = error.data;
        });

    };

    $scope.listarCampanhas();

});