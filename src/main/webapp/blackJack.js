let cards;
let playerName;
let hand = {hand:cards};
let players = [hand]

let playerHand;
let dealerHand;

//TODO: on Start game
function startGame()
{
    giveStarterCards();

}

function giveStarterCards()
{
    fetch('.api/game/getPlayerStarterCards')
        // .then(//todo: use cards)

    fetch('.api/game/getDealerStarterCards')
        // .then(//todo: use cards)
}

function onDoubleDown()
{
    onHit();
    endGame();
}

function onHit()
{
    //TODO: get 1 card for player
    fetch('.api/game/addPlayerCard')
        // .then(//todo: add card to hand)

}

function onStand()
{
    endGame();
}

function endGame()
{
    //TODO: count player cards
    //TODO: count dealer cards
}

function checkPlayerCards()
{

}

function checkDealerCards()
{
    //TODO: get dealer card count from backend
}

function displayPlayerCards()
{

}

function displayDealerCards()
{
    
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
    let balance = document.getElementById("balance").value;

    if(balance - bet < 0){
        document.getElementById("mainError").innerText = "You dont have enough money!";
    }else {
        document.getElementById("betText").innerText += bet;
        _bets = 0;
        _bets = bet;
        document.getElementById("gameTabel").hidden = false;
        document.getElementById("firstBetTable").hidden = true;
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

