/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.blackJack.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hand {
    private List<Card> hand = new ArrayList<>();

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
        int ace=0;
        for (Card card : hand) {
            total += card.getNum();
            if(card.getNum()==11)
            {
                ace++;
            }
        }
        if(total>21 && ace>0)
        {
            for (Card card:hand) {
                if(card.getNum()==11)
                {
                    card.setNum(1);
                    ace--;
                }
                if(total(hand)<=21 || ace==0)
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
