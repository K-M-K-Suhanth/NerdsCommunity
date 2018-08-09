myModule.controller("RegisterController", function($scope, $http, $rootScope,
		$location, $cookieStore) {

	$scope.user = {
		'loginname' : '',
		'password' : '',
		'username' : '',
		'emailid' : '',
		'mobile' : '',
		'role' : ''
	};

	$scope.register = function() {
		console.log('I am in register Function');
		console.log($scope.user);
		$scope.user.role = 'USER';
		$http.post('http://localhost:8055/NeardsCommunityAccess/registerUser',
				$scope.user).then(function(response) {
			console.log('User Regsitered');
			$rootScope.currentUser = $scope.user;
			$location.path('/login');
		});

	}

});