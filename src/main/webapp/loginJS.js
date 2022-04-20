function CheckInput(username, pwd, pwdR){
    let checkUsername = true;
    let checkPwd = true;
    if(username == "" || username == " "){
        document.getElementById("cUserNameLabel").style.color = "red";
        document.getElementById("cUserNameLabel").style.fontStyle = "italic";
        document.getElementById("cUserNameLabel").innerText = "* Username:";
        checkUsername = false;
    }
    else {
        document.getElementById("cUserNameLabel").style.color = "black";
        document.getElementById("cUserNameLabel").style.fontStyle = "normal";
        document.getElementById("cUserNameLabel").innerText = "Username:";
        checkUsername = true;
    }

    if(pwd != pwdR || pwd.length < 8){
        document.getElementById("pwdLable").style.color = "red";
        document.getElementById("pwdLable").style.fontStyle = "italic";
        document.getElementById("pwdLable").innerText = "* Password:";
        document.getElementById("pwd").innerText = "";

        document.getElementById("pwdRLable").style.color = "red";
        document.getElementById("pwdRLable").style.fontStyle = "italic";
        document.getElementById("pwdRLable").innerText = "* Password wiederholen:";
        document.getElementById("pwdR").innerText = "";
        checkPwd = false;
    }
    else {
        document.getElementById("pwdLable").style.color = "black";
        document.getElementById("pwdLable").style.fontStyle = "normal";
        document.getElementById("pwdLable").innerText = "Password:";

        document.getElementById("pwdRLable").style.color = "black";
        document.getElementById("pwdRLable").style.fontStyle = "normal";
        document.getElementById("pwdRLable").innerText = "Password wiederholen:";
        checkPwd = true;
    }

    if (checkUsername && checkPwd){
        document.getElementById("wrongInput").innerText = "";
        createNewUser(username, pwd);
    }
    else {
        document.getElementById("wrongInput").innerText = "Password wrong or username not available";
    }
}

function createNewUser(username, pwd){
    console.log("Creating User");

    login(username, pwd);
}

function login(username, pwd){
    let userData = {
        'username': username,
        'pwd': pwd
    }

    fetch('./api/login' ,{
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(userData)
    })
}