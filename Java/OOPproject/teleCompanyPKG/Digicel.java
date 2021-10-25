//============================================================================
// File Name   : Digicel.java
// Author      : Ashley Deans
// ID#         : 2007275
// Description : Child class Digicel extending service provider class
//============================================================================

package OOPproject.teleCompanyPKG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Digicel extends ServiceProvider {
	private static int numOfBranches = 0;
	private static int digicelCustomerCount = 0;

	public Digicel(String companyID, String address) {
		super(companyID, address);
	}

	// Getters and Setters 
	public static int getNumOfBranches() {
		return numOfBranches;
	}

	public static void setNumOfBranches(int numOfBranches) {
		Digicel.numOfBranches = numOfBranches;
	}

	public static int getDigicelCustomerCount() {
		return digicelCustomerCount;
	}

	public static void setDigicelCustomerCount(int digicelCustomerCount) {
		Digicel.digicelCustomerCount = digicelCustomerCount;
	}

	// Should this be boolean?
	public boolean createPhoneCredit(int cardNum, int balance) {
		// Should this be removed seeing as it's traditional error handling?
		int length = String.valueOf(cardNum).length();
		if (length != 13) {
			System.out.println("Card number must be 13 digits long");
			return false;
		}

		try {
			String status = "Available";
			FileWriter outFileStream = new FileWriter(new File("Digicel_CardInfomation.txt"), true);
			String record = cardNum + "\t" + balance + "\t" + status;
			outFileStream.write(record);
			outFileStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	};

	public void viewPhoneCredit() {
		Scanner inFileStream = null;
		int creditNum = 0;
		float balance = 0;
		String status = "";
		try {
			inFileStream = new Scanner(new File("Digicel_CardInfomation.txt"));
			while (inFileStream.hasNext()) {
				creditNum = inFileStream.nextInt();
				balance = inFileStream.nextFloat();
				status = inFileStream.next();

				System.out.println(creditNum + "\t" + balance + "\t" + status);
			}
			if (creditNum == 0) {
				System.out.println("No records found.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addCustomer(Customer c) { 
		FileWriter outFileStream = null;
		Scanner input = null;
		input = new Scanner(System.in);
		try {
			outFileStream = new FileWriter(new File("Flow_Customers"), true);
			checkCustomerUniqueValues(c);
			String newCustomer = c.getCustID() + "\t" + c.getName() + "\t" + c.getCreditBalance() + "\t" + c.getTelephone() + "\t" +  c.getAddress() +  "\n";	
			outFileStream.write(newCustomer);
			System.out.println("Information saved successfully!");
			super.addCustomer(c);
		}
		catch(UniqueValueException e){
			e.getMessage();
		}
		catch(Exception e) {
			System.err.println("\nAn unexpected error occured.");
		}
		finally {
			if(outFileStream != null) {
				try {
					outFileStream.close();
				}catch(Exception e) {
					System.err.println("\nAn unexpected error occured.");
				}		
			}
			if(input != null) {
				input.close();
			}
		}
	}

	public boolean checkCustomerUniqueValues(Customer c){
		Scanner inFileStream = null;
		String custID = "";
	    String name = "";
		float creditBalance = 0;
	    String telephone = "";
		String address = "";
		boolean check = true;
		try {
			inFileStream = new Scanner(new File("Digicel_Customers.txt"));
			while (inFileStream.hasNext()) {
				custID = inFileStream.next();
				name = inFileStream.next();
				creditBalance = inFileStream.nextFloat();
				telephone = inFileStream.next();
				address = inFileStream.nextLine();
				if (custID == c.getCustID()) {
					check = false;
					//inFileStream is closed in finally block
					throw new UniqueValueException("Customer ID already exists.");
				}
				else if(telephone == c.getTelephone().toString()){
					check = false;
					throw new UniqueValueException("Telephone number already in use.");
				}
			}
			return check;		
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally{
			if(inFileStream != null) {
				try {
					inFileStream.close();
				}catch(Exception e) {
					System.err.println("\nAn unexpected error occured.");
				}
				return check;		
			}
		}
	}

	public void viewCustomerBase() {
		Scanner inFileStream = null;
		String custID = "";
	    String name = "";
		float creditBalance = 0;
	    String telephone = "";
		String address = "";
		try {
			inFileStream = new Scanner(new File("Digicel_Customers.txt"));
			while (inFileStream.hasNext()) {
				custID = inFileStream.next();
				name = inFileStream.next();
				creditBalance = inFileStream.nextFloat();
				telephone = inFileStream.next();
				address = inFileStream.nextLine();
				System.out.println(custID + "\t" + name + "\t" + creditBalance + "\t" + telephone + "\t" + address);
			}
			if (custID == "") {
				System.out.println("No records found.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(inFileStream != null) {
				try {
					inFileStream.close();
				}catch(Exception e) {
					System.err.println("\nAn unexpected error occured.");
				}		
			}

		}
	}

	@Override
	public String toString() {
		return "Service Provider Name: Digicel \n" + super.toString() + "Number of Branches : " + numOfBranches;
	};

	// Cant be inherited due to static nature
	public static boolean login(String password) {
		System.out.println("Admin Information\n" + "Providor:\t" + "Digicel" + "\n" + "Password:\t" + password);

		if (password.equals("TheBiggerBetterNetwork2021")) {
			System.out.println("Successfully Logged in!");
			return true;
		} else {
			System.out.println("Incorrect Password!");
			return false;
		}

	}

}
