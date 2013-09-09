import java.io.*;
import java.text.*;
import java.util.*;

public class VendingMachine	{
	private String[] itemList = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"};
	private double[] priceList = {0.5, 0.75, 0.75, 1.25, 1.0, 0.75, 1.5, 1.25, 0.5, 0.75};
	public String type = "";
	protected double moneyInserted = 0.0;
	private double moneyEarned = 0.0;
	public boolean isOpen = true;
	Dispenser[] dispensers = new Dispenser[10];
	int lastClosed = 0;
	Calendar cal = Calendar.getInstance();
	String date = cal.get(cal.YEAR) + " " + cal.get(cal.MONTH) + " " + cal.get(cal.DATE) + " " + cal.get(cal.HOUR) + " " + cal.get(cal.MINUTE) + " " + cal.get(cal.SECOND);
	VendingMachine(String s)	{
		for (int i = 0; i < 10; i++)
			dispensers[i] = new Dispenser(itemList[i],priceList[i]);
		type = s;
	}
	VendingMachine(String[] items, String s)	{
		for (int i = 0; i < 10; i++)
			dispensers[i] = new Dispenser(items[i],priceList[i]);
		type = s;
	}
	VendingMachine(double[] prices, String s)	{
		for (int i = 0; i < 10; i++)
			dispensers[i] = new Dispenser(itemList[i],prices[i]);
		type = s;
	}
	VendingMachine(String[] items, double prices[], String s)	{
		for (int i = 0; i < 10; i++)
			dispensers[i] = new Dispenser(items[i],prices[i]);
		type = s;
	}
	public class MachineIsClosedException extends Exception {
		public MachineIsClosedException() {;}
	}
	public class WrongMachineException extends Exception {
		public WrongMachineException() {;}
	}
	public class NumbersDoNotMatchException extends Exception {
		public NumbersDoNotMatchException() {;}
	}
	public void state(int timeCount) throws IOException, NumbersDoNotMatchException	{
		if (isOpen && Math.random() < .1)	{
			isOpen = false;
			cal = Calendar.getInstance();
			date = cal.get(cal.YEAR) + " " + cal.get(cal.MONTH) + " " + cal.get(cal.DATE) + " " + cal.get(cal.HOUR) + " " + cal.get(cal.MINUTE) + " " + cal.get(cal.SECOND);
			printSummary(date, timeCount);
			lastClosed = timeCount;
		}
		else if (!isOpen && Math.random() < .5)	{
			isOpen = true;
			readSummary(date, lastClosed);
		}
	}
	public boolean invite(Customer c)	{
		try {
			if (!isOpen)
				throw new MachineIsClosedException();
			else if (type != c.type(c.choice))
				throw new WrongMachineException();
			else
				return true;
		}
		catch(MachineIsClosedException ex)	{
			System.out.println("Sorry, this vending machine is not open at the moment.");
			return false;
		}
		catch(WrongMachineException ex)	{
			System.out.println("Sorry, this is not the machine you are looking for.");
			return false;
		}
	}
	public void transaction(Customer c) {
		System.out.println("This vending machine sells: ");
		for (int i = 0; i < 9; i++)
			System.out.print(dispensers[i].name + ", ");
		System.out.println(dispensers[9].name);
		moneyInserted += c.cash;
		boolean found = false;
		int ind = 0;
		while (ind < 10 && !found)
			{
			if (dispensers[ind].name.equals(c.choice))
				found = true;
			ind++;
		}
		ind--;
		foodInfo(dispensers[ind].name);
		dispensers[ind].canWeSellThis(c, moneyInserted);
		moneyInserted = 0.0;
	}
	private void foodInfo(String name) {
		FoodInfo f = new FoodInfo(name);
		f.showFoodInfo();
	}
	public void printSummary(String date, int timeCount) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(date + " " + type + " " + timeCount + ".txt"));
		moneyEarned = 0;
		for (int i = 0; i < 10; i++)
			{
			out.println(dispensers[i].summary1());
			out.println(dispensers[i].summary2());
			moneyEarned += dispensers[i].moneyEarned;
		}
		out.println(moneyEarned);
		out.close();
	}
	public void readSummary(String date, int l) throws IOException, FileNotFoundException, NumbersDoNotMatchException	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(date + " " + type + " " + l + ".txt"));
			for (int i = 0; i < 10; i++)	{
				StringTokenizer st = new StringTokenizer(br.readLine());
				if (Integer.parseInt(st.nextToken()) != dispensers[i].initial-dispensers[i].count)
					throw new NumbersDoNotMatchException();
				st = new StringTokenizer(br.readLine());
			}
			br.close();
	    }
	    catch (FileNotFoundException ex)	{
	    	System.out.println("Sorry, the file wasn't found.");
	    }
		catch (NumbersDoNotMatchException ex)	{
			System.out.println("Uh oh... we might have to check for burglary.");
		}
	}
}
