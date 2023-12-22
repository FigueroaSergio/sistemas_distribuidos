 /*
 *  simpleWebsocket.js
 *
 *  @author Jose Simo. (c) ai2-UPV Creative Commons
 *  Rev: 2022
 */

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
/// Websockets:
////////////////////////////////////////////////////////////////////////////////

var socket = null;

function webSocketConnect() {
	
	console.log("Connecting websocket");
	if (socket != null) {
		socket.close();
		console.log("Closing websocket.");
	}
	//Get the url of websocket from the html document
	var baseURL = document.getElementById("callForm").elements["baseURL"].value;
    //
    
    socket = new WebSocket(baseURL); //"ws://localhost:8080/..."
    socket.onmessage = onMessage;
};

function webSocketClose() {
	
	if (socket != null) {
		socket.close();
		console.log("Closing websocket.");
	}
};

function onMessage(event) {
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	///Get data.
    //var data = JSON.parse(event.data);
	var responseStr = event.data;
	/////
	document.getElementById("SensorValue").innerHTML = "Received message: " +  responseStr;
	//
	//   
};

function formStart() {
	
	if (socket != null) {
	    socket.send("{\"start\":\"true\"}");

	}
};

function formStop() {
	
	if (socket != null) {
	    socket.send("{\"stop\":\"true\"}");

	}
};
