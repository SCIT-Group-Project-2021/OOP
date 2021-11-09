//============================================================================
// File Name   : Flow.java
// Author      : Ashley Deans
// ID#         : 2007275
// Description : Child class Flow extending service provider class
//============================================================================

package OOPproject.teleCompanyPKG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.prefs.*;

public class Flow extends ServiceProvider {
	private String parentCompanyName;
	private static int flowCustomerCount = 0;

	public Flow() {
		super();
		parentCompanyName = "";
	}

	// Primary Constructor
	public Flow(String companyID, String address, String pName) {
		super(companyID, address);
		parentCompanyName = pName;
	}

	// Getters and Setters
	public String getParentCompanyName() {
		return parentCompanyName;
	}

	public void setParentCompanyName(String parentCompanyName) {
		this.parentCompanyName = parentCompanyName;
	}

	public static int getFlowCustomerCount() {
		return flowCustomerCount;
	}

	public int getProvidorCustomerCount() {
		return flowCustomerCount;
	}

	public static void setFlowCustomerCount(int flowCustomerCount) {
		Flow.flowCustomerCount = flowCustomerCount;
	}

	// #TODO savePreferences and readPreferences override service providor and make it so totalCustomerCount is overridden
	// Used to store customer count value persistently without using a file
	public void savePreferences(int value) {
		Preferences prefs = Preferences.userNodeForPackage(Flow.class);                
		prefs.putInt("flowCustomerCount", value); 
	}

	 public int readPreferences() {
		Preferences prefs = Preferences.userNodeForPackage(Flow.class);
		return prefs.getInt("flowCustomerCount", 0);  
	}  

	// TODO change parameter type in OOAD
	@SuppressWarnings({"unused"})
	public void createPhoneCredit(String voucherNum, float balance) throws UniqueValueException{
		Scanner inFileStream = null;
		String creditNum = "";
		float recordBal = 0;
		String status = "";
		FileWriter outFileStream  = null;
		try {
			outFileStream = new FileWriter(new File("Flow_CardInformation.txt"), true);

			inFileStream = new Scanner(new File("Flow_CardInformation.txt"));

			while (inFileStream.hasNext()) {
				creditNum = inFileStream.next();
				recordBal = inFileStream.nextFloat();
				status = inFileStream.next();

				if (creditNum.equals(voucherNum)) {
					throw new UniqueValueException("Voucher number already exists.");
				}
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
			if(inFileStream != null){
				try {
					inFileStream.close();
				}catch(Exception e) {
					System.err.println("\nAn unexpected error occured.");
				}	
			}

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
	public String[][] viewPhoneCredit() {
		Scanner inFileStream = null;
		String creditNum = "";
		float balance = 0;
		String status = "";
		int i = 0;
		int recordCount = 0;
		String data[][] = null;
		try {
			inFileStream = new Scanner(new File("Flow_CardInformation.txt"));
			
			while (inFileStream.hasNext()) {
				creditNum = inFileStream.next();
				balance = inFileStream.nextFloat();
				status = inFileStream.next();
				recordCount++;
			}
			//TO DO Extremely inefficient, find a way to store digicel customer count
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return data;
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

		if(recordCount != 0){
			data = new String[recordCount][3];
			try{
				inFileStream = new Scanner(new File("Flow_CardInformation.txt"));
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

	
	public String addCustomer(Customer c) throws UniqueValueException { 
		FileWriter outFileStream = null;
		Scanner input = null;
		input = new Scanner(System.in);
		try {
			outFileStream = new FileWriter(new File("Flow_Customers.txt"), true);
			try {
				checkCustomerUniqueValues(c);
			} 
			catch(UniqueValueException e){
				System.out.println(e.getMessage());
				System.out.println("Inside UniqueValueException addCustomer() method");
				throw e;
			}

			if(c.getCustID().length() != 11){
				return "TRN is invalid - Length: " + c.getCustID().length();
			}
			
			String newCustomer = c.getCustID() + "\t" + c.getName() + "\t" + c.getCreditBalance() + "\t" + c.getTelephone().toString() + "\t" +  c.getAddress() +  "\n";	
			outFileStream.write(newCustomer);
			System.out.println("Information saved successfully!");
			super.addCustomer(c);
			flowCustomerCount++;
			savePreferences(flowCustomerCount);
			return("");
			
		}
		catch(IOException e){
			e.getStackTrace();
			return("\nAn unexpected error occured.");
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

	@SuppressWarnings({"unused"})
	public static void checkCustomerUniqueValues(Customer c) throws UniqueValueException{
		Scanner inFileStream = null;
		String custID = "";
	    String name = "";
		float creditBalance = 0;
	    String telephone = "";
		String address = "";
		try {
			inFileStream = new Scanner(new File("Flow_Customers.txt"));
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
		
		flowCustomerCount = readPreferences();

		if(getFlowCustomerCount() != 0){
			data = new String[getFlowCustomerCount()][5];
			try{
				inFileStream = new Scanner(new File("Flow_Customers.txt"));
				while (inFileStream.hasNext()) {
					data[i][0] = inFileStream.next();
					data[i][1] = inFileStream.next();
					data[i][2] = Float.toString(inFileStream.nextFloat());
					data[i][3] = inFileStream.next();
					data[i][4] = inFileStream.nextLine();
					i++;
					//System.out.println(custID + "\t" + name + "\t" + creditBalance + "\t" + telephone + "\t" + address);
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
		return "Service Provider Name: Flow \n" + super.toString() + "Parent Company Name : " + parentCompanyName;
	};

	// Cant be inherited due to static nature
	public static boolean login(String password) {

		System.out.println("Admin Information\n" + "Providor:\t" + "Flow" + "\n" + "Password:\t" + password);

		if (password.equals("TheWayIFlow2021")) {
			System.out.println("Successfully Logged in!");
			return true;
		} else {
			System.out.println("Incorrect Password!");
			return false;
		}

	}

}
