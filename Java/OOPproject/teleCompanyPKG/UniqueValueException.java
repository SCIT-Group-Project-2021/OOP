//=================================================================================
// File Name   : UniqueValueException.java
// Author      : Ashley Deans
// ID#         : 2007275
// Description : Custom exception class to return an error message when the admin 
//               tries to enter a telephone number or TRN that already exists 
//               in the customer file
//=================================================================================

package OOPproject.teleCompanyPKG;

public class UniqueValueException extends Exception {
    public UniqueValueException(String message) {
        super(message);
    }
}
