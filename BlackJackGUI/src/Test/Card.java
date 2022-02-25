/*
 * Card.java
 * <your name here>
 * <date>
 * <period>
 */
package Test;

public class Card implements Comparable<Card>{
    // Class constants
    private static final String[] faceValuesAsStrings = {"Ace", "2",
        "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen",
        "King"};
    private static final String[] suits = {"Clubs", "Hearts", "Diamonds",
        "Spades"};
    // Instance variable
    private int cardNumber;
    
    // Constructor
    public Card(int cardNumber){
        this.cardNumber = cardNumber;
    }
    
    // Accessors
    public int getFaceValueAsInt(){
        return cardNumber % 13 + 1;
    }
    
    public String getFaceValueAsString(){
        return faceValuesAsStrings[getFaceValueAsInt() - 1];
    }
    
    public String getSuit(){
        return suits[cardNumber / 13];
    }
    
    public int getCardNumber(){
       return cardNumber;
    }
    
    // Comparable interface method
    public int compareTo(Card other){
       return this.getFaceValueAsInt() - other.getFaceValueAsInt();
    }
    
    // toString
    public String toString(){
        String str = "Card: ";
        str += " the " + getFaceValueAsString();
        str += " of " + getSuit();
        return str;
    }
}
