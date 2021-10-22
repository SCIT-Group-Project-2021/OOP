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

public class Flow extends ServiceProvider{
	private String parentCompanyName;
	private static int flowCustomerCount;
	
	//Primary Constructor
	public Flow(String companyID, String address, String pName) {
		super(companyID, address);
		parentCompanyName = pName;
	}

	//Getters and Setters
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

	@Override
	public void addCustomer(Customer c) {
		super.addCustomer(c);
	}

	@Override
	public boolean createPhoneCredit(int cardNum, int balance) {
		int length = String.valueOf(cardNum).length();
		if(length != 13) {
			System.out.println("Card number must be 13 digits long");
			return false;
		}
		
		try {
			String status = "Available";
			FileWriter outFileStream = new FileWriter(new File("Flow_CardInformation.txt"), true);
			String record = cardNum + "\t" + balance + "\t" + status;
			outFileStream.write(record);
			outFileStream.close();
			return true;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		catch (Exception e) {
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
			while(inFileStream.hasNext()) {
				creditNum = inFileStream.nextInt();
				balance = inFileStream.nextFloat();
				status = inFileStream.next();
				
				System.out.println(creditNum + "\t" + balance + "\t" + status);
			}
			if(creditNum == 0) {
				System.out.println("No records found.");
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewCustomerBase() {
		
	}
	
	@Override
	public String toString() {
		return "Service Provider Name: Flow \n" + super.toString() + "Parent Company Name : " + parentCompanyName;
	};
	
	
}
