/*
 * Hand.java
 * <your name>
 * <date>
 * <period>
 */
 
// delete next line if using jGrasp. Else, modify to reflect the correct package.
package Test;

import java.util.ArrayList;
public class Hand {
    // instance variables
    private ArrayList<Card> hand;
    
    // Constructor
    public Hand(){
        hand = new ArrayList<Card>();
    }
    
    // Mutators
    // mutator - add a car
    public void addCard(Card c){
       hand.add(c);
    }
    
    // mutator - remove a card. Return true if
    // successful, else false. Make certain the card is
	// in the hand before attempting to remove it.
    public boolean removeCard(Card c){
       if(hand.contains(c)){
          hand.remove(c);
          return true;
       }
       return false;
    }
    
    // Accessors
    // int get number of cards.
    public int getNumberOfCards(){
       return hand.size();
    }
    
    // calculate and return the value of the hand in scoring for
    // game of 21. Face cards have a value of 10. An ace is 1 or 11, 
    // depending on which brings the total closer to or equal to 21
    // without going over. Examples:
	//   an Ace with a Queen totals 21.
	//   an Ace with a Jack and a 7 totals 18.
	//
	// NOTE: This method will be longer than the others in this class.
	
    public int getTotalForGameOf21(){
      int total=0;
      for(Card c:hand){
          if(c.getFaceValueAsInt()>=10){
              total+=10;
          }else if(c.getFaceValueAsInt()!=1){
              total+=c.getFaceValueAsInt();
          }
      }for(Card c:hand){
          if(c.getFaceValueAsInt()==1){
              if(total<=10)
                total+=11;
              else
                  total+=1;
          }
        }
      return(total);
    }
    // return the hand of cards as an ArrayList. Do not provide a
    // reference to the original. Make a copy.
    public ArrayList<Card> getList(){
       ArrayList<Card> tempHand = new ArrayList<Card>();
       for(Card c: hand){
          tempHand.add(c);
       }
       return tempHand;
    }
	
    // return true of the card is in the hand. Else, return false.
    public boolean peekCard(Card c){
        for(Card car:hand)
            if(car==c)
                return(true);
        return(false);
    }
    
    // return number of cards with this face value as a String
    public int numberOfCardsOfFaceValue(String face){
        int total=0;
        for(Card c:hand)
            if(c.getFaceValueAsString()==face)
                total++;
        return(total);
    }
    
    // return number of cards with this face value as an int
    public int numberOfCardsOfFaceValue(int face){
        int total=0;
        for(Card c:hand)
            if(c.getFaceValueAsInt()==face)
                total++;
        return(total);
    }
    
    // return number of cards with this suit
    public int numberOfCardsOfSuit(String suit){
        int total=0;
        for(Card c:hand)
            if(c.getSuit()==suit)
                total++;
        return(total);
    }
    
    // return an ArrayList of cards with this face value
    public ArrayList<Card> getCardsOfFaceValue(String face){
       ArrayList<Card> tempHand = new ArrayList<Card>();
       for(Card c: hand){
          if(c.getFaceValueAsString().equals(face)){
             tempHand.add(c);
          }
       }
       return tempHand;
    }
    
    // return an ArrayList of cards with this face value
    public ArrayList<Card> getCardsOfFaceValue(int face){
       ArrayList<Card> tempHand = new ArrayList<Card>();
       for(Card c: hand){
          if(c.getFaceValueAsInt()==(face)){
             tempHand.add(c);
          }
       }
       return tempHand;
    }
    
    // return an ArrayList of cards with this suit
    public ArrayList<Card> getCardsOfSuit(String suit){
       ArrayList<Card> tempHand = new ArrayList<Card>();
       for(Card c: hand){
          if(c.getSuit().equals(suit)){
             tempHand.add(c);
          }
       }
       return tempHand;
    }
    
    public String toString(){
       String str = "";
       for(Card c: hand){
          str += c + "\n";
       }
       return str;
    }

}
