public class FoodInfo	{
	private String[] food = {"Granola Bar", "Hershey's", "Snickers", "Twix", "Muffin", "Peanut Butter Cracker", "Reese's Puffs", "Apple", "Fig Newtons", "Cinnamon Toast Crunch", "Coca Cola", "Diet Coca Cola", "Sprite", "Water", "Fanta", "7-Up", "Gatorade", "Pepsi", "Diet Pepsi", "Red Bull"};
	private double[] calories = {132, 210, 250, 250, 426, 39, 130, 95, 200, 130, 182, 0, 252, 0, 283.5, 267.75, 136.5, 262.5, 0, 273};
	private double[] fat = {6, 13, 12, 12, 18, 9, 3.5, 0.3, 4, 2.8, 0.1, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //g
	private double[] sodium = {82, 35, 120, 100, 383, 370, 200, 2, 220, 210, 19, 70, 115.5, 0, 94.5, 78.75, 283.5, 57.75, 63, 504}; //mg
	private double[] carbohydrates = {18, 26, 33, 34, 61, 24, 22.8, 25, 40, 23.9, 46, 0, 67.2, 0, 388.5, 68.25, 35.7, 72.45, 0, 63}; //g
	private double[] protein = {2.8, 3, 4, 2, 5, 4, 2.0, 0.5, 2, 1.6, 0.3, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //g
	private String name;
	private double cal;
	private double f;
	private double na;
	private double carb;
	private double p;	
	public FoodInfo (String s)	{
		name = s;
		int i = 0;
		while (!name.equals(food[i]))
			i++;
		cal = calories[i];
		f = fat[i];
		na = sodium[i];
		carb = carbohydrates[i];
		p = protein[i];
	}
	public void showFoodInfo()	{
		System.out.println("Name: " + name);
		System.out.println("Calories: " + cal);
		System.out.println("Fat: " + f + "g");
		System.out.println("Sodium: " + na + "mg");
		System.out.println("Carbs: " + carb + "g");
		System.out.println("Protein: " + p + "g");
	}
}
