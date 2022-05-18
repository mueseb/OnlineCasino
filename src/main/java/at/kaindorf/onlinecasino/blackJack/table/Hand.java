/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack.table;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand = new ArrayList<>();

    public Hand(List<Card> hand) {
        this.hand = hand;
    }

    public Hand() {
    }

    public void addCardsToHand(List<Card> card)
    {
        hand.addAll(card);
    }

    public void addCardToHand(Card card)
    {
        hand.add(card);
    }

    public int getHandTotal()
    {
        int total=0;
        for (Card card : hand) {
            total += card.getNum();
        }
        return total;
    }

    public void clearHand()
    {
        hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getDealerHand()
    {
        List<Card> obscHand = hand;
        obscHand.remove(0);
        return obscHand;
    }
}
