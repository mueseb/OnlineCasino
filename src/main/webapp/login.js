let _jwt;
//Checks the input of the login inputs
//region checkInput
function checkInput(username, pwd) {
    let checkUsername = true;
    let checkPwd = true;
    document.getElementById("errorText").innerText = "";
    if (username == "" || username == " ") {
        document.getElementById("errorText").innerText += "Username is incorrect."
        checkUsername = false;
    } else {
        document.getElementById("errorText").innerText = "";
        checkUsername = true;
    }

    if (pwd.length < 8) {
        document.getElementById("errorText").innerText += "Password is incorrect.";
        checkPwd = false;
    } else {
        document.getElementById("errorText").innerText = "";
        checkPwd = true;
    }

    if (checkUsername && checkPwd) {
        createNewUser(username, pwd);
    }
}

//endregion

function login(username, pwd) {
    let userData = {
        'username': username,
        'pwd': pwd
    }

    fetch('./api/login', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(userData)
    })
        .then(res => {
            if (res.status != 200) {
                document.getElementById("errorText").innerText = "";
                document.getElementById("errorText").innerText = "Username or password wrong! (" +
                    res.status + " | " + res.statusText + ")";
            } else {
                _jwt = res.headers.get("Authorization");
                getBalance(username, pwd)
                document.getElementById("nameOnMainPage").innerText = username;
                document.getElementById("errorText").innerText = "Successful Login";
            }
        })
}

function createNewUser(username, pwd) {
    let newUserDate = {
        'username': username,
        'pwd': pwd
    }
    fetch('./api/login/createUser', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(newUserDate)
    })
        .then(res => {
            console.log(res.status)
            if (res.status != 200) {
                document.getElementById("errorText").innerText = "";
                document.getElementById("errorText").innerText = "An error accrued try again. (" +
                    res.status + " | " + res.statusText + ")";
            } else {
                document.getElementById("errorText").innerText = "";
                document.getElementById("errorText").innerText = "";
            }
        })
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
            document.getElementById("balance").innerText = data;
        })
        .then(data => document.getElementById("errorText").innerText += ", Balance:" + data);

}