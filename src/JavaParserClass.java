/**
 * Created by user name on 5/30/2014.
 */
import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.StringReader;

public class JavaParserClass {

    public static void main(String[] args){
        JsonParser parser = Json.createParser(new StringReader("[]"));
    }
}
