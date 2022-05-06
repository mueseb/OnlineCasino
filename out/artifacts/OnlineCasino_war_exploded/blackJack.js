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

function giveStarterCards(/*playerList*/)
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


