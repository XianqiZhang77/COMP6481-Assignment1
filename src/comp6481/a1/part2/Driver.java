package comp6481.a1.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Driver {
	
	private static int maxComputers = 10;
	private static String password = "password";
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome!");
		System.out.println("the maximum number your computerstore can contain: " + maxComputers);
		Computer[] inventory = new Computer[maxComputers];
		while(true) {
			System.out.println("============================================================");
			System.out.println("What do you want to do?");
			System.out.println("   1.   Enter new computers (password required)");
			System.out.println("   2.   Change information of a computer (password required)");
			System.out.println("   3.   Display all computers by a specific brand");
			System.out.println("   4.   Display all computers under a certain price");
			System.out.println("   5.   Quit");
			System.out.println("Please enter your choice >");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				
				if (!login(scanner)) {
					break;
				}	
				System.out.println("How many computers do you want to enter?");
				int curSpace = maxComputers - Computer.findNumberOfCreatedComputers();
				int number = scanner.nextInt();
				if (curSpace < number) {
					System.out.println("No enough space" + "\n" + "The remaining places are " + curSpace);
					break;
				}
				EnterNewComputers(inventory, number, scanner);
				break;
				
			case 2:
				
				if (!login(scanner)) {
					break;
				}
				boolean pass = true;
				int index;
				while (true) {
					System.out.println("Please enter the computer number you want to update");
					index = scanner.nextInt();
					if (index < 0 || index >= Computer.findNumberOfCreatedComputers()) {
						System.out.println("the entered index has no valid computer");
						System.out.println("Do you want to enter another computer?");
					    System.out.println("Press '1' to enter another computer, or any other number to quit this operation and go back to the main menu");
						if (scanner.nextInt() == 1) {
							continue;
						}
						pass = false;
					}
					break;
				}
				if (!pass) {
					break;
				}
				System.out.println("\n" + inventory[index].toString() + "\n");
				Update(inventory, index, scanner);
				break;

			case 3:
				System.out.println("Please enter a specific brand name");
				String brandName = scanner.next();
				List<Computer> brandList = new ArrayList<>();
				brandList = findComputersBy(brandName, inventory);
				for (Computer computer : brandList) {
					System.out.println("\n" + computer.toString());
				}
				break;
			case 4:
				System.out.println("Please enter a specific price");
				double price = scanner.nextDouble();
				List<Computer> UnderPriceList = findCheaperThan(price, inventory);
				for (Computer computer : UnderPriceList) {
					System.out.println("\n" + computer.toString());
				}
				break;
			case 5: 
				System.out.println("The system is closed");
				System.exit(0);
				break;
			default:
				System.out.println("The number you entered is invalid, please enter a number between 0 and 5");
				break;
			}	
		}

	}

	private static boolean login(Scanner scanner) {
		boolean status = false;
		for (int i = 1; i <= 3; i++) {
			status = true;
			System.out.println("Please enter your password");
			String input = scanner.next();
			if (!password.equals(input)) {
				status = false;
				System.out.println("Your password is invalid" + "\n" + "You have " + (3 - i) + " tries left to enter the correct password");
				continue;
			}
			break;
		}
		return status;
	}

	
	private static void EnterNewComputers(Computer[] inventory, int number, Scanner scanner) {
		
		for (int i = 0; i < number; i++) {
			int index = Computer.findNumberOfCreatedComputers();
			System.out.println("You are entering the Computer #" + index);
			System.out.println("Please enter the brand of this computer");
			String brand = scanner.next();
			System.out.println("Please enter the model of this computer");
			String model = scanner.next();
			System.out.println("Please enter the serial number(SN) of this computer");
			long SN = scanner.nextLong();
			System.out.println("Please enter the price of this computer");
			double price = scanner.nextDouble();
			inventory[index] = new Computer(brand, model, SN, price, i);
			System.out.println("Computer #" + index + " has been added");
			System.out.println("===================================================");
		}	
	}
	private static void Update(Computer[] inventory, int index, Scanner scanner) {
		
		while (true) {
			System.out.println("What information would you like to change?");
			System.out.println("    1.   brand");
			System.out.println("    2.   model");
			System.out.println("    3.   SN");
			System.out.println("    4.   price");
			System.out.println("    5.   Quit");
			int number = scanner.nextInt();
			switch (number) {
			case 1:
				System.out.println("You are updating the brand of computer #" + index);
				System.out.println("Please enter the new brand of computer #" + index);
				String brand = scanner.next();
				inventory[index].setBrand(brand);
				System.out.println("The brand has been changed to " + brand);
				break;
			case 2:
				System.out.println("You are updating the model of computer #" + index);
				System.out.println("Please enter the new model of computer #" + index);
				String model = scanner.next();
				inventory[index].setModel(model);
				System.out.println("The model has been changed to " + model);
				break;
			case 3:
				System.out.println("You are updating the serial number(SN) of computer #" + index);
				System.out.println("Please enter the new serial number(SN) of computer #" + index);
				long SN = scanner.nextLong();
				inventory[index].setSN(SN);;
				System.out.println("The serial number(SN) has been changed to " + SN);
				break;
			case 4:
				System.out.println("You are updating the price of computer #" + index);
				System.out.println("Please enter the new price of computer #" + index);
				double price = scanner.nextDouble();
				inventory[index].setPrice(price);
				System.out.println("The price has been changed to " + price);
				break;
			case 5:
				return;
			default:
				System.out.println("The number you enter should be between 1 and 5");
				break;
			}
		}
		
	}
	private static List<Computer> findComputersBy(String brandName, Computer[] inventory) {
		List<Computer> brandList = new ArrayList<Computer>();
		for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
			if (inventory[i].getBrand() != null && inventory[i].getBrand().equals(brandName)) {
				brandList.add(inventory[i]);
			}
		}
		return brandList;
	}
	
	private static List<Computer> findCheaperThan(double price, Computer[] inventory) {
		
		List<Computer> underPriceList = new ArrayList<Computer>();
		for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
			if (inventory[i].getPrice() < price) {
				underPriceList.add(inventory[i]);
			}
		}
		return underPriceList;
	}

}


