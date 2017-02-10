
package stockproject;


import java.util.HashMap;

public class Company 
{
    
    private final String companyTicker;
    HashMap<String, SingleDayQuote> fullHistory = new HashMap();
   
    
    
    
    
    public Company(String ticker, HashMap<String, SingleDayQuote> history)
    {
        this.companyTicker = ticker;
        this.fullHistory = history;

    }
    
    public void printStockInfo()
    {
        /////code below is templorary to test print method inside CompanyCreator///
        System.out.println(fullHistory.get("2016-12-01").high);
    }
    
    
}
