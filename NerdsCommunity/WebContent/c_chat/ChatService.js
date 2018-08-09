myModule.service('chatService',function($q,$timeout)
{
	console.log("in chat service");
	var service={},	listener = $q.defer(),	socket = {client:null,stomp:null},	messageIds = [];
	service.RECONNECT_TIMEOUT = 30000;
	var base_url = "http://localhost:8055/NeardsCommunityAccess";
	service.SOCKET_URL = base_url + "/chat";
	service.CHAT_TOPIC = "/topic/message";
	service.CHAT_BROKER = "/app/chat";
	service.send = function(message)
	{
		var id = Math.floor(Math.random() * 100000);
		socket.stomp.send(service.CHAT_BROKER,{priority:9},JSON.stringify({message:message,id:id}));
		messageIds.push(id);
	};
	
	service.receive = function()
	{
		return listener.promise;
	};
	
	var reconnect = function()
	{
		$timeout(function()
		{
			initialize()
		},RECONNECT_TIMEOUT);
	};
	
	var getMessage = function(data)
	{
		var message = JSON.parse(data),out={};
		out.message = message.message;
		out.time = new Date(message.time);
		return out;
	};
	
	var StartListener = function()
	{
		socket.stomp.subscribe(service.CHAT_TOPIC, function(data)
				{
					listener.notify(getMessage(data.body));
				});
	};
	
	var initialize = function()
	{
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({}, StartListener);
		socket.stomp.onClose = reconnect;
	};
	
	initialize();
	return service;
	
});






/*
app.filter('reverse', function() {
	  return function(items) {
	    return items.slice().reverse();
	  };
	});

	app.directive('ngFocus', function() {
	  return function(scope, element, attrs) {
	    element.bind('click', function() {
	      $('.' + attrs.ngFocus)[0].focus();
	    });
	  };
	});

	app.factory('socket', function($rootScope) {
	  alert('app factory')
	    var socket = new SockJS('/CollaborationBackend/portfolio');//from register STOMP end point - websocketconfiguration.java
	    var stompClient = Stomp.over(socket);
	    stompClient.connect('', '', function(frame) {
	      $rootScope.$broadcast('sockConnected', frame);
	    });

	    return {
	      stompClient: stompClient
	    };
	});




*/