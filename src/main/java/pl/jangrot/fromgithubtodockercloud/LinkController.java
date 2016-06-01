package pl.jangrot.fromgithubtodockercloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LinkController {

    @RequestMapping(value = "/links")
    public List<String> links() {
        return Arrays.asList("http://google.com", "http://spring.io");
    }
}
