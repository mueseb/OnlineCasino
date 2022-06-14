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
        .then(res => res.json())
        .then(data => {
            localStorage.setItem("balanceLS", data);
            document.getElementById("balance").innerText = data;
        })
       .then(data => document.getElementById("mainError").innerText += ", Balance:" + data);

}

function displayName(){
    document.getElementById("nameOnMainPage").innerText = " " + _username + " ";
}
