/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 06.04.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.blackJack.table;

public class Card {
    private String suit;
    private int num;
    private String numString;

    public Card(String suit, int num, String numString) {
        this.suit = suit;
        this.num = num;
        this.numString = numString;
    }

    @Override
    public String toString() {
        return suit +","+ num +","+numString;
    }

    public String getSuit() {
        return suit;
    }

    public int getNum() {
        return num;
    }

    public String getNumString() {
        return numString;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
