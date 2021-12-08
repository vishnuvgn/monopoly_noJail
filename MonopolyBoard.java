import java.util.*;

public class MonopolyBoard {

	private TreeMap<Integer, Property> _board;
	
	private int _number_of_properties;

	//public Property(String property_name, int cost, int rent, boolean buyable, boolean railroad, boolean utility) 
	
	public MonopolyBoard() {
		
		_number_of_properties = 40;
		
		_board = new TreeMap<Integer, Property>();
		
		Property property = new Property("GO", 0, 0, false, false, false);
		_board.put(0, property);
		
		Property property1 = new Property("Mediterranean Avenue", 60, 2, true, false, false);
		_board.put(1, property1);
		
		Property property2 = new Property("Community Chest #1", 0, 0, false, false, false);
		_board.put(2, property2);
		
		Property property3 = new Property("Baltic Avenue", 60, 4, true, false, false);
		_board.put(3, property3);

		Property property4 = new Property("Income Tax", 0, 200, false, false, false);
		_board.put(4, property4);
		
		Property property5 = new Property("Reading Railroad", 200, 25, true, true, false);
		_board.put(5, property5);

		Property property6 = new Property("Oriental Avenue", 100, 6, true, false, false);
		_board.put(6, property6);
		
		Property property7 = new Property("Chance #1", 0, 0, false, false, false);
		_board.put(7, property7);
		
		Property property8 = new Property("Vermont Avenue", 100, 6, true, false, false);
		_board.put(8, property8);
		
		Property property9 = new Property("Connecticut Avenue", 120, 8, true, false, false);
		_board.put(9, property9);

		Property property10 = new Property("In Jail / Just Visiting", 0, 0, false, false, false);
		_board.put(10, property10);
		
		Property property11 = new Property("St. Charles Place", 140, 10, true, false, false);
		_board.put(11, property11);
		
		//electric company is a utility, rent for utilities are 4 times what is on the dice, if both utilities owned it is 10x dice
		Property property12 = new Property("Electric Company", 150, 0, true, false, true);
		_board.put(12, property12);
		
		Property property13 = new Property("States Avenue", 140, 10, true, false, false);
		_board.put(13, property13);
		
		Property property14 = new Property("Virginia Avenue", 160, 12, true, false, false);
		_board.put(14, property14);
		
		Property property15 = new Property("Pennsylvania Railroad", 200, 25, true, true, false);
		_board.put(15, property15);

		Property property16 = new Property("St. James Place", 180, 14, true, false, false);
		_board.put(16, property16);
		
		Property property17 = new Property("Community Chest #2", 0, 0, false, false, false);
		_board.put(17, property17);

		Property property18 = new Property("Tennessee Avenue", 180, 14, true, false, false);
		_board.put(18, property18);
		
		Property property19 = new Property("New York Avenue", 200, 16, true, false, false);
		_board.put(19, property19);
		
		Property property20 = new Property("Free Parking", 0, 0, false, false, false);
		_board.put(20, property20);
		
		Property property21 = new Property("Kentucky Avenue", 220, 18, true, false, false);
		_board.put(21, property21);

		Property property22 = new Property("Chance #2", 0, 0, false, false, false);
		_board.put(22, property22);
		
		Property property23 = new Property("Indiana Avenue", 220, 18, true, false, false);
		_board.put(23, property23);

		Property property24 = new Property("Illinois Avenue", 240, 20, true, false, false);
		_board.put(24, property24);
		
		Property property25 = new Property("B_O Railroad", 200, 25, true, true, false);
		_board.put(25, property25);
		
		Property property26 = new Property("Atlantic Avenue", 260, 22, true, false, false);
		_board.put(26, property26);
		
		Property property27 = new Property("Ventnor Avenue", 260, 22, true, false, false);
		_board.put(27, property27);

		//water works is a utility, rent for utilities are 4 times what is on the dice, if both utilities owned it is 10x dice
		Property property28 = new Property("Water Works", 150, 0, true, false, true);
		_board.put(28, property28);
		
		Property property29 = new Property("Marvin Gardens", 280, 24, true, false, false);
		_board.put(29, property29);

		Property property30 = new Property("Go To Jail", 0, 0, false, false, false);
		_board.put(30, property30);
		
		Property property31 = new Property("Pacific Avenue", 300, 26, true, false, false);
		_board.put(31, property31);
		
		Property property32 = new Property("North Carolina Avenue", 300, 26, true, false, false);
		_board.put(32, property32);
		
		Property property33 = new Property("Community Chest #3", 0, 0, false, false, false);
		_board.put(33, property33);

		Property property34 = new Property("Pennsylvania Avenue", 320, 28, true, false, false);
		_board.put(34, property34);
		
		Property property35 = new Property("Short Line", 200, 25, true, true, false);
		_board.put(35, property35);

		Property property36 = new Property("Chance #3", 0, 0, false, false, false);
		_board.put(36, property36);
		
		Property property37 = new Property("Park Place", 350, 10, true, false, false);
		_board.put(37, property37);

		Property property38 = new Property("Luxury Tax", 0, 75, false, false, false);
		_board.put(38, property38);
		
		Property property39 = new Property("Boardwalk", 400, 50, true, false, false);
		_board.put(39, property39);	
	}
	
	//printing out board
	public void printLandedCount() {
		for(Map.Entry<Integer, Property> e : _board.entrySet()) {
			//System.out.println(e.getKey());
			Property temp = e.getValue();
			System.out.println(temp.getPropertyName() + " , " + temp.getlandedCount());
			//System.out.println(" => "+temp.getRent());	
		}
	}
	
	public HashMap<String, Integer> get_landed_map()
	{
		HashMap<String, Integer> temp_map = new HashMap<String, Integer>();
		for(Map.Entry<Integer, Property> e : _board.entrySet()) {
			//System.out.println(e.getKey());
			Property temp = e.getValue();
			
			//System.out.println(temp.getPropertyName() + " , " + temp.getlandedCount());
			
			temp_map.put(temp.getPropertyName(), temp.getlandedCount());
			
			//System.out.println(" => "+temp.getRent());	
		}
		return temp_map;
	}
	
	
	public int getNumberOfProperties() {
		return _number_of_properties;
	}
	
	public Property getProperty(int index) {
		return _board.get(index);
	}
	
	
}
