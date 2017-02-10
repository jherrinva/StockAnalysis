
package stockproject;

import java.io.IOException;

/**
 *
 * @author John Herrin
 */
public class newMain 
{
    public static void main(String args[]) throws IOException
    {
        CompanyCreator myCreator = CompanyCreator.getInstance(); // create instance
        myCreator.getAllCompanyInfo();  //create master Company list of info in instance
        myCreator.testToGetInfo();
    }
}
