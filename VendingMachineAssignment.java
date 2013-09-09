import java.io.*;
import java.util.*;

public class VendingMachineAssignment {
	public static void main (String[] args)	throws IOException, VendingMachine.NumbersDoNotMatchException {
		Scanner in = new Scanner(System.in);
		VendingMachine[] machines = new VendingMachine[2];
		String[] snacks = {"Granola Bar", "Hershey's", "Snickers", "Twix", "Muffin", "Peanut Butter Cracker", "Reese's Puffs", "Apple", "Fig Newtons", "Cinnamon Toast Crunch"};
		double[] snackPrices = {0.75, 1.0, 0.75, 0.75, 1.5, 0.5, 1.00, 1.00, 0.75, 0.75};
		machines[0] = new VendingMachine(snacks, snackPrices, "Snack");
		String[] drinks = {"Coca Cola", "Diet Coca Cola", "Sprite", "Water", "Fanta", "7-Up", "Gatorade", "Pepsi", "Diet Pepsi", "Red Bull"};
		double[] drinkPrices = {1.25, 1.25, 1.25, 1.25, 1.25, 1.25, 1.25, 1.25, 1.25, 1.25};
		machines[1] = new VendingMachine(drinks, drinkPrices, "Drink");
		int timeCount = 0;
		int customerCount = 0;
		boolean customerWelcome;
		while (timeCount != 100)
			{
			for (int i = 0; i < 2; i++)
				machines[i].state(timeCount);
			customerWelcome = false;
			int VendingMachineNumber = 0;
			if (Math.random() < .5)
				{
				System.out.println("Please enter cash amount this customer has.");
				double cash = Double.parseDouble(in.nextLine());
				System.out.println("Please enter the item this customer wants.");
				String choice = in.nextLine();
				Customer c = new Customer(cash,choice);
				System.out.println("Hello, customer #" + ++customerCount + "!");
				System.out.println("The current time is " + (timeCount+1));
				int i = 0;
				while (i < 2 && !customerWelcome)
					{
					customerWelcome = machines[i].invite(c);
					i++;
				}
				if (customerWelcome)
					{
					VendingMachineNumber = i;
					System.out.println("Please proceed to Vending Machine #" + VendingMachineNumber);
					machines[VendingMachineNumber-1].transaction(c);
				}
			}
			timeCount++;
		}
	in.close();
	}
}
