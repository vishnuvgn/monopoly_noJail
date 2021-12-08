import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MonopolyGame {

	Random generator = new Random();

	private ArrayList<Player> _competitors;

	private MonopolyBoard _board;
	private int _number_of_rounds;

	private TreeMap<Integer, ArrayList<Integer>> _diceRoll;

	private HashMap<Integer, Integer> _recordOfDiceRolls;

	private int _roundsPlayed;



	public MonopolyGame(MonopolyBoard board, ArrayList<Player> competitors, int number_of_rounds)
	{

		_board = board;
		_competitors = competitors;
		_number_of_rounds = number_of_rounds;
		_diceRoll = new TreeMap<Integer, ArrayList<Integer>>();
		_roundsPlayed = 0;

		for(int i = 0; i < competitors.size(); i++)
		{
			ArrayList<Integer> diceRollList = new ArrayList<Integer>();
			_diceRoll.put(i, diceRollList);
		}

		_recordOfDiceRolls = new HashMap<Integer, Integer>();

		_recordOfDiceRolls.put(2, 0);
		_recordOfDiceRolls.put(3, 0);
		_recordOfDiceRolls.put(4, 0);
		_recordOfDiceRolls.put(5, 0);
		_recordOfDiceRolls.put(6, 0);
		_recordOfDiceRolls.put(7, 0);
		_recordOfDiceRolls.put(8, 0);
		_recordOfDiceRolls.put(9, 0);
		_recordOfDiceRolls.put(10, 0);
		_recordOfDiceRolls.put(11, 0);
		_recordOfDiceRolls.put(12, 0);

	}
	public void playGame(FileWriter myWriter) {

		game: for(int round = 0; round < _number_of_rounds; round++) 
		{
			_roundsPlayed++;

			for(int i = 0; i < _competitors.size(); i++) 
			{

				Player current_player = _competitors.get(i);

				if(!current_player.isBankrupt()) 
				{
					int first_int = generator.nextInt(6) + 1;
					int second_int = generator.nextInt(6) + 1;	

					int dice_sum = first_int + second_int;

					(_diceRoll.get(i)).add(dice_sum);

					int current_value = (_recordOfDiceRolls.get(dice_sum));

					current_value++;

					_recordOfDiceRolls.put(dice_sum, current_value);


						//before means before modulo 
						int current_position_before = (current_player.getCurrentPosition() + dice_sum);

						int current_position_after = 0;

						if(current_position_before >= _board.getNumberOfProperties()/* =>40*/) 
						{
							current_player.addSalary(200);
							current_position_after = (current_position_before % (_board.getNumberOfProperties()));

						}
						else 
						{
							current_position_after = current_position_before;
						}
						current_player.setCurrentPosition(current_position_after);



						Property current_property = _board.getProperty(current_position_after);

						current_property.incrementlandedCount();

						//System.out.println("current landed property is:" + current_property.getPropertyName());

						if(current_property.isBuyable()) 
						{

							if(current_property.isPurchaseable()) 
							{
								if(current_player.addProperty(current_property))
								{
									current_property.setOwner(current_player);	
								}							
							}

							//already bought, 
							else {
								current_player.landedOnProperty(current_property);	
								Player other_owner = current_property.getOwner();	

								//if i own it, pass through
								if (current_player.getName() == other_owner.getName())
								{
									//System.out.println("I own this myself");
									continue;
								}

								int rent = current_player.calculateRent(current_property, dice_sum);
								other_owner.collectRent(rent);

								if(current_player.payRent(rent) == false)
								{		
									System.out.println("end of game, " + current_player.getName() + " is bankrupt");
									break game;

								}
								//landed on property
							}
						}

						else if(_board.getProperty(4) == current_property) // income tax 
						{
							current_player.landedOnProperty(current_property);	
							if(current_player.payIncomeTax(10, 200) == false)
							{

								System.out.println("end of game, " + current_player.getName() + " is bankrupt");
								break game;

							}
							//no need to call again ^, already did in if conditional
						}

						else if(_board.getProperty(38) == current_property) // luxury tax 
						{
							current_player.landedOnProperty(current_property);	
							if(current_player.payLuxuryTax(75) == false)
							{

								System.out.println("end of game, " + current_player.getName() + " is bankrupt");
								break game;

							}					
						}

						else {
							//free space
							current_player.landedOnProperty(current_property);	
						}
						//System.out.println("current capital is " + current_player.getCurrentCapital());
				}
			}//for(int i = 0; i < _competitors.size(); i++) {

		}//game: for(int round = 0; round < _number_of_rounds; round++) {



	int networth = 0;
	int winnerindex = 0;
	int current_capital = 0;
	for(int i = 0; i < _competitors.size(); i++)
	{
		if(!(_competitors.get(i).isBankrupt()))
		{
			if((_competitors.get(i)).getNetWorth() > networth)
			{
				winnerindex = i;
				networth = (_competitors.get(i)).getNetWorth();
				current_capital = (_competitors.get(i)).getCurrentCapital();
			}

		}
	}

	(_competitors.get(winnerindex)).setWinner();
	System.out.println("winner is: " + (_competitors.get(winnerindex)).getName() + ", net worth: " + networth + ", current capital: " + current_capital);
	System.out.print((_competitors.get(winnerindex)).getName() + "," + networth + "," + current_capital + "," +_roundsPlayed);


	try {
		myWriter.write((_competitors.get(winnerindex)).getName() + "," + networth + "," + current_capital + "," +_roundsPlayed);
	} 
	catch (IOException e) 
	{
		System.out.println("An error occurred.");
		e.printStackTrace();
	}



	ArrayList<Property> ownedProperties;
	ownedProperties = (_competitors.get(winnerindex)).getOwnedProperites();

	for(int j = 0; j < ownedProperties.size(); j++) 
	{
		Property temp_owned = ownedProperties.get(j);
		System.out.print("," + temp_owned.getPropertyName());
		try {
			myWriter.write("," + temp_owned.getPropertyName());
		} 
		catch (IOException e) 
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
	System.out.println();
	try {
		myWriter.write("\n");
	} 
	catch (IOException e) 
	{
		System.out.println("An error occurred.");
		e.printStackTrace();
	}


	}


	public ArrayList<Integer> getDiceRoll(int participantIndex) // 0 -> 3
	{
		return _diceRoll.get(participantIndex);
	}

	public HashMap<Integer, Integer> getdicerecord()
	{
		return _recordOfDiceRolls;
	}

	public int getRoundsPlayed()
	{
		return _roundsPlayed;
	}


}

