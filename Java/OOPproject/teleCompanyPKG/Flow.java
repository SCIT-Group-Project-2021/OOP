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

public class Flow extends ServiceProvider {
	private String parentCompanyName;
	private static int flowCustomerCount;

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

	public static void setFlowCustomerCount(int flowCustomerCount) {
		Flow.flowCustomerCount = flowCustomerCount;
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


	
	
	//Function used to save info to new file
	/*
	public void updateSaveCustomerInfofile(){
		FileWriter outFileStream = null;
		try { 
		outFileStream = new FileWriter(new File("DummyFile.dat"), true);
		String newCustomer = getCustID() + "\t" + getName() + "\t" + getAddress() +  
				"\t" + getCreditBalance() + "\t" + getTelephone() +  "\n";
		outFileStream.write(newCustomer);
		System.out.println("Information saved successfully!");
		}catch(Exception e) {
			System.out.println("\nAn unexpected error occured.");
		}finally {
			if(outFileStream != null) {
				try {
					outFileStream.close();
				}catch(IOException e) {
					e.printStackTrace();
				}		
			}
		}
	}*/

	@Override
	public boolean createPhoneCredit(int voucherNum, float balance) {
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
			inFileStream = new Scanner(new File("Flow_CardInfomation.txt"));

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

	@Override
	public void viewPhoneCredit() {
		Scanner inFileStream = null;
		int creditNum = 0;
		float balance = 0;
		String status = "";
		try {
			inFileStream = new Scanner(new File("Flow_CardInfomation.txt"));
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

	@Override
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
