/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 06.04.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.blackJack.table;

import at.kaindorf.onlinecasino.blackJack.data.Rank;
import at.kaindorf.onlinecasino.blackJack.data.Suit;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private Stack<Card> cardDeck = new Stack<>();
    private final List<String> suits = Stream.of(Suit.values()).map(Enum::name).collect(Collectors.toList());
    private final List<String> ranks = Stream.of(Rank.values()).map(Enum::name).collect(Collectors.toList());
    
    public Deck() {
        for (String suit : suits) {
            for (int j = 0; j < ranks.size(); j++) {
                if (j + 1 > 10) {
                    cardDeck.add(new Card(suit, 10, ranks.get(j)));
                } else {
                    cardDeck.add(new Card(suit, j + 1, ranks.get(j)));
                }
            }
        }
        Collections.shuffle(cardDeck);
    }

    public Stack<Card> getCardDeck()
    {
        return cardDeck;
    }

    public Card getCardFromDeck()
    {
        return cardDeck.pop();
    }

    public List<Card> getCardsFromDeck(int cnt)
    {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            cards.add(cardDeck.pop());
        }
        return cards;
    }
}
