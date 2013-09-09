public class Dispenser	{
	protected int initial = 20;
	protected int count;
	public String name;
	public double cost;
	protected double moneyEarned;
	public Dispenser(String item, double price)	{
		name = item;
		cost = price;
		count = initial;
	}
	public class NotEnoughCashException extends Exception {
		public NotEnoughCashException() {;}
	}
	public class ChoiceHasRunOutException extends Exception {
		public ChoiceHasRunOutException() {;}
	}
	public void canWeSellThis(Customer c, double cashInserted)	{
		try	{
			if (cost > cashInserted)
				throw new NotEnoughCashException();
			else if (count == 0)
				throw new ChoiceHasRunOutException();
			else
				{
				dispenseItem(cashInserted);
				c.cash -= cost;
				moneyEarned += cost;
			}
		}
		catch(NotEnoughCashException ex)	{
			System.out.println("Sorry, but you do not have enough money to buy this.");
			System.out.println("This transaction has been cancelled.");
		}
		catch(ChoiceHasRunOutException ex)	{
			System.out.println("Sorry, but this item has run out.");
			System.out.println("This transaction has been cancelled.");
		}
	}
	public void dispenseItem(double d) throws NotEnoughCashException, ChoiceHasRunOutException	{
		count--;
		System.out.println("The item you bought was: " + name);
		System.out.println("Its cost was: " + cost);
		System.out.println("You inserted: " + d);
		System.out.println("Your change is: " + (d-cost));
		System.out.println("Thank you for your business! We hope to see you again!");
	}
	public String summary1()	{
		return (initial-count) + " " + name;
	}
	public String summary2()	{
		return moneyEarned + " " + name;
	}
}
