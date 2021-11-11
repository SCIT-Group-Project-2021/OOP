//=================================================================================
// File Name   : InvalidVoucherNumber.java
// Author      : Ashley Deans
// ID#         : 2007275
// Description : Custom exception class to return an error message when the customer 
//               tries to use a credit voucher number that doesnt exist
//=================================================================================

package OOPproject.teleCompanyPKG;

public class InvalidVoucherNumber extends Exception {
    public InvalidVoucherNumber(String message) {
        super(message);
    }
}
