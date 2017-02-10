
package stockproject;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import org.apache.commons.io.FileUtils;


public class CompanyCreator 
{

    private static CompanyCreator companyCreatorInstance = null;
    private HashMap<String, Company> allCompanys = new HashMap<>();
    
    
    
    private CompanyCreator()
    {
                
    }
    
    public static CompanyCreator getInstance()
    {
        if (companyCreatorInstance == null)
        {
            companyCreatorInstance = new CompanyCreator();
        }
        return companyCreatorInstance;
        
    }
   
    /**
     * Iterates through a list of company tickers, uses downloadFile() to get the file, uses pullInfo() to create HashMap of the 
     * company info, then creates a company object with that info and adds it to HashMap of company objects stored in this instance.
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void getAllCompanyInfo() throws FileNotFoundException, IOException
    {
        
        String tickerFileLoc = "companylist.csv";
        CSVReader nameReader = new CSVReader(new FileReader(tickerFileLoc));
        
        String[] nextLine;
        String tempFileLoc;
        int x = 0;
        
        while ((nextLine = nameReader.readNext()) != null)
        {
            HashMap<String, SingleDayQuote> oneDay = new HashMap<>();
            if (x == 0){x++;continue;} //skips CSV category header
            if (x > 6){break;}  //temporary, to prevent downloading more than 6 companys info
            
            String tempTicker = nextLine[0];
            tempFileLoc = "StockFiles\\" + tempTicker + ".csv";
            
            //downloadFile(tempTicker);
            
            Company tempCompany = new Company(tempTicker, pullInfo(tempFileLoc));
            allCompanys.put(tempTicker, tempCompany);
            
           
            x++;
        }
     
    }
    
    
    
    /**
     * Downloads CSV file based on tickerName
     * @param theTicker - name of ticker to download info for
     * @return   name and location where file saved
     * @throws MalformedURLException 
     */
    private String downloadFile(String theTicker) throws MalformedURLException, IOException
        {
            String preUrl = "http://chart.finance.yahoo.com/table.csv?s=" + theTicker + "&a=3&b=12&c=1996&d=0&e=31&f=2017&g=d&ignore=.csv";
            String fileLocString = "StockFiles\\" + theTicker + ".csv";

            File currentFile = new File (fileLocString); // creates the file object to be used two lines down
            URL currentUrl = new URL (preUrl);  //creates a url to use on next line
            FileUtils.copyURLToFile(currentUrl, currentFile);  //actually downloads the file
            
            
            return fileLocString;
        }


/**
 * Analyzes a companies stock history csv file, creates SingleDayQuote objects from each days info, returns hashmap
 * of this company for a single company
 * 
 * @param fileLoc location to find the CSV file to analyze
 * @return HashMap of data for the company
 * @throws FileNotFoundException
 * @throws IOException 
 */
    private HashMap<String, SingleDayQuote> pullInfo(String fileLoc) throws FileNotFoundException, IOException
    {
        HashMap<String, SingleDayQuote> oneCompanyHistory = new HashMap<>();
        
        
        CSVReader priceReader = new CSVReader(new FileReader(fileLoc));
        
        String[] nextLine;
        int lineNumber = 0;
        
        while ((nextLine = priceReader.readNext()) != null)
        {
            
            
            if (lineNumber == 0){lineNumber++;continue;} // prevents adding of the csv's category names
            
            String date = nextLine[0];
            String open = nextLine[1];
            String high = nextLine[2];
            String low = nextLine[3];
            String close = nextLine[4];
            String volume = nextLine[5];
            String adjClose = nextLine[6];
            
            SingleDayQuote oneDay = new SingleDayQuote(date,open,high,low,close,volume,adjClose);
            oneCompanyHistory.put(date, oneDay);
        }
        
        return oneCompanyHistory;
    }
    
    
    public void testToGetInfo()
    {
        Set<String> keys = allCompanys.keySet(); //adds all keys inside hashtable to this set
        String exitDescriptions = "";
        int tempVolume;
        
        for (String theKey : keys) // iterates through all keys by the string keyvalues
        {
            allCompanys.get(theKey).printStockInfo();
        }
    }
    
    
    

    
    
}
