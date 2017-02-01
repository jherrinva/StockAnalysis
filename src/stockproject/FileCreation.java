
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
            String fileLocString = "C:\\Users\\Troy\\Downloads\\StockFiles\\" + theTicker + ".csv";

            File currentFile = new File (fileLocString); // creates the file object to be used two lines down
            URL currentUrl = new URL (preUrl);  //creates a url to use on next line
            FileUtils.copyURLToFile(currentUrl, currentFile);  //actually downloads the file
            
            
            return fileLocString;
        }



    public ArrayList<ArrayList<String>> pullInfo(String fileLoc) throws FileNotFoundException, IOException
    {

        ArrayList<ArrayList<String>> fcStockInfo = new ArrayList<ArrayList<String>>();

        CSVReader reader = new CSVReader(new FileReader(fileLoc));

        String[] nextLine;
        int lineNumber = 0;
        while ((nextLine = reader.readNext()) != null)
        {
            String tempEntryColZero = nextLine[0];
            String tempEntryColOne = nextLine[1];
            String tempEntryColSix = nextLine[6];

            fcStockInfo.get(lineNumber).add(tempEntryColZero);
            fcStockInfo.get(lineNumber).add(tempEntryColOne);
            fcStockInfo.get(lineNumber).add(tempEntryColSix);

            lineNumber++;
        }


        return fcStockInfo;
    }




    public String getAllTickerFiles()
    {
        String compTicker = "";
        return compTicker;
    }
    
}
