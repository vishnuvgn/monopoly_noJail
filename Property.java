public class Property{
	private String _property_name;
	private int _cost;
	private int _rent;
	private Player _owner;
	private boolean _buyable;
	private boolean _railroad;
	private boolean _utility;
	private boolean _bought;
	
	private int _landedCounter;
	
	//method creates instance of class - called a constructor
	public Property(String property_name, int cost, int rent, boolean buyable, boolean railroad, boolean utility) {
		this._property_name = property_name;
		this._cost = cost;
		this._rent = rent;
		this._buyable = buyable;
		this._railroad = railroad;
		this._utility = utility;
		this._bought = false;
		this._landedCounter = 0;
		//System.out.println("Propname = "+_property_name+ " cost = " + _cost+ "rent = "+ _rent);
	}
	
	public int getlandedCount()
	{
		return _landedCounter;
	}
	
	public void incrementlandedCount()
	{
		_landedCounter++;
	}
	

	public String getPropertyName() {
		return _property_name;
	}
	
	public int getCost() {
		return _cost;
	}
	
	public int getRent() {
		return _rent;
	}
	
	public boolean isRR() {
		return _railroad;
	}
	
	public boolean isUtility() {
		return _utility;
	}
	
	public void setOwner(Player player) {
		this._owner = player;
		this._bought = true;
	}
	public Player getOwner() {
		return _owner;
	}
	
	public boolean isPurchaseable() {
		if(_buyable == true && _bought == false) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isBuyable() 
		{
		if(_buyable == true) {
			return true;
		}
		else {
			return false;
		}
	}	
}
