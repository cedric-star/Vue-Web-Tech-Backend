package source.com.springbackend;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import org.json.JSONObject;

public class ProcessData {
    private String type;
    private String name;
    public ProcessData() {

    }

    private void parseString(String input) {
        JSONObject jsonObject = new JSONObject(input);

        type = jsonObject.getString("type");
        name = jsonObject.getString("name");
        String ingredients = jsonObject.getString("ingredients");
        String process = jsonObject.getString("process");

        System.out.println("Name: " + name);
        System.out.println("Ingredients: " + ingredients);
        System.out.println("Process: " + process);
    }
    public void safeInput(String input) {
        parseString(input);
        String path="test.json";

        /*if (type.equals("cooking")) {path = ".data/cooking/"+name;}
        else if (type.equals("backing")) {path = ".data/backing/"+name;}
        else {throw new RuntimeException("falscher typ: "+type);}*/

        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            System.out.println("dlsakjf: "+fr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fw = new FileWriter("test.json")) {
            fw.write(input);
            System.out.println("Successfully wrote JSON object to file...");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
