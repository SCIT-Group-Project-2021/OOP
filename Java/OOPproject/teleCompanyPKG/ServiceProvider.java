//============================================================================
// File Name   : ServiceProvider.java
// Author      : Ashley Deans
// ID#         : 2007275
// Description : Base service provider class
//============================================================================

package OOPproject.teleCompanyPKG;


@SuppressWarnings({"unused"})
public abstract class ServiceProvider {
	protected String companyID;
	protected String address;
	private static int totalCustomerCount = 0;
	private Customer customer;
	
	//Default Constructor
	public ServiceProvider() {
		this.companyID = "";
		this.address = "";
	}

	//Primary Constructor
	public ServiceProvider(String companyID, String address) {
		this.companyID = companyID;
		this.address = address;
	}
	
	// #region Getters and Setters for the class' attributes
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

	public static void setTotalCustomerCount(int num) {
		totalCustomerCount = num;
	}

	//Displays the total number of customers across all service providers
	public static int getTotalCustomerCount() {
		return totalCustomerCount;
	}

	public int getProviderCustomerCount() {
		return totalCustomerCount;
	}

	// #endregion
	

	// Method to create a new customer, will be overrided in child classes
	public boolean addCustomer(Customer c) throws UniqueValueException {
		totalCustomerCount++;
		return true;
	}


	public abstract boolean checkVoucherValidity(String voucherNum) throws UniqueValueException;


	//Phone Credit Creation Method, will be overrided in child classes
	public abstract void createPhoneCredit(String cardNum, float balance) throws UniqueValueException;
	
	//Abstract methods which will be made concrete in the child classes
	public abstract String[][] viewPhoneCredit();
	
	public abstract String[][] viewCustomerBase();

	
	//Display method for Service Provider's attributes
	public String toString() {
		return "Company ID:" + companyID + "\nAddress: " + address + "\n";
	}
	
}
