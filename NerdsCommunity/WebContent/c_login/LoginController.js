myModule.controller("LoginController", function($scope, $http, $rootScope,
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
});
