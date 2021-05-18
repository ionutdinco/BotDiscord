package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ResultParser {
//
//    private List<String> result;
//
//
//    public ResultParser(List<String> result) {
//        this.result = result;
//    }
//
//    public ResultParser(){
//        this.result = new ArrayList<>();
//    }
//
//    public void addResult(String result){
//        this.result.add(result);
//    }
//
//    public List<String> getResult(){
//        return this.result;
//    }

    public static List<String> parse(List<String> result){
        List<String> answere = new ArrayList<>();
        try {
            String[] resultSet = result.get(0).split(",");
            for(String crt: resultSet){

                if(crt.contains("title=")){
                    String curr = "```diff\n-Title\n";
                    String[] saVad = crt.split("title=");
                    curr += saVad[1];
                    curr += "```";
                    answere.add(curr);
                }
                if(crt.contains("description=")){
                    String curr = "```css\n[Description]\n";
                    curr += crt.split("description=")[1];
                    curr += "```";
                    answere.add(curr);
                }
                if(crt.contains("link=")){
                    String curr = "```ini\n[Link]\n```";
                    curr += crt.split("link=")[1];
                    answere.add(curr);
                }

            }

            if(answere.isEmpty()){
//            String current = "```json\n\"";
//
//            for(String s: result){
//                current += s;
//            }
//
//            current += "\"\n```";
                answere = result;
            }

            System.out.println(answere);
            result = answere;
            return result;
        } catch (Exception e) {
            answere.add("I don't know!");
            result = answere;
            return result;
        }

    }


}
