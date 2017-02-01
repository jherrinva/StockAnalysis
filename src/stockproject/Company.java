
package stockproject;


import java.util.ArrayList;

public class Company 
{
    
    private final String companyTicker;
    public ArrayList<ArrayList<String>> StockInfo = new ArrayList<ArrayList<String>>();
    public ArrayList<ArrayList<String>> croppedInfo = new ArrayList<ArrayList<String>>();

    
    
    
    public Company(String ticker, ArrayList<ArrayList<String>> infoToAdd)
    {
        this.companyTicker = ticker;
        this.StockInfo = infoToAdd;

    }
    
    public void printStockInfo()
    {
        
    }
    
    
}
