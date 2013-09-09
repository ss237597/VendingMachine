public class Customer	{
	public double cash;
	public String choice;
	public Customer(String want)	{
		cash = 0.0;
		choice = want;
	}
	public Customer(double money)	{
		cash = money;
		choice = "";
	}
	public Customer(double money, String want)	{
		cash = money;
		choice = want;
	}
	public String type(String choice)	{
		boolean isSnack = false;
		String[] snacks = {"Granola Bar", "Hershey's", "Snickers", "Twix", "Muffin", "Peanut Butter Cracker", "Reese's Puffs", "Apple", "Fig Newtons", "Cinnamon Toast Crunch"};
		for (int i = 0; i < 10; i++)
			if (choice.equals(snacks[i]))
				isSnack = true;
		boolean isDrink = false;
		String[] drinks = {"Coca Cola", "Diet Coca Cola", "Sprite", "Water", "Fanta", "7-Up", "Gatorade", "Pepsi", "Diet Pepsi", "Red Bull"};
		for (int i = 0; i < 10; i++)
			if (choice.equals(drinks[i]))
				isDrink = true;
		if (isSnack)
			return "Snack";
		if (isDrink)
			return "Drink";
		return "Neither";
	}
}
