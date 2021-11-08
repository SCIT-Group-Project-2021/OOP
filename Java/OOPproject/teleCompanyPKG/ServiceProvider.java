//============================================================================
// File Name   : ServiceProvider.java
// Author      : Ashley Deans
// ID#         : 2007275
// Description : Base service provider class
//============================================================================

package OOPproject.teleCompanyPKG;

import java.util.prefs.*;

public abstract class ServiceProvider {
	protected String companyID;
	protected String address;
	//Should this be private or protected?
	private static int totalCustomerCount = 0;
	private Customer customer;
	

	//Primary Constructor
	public ServiceProvider() {
		this.companyID = "";
		this.address = "";
	}

	//Primary Constructor
	public ServiceProvider(String companyID, String address) {
		this.companyID = companyID;
		this.address = address;
	}
	
	//Getters and Setters for the class' attributes
	public String getCompanyID() {
		return companyID;
	}


	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	//Displays the total number of customers across all service providers
	public int getTotalCustomerCount() {
		totalCustomerCount = readPreferences();
		return totalCustomerCount;
	}

	// Used to store customer count value persistently without using a file
	public void savePreferences(int value) {
		Preferences prefs = Preferences.userNodeForPackage(ServiceProvider.class);                
		prefs.putInt("totalCustomerCount", value); 
	}

	 public int readPreferences() {
		Preferences prefs = Preferences.userNodeForPackage(ServiceProvider.class);
		return prefs.getInt("totalCustomerCount", 0);  
	}  
	
	//Displays the total number of customers across all service providers
	// TODO Create a button for this in admin GUI and remove from this class
	public void displayTotalCustomerCount() {
		System.out.println("The total number of customer across all service providers is "+ totalCustomerCount);
	}
	
	//Add customer method, will be overrided in child classes
	public String addCustomer(Customer c) throws UniqueValueException {
		totalCustomerCount++;
		savePreferences(totalCustomerCount);
		return "";
	}
	
	//Phone Credit Creation Method, will be overrided in child classes
	public abstract void createPhoneCredit(String cardNum, float balance) throws UniqueValueException;
	
	//Abstract method which will be made concrete in the child classes
	public abstract String[][] viewPhoneCredit();
	
	public abstract String[][] viewCustomerBase();

	
	//Display method for Service Provider's attributes
	public String toString() {
		return "Company ID:" + companyID + "\nAddress: " + address + "\n";
	}
	
	
}
