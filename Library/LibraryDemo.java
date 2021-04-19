import java.util.*;
import java.net.*;  // url
import java.io.*;    // stream
import javax.json.*;   // Json Parser

public class LibraryDemo {

    static String LIBRARY_URL;
    private String topicSearch = ""; 
    
    public static void main () {
        LibraryDemo test = new LibraryDemo();
        System.out.println("Hello, this program will query The Digital Public Library of America's website based on your input.");
        
        test.setTopicSearch();
        test.setURL();
        
        
        
        System.out.println(LIBRARY_URL);
        InputStream input = openURL(LIBRARY_URL);
        JsonReader jsonReader = Json.createReader(input);
        JsonStructure js = jsonReader.read();
        jsonReader.close();       
        closeStream(input);
        
        JsonArray jsa = null;        
        JsonObject jso = null;
        if (js instanceof JsonObject) {
            jso= (JsonObject) js;
            jsa = jso.getJsonArray("docs");
        } else {
            jsa = (JsonArray)js;
        }
        int s = jsa.size();
        DigitalBooks [ ] booksData = new DigitalBooks [ s ];
        for (int i=0; i<s; i++) {
            JsonObject jdoc = jsa.getJsonObject(i);
            JsonObject source = jdoc.getJsonObject("sourceResource"); 
            JsonArray title = source.getJsonArray("title");

            String bookTitle = title.getString(0);

            DigitalBooks books = new DigitalBooks (bookTitle);

            booksData[i] = books;
        }
        System.out.println("Here are a list of related titles.");
        for (int i=0; i<booksData.length; i++) {
            DigitalBooks book = booksData[i];
            System.out.println(i + ") " + book);
        }
    }

    private static void closeStream (InputStream is) {
        try {
            is.close();
        } catch (Exception e) {
            System.err.println("Could not close the input stream.");
            System.err.println(e);
        }
    }

    private static InputStream openURL (String http) {
        URL url;
        InputStream source = null;
        try {
            url = new URL(http);
            source = url.openStream();
        } catch (Exception e) {
            System.err.println("Cannot open URL "+http);
            System.err.println(e);
        }
        return source;
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
    
    private void setTopicSearch(){
        topicSearch = getSearchInput();
    }
    
    private void setURL(){

        LIBRARY_URL = "https://api.dp.la/v2/items?&q=" + topicSearch + 
        "&sourceResource.format=Print&sourceResource.type=text&api_key=8beae7101d1627eeae162f7966a2bf87";

    }
    
}
