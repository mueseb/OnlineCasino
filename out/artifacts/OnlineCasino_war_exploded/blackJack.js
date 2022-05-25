// let cards;
// let playerName;
// let hand = {hand:cards};
// let players = [hand]

let playerHand;
let dealerHand;

//TODO: on Start game
function startGame()
{
    giveStarterCards();
}

function giveStarterCards()
{
    //TODO fetch player cards
    //TODO: fetch dealer cards
}

function onDoubleDown()
{
    onHit();
    endGame();
}

function onHit()
{
    //TODO: get 1 card for player
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







