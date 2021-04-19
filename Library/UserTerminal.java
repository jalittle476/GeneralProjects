import java.util.*;
/**
 * Write a description of class UserTerminal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UserTerminal
{
   public static void demo(){
    UserTerminal test = new UserTerminal();
    
    String blah = "This is a test! What will ?&&&?happen";
    
    String halb = test.removeSpecialChars(blah);
    
    System.out.println(halb);
       
    }
    
    public String getSearchInput(){

        Scanner input = new Scanner(System.in); 
        System.out.println("Please enter a topic to search for: ");
        String search = input.nextLine();
        String cleanedInput = removeSpecialChars(search);

        return cleanedInput;
    }

    public String removeSpecialChars(String userInput){

        String dirtyInput = userInput; 
        String cleanInput = "";

        for(int i=0; i < dirtyInput.length(); i++){

            if (dirtyInput.charAt(i)>64 && dirtyInput.charAt(i)<=122 || dirtyInput.charAt(i) == 32)

                cleanInput += dirtyInput.charAt(i);

        }

        return cleanInput;

    }
    
}
