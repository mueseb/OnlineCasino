/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 06.04.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.blackJack.table;

public class Card {
    private String suit;
    private int worth;
    private String numString;
    private String cardCode;

    public Card(String suit, int worth, String numString) {
        this.suit = suit;
        this.numString = numString;
        this.worth = Math.min(worth, 10);
        this.cardCode = String.format("%c%02d",suit.charAt(0),worth);
    }

    @Override
    public String toString() {
        return suit +","+ worth +","+numString;
    }

    public String getSuit() {
        return suit;
    }

    public int getWorth() {
        return worth;
    }

    public String getNumString() {
        return numString;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    public String getCardCode() {
        return cardCode;
    }
}
