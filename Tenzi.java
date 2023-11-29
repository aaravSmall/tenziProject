
package Tenzi;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

/**
 * @author aarav samal
 */
public class Tenzi {

	
	static ArrayList<Integer> activeDice = new ArrayList<>();
	static ArrayList<Integer> savedDice = new ArrayList<>();
	
	static int diceSaved;
	static int round = 1;
	static int playerNum = 1;
	
	static int player1type = 0;
	static int player2type = 0;
	static int player3type = 0;
	static int player4type = 0;
	
	static int player1Round = 1000;
	static int player2Round = 1000;
	static int player3Round = 1000;
	static int player4Round = 1000;
	
	static boolean gameDone = false; 
	static boolean PlayAgain = false; 
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		
		
		do{//Do while loop to make sure game is played atleast once 
			
			System.out.println("Pick: Player Mode(1)\n      Computer Mode(2)");//ask what mode they want to play
			String mode = input.next();
			
			if(mode.equals("1")) {//if playermode is chosen
				System.out.println("How many players are playing?");//ask number of players
				playerNum = input.nextInt();
				playerType(mode);
			}
			
			for(int x = 1; x <= playerNum; x++) {//Repeat game for number of players
	
				System.out.println("How many sides are on the dice you want to play with? Max 10 sides");
				int diceSides = input.nextInt();
				
				rollAllDice(savedDice, activeDice, round, diceSides);//rolling dice
				
				
				if(x == 1) {
					if(player1type == 1) {
						System.out.println("\nPlayer 1's turn");
						getNumToSave(diceSides);	
					}
					else {
						System.out.println("\nPlayer 1's turn");
						CompNumToSave();
					}
				}
				else if(x == 2) {
					if(player2type == 1) {
						System.out.println("\nPlayer 2's turn");
						getNumToSave(diceSides);	
					}
					else {
						System.out.println("\nPlayer 2's turn");
						CompNumToSave();
					}
				}
				else if(x == 3) {
					if(player3type == 1) {
						System.out.println("\nPlayer 3's turn");
						getNumToSave(diceSides);	
					}
					else {
						System.out.println("\nPlayer 3's turn");
						CompNumToSave();
					}
				}
				else if(x == 4){
					if(player4type == 1) {
						System.out.println("\nPlayer 4's turn");
						getNumToSave(diceSides);	
					}
					else {
						System.out.println("\nPlayer 4's turn");
						CompNumToSave();
					}
				}
				if(mode.equals("2")) {
					CompNumToSave();
				}
				
				gameDone = false;//reseting gameDone value
				
				while(gameDone == false) {	
			    	round++;
					
					if(round<=3) { //To prevent repeating of saved dice
						saveDice(savedDice, activeDice, diceSaved);//saving dice
						resetDice(activeDice);//reseting dice rolls for next round
					}
					
					rollAllDice(savedDice, activeDice, round, diceSides);//rolling dice
					saveDice(savedDice, activeDice, diceSaved);//saving dice
					resetDice(activeDice);//reseting dice
					gameDone = gameWon(gameDone, savedDice, x);//checking is the game has been won or not
				}
				
				resetDice(activeDice);//resetting dice
				resetDice(savedDice);//resetting saved dice rolls
				saveData(round, x);
				round = 1;//setting round numbers back to 1
				
				player1type = player2type = player3type = player4type = 0;
				
				System.out.print("Press any leter to continue for next round...");//Pausing so player can see their results
				String idk = input.next();
			}	
			
			WhoWon();
			PlayAgain = PlayAgain(PlayAgain);//Asking user if they want to play again

		}while(PlayAgain == true);//checking if they want to play again or not 
		 
		
		
	}
	
	public static int rollDie(int sides) {//rolling one dice
		
		 int rand = (int)(Math.random() * sides) + 1;//setting dice value to a number between 1-6
		 return rand;
	}
	
	public static void rollAllDice(ArrayList<Integer> a, ArrayList<Integer> b, int c, int d) {//rolling all the dice

		for(int x = 0; x < 10-a.size(); x++){
			b.add(rollDie(d));//adding rolled dice to activeDice array list 
		}
		 
		System.out.println("\nRound: " + c + "\nYour dice rolls are: "); //printing dice rolls
		print(b);
		 
	}
	
    public static void print(ArrayList<Integer> a) {//printing dice using cool images
    	
    	for(int x = 0; x < a.size(); x++){
			// System.out.print(a.get(x) + ", ");
    		if(a.get(x) == 1) {
    			System.out.print("\n+-------+\n|       |\n|   o   |\n|       |\n+-------+");
    		} 
    		if(a.get(x) == 2) {
    			System.out.print("\n+-------+\n|o      |\n|       |\n|      o|\n+-------+");
    		}
    		if(a.get(x) == 3) {
    			System.out.print("\n+-------+\n|o      |\n|   o   |\n|      o|\n+-------+");
    		}
    		if(a.get(x) == 4) {
    			System.out.print("\n+-------+\n|o     o|\n|       |\n|o     o|\n+-------+");
    		}
    		if(a.get(x) == 5) {
    			System.out.print("\n+-------+\n|o     o|\n|   o   |\n|o     o|\n+-------+");
    		}
    		if(a.get(x) == 6) {
    			System.out.print("\n+-------+\n|o     o|\n|o     o|\n|o     o|\n+-------+");
    		}
    		if(a.get(x) == 7) {
    			System.out.print("\n+-------+\n|o     o|\n|o  o  o|\n|o     o|\n+-------+");
    		}
    		if(a.get(x) == 8) {
    			System.out.print("\n+-------+\n|o  o  o|\n|o     o|\n|o  o  o|\n+-------+");
    		}
    		if(a.get(x) == 9) {
    			System.out.print("\n+-------+\n|o  o  o|\n|o  o  o|\n|o  o  o|\n+-------+");
    		}
    		if(a.get(x) == 10) {
    			System.out.print("\n+-------+\n|o  o  o|\n|o o o o|\n|o  o  o|\n+-------+");
    		}
		}
		
    	
	}
	
	public static void resetDice(ArrayList<Integer> a) {//resetting dice by removing them all from the array list Fifth Additional method
		
		for(int x = a.size()-1; x >= 0; x--) {
			a.remove(x);
		} 
	}
		                  
	public static void getNumToSave(int a) {//Asking user for what number to save
		Scanner input = new Scanner(System.in);
		
			System.out.println("\n\nWhat number do you want to save?");//asking what number to save
			diceSaved = input.nextInt();
			
			if(diceSaved > a) {
				System.out.println("Please enter a number between 1-"+a+": ");//checking number is between 1 and the max number of sides on the dice
				diceSaved = input.nextInt();
			}
			else if(diceSaved < 1) {
				System.out.println("Please enter a number between 1-"+a+": ");//checking number is between 1 and the max number of sides on the dice
				diceSaved = input.nextInt();
			}
	}
 	
	public static void CompNumToSave() {//picking a number to save for the computer. First additional method
		diceSaved = (int)(Math.random() * 6) + 1;
	}
	
	public static void saveDice(ArrayList<Integer> a, ArrayList<Integer> b, int c) {//saving dice by adding it to the saved arraylist
		
		for(int x = b.size()-1; x >=0; x--) {
			if(b.get(x) == c) {
				int transfer = b.remove(x);
				a.add(transfer);
			}
		}
		System.out.println("\nYour saved dice rolls are: " + a); 

	}
	
	public static boolean gameWon(boolean a, ArrayList<Integer> b, int c) {//checking if the game was won
		
		if(b.size() >= 10) {
			a = true;	
			System.out.println("\nThe game is over.\nPlayer " + c + " took " + round + " rounds to finish.");
			return a;
		}		
		else {
			a = false;
			return a;
			
		}
		
	}
	
	public static boolean PlayAgain(boolean a) {//Checking if user wants to play again
		Scanner input = new Scanner(System.in);

		
		System.out.println("Want to play again? y or n");
		String yesOrNo = input.next();
		
		if(yesOrNo.equals("y")) {
			a = true;
			return a;
		}
		else {
			a = false;
			return a;
		}
		
	}
	
	public static void playerType(String mode) {//getting the player type. Second Additonal Method
		Scanner input = new Scanner(System.in);

		
			
			for(int x = 1; x <= playerNum; x++) {//go through the number of players and checking if they are human or computer
				System.out.println("Is player" + x + " a human(1) or computer(2)? ");//ask if human of computer
				int type = input.nextInt();
				
				if(type == 1) {
					switch (x) {//swtch statement to set playertype as human
					case 1:  player1type = 1;
					break;
				
					case 2: player2type = 1;
					break;
				
					case 3: player3type = 1;
					break;
				
					case 4: player4type = 1;
					break;
					
					}
				}
				else {
					switch (x) {//swtch statement to set playertype as computer
					case 1:  player1type = 2;
					break;
				
					case 2: player2type = 2;
					break;
				
					case 3: player3type = 2;
					break;
				
					case 4: player4type = 2;
					break;
					
					}
				}
			}
		
	}

	public static void saveData(int a, int b) {//Saving the number of rounds each player takes Third Additional Method
		 if(b == 1) {
			 player1Round = a;
		 }
		 else if(b == 2) {
			 player2Round = a;
		 }
		 else if(b == 3) {
			 player3Round = a;
		 }
		 else {
			 player4Round = a;
		 }
	}
	
	public static void WhoWon() {//Saving the number of rounds each player takes Fourth Additional Method
		int a =  Math.min(Math.min(player1Round, player2Round), Math.min(player3Round, player4Round));
		
		if(a == player1Round) {
			System.out.print("Player 1 won the game\n");
		}
		else if(a == player2Round) {
			System.out.print("Player 2 won the game\n");
		}
		
		else if(a == player3Round) {
			System.out.print("Player 3 won the game\n");		
		}
		
		else if(a == player4Round) {
			System.out.print("Player 4 won the game\n");	
		}
		
	}
}
Tenzi.java
Displaying Tenzi.java.
