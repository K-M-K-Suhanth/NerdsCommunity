myModule.controller("JobController", function($scope, $rootScope, $location,
		$http) {
	$scope.jobdetail = {
		'jobid' : '',
		'salary' : '',
		'vacancies' : '',
		'company' : '',
		'position' : '',
		'posteddate':'',
		'location' : '',
		'requirments' : '',
		'lastdate':''
		
	};

	$scope.jobdetailData;
	
	$scope.addJob = function() {
		alert('Job Added Succesfully!');
		$location.path('/UserHome');
		console.log("add jobdetail");
		$http.post('http://localhost:8055/NeardsCommunityAccess/addJob',$scope.jobdetail)
		.then(function(response) 
		{
		});
	}
	
	function showJobs() {
		console.log("show jobdetail");
		$http.get('http://localhost:8055/NeardsCommunityAccess/showJobs')
		.then(function(response) 
		{
			$scope.jobdetailData = response.data;
			console.log($scope.jobdetailData);
		});
	}
	showJobs();
	
	$scope.applyjob=function(jobid)
	{
		alert('Applied!');
		$location.path('/UserHome');
		$http.get('http://localhost:8055/NeardsCommunityAccess/applyjob/'+jobid)
		.then(function(response)
		{
			console.log('Applying for job');
				
		});
	}
});