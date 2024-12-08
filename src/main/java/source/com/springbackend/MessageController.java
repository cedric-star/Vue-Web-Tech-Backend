package source.com.springbackend;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Defines the Message Controller and
 * sets reachable path to /app.
 * @see org.springframework
 * @see ProcessData
 * @see CorsConfig
 * @author Cedric Wünsch
 */
@RestController
@RequestMapping("/app")
public class MessageController {
    ProcessData processData = new ProcessData();

    /**
     * Description:
     * Defines /app/adddata as url for sending
     * the Json Object (Recipe) to the backend.
     * @param data Body of the incoming Request.
     * @return Error messages for user instructions.
     */
    @PostMapping("/adddata")
    public String addData(@RequestBody String data) {
        return processData.safeInput(data);
    }


    /**
     * Description:
     * Defines /app/getdata as url for receiving
     * the Json Data for either cooking or
     * baking.
     * @param type Decides if cooking or baking recipes.
     * @return Sends data back to user.
     * @see ProcessData
     */
    @PostMapping("getdata")
    public String getData(@RequestBody String type) {
        System.out.println("type: "+type);

        String path = "";
        if (type.equals("cooking")) path = "./data/cooking/cooking_recipes.json";
        if (type.equals("baking")) path = "./data/baking/baking_recipes.json";
        if (path.isEmpty()) return "wrong recipe type";
        System.out.println("path: "+path);

        String output = processData.readFromJson(path).toString(0);
        System.out.println("output: "+output);
        return output;
    }
}
