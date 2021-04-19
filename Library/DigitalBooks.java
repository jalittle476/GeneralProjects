
/**
 * Write a description of class eBooks here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DigitalBooks
{
    String title; 
    /**
     * Constructor for objects of class eBooks
     */
    public DigitalBooks(String title)
    {
        this.title = title; 
        
    }
    
    @Override 
    public String toString()
    {
        return "The title of this Book is " + this.title; 
    }
}
