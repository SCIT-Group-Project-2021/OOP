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
	public void addCredit(String addCredit) {
		String voucherNum, digicelPrefixArray[] = {"301", "302", "303", "304"};  
		String flowPrefixArray[] = {"601", "602", "603", "604"}; 
		String addCreditPin = "*121*", endSpecifier = "#", midSpecifier = "*";
		String creditFileName = "", customerFileName = "";
		int length;
		Scanner input = null;
		Scanner inFileStream = null;
		try{		
			input = new Scanner(System.in);
			boolean check = false;
			length = addCredit.length();

			while(addCredit != "###") {
				if(length != 29) { //29 represents the number of digits and symbols that are in the String 
					System.err.println("Invalid length");
					/*addCreditPrompt();
					addCredit = input.nextLine();
					length = addCredit.length();*/
				}
                else 
                {
					voucherNum = addCredit.substring(5,17); //this should assign the credit number to the variable credit 
					String prefix = addCredit.substring(22, 25);
					
					if(addCreditPin.equals(addCredit.substring(0,4))) {
						if(midSpecifier.equals(addCredit.charAt(18))) {
							if(endSpecifier.equals(addCredit.charAt(length - 1)) ) {
                                //Check if the prefix number is valid
								for(int i = 0; i < 4; i++) {
									if(prefix.equals(digicelPrefixArray[i])){
										creditFileName = "Digicel_CardInfomation";
                                        customerFileName = "Digicel_Customers";
                                        check = true;
									}
								}
                                if(check != true){
                                    for(int i = 0; i < 4; i++) {
                                        if(prefix.equals(flowPrefixArray[i])){
                                            creditFileName = "Flow_CardInformation";
                                            customerFileName = "Flow_Customers";
                                            check = true;
                                        }
                                    }
                                }
							}
						}
					}
                }

                //Check if the telephone number and voucher number is valid
                //Things I need to do:
                //Make the validation for both voucher code and telephone happen at the same time before files are updated
                //Find a way to update files
				if(check = true) {
					inFileStream = new Scanner(new File ("Credit.dat"));
					while(inFileStream.hasNext()) {
						int creditNumber = inFileStream.nextInt();
						float balance = inFileStream.nextFloat();
						String status = inFileStream.nextLine();
						
						if(credit.equals(String.valueOf(creditNumber))) {
							if(status != "Used" || status != "used") {
							System.out.println("\nAn amount of $" + balance + " was added to your account.\n");
							this.setStatus("Used");
							}
						}		

						FileWriter outFileStream = null;
						try { 
						outFileStream = new FileWriter(new File("creditDummyFile.dat"), true);
						String newCredit = getCreditNumber() + "\t" + getBalance() + "\t" + getStatus() + "\n";
						outFileStream.write(newCredit);
						System.out.println("\nInformation saved successfully!");
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
                    //Trying to delete original file here and rename the dummy file to it's name 
                    //delete("Customer.dat");
                    //"creditDummyFile.dat" renameTo("Customers.dat");
                        inFileStream = new Scanner(new File ("Customer.dat"));
                        while(inFileStream.hasNext()) {
                            Telephone telephone = null;
                            String custID = inFileStream.nextLine();
                            String lastName = inFileStream.nextLine();
                            String address = inFileStream.nextLine();
                            float creditBalance = inFileStream.nextFloat();
                            telephone.setPrefix(inFileStream.nextInt());
                            telephone.setAreacode(inFileStream.nextInt());
                            telephone.setSerial_number(inFileStream.nextInt());
                            
                            String custNumber = String.valueOf(telephone.getPrefix()).concat(String.valueOf(telephone.getAreacode())).concat(String.valueOf(telephone.getSerial_number()));
                            String TelNumber =  prefix.concat(addCredit.substring(18,27));
                            if(TelNumber.equals(custNumber)){ 
                                setCreditBalance(creditBalance + balance);
                            }

                            
                            updateSaveCustomerInfofile();
                            //Trying to delete the file here and rename the dummy file to it's name 
                            //delete("Customer.dat");
                            //"DummyFile.dat".renameTo("Customers.dat");			
                        }	
                    }
                }
            }
		}
        catch (Exception e) {
            e.getStackTrace();
			System.err.println("An unexpected error occured.");
		}finally {
			if(input != null) {
				input.close();
			}
			if (inFileStream!= null) {
				inFileStream.close();
			}
		}
	}
	
	
	
	public float checkBalance(String balance) {
		String TelNumber = "0000000000", prefix, prefix = "1";
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
		}
		

	public String toString() {
		return "\nCustomer ID: " + getCustID() + "\nSurname: " + getName() + "\nAddress: "
				+ getAddress() + "\nTelephone: " + getTelephone() + "\nCredit Balance: " + getCreditBalance();
	}
		
		
}
