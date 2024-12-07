package source.com.springbackend;

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


    @PostMapping("getdata")
    public String getData(@RequestBody String data) {
        //String path = processData.getPathByType()
        return null;
    }




}
