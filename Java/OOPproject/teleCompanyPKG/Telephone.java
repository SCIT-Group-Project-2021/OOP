//============================================================================
// Name        : Telephone.java
// Author      : Tyrien Gilpin - 2003893
// Editor/s	   : Ashley Deans - 2007275
// Version     : 2
// Description : Telephone class whose main function is to allows a admin to create 
//create a telephone number
//============================================================================

package OOPproject.teleCompanyPKG;

public class Telephone {
    private int areacode;
	private int prefix;
	private int serial_number;
	
	//Default Constructor
	public Telephone() {
		this.areacode = 000;
		this.prefix = 000;
		this.serial_number = 0000;
	}
	
	//Primary Constructor
	public Telephone(int areacode, int prefix, int serial_number, int provider) throws InvalidTelephoneNumber{

		try{
			checkPrefix(prefix, provider);
		}
		catch(InvalidTelephoneNumber e){
			throw e;
		}

		this.areacode = areacode;
		this.prefix = prefix;
		this.serial_number = serial_number;
		System.out.println(this.toString());
	}

    //Checks if the prefix is valid
	// TODO Remove provider from telephone parameters
    public static void checkPrefix(int p, int provider) throws InvalidTelephoneNumber{
        int prefixArray[];
		int digicelPrefixArray[] = {301, 302, 303, 304};
		int flowPrefixArray[] = {601, 602, 603, 604};    
        int check = 0;

		if(provider == 1){
			prefixArray = digicelPrefixArray;
		}			
		else{
			prefixArray = flowPrefixArray;
		}
			
		
        for(int i = 0; i < 4; i++){
            if(p == prefixArray[i]){
                check = 1;
                break;
            }
        }

        if(check != 1){
			throw new InvalidTelephoneNumber("Prefix is invalid"); 
        }

		System.out.println("Prefix is valid");

    }

	public static int isValidTelephone(String tele) throws InvalidTelephoneNumber{
        int prefixArray[];
		int digicelPrefixArray[] = {301, 302, 303, 304};
		int flowPrefixArray[] = {601, 602, 603, 604};    
        int check = -1;
		int increase = 1;
		int p = Integer.parseInt(tele.substring(3,6));
		prefixArray = digicelPrefixArray;
			
		for(int i = 0; i < 4; i++) {
			if(p == digicelPrefixArray[i]) {
				check = 1;
				break;
			}
			else if(p == flowPrefixArray[i]) {
				check = 2;
				break;
			}				
		}

		// TODO Make Gabe see how dumb I am
		/*
		while(increase < 3){
			for(int i = 0; i < 4; i++){
				if(p == prefixArray[i]){
					check+=increase;
					break;
				}
			}
			increase++;
			prefixArray = flowPrefixArray;	
		}*/
        

        if(check == -1){
			throw new InvalidTelephoneNumber("Prefix is invalid"); 
        }

		System.out.println("Prefix is valid");
		return check;
    }

	//Copy Constructor
    //Is this really necessary? We wouldnt want duplicate telephone numbers
	public Telephone(Telephone TelNum) {
		this.areacode = TelNum.areacode;
		this.prefix = TelNum.prefix;
		this.serial_number = TelNum.serial_number;
	}

	
	//Getters and Setters
	public int getAreacode() {
		return areacode;
	}


	public void setAreacode(int areacode) {
		this.areacode = areacode;
	}


	public int getPrefix() {
		return prefix;
	}


	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}


	public int getSerial_number() {
		return serial_number;
	}


	public void setSerial_number(int serial_number) {
		this.serial_number = serial_number;
	}
	
	//Show method
	public String toString() {
		return Integer.toString(getAreacode()) + Integer.toString(getPrefix()) + Integer.toString(getSerial_number());
	}
}
