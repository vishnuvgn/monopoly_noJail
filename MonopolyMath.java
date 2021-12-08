//vishnu venugopal
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


//import java.util.Scanner;

public class MonopolyMath {
	
	static HashMap<Integer, Integer> _total_dicerollrecord = new HashMap<Integer, Integer> ();
	static HashMap <String, Integer> _total_board = new HashMap <String, Integer>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		_total_dicerollrecord.put(2,0);
		_total_dicerollrecord.put(3,0);
		_total_dicerollrecord.put(4,0);
		_total_dicerollrecord.put(5,0);
		_total_dicerollrecord.put(6,0);
		_total_dicerollrecord.put(7,0);
		_total_dicerollrecord.put(8,0);
		_total_dicerollrecord.put(9,0);
		_total_dicerollrecord.put(10,0);
		_total_dicerollrecord.put(11,0);
		_total_dicerollrecord.put(12,0);
		
		set_up_property_map();
		
		
		FileWriter myWriter = null;
		
		try {
		      File myObj = new File("monopoly1_data.csv");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      }
		      else 
		      {
		        System.out.println("File already exists.");
		      }
		    } 
		catch (IOException e) // ex: if disk is full, c drive
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		try {
		      myWriter = new FileWriter("monopoly1_data.csv");
		    } 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

		System.out.println();
		try {
			myWriter.write("Player Name" + "," + "Net Worth" + "," + "Current Capital" + "," + "Rounds Played" + "," + "Property 1" + "," + "Property 2" + "," + "Property 3" + "," + "Property 4" + "," + "Property 5" + "," + "Property 6" + "," + "Property 7" + "," + "Property 8" + "," + "Property 9" + "," + "Property 10");
			myWriter.write("\n");
		    } 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		

		
		
		for(int game_number = 0; game_number < 1000; game_number++) 
		{
		
		Random generator = new Random();
		//Scanner input = new Scanner(System.in);

		//ArrayList<Property> property_list = new ArrayList<Property>();

		//calling constructor on class MonopolyBoard

		MonopolyBoard board = new MonopolyBoard();

		

		ArrayList<Property> landedOnProperties;
		ArrayList<Integer> dice_Roll_List;

		//TODO
		HashMap<Integer, Integer> dicerollrecordHashMap;

		

			ArrayList<Player> competitors = new ArrayList<Player>(4);

			Player player1 = new Player("Player 1", 1000);
			competitors.add(player1);

			Player player2 = new Player("Player 2", 1000);
			competitors.add(player2);

			Player player3 = new Player("Player 3", 1000);
			competitors.add(player3);

			Player player4 = new Player("Player 4", 1000);		
			competitors.add(player4);

			//number of rounds
			int number_of_rounds = 50;



			MonopolyGame game = new MonopolyGame(board, competitors, number_of_rounds);
			game.playGame(myWriter);

			System.out.println("rounds played: " + game.getRoundsPlayed());


			ArrayList<Property> ownedProperties;

			for(int i = 0; i < competitors.size(); i++)
			{
				if((competitors.get(i).getWinner()))
				{
					ownedProperties = (competitors.get(i)).getOwnedProperites();

					for(int j = 0; j < ownedProperties.size(); j++) 
					{
						Property temp_owned = ownedProperties.get(j);
						System.out.println("owned" + temp_owned.getPropertyName());
					}
					/*landedOnProperties = (competitors.get(i)).getPastLanded();
					
					for(int k = 0; k < landedOnProperties.size(); k++) 
					{	
						Property temp_landed_on = landedOnProperties.get(k);
						System.out.println("landed on" + temp_landed_on.getPropertyName());
					}*/
					
				}
				
				
			}


			//landedOnProperties = player1.getPastLanded();

			/*
		for(int i = 0; i < landedOnProperties.size(); i++) 
		{	
			Property temp_landed_on = landedOnProperties.get(i);
			System.out.println("landed on" + temp_landed_on.getPropertyName());
		}*/



			/*
		dice_Roll_List = game.getDiceRoll(0);

		for(int i = 0; i < dice_Roll_List.size(); i++)
		{
			System.out.println("roll: " + dice_Roll_List.get(i));
		}*/


			System.out.println("player 1 cc) after: " + player1.getCurrentCapital());
			System.out.println("player 2 cc) after: " + player2.getCurrentCapital());
			System.out.println("player 3 cc) after: " + player3.getCurrentCapital());
			System.out.println("player 4 cc) after: " + player4.getCurrentCapital());

			System.out.println("player 1 nw) after: " + player1.getNetWorth());
			System.out.println("player 2 nw) after: " + player2.getNetWorth());
			System.out.println("player 3 nw) after: " + player3.getNetWorth());
			System.out.println("player 4 nw) after: " + player4.getNetWorth());





			HashMap<Integer, Integer> temp_dicerollrecord = game.getdicerecord();




			Iterator hmIterator = temp_dicerollrecord.entrySet().iterator(); 

			// Iterate through the hashmap 
			System.out.println("dice roll record (statistics of dice)"); 

			while (hmIterator.hasNext()) { 
				Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
				//int marks = ((int)mapElement.getValue() + 10); 
				System.out.println(mapElement.getKey() + " : " + mapElement.getValue()); 
			} 
			
			add_dice_record(temp_dicerollrecord);
			
			
			board.printLandedCount();
			add_property_record(board.get_landed_map());

		}// end of Game
		
		//write collected data
		writeLandedRecord();
		
		writeDiceRecord();
		
		
		try {
		      
		      myWriter.close();
		      
		    } 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }



	}
	
	static public void writeDiceRecord()
	{
		FileWriter landedRecordFile = null;
		
		try {
		      File myObj = new File("Monopoly_diceroll_record.csv");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      }
		      else 
		      {
		        System.out.println("File already exists.");
		      }
		    } 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		try {
			landedRecordFile = new FileWriter("Monopoly_diceroll_record.csv");
			//landedRecordFile.write(_total_board.keySet() + "," + _total_board.get(i));
			//landedRecordFile.close();
		      //System.out.println("Successfully wrote to the file.");
		    } 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		for (Integer i : _total_dicerollrecord.keySet()) {
			try {
				landedRecordFile.write(i + "," + _total_dicerollrecord.get(i));
				landedRecordFile.write("\n");

				//landedRecordFile.close();
			      //System.out.println("Successfully wrote to the file.");
			    } 
			catch (IOException e) 
			{
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }		    
			}
		try {
			landedRecordFile.close();
		    } 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	}
	
	
	
	
	static public void writeLandedRecord()
	{
		FileWriter landedRecordFile = null;
		
		try {
		      File myObj = new File("Monopoly_landed_record.csv");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      }
		      else 
		      {
		        System.out.println("File already exists.");
		      }
		    } 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		try {
			landedRecordFile = new FileWriter("Monopoly_landed_record.csv");
			//landedRecordFile.write(_total_board.keySet() + "," + _total_board.get(i));
			//landedRecordFile.close();
		      //System.out.println("Successfully wrote to the file.");
		    } 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		for (String i : _total_board.keySet()) {
			try {
				landedRecordFile.write(i + "," + _total_board.get(i));
				landedRecordFile.write("\n");

				//landedRecordFile.close();
			      //System.out.println("Successfully wrote to the file.");
			    } 
			catch (IOException e) 
			{
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }		    
			}
		try {
			landedRecordFile.close();
		    } 
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	}
	
	
	
	
	
	static public void add_property_record(HashMap<String, Integer> landedrecord) 
	{	
		for (String i : landedrecord.keySet()) 
		{
			//System.out.println(i);
			Integer val1 = landedrecord.get(i);

			_total_board.computeIfPresent(i, (key, val) -> val + val1); // looks for value of key
		}
		System.out.println(_total_board);
	}	
	
	
	
	static public void add_dice_record(HashMap<Integer, Integer> dicerollrecord) 
	{	
		for (Integer i : dicerollrecord.keySet()) 
		{
			//System.out.println(i);
			Integer val1 = dicerollrecord.get(i);

			_total_dicerollrecord.computeIfPresent(i, (key, val) -> val + val1);

		}
		System.out.println(_total_dicerollrecord);
	}	
	
	
	static public void set_up_property_map()
	{
		
		
		_total_board.put("GO", 0);
		_total_board.put("Mediterranean Avenue", 0);
		_total_board.put("Community Chest #1", 0);
		_total_board.put("Baltic Avenue", 0);		
		_total_board.put("Income Tax", 0);
		_total_board.put("Reading Railroad", 0);
		_total_board.put("Oriental Avenue", 0);		
		_total_board.put("Chance #1", 0);
		_total_board.put("Vermont Avenue", 0);
		_total_board.put("Connecticut Avenue", 0);
		_total_board.put("In Jail / Just Visiting", 0);
		_total_board.put("St. Charles Place", 0);
		_total_board.put("Electric Company", 0);
		_total_board.put("States Avenue", 0);
		_total_board.put("Virginia Avenue", 0);
		_total_board.put("Pennsylvania Railroad", 0);
		_total_board.put("St. James Place", 0);
		_total_board.put("Community Chest #2", 0);
		_total_board.put("Tennessee Avenue", 0);
		_total_board.put("New York Avenue", 0);
		_total_board.put("Free Parking", 0);
		_total_board.put("Kentucky Avenue", 0);
		_total_board.put("Chance #2", 0);
		_total_board.put("Indiana Avenue", 0);
		_total_board.put("Illinois Avenue", 0);
		_total_board.put("B_O Railroad", 0);
		_total_board.put("Atlantic Avenue", 0);
		_total_board.put("Ventnor Avenue", 0);
		_total_board.put("Water Works", 0);
		_total_board.put("Marvin Gardens", 0);
		_total_board.put("Go To Jail", 0);
		_total_board.put("Pacific Avenue", 0);
		_total_board.put("North Carolina Avenue", 0);
		_total_board.put("Community Chest #3", 0);
		_total_board.put("Pennsylvania Avenue", 0);
		_total_board.put("Short Line", 0);
		_total_board.put("Chance #3", 0);
		_total_board.put("Park Place", 0);
		_total_board.put("Luxury Tax", 0);
		_total_board.put("Boardwalk", 0);

	}
	
	
	
}


