package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //return 하는 값을 화면에 바로 반환해준다?
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass()); //내 클래스 지정

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
       // System.out.println("name = " + name);

        log.trace("trace log = {}" , name);
        log.debug("debug log = {}", name);
        log.info("info log = {}",name);
        log.warn("warn log = {}",name);
        log.error("error log = {}", name);

        return "ok";
    }

}
