 /*
 * GameOf21.java
 * by w p osborne
 * 2/13/2022

*//*
The goal of the game is to for the player and computer to see who can come 
closest to a value of 21 without going over. Exceeding a total of 21 is an
instant loss.
 of the game is to for the player and computer to see who can come 
Ties are awarded as a win to the computer.

The play process is:
   1. The computer draws two cards for itself, calculates the total of their
      face values, and displays the cards and total. 
      IMPORTANT: if these two cards total 21 the computer automatically wins.
   2. The player is given two cards and their total. The player can then ask 
      for additional cards, one at a time, in an effort to exceed the totalhout going over. Exceeding a total of 21 is an
      of the computer's first two cards without going over. 
      IMPORTANT: If the player goes over 21 the player loses instantly.
   3. After the player stops drawing cards and if the player hasn't gone
      over a total of 21 the computer draws cards in an effort to equal or
      exceeed the player's total. If the computer is successful, it wins. If
      it goes over 21 in the effort, it loses.

Calculating totals:
   Each card has the face value returned by the card objects getFaceValueAsInt()
   method with the following exceptions:
   1. An Ace is either a 1 or 11, depending on which gives the higher total
      without that total exceeding 21.
   2. Face cards (Jack, Queen, and King) have a value of 10, regardless of
      what the getFaceValueAsInt() method returns.

Finishing the session:
After the player chooses to stop playing the program prints the number of 
wins and the number of games played. It then thanks the user.
*/

///// PACKAGE STATEMENT - Be sure to comment out if using jGrasp or change
// to correct package if using NetBeans.
package Test;
import DrawPanel.DrawingPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Math;  
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
public class GameOf21 {
   // class constants
   private static final Color background=new Color(252, 136, 237);
   private static int SF;
   public static Graphics g;
   public static DrawingPanel p;
   public static void main(String[] args){
   
// create a card deck4
      p = new DrawingPanel((int)(1920/1.5),(int)(1080/1.5));
      SF=p.getWidth()/100;
      g = p.getGraphics();
      g.setColor(background);
      g.fillRect(0, 0, p.getWidth(),p.getHeight());
      CardDeck deck = new CardDeck();
      // Scanner for keyboard 
      Scanner kb = new Scanner(System.in);
      // counters
      int gamesTotal = 0;
      int gamesWon = 0;
      int money = 100;
      char mainMenuSelection;
      
      do{
         System.out.println();
         System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
         System.out.println("Would you like to play a game?");
         mainMenuSelection = getMenuSelection("Enter Y for yes, N for no:",
                                                "YN", kb);
         if(mainMenuSelection == 'Y'){      
             g.fillRect(0*SF, 0*SF, 100*SF,100*SF);

            g.fillRect(25*SF, 40*SF, 100*SF,30*SF);
            int bet;
            do{
                System.out.println("How much would you like to bet? you have $"+money);        
                bet=kb.nextInt();
            }while(bet>money);
            gamesTotal++;
            Branches bran=playGame(kb, deck);
            if(bran.BranchTotal.size()==1){
                if(bran.BranchWon.get(0)==1){
                   System.out.println("CONGRATULATIONS! You won that game.");
                   money+=bet*(bran.BranchDoubled.get(0)?2:1);
                   gamesWon++;
                } else if(bran.BranchWon.get(0)==0){
                   System.out.println("SORRY but you lost that game.");
                   money-=bet*(bran.BranchDoubled.get(0)?2:1);
                } else if(bran.BranchWon.get(0)==3){
                   System.out.println("You got blackjacck! 3/2 payout");
                   money+=bet*(bran.BranchDoubled.get(0)?2:1)*1.5;
                }else{
                    System.out.println("You pushed, your money hasn't changed");
                }
            }
            else{
                int total=0;
                for(int i=0;i<bran.BranchTotal.size();i++){
                    if(bran.BranchWon.get(i)==1){
                        total+=bran.BranchDoubled.get(i)?2:1;
                    }
                    if(bran.BranchWon.get(i)==0){
                        total-=bran.BranchDoubled.get(i)?2:1;
                    }
                }
                
                System.out.println("You played "+bran.BranchTotal.size()+" split hands, with a total winning of "+bet*total);
                money+=bet*total;
                
            }
            
            System.out.println("You now have $"+money);
         }
      } while(mainMenuSelection != 'N');
      System.out.println("\nYou won " + gamesWon + " games out of " + gamesTotal);
      System.out.println("\nYou have $" + money);
      if(money<50){
        System.out.println("loser");
      }else{
        System.out.println("Thanks for playing.");
      }
   }
   public static Image getCardImage(Card c){
       int newNum=c.getFaceValueAsInt();
       if(newNum!=1)
           newNum=(14-newNum);
       else
           newNum--;
       newNum+=(c.getCardNumber()/13)*13;
       String formatted = String.format("%03d",newNum+1);
       Image im=p.loadImage("C:/Users/Emily/Documents/PlayingCardsFullDeckCardGamesAlltheSuitsClipArtClipartCommercial-1/playingcardsplain_"+formatted+".png");
       return im;
   }
   public static void drawHand(Hand h,boolean IsComputer){
       ArrayList<Card> c = new ArrayList<>(h.getList());
       if(IsComputer)
           g.fillRect(25*SF, 0, 100*SF,20*SF);
       else
           g.fillRect(25*SF, 40*SF, 100*SF,30*SF);
       if(c.size()<=5){
           for(int i=0;i<c.size();i++){
               if(IsComputer)
                   g.drawImage(getCardImage(h.getList().get(i)),(25+(75-(15*c.size()))/2)*SF+(15*i*SF), 2, 15*SF, 20*SF, p);
               else
                   g.drawImage(getCardImage(h.getList().get(i)),(25+(75-(15*c.size()))/2)*SF+(15*i*SF), 40*SF, 15*SF, 20*SF, p);
           }
       }
       
   }

   public static void drawHand(Hand h, Card car){
       ArrayList<Card> c = new ArrayList<>(h.getList());
       
       c.add(car);
       g.fillRect(25*SF, 40*SF, 100*SF,30*SF);
       if(c.size()<=5){
           for(int i=0;i<c.size();i++){
               g.drawImage(getCardImage(c.get(i)),(25+(75-(15*c.size()))/2)*SF+(15*i*SF), 0, 15*SF, 20*SF, p);
           }
       }
       
   }
   public static Branches playGame(Scanner kb, CardDeck deck){
      Branches b;
      Hand computerHand = new Hand(); 
      Hand secondCard=new Hand();
      computerHand.addCard(getACard(deck));
      secondCard.addCard(getACard(deck));
      int computerTotal = computerHand.getTotalForGameOf21();

      System.out.println();
      printHand("Computer's hand:", computerHand);
      
      // If computer's first two cards total 21 computer automatically
      // wins, since the best the player can do is tie.
      if(computerTotal+secondCard.getTotalForGameOf21() == 21){
         b=new Branches();
         b.BranchWon.add(0);
         b.BranchTotal.add(0);
         b.BranchDoubled.add(false);
         drawHand(computerHand, secondCard.getList().get(0));
         
         return(b);
      }
      else{
      Image im2=p.loadImage("C:/Users/Emily/Documents/PlayingCardsFullDeckCardGamesAlltheSuitsClipArtClipartCommercial-1/playingcardsplain_055.png");
      
      g.fillRect(0*SF, 0*SF, 100*SF,30*SF);
      g.drawImage(im2,55*SF, 0, 15*SF, 20*SF, p);
      g.drawImage(getCardImage(computerHand.getList().get(0)),40*SF, 0, 15*SF, 20*SF, p);
      b = playerDraws(kb, deck);
      for(int i=0;i<b.BranchTotal.size();i++){
        int playerTotal=b.BranchTotal.get(i);
        if(playerTotal <= 21){
           computerHand.addCard(secondCard.getList().get(0));
           computerTotal = computerDraws(playerTotal, computerTotal, 
                   computerHand, deck);
           if(computerTotal <= 21 && computerTotal > playerTotal){
              b.BranchWon.add(0);
           }
        } else {
           b.BranchWon.add(0);
        }
        if(computerTotal==playerTotal)
            b.BranchWon.add(2);
        else if(playerTotal==21){
            b.BranchWon.add(3);
        }else{
            b.BranchWon.add(1);
        }
      }
      }

       drawHand(computerHand, true);
       return(b);
      
    }
   
   public static Branches playerDraws(Scanner kb, CardDeck deck){
      //ArrayList<Card> hand = new ArrayList<Card>();
      Hand hand = new Hand();
      boolean doubled=false;
      Branches b =new Branches();
      // draw first two cards
      hand.addCard(getACard(deck));
      hand.addCard(getACard(deck));
       drawHand(hand,false);
      boolean split=false;
      // evaluate hand
      int handTotal = hand.getTotalForGameOf21();
      
      // print hand
      printHand("Player's Hand:", hand);
      
      
      char selection;
      do {
          if(hand.getTotalForGameOf21()==21){break;}
          ArrayList<Card> tempHand = hand.getList();
         if(tempHand.size()==2){
             if(tempHand.get(0).getFaceValueAsInt()==tempHand.get(1).getFaceValueAsInt()||(tempHand.get(0).getFaceValueAsInt()>9&&tempHand.get(1).getFaceValueAsInt()>9)){
                System.out.println("Would you like to hit, split, double, or stand");
                selection = getMenuSelection("Enter H, P, D, S: ", "HPDS", kb);
             if(selection == 'P'){
                playerDraws(kb, deck,b,tempHand.get(0));
                playerDraws(kb, deck,b,tempHand.get(1));
                split=true;
                break;
             }
             }else{
             System.out.println("Would you like to hit, double, or stand");
             selection = getMenuSelection("Enter H, D, S: ", "HDS", kb);
             }
             if(selection == 'D'){
                hand.addCard(getACard(deck));
                handTotal = hand.getTotalForGameOf21();
                printHand("Player's Hand:", hand);
                doubled=true;
                break;
             }             
         }
         else{
            System.out.println("Hit or stand? ");
            selection = getMenuSelection("Enter H or S:", "HS", kb);
         }
         if(selection == 'H'){
            hand.addCard(getACard(deck));
            handTotal = hand.getTotalForGameOf21();
            printHand("Player's Hand:", hand);
             drawHand(hand,false);
         }
      } while(selection != 'S' && handTotal < 21);
       
      if(!split){
        b.BranchTotal.add(handTotal);
        b.BranchDoubled.add(doubled);
      }
       drawHand(hand,false);
      return(b);
   }
   public static Branches playerDraws(Scanner kb, CardDeck deck,Branches bran,Card c){
       
      g.fillRect(25*SF, 40*SF, 100*SF,30*SF);
      //ArrayList<Card> hand = new ArrayList<Card>();
      Hand hand = new Hand();
      boolean doubled=false;
      // draw first two cards
      boolean split =false;
      hand.addCard(c);
      // evaluate hand
      int handTotal = hand.getTotalForGameOf21();
      // print hand
      
      printHand("Player's Hand:", hand);
      char selection;
      do {
          ArrayList<Card> tempHand = hand.getList();
         if(tempHand.size()==2){
             if(tempHand.get(0).getFaceValueAsInt()==tempHand.get(1).getFaceValueAsInt()){
                System.out.println("Would you like to hit, split, or stand");
                selection = getMenuSelection("Enter H, P, S: ", "HPS", kb);
                if(selection == 'P'){
                    playerDraws(kb, deck,bran,tempHand.get(0));
                    playerDraws(kb, deck,bran,tempHand.get(1));
                    split=true;
                    break;
                }
                
             }
             else{
             System.out.println("Would you like to hit,or stand");
                selection = getMenuSelection("Enter H, S: ", "HS", kb);
             
             }     
         }
         else{
            if(tempHand.size()==1){
                System.out.println("Would you like to hit, double, or stand");
               selection = getMenuSelection("Enter H, D, S: ", "HDS", kb);


               if(selection == 'D'){
                  hand.addCard(getACard(deck));
                  handTotal = hand.getTotalForGameOf21();
                  printHand("Player's Hand:", hand);
                  doubled=true;
                  break;
               }        
            }
            else{
               System.out.println("Hit or stand? ");
               selection = getMenuSelection("Enter H or S:", "HS", kb);
            }
            
         }
         if(selection == 'H'){
            hand.addCard(getACard(deck));
            handTotal = hand.getTotalForGameOf21();
            printHand("Player's Hand:", hand);
             drawHand(hand,false);
         }
      } while(selection != 'S' && handTotal < 21);
      
      if(!split){
        bran.BranchTotal.add(handTotal);
        bran.BranchDoubled.add(doubled);
      }
       drawHand(hand,false);
      return(bran);
   }
   
   public static int computerDraws(int playerTotal, int computerTotal,Hand hand, CardDeck deck){
       drawHand(hand,true);
      int handTotal = computerTotal;
      while(handTotal < 17){
          drawHand(hand,true);
         hand.addCard(getACard(deck));
         handTotal = hand.getTotalForGameOf21();
      }
      printHand("Computer hand:", hand);
      return handTotal;
   }
   
   public static void printHand(String title, Hand hand){
      int total = hand.getTotalForGameOf21();
      System.out.println();
      System.out.println(title);
      for(Card c: hand.getList()){
         System.out.println(c);
      }
      System.out.println("Hand total: " + total);
      System.out.println();
   }

   public static char getMenuSelection(String message, String choices,
                                       Scanner kb){      
      String entry;
      char returnChar;
      
      do {
         System.out.print(message +  " ");
         entry = kb.next();
         entry = entry.toUpperCase();
         returnChar = entry.charAt(0);
      } while(!checkSelection(choices, returnChar));

      return returnChar;
   }
   
   public static boolean checkSelection(String choices, char targetChar){
      return choices.indexOf(targetChar) != -1;
   }
   
   public static Card getACard(CardDeck deck){
      if(deck.getNumberOfCardsDrawn() >= 52){
         System.out.println("Now drawing from a new deck.");
         deck.resetDeck();
      }
      return deck.drawACard();
   }

}
