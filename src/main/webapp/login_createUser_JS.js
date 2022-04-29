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
        .then(res => {
            if(res.status != 200){
                //todo: display error
            }
        })
}

//region checkInput
function checkInput(username, pwd){
    let checkUsername = true;
    let checkPwd = true;
    document.getElementById("errorText").innerText ="";
    if(username == "" || username == " "){
       document.getElementById("errorText").innerText += "Username is incorrect."
        checkUsername = false;
    }
    else {
        document.getElementById("errorText").innerText = "";
        checkUsername = true;
    }

    if(pwd.length < 8){
        document.getElementById("errorText").innerText += "Password is incorrect.";
        checkPwd = false;
    }
    else {
        document.getElementById("errorText").innerText = "";
        checkPwd = true;
    }

    if (checkUsername && checkPwd){
        createNewUser(username, pwd);
    }
}
//endregion

function createNewUser(username, pwd){
    let newUserDate = {
        'username': username,
        'pwd': pwd
    }
    fetch('./api/creatUser', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(newUserDate)
    })
        .then(res => {
            if(res.status != 200){
                document.getElementById("errorText").innerText = "An error accrued try again. (" +
                    res.status + " | " + res.statusText + ")";
            }
            else{
                document.getElementById("errorText").innerText = "";
            }
        })

    login(username, pwd);
}