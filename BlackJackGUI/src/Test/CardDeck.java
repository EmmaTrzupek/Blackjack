// CardDeck.java
// by w p osborne
// 2/12/2022

package Test;

import java.util.Random;
import java.util.Arrays;

public class CardDeck {
   // instance variables
   private boolean[] cardsDealt;
   private int numberOfCardsDrawn;
   private Random gen;
   
   // Class constants
   private static final boolean CARD_DRAWN = true;
   private static final boolean CARD_NOT_DRAWN = false;
   private static final int DECK_SIZE = 52;
   
   // Constructor
   public CardDeck(){
      cardsDealt = new boolean[DECK_SIZE];
      numberOfCardsDrawn = 0;
      gen = new Random();
   }
   
   // Methods
   public Card drawACard(){
      if(numberOfCardsDrawn >= DECK_SIZE){
         return null;  // no cards available.
      }
      int nextCardIndex;
      do{
         nextCardIndex = gen.nextInt(DECK_SIZE);
      } while (cardsDealt[nextCardIndex]);
      cardsDealt[nextCardIndex] = CARD_DRAWN;
      numberOfCardsDrawn++;
      return new Card(nextCardIndex);
   }
   
   public int getDeckSize(){
      return DECK_SIZE;
   }
   
   public int getNumberOfCardsDrawn(){
      return numberOfCardsDrawn;
   }
   
   public void resetDeck(){
      Arrays.fill(cardsDealt, CARD_NOT_DRAWN);
      numberOfCardsDrawn = 0;
   }
   
   public String toString(){
      String str = "Deck of " + DECK_SIZE + " cards of which " +
              numberOfCardsDrawn + " have been drans.";
      return str;
   }
}
