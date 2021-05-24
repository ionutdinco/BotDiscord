package events;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Communication class is responsible with communication with local json file
 */
public class Communication extends ListenerAdapter {
    /**
     * Searches in local file "data.json" for some words from parameter phrase
     * @param phrase an array of String that contains some words to look after
     * @return a List of String that contains informations about words received as a parameter. Can be null too.
     */
    public static List<String> searchKey(String[] phrase) {

        List<String> result = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/data.json"));


            JSONArray arr = (JSONArray) ((JSONObject) obj).get("info");
            for (var word : phrase) {
                Iterator<JSONObject> iterator = arr.iterator();
                while (iterator.hasNext()) {

                    JSONObject jsonObject = iterator.next();

                    if (jsonObject.get("meta").toString().equalsIgnoreCase(word)) {
                        result.add("\n" + jsonObject.get("data").toString());
                    }
                }
            }

            return result;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * This function takes the `key` parameter and search for it in the file "data.json"
     * and add `value` to existing informations, then the file is updated.
     * @param key type String, represent the key after we search
     * @param value type String, represent the information about the key
     */
    public static void addInfo(String key,String value){

        JSONObject obj = new JSONObject();
        obj.put("meta",key);
        obj.put("data",value);

        JSONParser parser = new JSONParser();
        JSONArray a = null;   // reading the file and creating a json array of it.
        try {
            Object object = parser.parse(new FileReader("src/main/resources/data.json"));
            a = (JSONArray) ((JSONObject) object).get("info");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        a.add(obj);   // adding your created object into the array[
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/data.json");         // writing back to the file
            fileWriter.write("{ \"info\" : " + a.toJSONString()+" }");
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
