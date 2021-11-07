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

	public Digicel() {
		super();
	}
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
	public boolean createPhoneCredit(int voucherNum, float balance) {
		// Should this be removed seeing as it's traditional error handling?
		int length = String.valueOf(voucherNum).length();
		if (length != 13) {
			System.err.println("Voucher number must be 13 digits long");
			return false;
		}

		if(balance != 100 && balance != 200 && balance != 500 && balance != 1000){
			System.err.println("Balance can only be equal to $100, $200, $500 or $1000");
			return false;
		}
		try {
			Scanner inFileStream = null;
			int creditNum = 0;
			float recordBal = 0;
			String status = "";
			inFileStream = new Scanner(new File("Digicel_CardInfomation.txt"));

			while (inFileStream.hasNext()) {
				creditNum = inFileStream.nextInt();
				recordBal = inFileStream.nextFloat();
				status = inFileStream.next();

				if (creditNum == voucherNum) {
					throw new UniqueValueException("Voucher number already exists.");
				}
			}
			status = "Available";
			FileWriter outFileStream = new FileWriter(new File("Flow_CardInformation.txt"), true);
			String record = voucherNum + "\t" + balance + "\t" + status;
			outFileStream.write(record);
			outFileStream.close();
			return true;
		}
		 catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

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

	
	public String addCustomer(Customer c) throws UniqueValueException { 
		FileWriter outFileStream = null;
		Scanner input = null;
		input = new Scanner(System.in);
		try {
			outFileStream = new FileWriter(new File("Digicel_Customers.txt"), true);
			try {
				checkCustomerUniqueValues(c);
			} 
			catch(UniqueValueException e){
				System.out.println(e.getMessage());
				System.out.println("Inside UniqueValueException addCustomer() method");
				throw e;
			}

			/*
			if(c.getTelephone().toString().length() != 12){
				return "Telephone number is invalid - Length: " + c.getTelephone().toString().length() + c.getTelephone();
			}*/

			if(c.getCustID().length() != 11){
				return "TRN is invalid - Length: " + c.getCustID().length();
			}
			
			String newCustomer = c.getCustID() + "\t" + c.getName() + "\t" + c.getCreditBalance() + "\t" + c.getTelephone().toString() + "\t" +  c.getAddress() +  "\n";	
			outFileStream.write(newCustomer);
			System.out.println("Information saved successfully!");
			super.addCustomer(c);
			digicelCustomerCount++;
			return("");
			
		}
		catch(IOException e){
			e.getStackTrace();
			return("\nAn unexpected error occured.");
		}
		/*
		catch(Exception e) {
			return("\nAn unexpected error occured.");
		}*/
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

	public static void checkCustomerUniqueValues(Customer c) throws UniqueValueException{
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
				if (custID.equals(c.getCustID())) {
					//inFileStream is closed in finally block
					throw new UniqueValueException("Customer ID already exists.");
				}
				else if(telephone.equals(c.getTelephone().toString())){
					System.out.println(c.getTelephone().toString());
					throw new UniqueValueException("Telephone number already in use.");
				}
			}	
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		/*
		catch (Exception e) {
			e.printStackTrace();
		}*/
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

	
	public String[][] viewCustomerBase() {
		Scanner inFileStream = null;
		String custID = "";
	    String name = "";
		float creditBalance = 0;
	    String telephone = "";
		String address = "";
		int i = 0;
		try {
			inFileStream = new Scanner(new File("Digicel_Customers.txt"));
			System.out.println("Search commenced");
			while (inFileStream.hasNext()) {
				custID = inFileStream.next();
				name = inFileStream.next();
				creditBalance = inFileStream.nextFloat();
				telephone = inFileStream.next();
				address = inFileStream.nextLine();
				digicelCustomerCount++;
				//System.out.println(custID + "\t" + name + "\t" + creditBalance + "\t" + telephone + "\t" + address);
			}
			//TO DO Extremely inefficient, find a way to store digicel customer count
			if (custID == "") {
				System.out.println("No records found.");
			}
			//return data;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		/*catch (Exception e) {
			e.printStackTrace();
		}*/
		System.out.println("making array...");
		String data[][] = new String[getDigicelCustomerCount()][5];
		try{
			inFileStream = new Scanner(new File("Digicel_Customers.txt"));
			while (inFileStream.hasNext()) {
				data[i][0] = inFileStream.next();
				data[i][1] = inFileStream.next();
				data[i][2] = Float.toString(inFileStream.nextFloat());
				data[i][3] = inFileStream.next();
				data[i][4] = inFileStream.nextLine();
				i++;
				//System.out.println(custID + "\t" + name + "\t" + creditBalance + "\t" + telephone + "\t" + address);
			}
			System.out.println("Array Complete " + data[2][1]);
		}
		catch (FileNotFoundException e) {
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
		return data;
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
