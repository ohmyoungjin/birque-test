package biz.brique.coding.test.four;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChartController {

    /**
     * 해당 url 들어가면 Chart 관련된 화면이 나옵니다.
     * @return
     */
    @GetMapping("/test-four")
    public String view() {
        return "chart";
    }
}
