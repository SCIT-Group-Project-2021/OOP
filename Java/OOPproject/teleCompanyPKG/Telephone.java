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
	public Telephone(int areacode, int prefix, int serial_number) {
        if(!checkTelephone(areacode, prefix, serial_number) || !checkPrefix(areacode, prefix, serial_number)){
            System.err.println("Invalid phone number");
            return;
        }
		this.areacode = areacode;
		this.prefix = prefix;
		this.serial_number = serial_number;
	}
	
    //Checks length of telephone sections
    public boolean checkTelephone(int ac, int p, int sn){
        if(String.valueOf(ac).length() != 3){
            return false;
        }
        if(String.valueOf(p).length() != 3){
            return false;
        }
        if(String.valueOf(sn).length() != 4){
            return false;
        }
        return true;
    }

    //Checks if the prefix is valid
    public boolean checkPrefix(int ac, int p, int sn){
        int prefixArray[] = {301, 302, 303, 304, 601, 602, 603, 604};  
        int check = 0;
        for(int i = 0; i > 3; i++){
            if(p == prefixArray[i]){
                check = 1;
                break;
            }
        }

        if(check != 1){
            return false;
        }
        return true;
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
		return "\nTelephone: " + getPrefix() + "-" + getAreacode() + "-" + getSerial_number();
	}
}
