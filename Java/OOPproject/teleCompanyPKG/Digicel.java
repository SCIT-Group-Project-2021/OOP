//======OOPproject.teleCompanyPKG========================================================
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
	private static int digicelCustomerCount= 0;

	
	public Digicel(String companyID, String address) {
		super(companyID, address);
		// TODO Auto-generated constructor stub
	}


	//Getters and Setters for the class' attributes
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
	
	//Should this be boolean?
	public boolean createPhoneCredit(int cardNum, int balance) {
		//Should this be removed seeing as it's traditional error handling?
		int length = String.valueOf(cardNum).length();
		if(length != 13) {
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
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		catch (Exception e) {
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
	
	/* Complete customer 
	public void addCustomer(Customer c) {
		totalCustomerCount++;
	}
	*/
	
	//change values
	public void viewCustomerBase() {
		Scanner inFileStream = null;
		int creditNum = 0;
		float balance = 0;
		String status = "";
		try {
			inFileStream = new Scanner(new File("Digicel_CardInfomation.txt"));
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
	public String toString() {
		return "Service Provider Name: Digicel \n" + super.toString() + "Number of Branches : " + numOfBranches;
	};
	
	
}
