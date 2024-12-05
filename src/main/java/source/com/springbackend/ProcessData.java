package source.com.springbackend;

import java.io.FileWriter;
import java.io.IOException;
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
        String path;
        if (type.equals("cooking")) {path = "./cooking/"+name;}
        else if (type.equals("backing")) {path = "./backing/"+name;}
        else {throw new RuntimeException("falscher typ: "+type);}


        try (FileWriter file = new FileWriter("test.json")) {
            file.write(input);
            System.out.println("Successfully wrote JSON object to file...");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
