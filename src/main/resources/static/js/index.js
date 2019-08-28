var stompClient = null;

function setConnected(connected, roomID) {
//roomID is set after button click, so initial value is undefined, it causes error, so it this if is for testing only,
// to be deleted
    if(isNaN(roomID))
        roomID=1;
    //selecting html elements with string concatenation, to be deleted
    document.getElementById('connect' + roomID).disabled = connected;
    document.getElementById('disconnect' + roomID).disabled = !connected;
    document.getElementById('conversationDiv' + roomID).style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response' + roomID).innerHTML = '';
}

function connect(roomID) {
//roomID is set after button click, so initial value is undefined, it causes error, so it this if is for testing only,
// to be deleted
    if(isNaN(roomID))
        roomID=1;
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
    if(isNaN(roomID))
        roomID=1;
    //selecting html elements with string concatenation, to be deleted
    var from = document.getElementById('from' + roomID).value;
    var text = document.getElementById('text' + roomID).value;
    stompClient.send("/chat/" + roomID, {},
        JSON.stringify({'from': from, 'text': text}));
}

function showMessageOutput(messageOutput, roomID) {
    //roomID is set after button click, so initial value is undefined, it causes error, so it this if is for testing only,
// to be deleted
    if(isNaN(roomID))
        roomID=1;
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
    if(isNaN(roomID))
        roomID=1;
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
    if(isNaN(roomID))
        roomID=1;
    //selecting html element with string concatenation, to be deleted
    var from = document.getElementById('from' + roomID).value ? document.getElementById('from' + roomID).value : 'Anonymous';
    var text = "has left";
    stompClient.send("/chat/" + roomID, {},
        JSON.stringify({'from': from, 'text': text})); //incoming message pattern
}
