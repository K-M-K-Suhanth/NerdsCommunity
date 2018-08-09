var myModule=angular.module("MyModule",['ngRoute','ngCookies']);

myModule.config(function ($routeProvider)
		{
		
		$routeProvider.when("/",{templateUrl:"/index.html"})
						//about
						.when("/UserHome",{templateUrl:"c_user/UserHome.html"})	
						/*.when("/about",{templateUrl:"c_about/about.html"})*/
						//login
						.when("/login",{templateUrl:"c_user/Login.html"})
					    .when("/register",{templateUrl:"c_user/Register.html"})
						//blog controlles
						.when("/AddBlog",{templateUrl:"c_blog/AddBlog.html"})
						.when("/ShowBlog",{templateUrl:"c_blog/ShowBlog.html"})
						.when("/AdminBlog",{templateUrl:"c_blog/AdminBlog.html"})
						.when("/blogComment",{templateUrl:"c_blog/BlogComment.html"})
						//forum controlles
						.when("/AddForum",{templateUrl:"c_forum/AddForum.html"})
						.when("/ShowForum",{templateUrl:"c_forum/ShowForum.html"})
						.when("/AdminForum",{templateUrl:"c_forum/AdminForum.html"})
						.when("/forumDiscussion",{templateUrl:"c_forum/ForumDiscussion.html"})
						//job controlles
						.when("/AddJob",{templateUrl:"c_job/AddJob.html"})
						.when("/ShowJob",{templateUrl:"c_job/ShowJob.html"})
						//friends controlles
						.when("/chat",{templateUrl:"c_chat/Chat.html"})
						.when("/FriendList",{templateUrl:"c_friend/ShowFriendList.html"})
						.when("/PendingFriendRequest",{templateUrl:"c_friend/ShowPendingFriendRequestList.html"})
						.when("/SuggestedFriend",{templateUrl:"c_friend/ShowSuggestedFriendList.html"})
						//profile controlles
						.when("/profilePic",{templateUrl:"c_user/ProfilePictureUpdate.html"});
						
		});
myModule.run(function($rootScope,$cookieStore)
		{
		console.log('I am in run function');
		console.log($rootScope.currentUser);
		if($rootScope.currentUser==undefined)
			{
			$rootScope.currentUser=$cookieStore.get('userDetails');
			}
});