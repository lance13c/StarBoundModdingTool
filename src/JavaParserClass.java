/**
 * Created by user name on 5/30/2014.
 */
import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.StringReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JavaParserClass {
    private final String DIR_TEST = "C:\\Program Files " +
            "(x86)\\Steam\\SteamApps\\common\\Starbound\\extracted assets\\recipes\\level33\\tier6door.recipe";


    public static void main(String[] args){
        JavaParserClass javaParser = new JavaParserClass();
        JSONParser parser = new JSONParser();
        System.out.println("======Parsing======");

        try {
            Object obj = parser.parse(javaParser.DIR_TEST);

        }catch (ParseException e){
            e.getStackTrace();
        }finally {
            //parser.
        }

    }

    public JavaParserClass(){

    }
}
