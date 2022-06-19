/*
*Cards:
* First Card of Dealer
* F00 = First Card
* Clubs (C)
* Hearts (H)
* Spades (S)
* Diamonds (D)
* 02 - 10
* Pic Cards
* Ace = 01
* Jack = 11
* Queen = 12
* King = 13
*
* Examples
* C02 = Clubs 02
* H13 = King of Hearts
* S01 = Ace of Spades
* D11 = Jack of Diamonds
*
* Your cards should be 21, when you are over 21 u lose
* when you are under 21 you should have more than the dealer
* */

let playerHand = [];
let dealerHand = [];

let card = "F01";
let playerSrc;
let dealerSrc;
let _bets = 0;

let w = 15;
let h = 20;

let border = 5;

function startGame() {

    let userData = {
        'username': localStorage.getItem("name"),
        'bet': _bets
    }

    console.log(userData.username)
    fetch('./api/game/initGame', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(userData)

    })
        .then(res => {
            if (res.status == 200) {

                giveStarterCards();
                for (let i = 0; i < playerHand.length; i++) {
                    console.log(playerHand[i]);
                }
                // checkPlayerCards();

            }
        })

}


function giveStarterCards() {
    fetch('./api/game/getPlayerStarterCards')
        .then(res => {
            return res.text()
        })
        .then(data => {
            console.log(data)
            let token = data.split(";");
            for (let i = 0; i < token.length; i++) {
                playerHand[i] = token[i];
                console.log("Player: " + token[i])
            }
        }).then(displayPlayerCards)
    h = 20;
    w = 15;
    fetch('./api/game/getDealerStarterCards')
        .then(res => {
            return res.text()
        })
        .then(data => {
            let token = data.split(";");
            dealerHand = []
            for (let i = 0; i < token.length; i++) {
                dealerHand[i] = token[i];
                console.log("Dealer: " + token[i])
            }
        }).then(displayDealerCards)
}

function onDoubleDown() {
    _bets *= 2;
    document.getElementById("betText").innerText = "Bet: " + _bets
    onHit();
    endGame();
}

function onHit() //draw a card
{
    document.getElementById("doubleBet").style.opacity = 0.5;
    document.getElementById("doubleBet").style.transition = "100ms";
    document.getElementById("doubleBet").onclick = "";
    fetch('./api/game/addPlayerCard')
        .then(res => {
            return res.text()
        })
        .then(data => {
            let token = data.split(";");
            for (let i = 0; i < token.length; i++) {
                playerHand[i] = token[i];
            }
        })
        .then(displayPlayerCards)
}

function onStand() {

    endGame();
}

function endGame() {
    let time = dealerHand.length * 1500;
    time += 10;
    disableButtons();
    //dealer is gaming
    fetch('./api/game/addDealerCard')
        .then(res => {
            return res.text()
        })
        .then(data => {
            let token = data.split(";");
            dealerHand = []
            for (let i = 0; i < token.length; i++) {

                dealerHand[i] = token[i];
            }
        })
        .then(displayDealerCards)
        .then(res =>{sleep(time);
            on();})

    //return winner player/dealer
    fetch('./api/game/whoWon')
        .then(res => {
            return res.text()
        })
        .then(data => {
            console.log(data + " won")
            if (data === "player") {
                updateBalance(_bets)
                document.getElementById("overlayText").innerText = "You won"
            } else if (data === "dealer") {
                updateBalance(-_bets)
                document.getElementById("overlayText").innerText = "You lost"
            } else {
                updateBalance(0)

            }

        })

    document.getElementById("mainError").innerText = "Game has ended";
}

function disableButtons() {
    document.getElementById("onHitButton").style.opacity = 0.5;
    document.getElementById("onHitButton").style.transition = "100ms";
    document.getElementById("onHitButton").onclick = "";

    document.getElementById("doubleBet").style.opacity = 0.5;
    document.getElementById("doubleBet").style.transition = "100ms";
    document.getElementById("doubleBet").onclick = "";

    document.getElementById("endGameButton").style.opacity = 0.5;
    document.getElementById("endGameButton").style.transition = "100ms"
    document.getElementById("endGameButton").onclick = ""
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

// function checkPlayerCards() //count cards
// {
//     fetch('./api/game/getPlayerCardCount')
//         .then(res => res.text())
//         .then(data => {
//             document.getElementById("cardCount").innerText = data;
//         })
// }

function checkDealerCards() {
    fetch('./api/game/getDealerCardCount')
        .then(res => res.text())
        .then(data => {
            document.getElementById("dealerCardCount").innerText = data;
        })
}

function displayPlayerCards() {
    let table = document.getElementById("displayPlayerCard");
    table.innerHTML = "";
    let win;
    win = playerHand[0]
    let td = document.createElement("td");
    td.innerHTML = "";
    for (let i = 1; i < playerHand.length; i++) {
        card = playerHand[i];
        console.log("Player: " + card)
        playerSrc = "https://raw.githubusercontent.com/mueseb/OnlineCasino/stable/src/main/resources/assets/" + card + ".png"

        let image = document.createElement("img");

        // console.log(playerSrc);
        //
        // console.log(playerHand[0]);
        // console.log(playerSrc);
        image.src = playerSrc;
        image.style.height = h+"%";
        image.style.width = w + "%";
        image.style.position = "center";

        if(i == border){
            //12.5h 7.5w
            if(h != 12.5 && w != 7.5){
                border = border + 1;
                h -= 1.5;
                w -= 1.5;
            }
        }
        td.append(image);
        table.append(td);

    }
    console.log("win")
    if (win === "los") {
        endGame()
    }
}

async function displayDealerCards() {
    let table = document.getElementById("displayDealerCard");
    table.innerHTML = "";
    let td = document.createElement("td");
    td.innerHTML = "";
    for (let i = 0; i < dealerHand.length; i++) {
        card = dealerHand[i];
        console.log("Dealer: " + dealerHand[i])
        dealerSrc = "https://raw.githubusercontent.com/mueseb/OnlineCasino/stable/src/main/resources/assets/" + card + ".png"
        let image = document.createElement("img");

        // console.log(dealerSrc);

        // console.log(dealerHand[0]);
        // console.log(dealerSrc);
        image.src = dealerSrc;
        image.style.height = h+"%";
        image.style.width = w+"%";
        image.style.position = "center";
        td.append(image);
        table.append(td);
        await sleep(1000);
    }
}


//getplayercards(num)
//getdealercards(num)
//getplayerbalance info
//startgame send bet ->getplayercards -> getdealercards
//

//stand -> getWinner
//hit -> getPlayerCards /-> dealerWin

//getWinner -> dealerTurn -> getalldealerCards -> CardCount //savesStats ->write to database -> getplayerbalance


function firstBet(bet) {
    let balance = parseInt(localStorage.getItem("balanceLS"))
    console.log(balance);
    console.log(bet);

    if (balance - bet < 0 || bet == 0) {
        document.getElementById("mainError").innerText = "Not enough money"
    } else {
        document.getElementById("betText").innerText += " " + bet;
        _bets = 0;
        _bets = bet;
        document.getElementById("firstBetTable").hidden = true;
        document.getElementById("gameTable").hidden = false;


        startGame()
    }
}

function updateBalance(newBalance) {
    let username = document.getElementById("nameOnMainPage").innerText;
    console.log(_bets)

    let balanceData = {
        'name': username,
        'balance': newBalance,
        'bet': _bets
    }

    fetch('./api/game', {
        method: 'PATCH',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(balanceData)
    })
}



