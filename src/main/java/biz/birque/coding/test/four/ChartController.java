package biz.birque.coding.test.four;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChartController {

    @GetMapping("/test-four")
    public String view() {
        return "chart";
    }
}
