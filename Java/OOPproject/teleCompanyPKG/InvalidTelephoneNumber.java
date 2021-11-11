//========================================================================================
// File Name   : InvalidTelephoneNumber.java
// Author      : Ashley Deans
// ID#         : 2007275
// Description : Custom exception class to return an error message when the customer enters 
//               a telephone number that doesn't exist in any of the customer files
//=========================================================================================

package OOPproject.teleCompanyPKG;

public class InvalidTelephoneNumber extends Exception {
    public InvalidTelephoneNumber(String message) {
        super(message);
    }
}
