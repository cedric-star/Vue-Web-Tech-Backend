package source.com.springbackend;

import java.io.*;
import java.util.ArrayList;

import org.json.*;

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
    private final String path_baking;
    private final String path_cooking;
    private final String[] requiredJsonKeys;
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
        this.path_baking="./data/baking/baking_recipes.json";
        this.path_cooking="./data/cooking/cooking_recipes.json";
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
     * @return Message for frontend and console logging.
     */
    public String safeInput(String input) {
        this.message = "";
        try {
            JSONObject inputJson = checkInput(input);
            if (isItemInData(inputJson.getString("name"), inputJson.getString("type"))) return "already saved: "+inputJson.getString("name");


            String path = getPathByType(inputJson.getString("type"));
            JSONArray jsonArray = readFromJson(path);

            jsonArray.put(inputJson);

            write2json(jsonArray, path);
        } catch (Exception e) {
            System.err.println("An Exception occured in safeInput():\n"+e.getMessage());
        }

        return initProcessEnd("successfully added recipe");
    }


    /**
     * Description:
     * returns one complete Json File,
     * all recipes for on specific type.
     * @param type Which recipes are requested.
     * @return Parsed Json Array as String.
     */
    public String getData(String type) {
        this.message = "";
        String output = "";
        String path;
        try {
            path = getPathByType(type);
            if (path == null || path.isEmpty()) return "wrong recipe type";

            output = readFromJson(path).toString(0);
        }catch (Exception e) {
            System.err.println("An Exception occured while getData():\n"+e.getMessage());
        }

        return initProcessEnd(output);
    }


    /**
     * Description:
     * Searches for a name
     * @param input Takes name;type and deletes it in Json File for.
     * @return Message for frontend console logging.
     */
    public String deleteInput(String input) {
        this.message = "";
        try {
            String[] inputAr = input.split(";");
            String name = inputAr[0];
            String type = inputAr[1];
            if (!isItemInData(name, type)) return "no such recipe name: "+name;
            String path = getPathByType(type);

            String removedItemName = "";
            JSONArray jsonArray = readFromJson(path);

            for (int i= jsonArray.length()-1; i>=0; i--) {
                removedItemName = jsonArray.getJSONObject(i).getString("name");
                if (removedItemName.equals(name)) {
                    System.out.println("removed recipe name: "+jsonArray.getJSONObject(i).toString());
                    jsonArray.remove(i);
                    break;
                }
            }
            write2json(jsonArray, path);
            System.out.println("successfully deleted: "+removedItemName);

        } catch (Exception e) {
            System.err.println("An Exception occured while deleteInput():\n"+e.getMessage());
        }

        return initProcessEnd("successfully deleted recipe");
    }


    /**
     * Description:
     * Takes name and looks for it in the Json File.
     * @param name This name should stand in Json File "name":""
     * @param type This specifies which type of recipes shall be searched.
     * @return True if found, false if not.
     */
    private boolean isItemInData(String name, String type) {
        JSONArray recipes = null;
        if (type.equals("cooking")) recipes = readFromJson(path_cooking);
        else if (type.equals("baking")) recipes = readFromJson(path_baking);
        else errorHandler(new Exception("Wrong type for method isItemInData"), "Wrong type: "+type);

        try {
            for (int i=0; i<recipes.length(); i++) {
                if (recipes.getJSONObject(i).getString("name").equals(name)) {
                    return true;
                }
            }
        } catch (Exception e) {
            errorHandler(e,"Exception while checking if item: "+name+" is in "+ type +"Recipes Json");
        }

        return false;
    }


    /**
     * Description:
     * Takes a String such as a response message
     * or Json content and either returns it
     * to the user or, if anything went wrong send
     * the message attribute as error response to
     * the frontend.
     * @param msg Content sent to frontend when everything worked.
     * @return Content directly sen to frontend.
     */
    private String initProcessEnd(String msg) {
        if (!message.isEmpty()) {
            msg = message;
            message = "";
            return msg;
        }
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
            errorHandler(e,"Error while parsing input to JSONObject: "+input);
        }

        ArrayList<String> keysNotFound = new ArrayList<>(10);
        ArrayList<String> keysEmpty = new ArrayList<>(10);
        for (String requiredJsonKey : requiredJsonKeys) {
            if (!jsonObject.has(requiredJsonKey)) keysNotFound.add(requiredJsonKey);
            else {
                if (jsonObject.getString(requiredJsonKey).equals("")||jsonObject.getString(requiredJsonKey) == null) {
                    keysEmpty.add(requiredJsonKey);
                }
            }
        }
        if (!keysNotFound.isEmpty()) errorHandler(new Exception("Input Json misses some keys"),"Keys missing: "+keysNotFound.toString());
        if  (!keysEmpty.isEmpty()) errorHandler(new Exception("Input Json has empty Values"),"Keys empty: "+keysEmpty.toString());

        if (jsonObject.length()!=requiredJsonKeys.length+1) {//plus on because of additives are not required
            errorHandler(new Exception("Unexpected amount of keys in input Json"),"Input length "+requiredJsonKeys.length+" != "+jsonObject.length());
        }
        return jsonObject;
    }


    /**
     * Description:
     * Reads the type of Json Object and
     * decides if it´s stored in cooking or baking
     * @param type Parsed Json Object from frontend.
     * @return Path to correct Json File or null and throws RuntimeException().
     */
    private String getPathByType(String type) {
        if (type.equals("cooking")) {return path_cooking;}
        else if (type.equals("baking")) {return path_baking;}
        else { errorHandler(new Exception("The type given to method: getPathByType()\nisn´t cooking or baking!")
                ,"Invalid type: "+type);}
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
        StringBuilder content = new StringBuilder(1000);
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
        } catch (Exception e) {
            errorHandler(e,"Error while reading file from backend");
        } finally {
            try {
                fis.close();
                bis.close();
                dis.close();
            } catch (IOException e) {
                errorHandler(e,"Error while closing file after reading");
            }

        }
        return new JSONArray(content.toString());
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

        } catch (Exception e) {
            errorHandler(e, "Error while writing data back to file");
        } finally {
            try {
                dos.close();
                bos.close();
                fos.close();
            } catch (IOException e) {
                errorHandler(e, "Error while closing file after writing data");
            }
        }
    }


    /**
     * Description:
     * When a fatal error occurs, the error message
     * is being printed out and also
     * saved as String so that it can be sent
     * back for user information
     * @param message Error message that will be sent to user and console.
     * @param e Exception, coming from specific error.
     */
    private void errorHandler(Exception e, String message) {
        System.out.println(e.getMessage());
        System.out.println(message);
        this.message = message;
        throw new RuntimeException(message);
    }
}
