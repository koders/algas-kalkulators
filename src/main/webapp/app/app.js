'use strict';

angular.module('myApp', [
    'ui.bootstrap',
    'angular-loading-bar'
])
    .controller('MainCtrl', function ($scope, mainFactory, $timeout) {

        $scope.neto1 = {};
        $scope.neto2 = {};
        $scope.neto3 = {};
        $scope.salary4 = {};
        $scope.salary5 = {};

        /**
         * Step 1 method - fast and ugly
         */
        $scope.calculate1 = function (bruto) {
            var social = bruto * 10.50 / 100;
            var IIN = (bruto - social - 75) * 23 / 100;
            IIN = Math.max(IIN, 0);
            $scope.neto1 = bruto - social - IIN;
        };

        /**
         * Step 2 method
         */
        $scope.calculate2 = function (bruto) {
            mainFactory.calculateNeto2(bruto, function (data) {
                $scope.neto2 = data.data;
            });
        };

        /**
         * Step 3 method
         */
        $scope.calculate3 = function (bruto, dependents) {
            mainFactory.calculateNeto3(bruto, dependents, function (data) {
                $scope.neto3 = data.data;
            });
        };

        /**
         * Step 4 method
         */
        $scope.calculate4 = function (bruto, dependents) {
            mainFactory.calculateNeto4(bruto, dependents, function (data) {
                $scope.salary4 = data.data;
            });
        };

        /**
         * Step 5 method
         */
        $scope.calculate5 = function (bruto, dependents) {
            mainFactory.calculateNeto4(bruto, dependents, function (data) {
                // Basically add key, value pairs to salary5 object, so we don't lose existing salary5 object data
                for (var i in data.data) {
                    if (data.data.hasOwnProperty(i)) {
                        $scope.salary5[i] = data.data[i];
                    }
                }
            });
        };

        /**
         * Initializes currencies
         */
        mainFactory.getCurrencyRates(function (data) {
            $scope.latestRates = data.data;
            $scope.currencies = [];

            // Initialize EUR currency
            var euroCurrency = {name: 'EUR', rate: 1};
            $scope.salary5.currency = euroCurrency;
            $scope.currencies.push(euroCurrency);

            // Remodeling currency rates to {name, value} objects instead of key:value pairs
            for (var i in $scope.latestRates.rates) {
                if ($scope.latestRates.rates.hasOwnProperty(i)) {
                    $scope.currencies.push({name: i, rate: $scope.latestRates.rates[i]});
                }
            }
        });

        $scope.infoToggle = function () {
            $scope.hideInfo = !$scope.hideInfo;
            $scope.infoText = $scope.hideInfo ? 'Rādi mani' : 'Paslēp mani';
        };

        $scope.go = function() {
            $scope.g = true;
            $timeout(function(){$scope.g = false;}, 200);
        };

        $scope.hideInfo = true;
        $scope.infoToggle();
    })

/**
 * Main factory for rest api calls
 */
    .factory('mainFactory', function ($http) {
        return {
            calculateNeto2: function (bruto, callback) {
                $http.get('rest/' + Constants.CALCULATE2 + '?bruto=' + bruto).then(function (data) {
                    callback(data);
                });
            },
            calculateNeto3: function (bruto, dependents, callback) {
                $http.get('rest/' + Constants.CALCULATE3 + '?bruto=' + bruto + '&dependents=' + dependents).then(function (data) {
                    callback(data);
                });
            },
            calculateNeto4: function (bruto, dependents, callback) {
                //usSpinnerService.spin('spinner');
                $http.get('rest/' + Constants.CALCULATE4 + '?bruto=' + bruto + '&dependents=' + dependents).then(function (data) {
                    callback(data);
                });
            },
            // Call get on currency rate service
            getCurrencyRates: function (callback) {
                //usSpinnerService.spin('spinner');
                $http.get('http://api.fixer.io/latest').then(function (data) {
                    callback(data);
                });
            }
        }
    })

/**
 * Tab directive
 */
    .directive('myTab', function () {
        return {
            restrict: 'E',
            scope: {
                title: '@',
                calculate: '=',
                result: '=',
                showDependentsVal: '@showDependents',
                salaryObjectVal: '@salaryObject',
                useCurrencyVal: '@useCurrency',
                currencies: '='
            },
            templateUrl: 'app/directives/tab.html',
            link: function (scope) {
                // convert to booleans
                scope.showDependents = scope.showDependentsVal === 'true';
                scope.salaryObject = scope.salaryObjectVal === 'true';
                scope.useCurrency = scope.useCurrencyVal === 'true';
                // Initialize EUR currency
                if (scope.useCurrency) {
                    scope.currency = {name: 'EUR', rate: 1};
                }
            }
        };
    });