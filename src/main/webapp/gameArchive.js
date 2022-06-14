/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 24.05.2022
 * @project-name: Online Casino
 */

window.onload = () =>{
    getAllGames();
}

function getAllGames(){
    fetch('./api/game/gameArchive', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(localStorage.getItem("name"))
    })
        .then(res => {
            if(res.status == 200){
                return res.json();
            }
        })
        .then(data => {
            let table = document.getElementById("archiveTable");
            for (let i = 0; i < data.length; i++) {
                let tr = document.createElement("tr");
                let td = document.createElement("td");

                td.innerText = "";

                //  int gameID;
                //  int playerID;
                //  int bet;
                //  Hand dealerHand;
                //  Hand playerHand;
                //  Timestamp startTime;
                //  Timestamp endTime;
                //  int result;

                td.innerText += data[i].bet;
                td.innerText += data[i].startTime;
                td.innerText += data[i].endTime;
                td.innerText += data[i].result;

                let token = data[i].playerHand.split(";");

                for (let j = 0; j < token.length; j++) {
                    let image = "https://raw.githubusercontent.com/mueseb/OnlineCasino/stable/src/main/resources/assets/" + token[j] + ".png";
                    image.style.height = "20%";
                    image.style.width = w + "15%";
                    image.style.position = "center";
                    td.append(image);
                }

                token = data[i].dealerHand.split(";");

                for (let x = 0; x < token.length; x++) {
                    let image = "https://raw.githubusercontent.com/mueseb/OnlineCasino/stable/src/main/resources/assets/" + token[x] + ".png";
                    image.style.height = "20%";
                    image.style.width = w + "15%";
                    image.style.position = "center";
                    td.append(image);
                }


            }
        })
}

