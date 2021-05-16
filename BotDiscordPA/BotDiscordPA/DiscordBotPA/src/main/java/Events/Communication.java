package Events;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.Entity;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Communication extends ListenerAdapter {

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

                    if (jsonObject.get("meta").equals(word)) {
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
