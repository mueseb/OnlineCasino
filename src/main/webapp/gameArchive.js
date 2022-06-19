/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 24.05.2022
 * @project-name: Online Casino
 */

window.onload = () =>{
    getAllGames();
}

function getAllGames(){

    fetch('./api/game/getPlayerGames', {
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
            console.log(data[0]);
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

                /*23*/

                let startTime, endTime;

                startTime = data[i].startTime + "";
                endTime = data[i].endTime + "";

                startTime = startTime.substring(0, 23);
                startTime = startTime.replaceAll("T", " ");

                endTime = endTime.substring(0, 23);
                endTime = endTime.replaceAll("T", " ");





                td = document.createElement("td");
                td.innerText += startTime;
                tr.append(td);
                td = document.createElement("td");
                td.innerText += endTime;
                tr.append(td);
                td = document.createElement("td");
                td.innerText += data[i].result;
                tr.append(td);
                td = document.createElement("td");
                td.innerText += data[i].bet;
                tr.append(td);
                td = document.createElement("td");
                table.append(tr);

                // let token = data[i].playerHand.split(";");
                //
                // for (let j = 0; j < token.length; j++) {
                //     let image = "https://raw.githubusercontent.com/mueseb/OnlineCasino/stable/src/main/resources/assets/" + token[j] + ".png";
                //     image.style.height = "20%";
                //     image.style.width = w + "15%";
                //     image.style.position = "center";
                //     td.append(image);
                // }
                //
                // token = data[i].dealerHand.split(";");
                //
                // for (let x = 0; x < token.length; x++) {
                //     let image = "https://raw.githubusercontent.com/mueseb/OnlineCasino/stable/src/main/resources/assets/" + token[x] + ".png";
                //     image.style.height = "20%";
                //     image.style.width = w + "15%";
                //     image.style.position = "center";
                //     td.append(image);
                // }


            }
        })
}

