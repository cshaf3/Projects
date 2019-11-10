import java.util.*;
import java.io.BufferedReader;

 public class RollDice 
 {
  
	private int die1;
    private int die2;

        public RollDice() 
        {
            roll();
        }
        
        public void roll() 
        {
            die1 = (int)(Math.random()*6) + 1;
            die2 = (int)(Math.random()*6) + 1;
        }
                 
        public int getDie1() 
        {
           return die1;
        }
        
        public int getDie2() 
        {
           return die2;
        }
        
        public int getTotal() 
        {
           int total = die1 + die2;
           return total;
        }
 
 }