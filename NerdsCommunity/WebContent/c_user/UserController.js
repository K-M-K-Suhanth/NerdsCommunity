myModule.controller("UserController", function($scope, $http, $rootScope,
		$location, $cookieStore) {

	$scope.user = {
		'loginname' : '',
		'password' : '',
		'username' : '',
		'emailid' : '',
		'mobile' : '',
		'role' : ''
	};

	$scope.checklogin = function() {
		alert('Logged In!');
		console.log('I am in Check Login');
		console.log($scope.user);
		$http.post('http://localhost:8055/NeardsCommunityAccess/checkLogin',
				$scope.user).then(function(response) {
			$scope.user = response.data;
			$rootScope.currentUser = response.data;
			$cookieStore.put('userDetails', response.data);
			console.log('User is Correct');
			$location.path('/UserHome');
		});

	}

	$scope.register = function() 
	{
		alert('Registerd succesfully!');
		$location.path('/login');
		console.log('I am in register Function');
		console.log($scope.user);
		$scope.user.role = 'USER';
		$http.post('http://localhost:8055/NeardsCommunityAccess/registerUser',
				$scope.user).then(function(response) {
			console.log('User Regsitered');
			$rootScope.currentUser = $scope.user;
		
		});

	}

});
