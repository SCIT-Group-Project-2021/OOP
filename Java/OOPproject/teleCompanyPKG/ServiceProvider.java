//============================================================================
// File Name   : ServiceProvider.java
// Author      : Ashley Deans
// ID#         : 2007275
// Description : Base service provider class
//============================================================================

package OOPproject.teleCompanyPKG;

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


	public int getTotalCustomerCount() {
		return totalCustomerCount;
	}
	
	//Displays the total number of customers across all service providers
	public void displayTotalCustomerCount() {
		System.out.println("The total number of customer across all service providers is "+ totalCustomerCount);
	}
	
	//Add customer method, will be overrided in child classes
	public String addCustomer(Customer c) throws UniqueValueException {
		totalCustomerCount++;
		return "";
	}
	
	//Phone Credit Creation Method, will be overrided in child classes
	public boolean createPhoneCredit(int cardNum, float balance) {
		return true;
	};
	
	//Abstract method which will be made concrete in the child classes
	public abstract void viewPhoneCredit();
	
	public abstract String[][] viewCustomerBase();

	
	//Display method for Service Provider's attributes
	public String toString() {
		return "Company ID:" + companyID + "\nAddress: " + address + "\n";
	}
	
	
}
