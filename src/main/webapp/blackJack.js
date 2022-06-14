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
let _src;


function startGame()
{

    let userData = {
        'username': localStorage.getItem("name"),
        'pwd': localStorage.getItem("pwd")
    }
    fetch('./api/game/initGame', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(userData)

    })
        .then(res => {
            let errorText = res.status + " | " + res.statusText;
            if(res.status == 200){
                document.getElementById("mainError").innerText = errorText;
                giveStarterCards();

                for (let i = 0; i < playerHand.length; i++) {
                    console.log(playerHand[i]);
                }
                checkPlayerCards();
                displayPlayerCards();
            }
            else{

                document.getElementById("mainError").innerText = errorText;
            }
        })


}

function giveStarterCards()
{
    fetch('./api/game/getPlayerStarterCards')
        .then(res => {
            console.log(res.status + " | " + res.statusText)
            res.text()
        })
        .then(data => {
            let token = data.split(";");
            for (let i = 0; i < token.length; i++) {
                playerHand[i] = token[i];
            }
        })

    fetch('./api/game/getDealerStarterCards')
        .then(res => {
            console.log(res.status + " | " + res.statusText)
            res.text()
        })
        .then(data => {
            let token = data.split(";");
            for (let i = 0; i < token.length; i++) {
                dealerHand[i] = token[i];
            }
        })

}

function onDoubleDown()
{
    _bets *= 2;
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
            if(res.status == 200){
                res.text();
            }
        })
        .then(data => {
            let token = data.split(";");
            for (let i = 0; i < token.length; i++) {
                playerHand[i] = token[i];
            }
        })
        .then(displayPlayerCards)
        .then(checkPlayerCards)
}

function onStand(){

    endGame();
}

function endGame()
{

    //dealer is gaming
    fetch('./api/game/getDealerCard')
        .then(res => {
            if(res.status == 200){
                res.text();
            }
        })
        .then(data => {
            let token = data.split(";");
            for (let i = 0; i < token.length; i++) {
                dealerHand[i] = token[i];
            }
        })
        .then(displayDealerCards)

    //return winner player/dealer
    fetch('./api/game/whoWon')
        .then(res => res.text())
        .then(data => winner=data)

        if(winner = "player"){
            updateBalance(_bets)
        }
        else{
            updateBalance(-_bets)
        }

    document.getElementById("mainError").innerText = "Game has ended";
}

function checkPlayerCards() //count cards
{
    fetch('./api/game/getPlayerCardCount')
        .then(res => res.text())
        .then(data => {
            document.getElementById("cardCount").innerText = data;
        })
}

function checkDealerCards()
{
    fetch('./api/game/getDealerCardCount')
        .then(res => res.text())
        .then(data => {
            document.getElementById("dealerCardCount").innerText = data;
        })
}

function displayPlayerCards()
{
    let table = document.getElementById("displayPlayerCard");
    table.innerHTML = "";
    for (let i = 0; i < playerHand.length; i++) {
        card = playerHand[i];
        playerSrc = "https://github.com/mueseb/OnlineCasino/blob/stable/src/main/resources/assets/" + card + ".png?raw=true"
        let td = document.createElement("td");
        let image = document.createElement("img");

        console.log(playerSrc);

        console.log(playerHand[0]);
        console.log(playerSrc);
        image.src = playerSrc;

        td.append(image);
        table.append(td);
    }
}

function displayDealerCards()
{
    let table = document.getElementById("displayDealerCard");
    table.innerHTML = "";
    for (let i = 0; i < dealerHand.length; i++) {
        card = dealerHand[i];
        dealerSrc = "https://github.com/mueseb/OnlineCasino/blob/stable/src/main/resources/assets/" + card + ".png?raw=true"
        let td = document.createElement("td");
        let image = document.createElement("img");

        console.log(dealerSrc);

        console.log(dealerHand[0]);
        console.log(dealerSrc);
        image.src = dealerSrc;

        td.append(image);
        table.append(td);
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


function firstBet(bet){
    let balance = parseInt(localStorage.getItem("balanceLS"))
    console.log(balance);
    console.log(bet);

    if(balance - bet < 0){
        document.getElementById("mainError").innerText = "You dont have enough money!";
    }else {
        document.getElementById("betText").innerText += " "+bet;
        _bets = 0;
        _bets = bet;
        document.getElementById("firstBetTable").hidden = true;
        document.getElementById("gameTable").hidden = false;


        startGame()
    }
}

function updateBalance(newBalance) {
    let username = document.getElementById("nameOnMainPage");

    let balanceData = {
        'username': username,
        'balance': newBalance
    }

    fetch('./api/game', {
        method: 'PATCH',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(balanceData)
    })
}



