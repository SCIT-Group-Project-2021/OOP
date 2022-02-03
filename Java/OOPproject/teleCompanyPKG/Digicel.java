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

	// Default Constructor
	public Digicel() {
		super();
	}

	// Primary Constructor
	public Digicel(String companyID, String address) {
		super(companyID, address);
		numOfBranches++;
	}

	// #region Getters and Setters 
	public static int getNumOfBranches() {
		return numOfBranches;
	}

	public static void setNumOfBranches(int numOfBranches) {
		Digicel.numOfBranches = numOfBranches;
	}

	@SuppressWarnings({"unused"})
	public static int getDigicelCustomerCount() {
		Scanner inFileStream = null;
		String custID = "";
	    String name = "";
		float creditBalance = 0;
	    String telephone = "";
		String address = "";
		digicelCustomerCount = 0;
		try {
			inFileStream = new Scanner(new File("Digicel_Customers.txt"));
			while (inFileStream.hasNext()) {
				custID = inFileStream.next();
				name = inFileStream.next();
				creditBalance = inFileStream.nextFloat();
				telephone = inFileStream.next();
				address = inFileStream.nextLine();
				digicelCustomerCount++;
			}	
			setDigicelCustomerCount(digicelCustomerCount);
			return digicelCustomerCount;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return digicelCustomerCount;
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

	public int getProviderCustomerCount() {
		return digicelCustomerCount;
	}

	public static void setDigicelCustomerCount(int digicelCustomerCount) {
		Digicel.digicelCustomerCount = digicelCustomerCount;
	}

	// #endregion

	// Method to create phone credit
	@SuppressWarnings({"unused"})
	public void createPhoneCredit(String voucherNum, float balance) throws UniqueValueException{
		String creditNum = "";
		float recordBal = 0;
		String status = "";
		FileWriter outFileStream  = null;
		try {
			outFileStream = new FileWriter(new File("Digicel_CardInformation.txt"), true);

			// Checks if the voucher number alraedy exists in the Digical Card Information File
			try {
				checkVoucherValidity(voucherNum);
			} 
			catch(UniqueValueException e){
				System.out.println(e.getMessage());
				System.out.println("Inside UniqueValueException createPhoneCredit() method");
				throw e;
			}
			
			status = "Available";
			String record = voucherNum + "\t" + balance + "\t" + status + "\n";
			outFileStream.write(record);
			outFileStream.close();
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(outFileStream != null){
				try {
					outFileStream.close();
				}catch(Exception e) {
					System.err.println("\nAn unexpected error occured.");
				}	
			}
		}
	}


	@SuppressWarnings({"unused"})
	public boolean checkVoucherValidity(String voucherNum) throws UniqueValueException{
		Scanner inFileStream = null;
		String creditNum = "";
		float recordBal = 0;
		String status = "";
		try {
			inFileStream = new Scanner(new File("Digicel_CardInformation.txt"));

			while (inFileStream.hasNext()) {
				creditNum = inFileStream.next();
				recordBal = inFileStream.nextFloat();
				status = inFileStream.next();

				if (creditNum.equals(voucherNum)) {
					throw new UniqueValueException("Voucher number already exists.");
				}
			}
			return true;
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
			return true;
		} 
		finally{
			if(inFileStream != null){
				try {
					inFileStream.close();
				}catch(Exception e) {
					System.err.println("\nAn unexpected error occured.");
				}	
			}
		}
	}

	// Returns a 2 dimensional array to make the columns and rows for the table in the admin GUI pannel
	@SuppressWarnings({"unused"})
	public String[][] viewPhoneCredit() {
		Scanner inFileStream = null;
		String creditNum = "";
		float balance = 0;
		String status = "";
		int i = 0;
		int recordCount = 0;
		String data[][] = null;

		// Counts the number of records in the credit information file to make the array
		try {
			inFileStream = new Scanner(new File("Digicel_CardInformation.txt"));
			
			while (inFileStream.hasNext()) {
				creditNum = inFileStream.next();
				balance = inFileStream.nextFloat();
				status = inFileStream.next();
				recordCount++;
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return data;
		} 

		finally{
			if(inFileStream != null){
				try {
					inFileStream.close();
				}catch(Exception e) {
					System.err.println("\nAn unexpected error occured.");
				}		
			}
		}

		// Filling the array with values
		if(recordCount != 0){
			data = new String[recordCount][3];
			try{
				inFileStream = new Scanner(new File("Digicel_CardInformation.txt"));
				while (inFileStream.hasNext()) {
					data[i][0] = inFileStream.next();
					data[i][1] = Float.toString(inFileStream.nextFloat());
					data[i][2] = inFileStream.next();
					i++;
				}
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
		}
		return data;
	}

	// Method to create a new customer 
	public boolean addCustomer(Customer c) throws UniqueValueException { 
		FileWriter outFileStream = null;
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
			
			String newCustomer = c.getCustID() + "\t" + c.getName() + "\t" + c.getCreditBalance() + "\t" + c.getTelephone().toString() + "\t" +  c.getAddress() +  "\n";	
			outFileStream.write(newCustomer);
			System.out.println("Information saved successfully!");

			// Calls parent function to increment the total customer count
			super.addCustomer(c);
			digicelCustomerCount++;
			return true;
		}
		catch(IOException e){
			e.getStackTrace();
			return false;
		}
		
		finally {
			if(outFileStream != null) {
				try {
					outFileStream.close();
				}catch(Exception e) {
					System.err.println("\nAn unexpected error occured.");
				}		
			}
		}
		
	}

	// Checks the telephone and TRN to ensure that they are unique
	@SuppressWarnings({"unused"})
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

	
	@SuppressWarnings({"unused"})
	public String[][] viewCustomerBase() {
		Scanner inFileStream = null;
		String custID = "";
	    String name = "";
		float creditBalance = 0;
	    String telephone = "";
		String address = "";
		int i = 0;
		String data[][] = null;

		// Creates an array to assign the customer values to so that it can be returned to the table in gui
		if(getDigicelCustomerCount() != 0){
			data = new String[getDigicelCustomerCount()][5];
			try{
				inFileStream = new Scanner(new File("Digicel_Customers.txt"));
				while (inFileStream.hasNext()) {
					data[i][0] = inFileStream.next();
					data[i][1] = inFileStream.next();
					data[i][2] = Float.toString(inFileStream.nextFloat());
					data[i][3] = inFileStream.next();
					data[i][4] = inFileStream.nextLine();
					i++;
				}
				
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
				return data;
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
		return data;
	}

	@Override
	public String toString() {
		return "Service Provider Name: Digicel \n" + super.toString() + "Number of Branches : " + numOfBranches;
	};

	
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
