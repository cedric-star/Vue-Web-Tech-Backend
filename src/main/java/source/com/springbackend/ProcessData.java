package source.com.springbackend;

import java.io.*;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

/**
 * Description:
 * This class stores an incoming String as Json Object.
 * Stored in /data as baking or cooking recipe.
 * Checks input for errors and parses to Json Object
 * Works by reading Json File,
 * adding input to temporary Json Object,
 * and then writing everything back to Json file.
 * @author Cedric Wünsch
 * @see org.json
 * @see java.io
 */
public class ProcessData {
    private String path_backing;
    private String path_coocking;
    private String[] requiredJsonKeys;
    private String message;


    /**
     * Description:
     * Constructor sets paths to Json Files,
     * and defines the required Keys in incoming
     * Json Objects.
     * Also, defines message for sending back to the frontend,
     * about if or if not everything worked.
     */
    public ProcessData() {
        this.path_backing="./data/baking/baking_recepies.json";
        this.path_coocking="./data/cooking/cooking_recepies.json";
        this.requiredJsonKeys = new String[]{"type", "name", "ingredients", "process"};
        this.message = "";
    }


    /**
     * Description:
     * Wrapper Method for everything,
     * creates JSONArray, adds JSONObject,
     * finally writes everything back.
     * Try catch block wrapping code so the error
     * (if happen) don´t conflict with the running code.
     * @param input Incoming Json Object from frontend.
     * @return message for frontend and console logging
     */
    public String safeInput(String input) {
        try {
            JSONObject inputJson = checkInput(input);

            String path = getPathByType(inputJson);
            JSONArray jsonArray = readFromJson(path);

            jsonArray.put(inputJson);

            write2json(jsonArray, path);
        } catch (Exception e) {
            System.err.println("An Exception occured: " + e.getMessage());
        }
        String msg = getMessages();
        message = "";
        System.out.println(msg);
        return msg;
    }


    /**
     * Description:
     * Tests if input is valid, testing for:
     * is it a Json Object?
     * has it all required Keys?
     * is nothing empty?
     * ist the amount of key correct?
     * @param input Json Object as String from frontend.
     * @return Finished Json Object, or null if anything went wrong.
     */
    private JSONObject checkInput(String input) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(input);
        } catch (Exception e) {
            e.printStackTrace();
            errorHandler("Error while parsing input to JSONObject: "+input);
        }
        for (String requiredJsonKey : requiredJsonKeys) {
            if (!jsonObject.has(requiredJsonKey)) {
                errorHandler("Error, input hasn´t key "+requiredJsonKey);
            }
            if (jsonObject.getString(requiredJsonKey).equals("")) {
                errorHandler("Error, key: "+requiredJsonKey+" is empty");
            }
        }
        if (jsonObject.length()!=requiredJsonKeys.length) {
            errorHandler("Error, input length "+requiredJsonKeys.length+" != "+jsonObject.length());
        }
        return jsonObject;
    }


    /**
     * Description:
     * Reads the type of Json Object and
     * decides if it´s stored in cooking or baking
     * @param inputJson Parsed Json Object from frontend.
     * @return Path to correct Json File or null and throws RuntimeException().
     */
    private String getPathByType(JSONObject inputJson) {
        String type = inputJson.getString("type");
        if (type.equals("cooking")) {return path_coocking;}
        else if (type.equals("baking")) {return path_backing;}
        else { errorHandler("Invalid type: "+type);}
        return null;
    }


    /**
     * Description:
     * Reading line by line with 3 stacked InputStreams.
     * Writes \n after every line.
     * Storing read content as Json Array
     * @param path Decides wich Json File should be read from.
     * @return Json Array from backend Json File.
     */
    private JSONArray readFromJson(String path) {
        StringBuilder content = new StringBuilder();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(path);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            while (dis.available() > 0) {
                content.append(dis.readLine());
                content.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            errorHandler("Error while reading file from backend!");
        } finally {
            try {
                fis.close();
                bis.close();
                dis.close();
            } catch (Exception e) {
                errorHandler("Error while closing file after reading!");
                e.printStackTrace();
            }

        }
        JSONArray jsonArray = new JSONArray(content.toString());
        return jsonArray;
    }


    /**
     * Description:
     * Stores everything by writing it back to
     * the correct Json File.
     * @param content Finished Array that needs to be stored.
     * @param path Defines correct path to required Json File.
     */
    private void write2json(JSONArray content, String path) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream(path);
             bos = new BufferedOutputStream(fos);
             dos = new DataOutputStream(bos);

            dos.writeBytes(content.toString(2));

        } catch (IOException e) {
            errorHandler("Error while writing to file in backend!");
            e.printStackTrace();
        } finally {
            try {
                dos.close();
                bos.close();
                fos.close();
            } catch (IOException e) {
                errorHandler("Error while closing file after writing!");
                e.printStackTrace();
            }
        }
    }


    /**
     * Description:
     * When a fatal error occures, the error message
     * is being printed out and also
     * saved as String so that it can be sent
     * back for user information
     * @param message Error message that will be sent to user and console.
     */
    private void errorHandler(String message) {
        this.message = message;
        throw new RuntimeException(message);
    }


    /**
     * Description:
     * Getter for messages,
     * turns String into Json Object, it
     * can be interpreted by JavaSkript.
     * @return String error message
     * @see MessageController
     */
    private String getMessages() {
        if (message.isEmpty()) return "{\"message\":\"no errors occured!\"}";
        else {
            String output = "{\"message\":\""+message+"\"}";
            return output;
        }
    }

}
