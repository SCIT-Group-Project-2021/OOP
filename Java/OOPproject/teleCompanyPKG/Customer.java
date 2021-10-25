package OOPproject.teleCompanyPKG;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;

public class Customer {

	private String custID;
	private String name;
	private String address;
	private Telephone telephone;
	private float creditBalance;
	
	//Primary Constructor
	public Customer(String custID, String name, String address, Telephone telephone) {
		this.custID = custID;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		//this.creditBalance = creditBalance; Because the credit balance is automatically set, is there a reason to accept credit balance?
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
	
	
	
	@SuppressWarnings("unlikely-arg-type")
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
				
				if(addCreditPin.equals(mmiCode.substring(0,4))) {
					if(midSpecifier.equals(mmiCode.charAt(18))) {
						if(endSpecifier.equals(mmiCode.charAt(length - 1)) ) {
							//Check if the prefix number is valid
							for(int i = 0; i < 4; i++) {
								if(prefix.equals(digicelPrefixArray[i])){
									creditFile = new File("Digicel_CardInfomation");
									customerFile = new File ("Digicel_Customers");
									check = true;
								}
							}
							if(check != true){
								for(int i = 0; i < 4; i++) {
									if(prefix.equals(flowPrefixArray[i])){
										creditFile = new File ("Flow_CardInformation");
										customerFile = new File("Flow_Customers");
										check = true;
									}
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
	
	
	/*
	public float checkBalance(String balance) {
		String TelNumber = "0000000000", prefix;
		String digicelPrefixArray[] = {"301", "302", "303", "304"};  
		String flowPrefixArray[] = {"601", "602", "603", "604"}; 
		
		Scanner input = null;
		Scanner inFileStream = null;
		try {
				input = new Scanner(System.in);
				checkBalancePrompt();
				balance = input.nextLine();
				TelNumber =  prefix.concat(balance.substring(5,14));
				int length = balance.length();
				while(balance != "###"){
					if(length == 15){
							//TelNumber =  prefix.concat(balance.substring(5,14));
							prefix = balance.substring(5,7);
							for(int i = 0; i < 4; i++) {
								if(prefix.equals(digicelPrefixArray[i])) {
									inFileStream = new Scanner(new File ("Digicel_Customers.dat")); //Note to self, check if this means it reads from an existing file or not
								}
								else if(prefix.equals(flowPrefixArray[i])) {
									inFileStream = new Scanner(new File ("Flow_Customers.dat"));
								}
								else {
									System.err.println("The telephone number you entered is invalid.");
									checkBalancePrompt();
									balance = input.nextLine();
								}					
							}
						
							while(inFileStream.hasNext()) {
								Telephone telephone = null;
								String custID = inFileStream.nextLine();
								String name = inFileStream.nextLine();
								String address = inFileStream.nextLine();
								float creditBalance = inFileStream.nextFloat();
								//NTS: Figure out how to read all the info at once 
								telephone.setPrefix(inFileStream.nextInt());
								telephone.setAreacode(inFileStream.nextInt());
								telephone.setSerial_number(inFileStream.nextInt());
								
								String custNumber = String.valueOf(telephone.getPrefix()).concat(String.valueOf(telephone.getAreacode())).concat(String.valueOf(telephone.getSerial_number()));
								if(TelNumber.equals(custNumber)){ 
									System.out.print("\nYour Credit Balance is: ");
									return creditBalance;
								}
							}	
						}else{
							System.err.println("\nThe information you entered is invalid.\n");
							checkBalancePrompt();
							balance = input.nextLine();
						}
						System.out.println("Telephone Number: " + TelNumber + "is not assigned or does not exists.");
					}
			}catch (Exception e) {
				System.err.println("Sorry, an unexpected error occured.");
			}finally {
				if(input != null) {
					input.close();
				}
				if (inFileStream!= null) {
					inFileStream.close();
				}
			}
			return 0;
		}*/
		

	public String toString() {
		return "\nCustomer ID: " + getCustID() + "\nSurname: " + getName() + "\nAddress: "
				+ getAddress() + "\nTelephone: " + getTelephone() + "\nCredit Balance: " + getCreditBalance();
	}
		
		
}
