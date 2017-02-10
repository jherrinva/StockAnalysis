
package stockproject;

/**
 * This class creates an object containing quote info for a single day for a company
 * @author John Herrin
 */
public class SingleDayQuote 
{
    String date;
    double open;
    double high;
    double low;
    double close;
    int volume;
    double adjClose;
    
    
    
    public SingleDayQuote (String date, String open, String high, String low, String close, String volume, String adjClose)
    {
        this.date = date;
        this.open = Double.parseDouble(open);
        this.high = Double.parseDouble(high);
        this.low = Double.parseDouble(low);
        this.close = Double.parseDouble(close);
        this.volume = Integer.parseInt(volume);
        this.adjClose = Double.parseDouble(adjClose);
    }
}
