let _username;
let _pwd;
window.onload = () => {
    _username = localStorage.getItem("name");
    _pwd = localStorage.getItem("pwd")
    getBalance(_username, _pwd);
    displayName();
}

function getBalance(username, pwd) {
    let newUserDate = {
        'username': username,
        'pwd': pwd
    }
    fetch('./api/game', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(newUserDate)
    })
        .then(res => {return res.text()})
        .then(data => {
            localStorage.setItem("balanceLS", data);
            document.getElementById("balance").innerText = data;
        })

}

function displayName(){
    console.log("Display Name ------------------------")
    document.getElementById("nameOnMainPage").innerText = " " + _username + " ";
}
function on() {
    document.getElementById("overlay").style.display = "block";
}

function off() {
    document.getElementById("overlay").style.display = "none";
    window.location.href = "mainPage.html";
}

function noBalance(){
    document.getElementById("overlay").style.display = "block";
}
