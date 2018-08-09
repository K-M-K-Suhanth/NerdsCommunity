myModule.controller("ForumController",function($scope,$http,$rootScope,$location)
		{	
			$scope.forum={'forumId':0,'forumName':'','forumContent':'','createDate':'','loginname':'','status':''};
			
			$scope.forumData;
			
			$scope.forumDiscussion={'discussionId':0,'forumId':'','discussionText':'','loginname':'','discussionDate':''};
			
			$rootScope.forumDiscussionData;
			
			$rootScope.forumInfo;
			
			
			
			$scope.addForum=function()
			{	
				alert('Forum Added Successfully!');
				console.log("Add fn");
				$http.post('http://localhost:8055/NeardsCommunityAccess/addForum',$scope.forum)
				.then(function(response)
				{
				});
			}
			
			function showForum()
			{
				console.log("Show fn");
				$http.get('http://localhost:8055/NeardsCommunityAccess/listForums')
				.then(function(response)
				{
					$scope.forumData=response.data;
					console.log($scope.forumData);
				});
			}
			
			$scope.approveForums=function(forumId)
			{
				alert('Approved!');
				$http.get('http://localhost:8055/NeardsCommunityAccess/approveForum/'+forumId)
				.then(function(response)
				{
					console.log('Approving the Forum');
				});
			}
			
			$scope.rejectForums=function(forumId)
			{
				alert('Rejected!');
				$http.get('http://localhost:8055/NeardsCommunityAccess/rejectForum/'+forumId)
				.then(function(response)
				{
					console.log('Rejecting the Forum');
				});
			}
			
			$rootScope.forumDiscussion1=function(forum)
			{
				console.log('I am in Forum Discussion Controller');
				$http.get('http://localhost:8055/NeardsCommunityAccess/getAllForumDiscussion/'+forum.forumId)
				.then(function(response)
				{
					$rootScope.forumInfo=forum;
					console.log($rootScope.forumInfo);
					$rootScope.forumDiscussionData=response.data;
					$location.path('/forumDiscussion');	
				});
				
			}
			
			$scope.insertForumDiscussion=function()
			{
				alert('Discussion Added!');
				console.log('Inserting Forum Discussion');
				$scope.forumDiscussion.loginname=$rootScope.currentUser.loginname;
				$scope.forumDiscussion.forumId=$rootScope.forumInfo.forumId;
				$http.post('http://localhost:8055/NeardsCommunityAccess/insertForumDiscussion',$scope.forumDiscussion)
				.then(function(response)
				{
					console.log('Successful');
					$location.path('/forumDiscussion');	
				});
			}
			showForum();
		
		});
			
			
			
		
			
		