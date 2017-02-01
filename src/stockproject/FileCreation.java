
package stockproject;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;


public class FileCreation 
{

    
    /**
     * Downloads CSV file based on tickerName
     * @param theTicker - name of ticker to download info for
     * @return   name and location where file saved
     * @throws MalformedURLException 
     */

    public String downloadFile(String theTicker) throws MalformedURLException, IOException
        {
            String preUrl = "http://chart.finance.yahoo.com/table.csv?s=" + theTicker + "&a=3&b=12&c=1996&d=0&e=31&f=2017&g=d&ignore=.csv";
            String fileLocString = "StockFiles\\" + theTicker + ".csv";

            File currentFile = new File (fileLocString); // creates the file object to be used two lines down
            URL currentUrl = new URL (preUrl);  //creates a url to use on next line
            FileUtils.copyURLToFile(currentUrl, currentFile);  //actually downloads the file
            
            
            return fileLocString;
        }


/**
 *  Takes in all relevant stock info to pass to company object
 * @param fileLoc  location of file to analyze
 * @return 2dimensional array to be returned to company object (and saved there)
 * @throws FileNotFoundException
 * @throws IOException 
 */
    public ArrayList<ArrayList<String>> pullInfo(String fileLoc) throws FileNotFoundException, IOException
    {

        ArrayList<ArrayList<String>> fcStockInfo = new ArrayList<ArrayList<String>>();

        CSVReader priceReader = new CSVReader(new FileReader(fileLoc));

        String[] nextLine;
        int lineNumber = 0;
        while ((nextLine = priceReader.readNext()) != null)
        {
            ArrayList<String> oneDayInfo = new ArrayList<>();
            
            if (lineNumber == 0){lineNumber++;continue;}
            
            oneDayInfo.add(nextLine[0]);
            oneDayInfo.add(nextLine[1]);
            oneDayInfo.add(nextLine[6]);

            fcStockInfo.add(oneDayInfo);
            lineNumber++;
        }


        return fcStockInfo;
    }



/**
 * Goes through list of company tickers, passes ticker information to download method to get file.  Does this
 * for all companies in list, then creates company objects and adds these objects to arrayList.  Accesses pullInfo method
 * @return  Returns arrayList of objects of type Company 
 * @throws FileNotFoundException
 * @throws IOException 
 */
    public ArrayList<Company> getAllCompanyInfo() throws FileNotFoundException, IOException
    {
        
        String tickerFileLoc = "companylist.csv";
        CSVReader nameReader = new CSVReader(new FileReader(tickerFileLoc));
        ArrayList<Company> allCompanys = new ArrayList<>();
        ArrayList<ArrayList<String>> tempInfo = new ArrayList<ArrayList<String>>();
        
        String[] nextLine;
        String tempFileLoc;
        int x = 0;
        
        while ((nextLine = nameReader.readNext()) != null)
        {
            if (x == 0){x++;continue;}
            if (x > 6){break;}
            
            String tempTicker = nextLine[0];
            tempFileLoc = "StockFiles\\" + tempTicker + ".csv";
            
            downloadFile(tempTicker);
            tempInfo = pullInfo(tempFileLoc);
            Company tempCompany = new Company(tempTicker, tempInfo);
            allCompanys.add(tempCompany);
            
            x++;
        }
        
        
  
        return allCompanys;
    }
    
}
