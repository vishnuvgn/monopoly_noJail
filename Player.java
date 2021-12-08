import java.util.*;

public class Player{
	private String _name;
	private int _initialCapital;
	private int _currentCapital;
	
	private ArrayList<Property> _ownedProperties;
	private ArrayList<Property> _pastLandedProperties;
	private int _currentPosition;
	private int _numOfRR;
	private int _numOfUtil;
	private int _assetValue;
	
	private boolean _winnerFlag;
	
	
	//method creates instance of class - called a constructor
	public Player(String name, int initialCapital) {
		this._name = name;
		this._initialCapital = initialCapital;
		this._currentCapital = initialCapital;
		_ownedProperties = new ArrayList<Property>();
		_pastLandedProperties = new ArrayList<Property>();
		_currentPosition = 0;
		_assetValue = 0;
		
		_winnerFlag = false;
		
		//System.out.println("name = "+_name+ " InitCap = " + _initialCapital+ "CurrentCap = "+ _currentCapital );
	}
	

	public String getName() {
		return _name;
	}
	
	public int calculateRent(Property property, int dice_sum) {
		if(property.isRR() == true) {
			if(_numOfRR == 1) {
				int rent = 25;
				return rent;
			}
			else if(_numOfRR == 2) {
				int rent = 50;
				return rent;
			}
			else if(_numOfRR == 3) {
				int rent = 100;
				return rent;
			}
			else if(_numOfRR == 4) {
				int rent = 200;
				return rent;
			}
			else {
				return 0;
			}
		}
		else if(property.isUtility() == true) {
			if(_numOfUtil == 1) {
				int rent = (4 * dice_sum);
				return rent;
			}
			else if(_numOfUtil == 2) {
				int rent = (10 * dice_sum);
				return rent;
			}
			else {
				return 0;
			}
		}
		else {
			int rent = property.getRent();
			return rent;
		}
	}
	
	public boolean payRent(int rent) {
		if(_currentCapital >= rent)
		{
			_currentCapital = _currentCapital - rent;	
			return true;
		}
		_currentCapital = _currentCapital - rent;	
		return false;
	}
	
	public void collectRent(int rent) {
		_currentCapital = _currentCapital + rent;
	}
	
	
	public boolean addProperty(Property property) 
	{
		int cost = property.getCost();
		
		landedOnProperty(property);
		
		if(_currentCapital < cost) 
		{
			return false;
		}
		else 
		{
			_currentCapital = _currentCapital - cost;
			
			_ownedProperties.add(property);
			
			
			//check if rr, increment rr
			if(property.isRR() == true) {
				_numOfRR++;
			}
			//else chck if util, increment util
			if(property.isUtility() == true) {
				_numOfUtil++;
			}
			
			_assetValue = _assetValue + cost;
			
			return true;	
		}
		
	}
	
	public void landedOnProperty(Property property) {
		_pastLandedProperties.add(property);
	}
	
	public ArrayList<Property> getPastLanded(){
		return _pastLandedProperties;
	}
	
	public ArrayList<Property> getOwnedProperites(){
		return _ownedProperties;
	}
	
	public void addSalary(int salary) {
		_currentCapital = _currentCapital + salary;
	}
	
	public void setCurrentPosition(int currentPosition) {
		 _currentPosition = currentPosition;
	}
	
	public int getCurrentPosition() {
		return _currentPosition;
	}

	public int getNetWorth() {
		return(_assetValue + _currentCapital);
	}
	
	public int getCurrentCapital()
	{
		return ( _currentCapital);
	}
	
	public boolean payIncomeTax(int tax_rate, int default_tax) 
	{
		int net_worth = this.getNetWorth();
		int tax = (net_worth * tax_rate) / 100;
		
		
		if(tax > default_tax)
		{
			if(_currentCapital >= default_tax)
			{
				_currentCapital = _currentCapital - default_tax;	
				return true;
			}
			else
			{
				_currentCapital = _currentCapital - default_tax;				
			}
		}
		else 
		{
			if(_currentCapital >= tax)
			{
				_currentCapital = _currentCapital - tax;	
				return true;
			}
			else
			{
				_currentCapital = _currentCapital - tax;	
			}	
		}

		return false;
	}
	
	public boolean payLuxuryTax(int tax) 
	{		
		
		
		if(_currentCapital >= tax)
		{
			_currentCapital = _currentCapital - tax;	
			return true;
		}
		//have to pay tax
		_currentCapital = _currentCapital - tax;	
		return false;
	
	}
	
	public boolean isBankrupt()
	{
		if(_currentCapital < 0) 
		{
			return true;
		}
		return false;
	}
	
	public void setWinner()
	{
		_winnerFlag = true;
		
	}

	public boolean getWinner()
	{
		return _winnerFlag;
	}
	
}
