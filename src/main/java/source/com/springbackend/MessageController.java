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
        System.out.println("trying to add: "+data);
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
        System.out.println("trying to get:"+type);
        return processData.getData(type);
    }


    /**
     * Description:
     * Definges /app/deletedata as url for deleting an Json
     * Object in the Json Files.
     * @param msg gives name and type of recipe: "name;type"
     * @return Sends user information message back to user.
     */
    @PostMapping("deletedata")
    public String deleteData(@RequestBody String msg) {
        System.out.println("trying to delete: "+msg);
        return processData.deleteInput(msg);
    }
}
