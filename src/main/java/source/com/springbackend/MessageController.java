package source.com.springbackend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class MessageController {
    ProcessData processData = new ProcessData();

    @PostMapping("/adddata")
    public String getData(@RequestBody String data) {
        System.out.println(data);
        processData.safeInput(data);
        return data;
    }

}
