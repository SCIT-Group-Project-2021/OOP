//========================================================================================
// File Name   : InvalidMMICode.java
// Author      : Ashley Deans
// ID#         : 2007275
// Description : Custom exception class to return an error message when the customer enters 
//               a the incorrect symbol combination for the add credit method or the check
//               balance method (e.g. - *121*, *120*, etc.)
//=========================================================================================

package OOPproject.teleCompanyPKG;

public class InvalidMMICode extends Exception {
    public InvalidMMICode(String message) {
        super(message);
    }
}
