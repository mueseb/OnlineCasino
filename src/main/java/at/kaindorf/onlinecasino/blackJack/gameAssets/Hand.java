/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack.gameAssets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hand {
    private List<Card> cards = new ArrayList<>();

    public void addCardsToHand(List<Card> card)
    {
        cards.addAll(card);
    }

    public void addCardToHand(Card card)
    {
        cards.add(card);
    }

    public int getHandTotal()
    {
        int total=0;
        int ace=0;
        for (Card card : cards) {
            total += card.getNum();
            if(card.getNum()==11)
            {
                ace++;
            }
        }
        if(total>21 && ace>0)
        {
            for (Card card: cards) {
                if(card.getNum()==11)
                {
                    card.setNum(1);
                    ace--;
                }
                if(total(cards)<=21 || ace==0)
                {
                    return total;
                }
            }
        }
        return total;
    }

    public int total(List<Card> hand)
    {
        int total=0;
        for (Card card : hand) {
            total += card.getNum();
        }
        return total;
    }

    public void clearHand()
    {
        cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> getDealerHand()
    {
        List<Card> obscHand = cards;
        obscHand.remove(0);
        return obscHand;
    }

    @Override
    public String toString() {
        String cards = "";
        for (Card card: this.cards) {
            cards+=card.getNum()+", ";
        }
        cards+="\n";
        return cards;
    }
}
