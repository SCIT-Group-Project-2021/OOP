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

	//Primary Constructor
	public Customer(String custID, String name, String address, Telephone telephone){
		this.custID = custID;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.creditBalance = 100;
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
	
	public void addCreditPrompt() {
		System.out.println("\nIf you wish to add credit, enter the following information. else press ### to end.");
		System.out.println("\n*121* followed by the 13-digit card number followed by '*' then your telphone number.");
		System.out.println("\nlastly, enter the number sign(#). That is: *121*[card number]*[customer telephone number]#e.g. *121*123456789012*000000000#.");		
	}
	

	public void checkBalancePrompt() {
		System.out.println("\nIf you wish to check your balance enter the following information or enter ### to exit.");
		System.out.println("\n*120* followed by your telephone number then enter the number sign(#).");
		System.out.println ("\nThat is *120*[customer telephone number]# e.g., *120*3012345678#.\n");	
	}
	
	

	@SuppressWarnings({"unlikely-arg-type", "unused"})
	public void addCredit(String mmiCode) {
		String voucherNum = "", digicelPrefixArray[] = {"301", "302", "303", "304"};  
		String flowPrefixArray[] = {"601", "602", "603", "604"}; 
		String addCreditPin = "*121*", endSpecifier = "#", midSpecifier = "*";
		File creditFile = new File(""), customerFile = new File("");
		int length;
		//Scanner input = null;
		Scanner inFileStream = null;
		float voucherBalance = 0;
		
		boolean check = false;
		String userTeleNumber = "";
		try{		
			//input = new Scanner(System.in);
			length = mmiCode.length();
			if(length != 29) { //29 represents the number of digits and symbols that are in the String 
				System.err.println("Invalid length");
				/*addCreditPrompt();
				mmiCode = input.nextLine();
				length = mmiCode.length();*/
			}
			else 
			{
				voucherNum = mmiCode.substring(5,17); //this should assign the voucher number to the variable credit 
				String prefix = mmiCode.substring(22, 25);
				
				//Checking if the mmi code is valid
				if(addCreditPin.equals(mmiCode.substring(0,4))) {
					if(midSpecifier.equals(mmiCode.charAt(18))) {
						if(endSpecifier.equals(mmiCode.charAt(length - 1)) ) {
							//Check if the prefix number is valid
							for(int i = 0; i < 4; i++) {
								if(prefix.equals(digicelPrefixArray[i])) {
									creditFile = new File("Digicel_CardInfomation");
									customerFile = new File ("Digicel_Customers");
									check = true;
									break;
								}
								else if(prefix.equals(flowPrefixArray[i])){
									creditFile = new File ("Flow_CardInformation");
									customerFile = new File("Flow_Customers");
									check = true;
									break;
								}
							}
						}
					}
				}
			}

			//Check if the voucher number and telephone number is valid
			if(check = true) {
				inFileStream = new Scanner(creditFile);
				while(inFileStream.hasNext()) {
					int creditNumber = inFileStream.nextInt();
					voucherBalance = inFileStream.nextFloat();
					String status = inFileStream.nextLine();
					
					if(voucherNum.equals(String.valueOf(creditNumber))) {
						if(status != "Used" || status != "used") {
						//System.out.println("\nAn amount of $" + balance + " was added to your account.\n");
							check = true;
						}
						else{
							check = false;
							System.err.println("Invalid voucher number");
						}
					}		
				}

				//inFileStream is closed in the finally block
				inFileStream = new Scanner(customerFile);
				while(inFileStream.hasNext()) {
					String custID = inFileStream.next();
					String lastName = inFileStream.next();
					float creditBalance = inFileStream.nextFloat();
					int telephone = inFileStream.nextInt();
					String address = inFileStream.nextLine();
					

					/*
					telephone.setPrefix(inFileStream.nextInt());
					telephone.setAreacode(inFileStream.nextInt());
					telephone.setSerial_number(inFileStream.nextInt());
					
					String custNumber = String.valueOf(telephone.getPrefix()).concat(String.valueOf(telephone.getAreacode())).concat(String.valueOf(telephone.getSerial_number()));*/
					userTeleNumber =  mmiCode.concat(mmiCode.substring(19,29));
					if(userTeleNumber.equals(telephone)){ 
						check = true;
					}
					else{
						check = false;
						System.err.println("Invalid telephone number");
					}		
				}	
			}
			else{
				System.err.println("Invalid MMI code");
			}

			if(check = true){
				updateCreditFile(voucherNum, creditFile);
				updateCustomerFile(voucherBalance, customerFile, userTeleNumber);
			}	
		}
        catch (Exception e) {
            e.getStackTrace();
			System.err.println("An unexpected error occured.");
		}
		finally {
			if (inFileStream!= null) {
				inFileStream.close();
			}
		}
	}

	public void updateCreditFile(String voucherNum, File creditFile){
		FileWriter outFileStream = null;
		File creditTempFile = new File("temp.txt");
		Scanner inFileStream = null;
		try { 
			inFileStream = new Scanner(creditFile);
			outFileStream = new FileWriter(creditTempFile, true);
			while(inFileStream.hasNext()) {
				int creditNumber = inFileStream.nextInt();
				float recordBalance = inFileStream.nextFloat();
				String status = inFileStream.nextLine();

				if(Integer.toString(creditNumber) == voucherNum){
					status = "Used";
				}
				String record = creditNumber + "\t" + recordBalance + "\t" + status +  "\n";	
				outFileStream.write(record);
			}

			creditTempFile.renameTo(creditFile);
		}
		catch(FileNotFoundException e){
			e.getStackTrace();
		}
		catch(Exception e) {
			System.out.println("\nAn unexpected error occured.");
		}
		finally{
			try{
				outFileStream.close();
			}
			catch(Exception e){
				e.getStackTrace();
			}
		}
	}

	public void updateCustomerFile(float voucherBalance, File customerFile, String userTeleNumber){
		Scanner inFileStream = null;
		FileWriter outFileStream = null;
		File cusTempFile = new File("temp.txt");
		try{
			while(inFileStream.hasNext()){
				inFileStream = new Scanner(customerFile);
				outFileStream = new FileWriter(cusTempFile, true);
				String custID = inFileStream.next();
				String lastName = inFileStream.next();
				float creditBalance = inFileStream.nextFloat();
				int telephone = inFileStream.nextInt();
				String address = inFileStream.nextLine();

				if(userTeleNumber.equals(Integer.toString(telephone))){ 
					creditBalance += voucherBalance;
				}

				String record = custID + "\t" + lastName + "\t" + creditBalance + "\t" + telephone + "\t" +  address +  "\n";	
				outFileStream.write(record);
			}
			
			cusTempFile.renameTo(customerFile);
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
	public float checkBalance(String balanceChecker) {
		String TelNumber = "000000000", prefix, areaCode = "876", checkBalancePin = "*120*", endSpecifier = "#";
		String digicelPrefixes[] = {"301", "302", "303", "304"}, flowPrefixes[] = {"601", "602", "603", "604"};  
		float balance = 0;
		//Scanner input = null;
		Scanner inFileStream = null;
		try {
			//input = new Scanner(System.in);
			//checkBalancePrompt();
			//balanceChecker = input.nextLine();
			if(balanceChecker.length() == 16){//Checking if the length of the numbers entered is the same as this -> *120*8760000000#
				if(checkBalancePin.equals(balanceChecker.substring(0,5))) {//Checking for *120*
					if(areaCode.equals(balanceChecker.substring(5,8))) {//Checking for area code 876
						if(endSpecifier.equals(balanceChecker.charAt(balanceChecker.length() - 1))){//Checking for the #
							TelNumber = balanceChecker.substring(5,15);//Assigning index 5 to 14 of the input which should resemble this -> *120*8760000000# where index 5 to 14 is 8760000000
							prefix = balanceChecker.substring(8,11); //Assigning index 8 to 10 of the input which to the prefix variable
							for(int i = 0; i < 4; i++) {
								if(prefix.equals(digicelPrefixes[i])) {
									inFileStream = new Scanner(new File ("Digicel_Customers.txt"));
									break;
								}
								else if(prefix.equals(flowPrefixes[i])) {
									inFileStream = new Scanner(new File ("Flow_Customers.txt"));
									break;
								}				
							}
							if(inFileStream == null){
								System.err.println("Telephone number entered is invalid.");
								return 0;
							}
							while(inFileStream.hasNext()) {
								//Telephone telephone = null;
								String custID = inFileStream.next();
								String lastName = inFileStream.next();
								float creditBalance = inFileStream.nextFloat();
								int telephone = inFileStream.nextInt();
								String address = inFileStream.nextLine();

								if(TelNumber.equals(Integer.toString(telephone))){ 
									System.out.print("\nYour Credit Balance is: ");
									balance = creditBalance;
								}
								else{
									System.out.println("Telephone Number: " + TelNumber + "is not assigned or does not exists.");
								}
							}
						}
					}
				}
			}
			else{
				System.err.println("Invalid MMI code");
				return balance;
				//checkBalancePrompt();
				//balanceChecker = input.nextLine();
			}
		}
		catch(FileNotFoundException e){
			System.err.println("File not found.");
			e.getStackTrace();
			return balance;
		}
		catch (Exception e) {
			System.err.println("Sorry, an unexpected error occured.");
			return balance;
		}
		finally {
			/*if(input != null) {
				input.close();
			}*/
			if (inFileStream!= null) {
				inFileStream.close();
			}
		}
		return balance;
	}

	// TODO Check for telephone being taken in as a string (needs to be changed from int)
	// TODO Should this stay static or would using it on the object make more sense?
	// If so the if statement when customer is found would have to be changed
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
					c = new Customer(custID, lastName, address, new Telephone(Integer.parseInt(telephone.substring(0,3)), Integer.parseInt(telephone.substring(3,6)), Integer.parseInt(telephone.substring(6,10)),provider));
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
		return "\nCustomer ID: " + getCustID() + "\nSurname: " + getName() + "\nAddress: "
				+ getAddress() + "\nTelephone: " + getTelephone() + "\nCredit Balance: " + getCreditBalance();
	}
		
		
}
