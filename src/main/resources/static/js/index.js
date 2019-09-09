var stompClient = null;
var userID = 1; //real ID should be set after successful login
var token;

function createChannel() {
    var jObj;
    var request = new XMLHttpRequest();
    var url = "https://localhost:8444/goChatMe/channel/createPrivateChannel";
    request.open("POST", url);
    request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    request.onload = function () {
        if (this.readyState === 4 && this.status === 200) {
            jObj = JSON.parse(request.responseText);
            /*alert(jObj.exists);*/ //property exists,
            //jObj.exists is a flag which decides whether you need to restore messages from last conversation,
            // or it's brand new conversation
            token = jObj.token;
            sendNotification(); //send private chat invitation to another user
        } else if (this.status === 400) {
            alert("blad serwera/url niepoprawne zapytanie lub parametry");

        } else {
            alert("STATUS " + this.status);
        }
    };
    request.send(
        "senderID=" + JSON.stringify(Number(document.getElementById('myID').value)) +
        "&destinationUserNickname=" + document.getElementById('chatWith').value
    );


}


function showNotification(notification) {

    var accept = confirm('User ' + notification.from + ' wants to create a private chat with you');
    if (accept) {
            var socket = new SockJS('https://localhost:8444/chat');
               stompClient = Stomp.over(socket);
            var userNickname = document.getElementById('privateFrom').value;
            token = notification.token;
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/chatroom/private/' + token + '/', function (messageOutput) {
                    showPrivateMessageOutput(JSON.parse(messageOutput.body));
                },
                {"username": userNickname});
        });
    }

}

function sendNotification() {
    var senderID = document.getElementById('myID').value;
    var from = document.getElementById('privateFrom').value;
    var destinationUserNickname = document.getElementById('chatWith').value;
    stompClient.send("/chat/notifications/" + senderID, {},
        JSON.stringify({'from': from, 'token': token, 'receiver': destinationUserNickname}));
    connectToCreatedPrivateChat()
}

function connectToCreatedPrivateChat() {

        var userNickname = document.getElementById('privateFrom').value;
        var socket = new SockJS('https://localhost:8444/chat');
        stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
            stompClient.subscribe('/chatroom/private/' + token + '/', function (messageOutput) {
                    showPrivateMessageOutput(JSON.parse(messageOutput.body));
                },
                {"username": userNickname});
        });

}

function enableNotifications() {
    userID = document.getElementById('myID').value;
    var socket = new SockJS('https://localhost:8444/chat');
    stompClient = Stomp.over(socket);
    function setNotificationsEnabled(frame) {
        document.getElementById('notifications').checked = 'checked';
    }

    stompClient.connect({}, function (frame) {
        var from = document.getElementById('privateFrom').value;
        setNotificationsEnabled(frame);
        stompClient.subscribe('/chatroom/notifications/' + userID + '/', function (notification) {
                showNotification(JSON.parse(notification.body));
            },
            {"username": from});
    });

}

function setConnected(connected, roomID) {
//roomID is set after button click, so initial value is undefined, it causes error, so it this if is for testing only,
// to be deleted
    if (isNaN(roomID))
        roomID = 1;
    //selecting html elements with string concatenation, to be deleted
    document.getElementById('connect' + roomID).disabled = connected;
    document.getElementById('disconnect' + roomID).disabled = !connected;
    document.getElementById('conversationDiv' + roomID).style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response' + roomID).innerHTML = '';
}

function showPrivateMessageOutput(privateMessageOutput) {
    var response = document.getElementById('privateResponse');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(privateMessageOutput.from + ": "
        + privateMessageOutput.text + " (" + privateMessageOutput.time + ")"));
    response.appendChild(p);
}

function connect(roomID) {
//roomID is set after button click, so initial value is undefined, it causes error, so it this if is for testing only,
// to be deleted
    if (isNaN(roomID))
        roomID = 1;
    //selecting html elements with string concatenation, to be deleted
    var username = document.getElementById('from' + roomID).value;
    var socket = new SockJS('https://localhost:8444/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true, roomID);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/chatroom/' + roomID + '/', function (messageOutput) {
                showMessageOutput(JSON.parse(messageOutput.body), roomID);
            },
            {"username": username});
    });
}

function sendMessage(roomID) {
    //roomID is set after button click, so initial value is undefined, it causes error, so it this if is for testing only,
// to be deleted
    if (isNaN(roomID))
        roomID = 1;
    //selecting html elements with string concatenation, to be deleted
    var from = document.getElementById('from' + userID).value;
    var text = document.getElementById('text' + roomID).value;
    stompClient.send("/chat/" + roomID, {},
        JSON.stringify({'from': from, 'text': text}));
}

function sendPrivateMessage(receiverNickname) {
      var from = document.getElementById('privateFrom').value;
    var text = document.getElementById('privateText').value;
    stompClient.send("/chat/private/" + token, {},
        JSON.stringify({'from': from, 'text': text}));
}

function showMessageOutput(messageOutput, roomID) {
    //roomID is set after button click, so initial value is undefined, it causes error, so it this if is for testing only,
// to be deleted
    if (isNaN(roomID))
        roomID = 1;
    //selecting html element with string concatenation, to be deleted
    var response = document.getElementById('response' + roomID);
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(messageOutput.from + ": "
        + messageOutput.text + " (" + messageOutput.time + ")"));
    response.appendChild(p);
}

function disconnect(roomID) {
    //roomID is set after button click, so initial value is undefined, it causes error, so it this if is for testing only,
// to be deleted
    if (isNaN(roomID))
        roomID = 1;
    if (stompClient != null) {
        sendLeaveMessage(roomID);
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendLeaveMessage(roomID) {
//roomID is set after button click, so initial value is undefined, it causes error, so it this if is for testing only,
// to be deleted
    if (isNaN(roomID))
        roomID = 1;
    //selecting html element with string concatenation, to be deleted
    var from = document.getElementById('from' + roomID).value ? document.getElementById('from' + roomID).value : 'Anonymous';
    var text = "has left";
    stompClient.send("/chat/" + roomID, {},
        JSON.stringify({'from': from, 'text': text})); //incoming message pattern
}
