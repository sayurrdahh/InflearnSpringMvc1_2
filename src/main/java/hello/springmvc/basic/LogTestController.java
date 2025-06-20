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

        //로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
        //log.debug("String concat log=" + name);

        log.trace("trace log = {}" , name);
        log.debug("debug log = {}", name);
        log.info("info log = {}",name); 
        log.warn("warn log = {}",name);
        log.error("error log = {}", name);

        return "ok";
    }

}
