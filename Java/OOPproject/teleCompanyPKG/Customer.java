package OOPproject.teleCompanyPKG;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
//import java.io.IOException;
import java.lang.String;


public class Customer {

	private String custID;
	private String name;
	private String address;
	private Telephone telephone;
	private float creditBalance;
	
	//Default Constructor
	public Customer() {
		this.custID = "000";
		this.name = "";
		this.address = "";
		this.telephone = new Telephone();
		this.creditBalance = 0;
	}

	//Primary Constructor - for new customers
	public Customer(String custID, String name, String address, Telephone telephone){
		this.custID = custID;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.creditBalance = 100;
	}
	//Primary Constructor - for existing customers
	public Customer(String custID, String name, String address, Telephone telephone, float creditBalance){
		this.custID = custID;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.creditBalance = creditBalance;
	}
	
	//Copy constructor - Is this necessary?
	public Customer(Customer ctmr) {
		this.custID = ctmr.custID;
		this.name = ctmr.name;
		this.address = ctmr.address;
		this.telephone = ctmr.telephone;
		this.creditBalance = ctmr.creditBalance;
	}
		
	//Getters and Setters
	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Telephone getTelephone() {
		return telephone;
	}

	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}

	public float getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(float creditBalance) {
		this.creditBalance = creditBalance;
	}
	
	// TODO Discuss whether or not using the check == true is the best way to execute this method
	@SuppressWarnings({"unused"})
	public float addCredit(String mmiCode, Customer c) throws InvalidTelephoneNumber, InvalidVoucherNumber, InvalidMMICode{
		String voucherNum = "", digicelPrefixArray[] = {"301", "302", "303", "304"};  
		String flowPrefixArray[] = {"601", "602", "603", "604"}; 
		String addCreditPin = "*121*", endSpecifier = "#", midSpecifier = "*";
		File creditFile = new File(""), customerFile = new File("");
		int length;

		Scanner inFileStream = null;
		float voucherBalance = 0;
		
		boolean numCheck = false;
		boolean vouchCheck = false;

		boolean check = false;
		String userTeleNumber = "";

		try{		
			length = mmiCode.length();

			voucherNum = mmiCode.substring(5,18); //this should assign the voucher number to the variable credit 

			// using customer prefix instead because we cant add credit from flow to digicel and vice versa
			// TODO Might have to remove customer log in
			String prefix = mmiCode.substring(22, 25);
			
			//Checking if the mmi code is valid
			if(addCreditPin.equals(mmiCode.substring(0,5))) {
				if(midSpecifier.equals(Character.toString(mmiCode.charAt(18)))) {
					if(endSpecifier.equals(Character.toString(mmiCode.charAt(length - 1))) ) {
						//Check if the prefix number is valid
						for(int i = 0; i < 4; i++) {
							if(prefix.equals(digicelPrefixArray[i])) {
								creditFile = new File("Digicel_CardInformation.txt");
								customerFile = new File ("Digicel_Customers.txt");
								check = true;
								break;
							}
							else if(prefix.equals(flowPrefixArray[i])){
								creditFile = new File ("Flow_CardInformation.txt");
								customerFile = new File("Flow_Customers.txt");
								check = true;
								break;
							}
						}
					}
				}
			}
			// TODO Is there an easier way around not having to make an else or every id statement for this error?
			else{
				throw new InvalidMMICode("Invalid MMI code");
			}

			//Check if the voucher number and telephone number is valid
			if(check == true) {
				String creditNumber = "";
				String status = "";
				try{
					inFileStream = new Scanner(creditFile);
					while(inFileStream.hasNext()) {
						creditNumber = inFileStream.next();
						voucherBalance = inFileStream.nextFloat();
						status = inFileStream.next();
						
						// Changed this from check to vouchCheck because if you use check your just overriding everything else
						if(voucherNum.equals(creditNumber)) {
							if(status.equals("Available") || status.equals("available")) {
								vouchCheck = true;
								break;
							}
							else{
								throw new InvalidVoucherNumber("The voucher number entered has already been used");
							}
						}	
					}
					inFileStream.close();

					String custID = "";
					String lastName = "";
					float creditBalance = 0;
					String telephone = "";
					String address = "";
					inFileStream = new Scanner(customerFile);
					while(inFileStream.hasNext()) {
						custID = inFileStream.next();
						lastName = inFileStream.next();
						creditBalance = inFileStream.nextFloat();
						telephone = inFileStream.next();
						address = inFileStream.nextLine();
						
						// Changed this from check to numcheck because if you use check your just overriding everything else
						userTeleNumber =  mmiCode.substring(19,29);
						if(userTeleNumber.equals(telephone)){ 
							numCheck = true;
							break;
						}	
					}	
					inFileStream.close();
				}
				catch(FileNotFoundException e){
					e.getMessage();
				}
				
			}

			if (numCheck == true) {
				if (vouchCheck == true) {
					updateCreditFile(voucherNum, creditFile);
					updateCustomerFile(voucherBalance, customerFile, userTeleNumber);
					return voucherBalance;
				}
				else{
					throw new InvalidVoucherNumber("The voucher number entered is invalid");
				}
			}
			else{
				throw new InvalidTelephoneNumber("The telephone number entered does not exist");
			}
		}
		finally {
			if (inFileStream!= null) {
				inFileStream.close();
			}
		}

		
	}

	public void updateCreditFile(String voucherNum, File creditFile){
		FileWriter outFileStream = null;
		File creditTempFile = new File("tempCredit.txt");
		Scanner inFileStream = null;
		String creditNumber = "";
		float recordBalance = 0;
		String status = "";
		try { 
			inFileStream = new Scanner(creditFile);
			outFileStream = new FileWriter(creditTempFile, true);
			while(inFileStream.hasNext()) {
				creditNumber = inFileStream.next();
				recordBalance = inFileStream.nextFloat();
				status = inFileStream.next();

				if(creditNumber.equals(voucherNum)){
					status = "Used";
				}
				String record = creditNumber + "\t" + recordBalance + "\t" + status +  "\n";	
				outFileStream.write(record);
			}

			inFileStream.close();
			outFileStream.close();
			boolean successD = creditFile.delete();
			boolean success = creditTempFile.renameTo(creditFile);
			System.out.println("Deletion successful = " + successD + "\nCredit file: " + success);
		}
		catch(FileNotFoundException e){
			e.getStackTrace();
		}
		catch(Exception e) {
			System.out.println("\nAn unexpected error occured.");
		}
		finally{
			try{
				if(outFileStream!= null){
					try{
						outFileStream.close();
					}
					catch(Exception e){
						e.getStackTrace();
					}
				}
			}
			catch(Exception e){
				e.getStackTrace();
			}
		}
	}

	public void updateCustomerFile(float voucherBalance, File customerFile, String userTeleNumber){
		Scanner inFileStream = null;
		FileWriter outFileStream = null;
		File cusTempFile = new File("tempCustomer.txt");

		try{
			inFileStream = new Scanner(customerFile);
			outFileStream = new FileWriter(cusTempFile, true);
			while(inFileStream.hasNext()){
				String custID = inFileStream.next();
				String lastName = inFileStream.next();
				float creditBalance = inFileStream.nextFloat();
				String telephone = inFileStream.next();
				String address = inFileStream.nextLine();

				if(userTeleNumber.equals(telephone)){ 
					creditBalance += voucherBalance;
				}

				String record = custID + "\t" + lastName + "\t" + creditBalance + "\t" + telephone + "\t" +  address +  "\n";	
				outFileStream.write(record);
			}
			inFileStream.close();
			outFileStream.close();
			boolean successD = customerFile.delete();
			boolean success = cusTempFile.renameTo(customerFile);
			System.out.println("Customer Delete success: " + successD+ "\nCustomer file: " + success);
		}
		catch(FileNotFoundException e) {
			System.out.println("\nFile not found.");
			e.getStackTrace();
		}
		catch(Exception e) {
			System.out.println("\nAn unexpected error occured.");
		}
		finally {
			if(outFileStream != null) {
				try {
					outFileStream.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}		
			}
		}
	}

	@SuppressWarnings({"unused"})
	public float checkBalance(String balanceChecker) throws InvalidMMICode, InvalidTelephoneNumber{
		String TelNumber = "000000000", prefix, areaCode = "876", checkBalancePin = "*120*", endSpecifier = "#";
		String digicelPrefixes[] = {"301", "302", "303", "304"}, flowPrefixes[] = {"601", "602", "603", "604"};  
		float balance = 0;
		Scanner inFileStream = null;
		try {
			if(checkBalancePin.equals(balanceChecker.substring(0,5))) {//Checking for *120*
				if(areaCode.equals(balanceChecker.substring(5,8))) {//Checking for area code 876
					if(endSpecifier.equals(Character.toString(balanceChecker.charAt(balanceChecker.length()- 1)))){//Checking for the #
						TelNumber = balanceChecker.substring(5,15);//Assigning index 5 to 14 of the input which should resemble this -> *120*8760000000# where index 5 to 14 is 8760000000
						prefix = balanceChecker.substring(8,11); //Assigning index 8 to 10 of the input which to the prefix variable
						for(int i = 0; i < 4; i++) {
							if(prefix.equals(digicelPrefixes[i])) {
								inFileStream = new Scanner(new File("Digicel_Customers.txt"));
								break;
							}
							else if(prefix.equals(flowPrefixes[i])) {
								inFileStream = new Scanner(new File ("Flow_Customers.txt"));
								break;
							}				
						}
						if(inFileStream == null){
							throw new InvalidTelephoneNumber("The telephone number is invalid");
						}
						while(inFileStream.hasNext()) {
							String custID = inFileStream.next();
							String lastName = inFileStream.next();
							float creditBalance = inFileStream.nextFloat();
							String telephone = inFileStream.next();
							String address = inFileStream.nextLine();

							if(TelNumber.equals(telephone)){ 
								balance = creditBalance;
								break;
							}
						}
						if(balance == 0){
							throw new InvalidTelephoneNumber("The telephone number entered does not exist");
						}
					}
				}
			}

			if(inFileStream == null){
				throw new InvalidMMICode("Invalid MMI code");
			}
		}
		catch(FileNotFoundException e){
			System.err.println("File not found.");
			e.getStackTrace();
			return balance;
		}
		finally {
			if (inFileStream!= null) {
				inFileStream.close();
			}
		}
		return balance;
	}

	// TODO Should this stay static or would using it on the object make more sense?
	// If so the if statement when customer is found would have to be changed
	// TODO Update it so that capital/common letters do not interfere with the log in
	@SuppressWarnings({"unused"})
	public static Customer search(int provider, String lastNameEntered, String tele){
		boolean bool = false;
		Customer c = null;
		Scanner inFileStream = null;
		try{
			if(provider == 1){
				inFileStream = new Scanner(new File ("Digicel_Customers.txt"));
			}
			else{
				inFileStream = new Scanner(new File ("Flow_Customers.txt"));
			}

			while(inFileStream.hasNext()){
				String custID = inFileStream.next();
				String lastName = inFileStream.next();
				float creditBalance = inFileStream.nextFloat();
				String telephone = inFileStream.next();
				String address = inFileStream.nextLine();

				if(tele.equals(telephone) && lastNameEntered.equals(lastName)){ 
					c = new Customer(custID, lastName, address, new Telephone(Integer.parseInt(telephone.substring(0,3)), Integer.parseInt(telephone.substring(3,6)), Integer.parseInt(telephone.substring(6,10))), creditBalance);
				}
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTelephoneNumber e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(inFileStream != null){
				try{
					inFileStream.close();
				}
				catch(Exception e){
					e.getStackTrace();
				}
			}
		}

		return c;
	}
	public String toString() {
		return "\nCustomer ID: " + getCustID() + "\nSurname: " + getName() + "\nAddress: " + getAddress() + "\nTelephone: " + getTelephone() + "\nCredit Balance: " + getCreditBalance();
	}
		
		
}
