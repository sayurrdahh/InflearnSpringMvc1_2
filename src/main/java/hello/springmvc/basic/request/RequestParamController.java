package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    //단순히 HttpServletRequest가 제공하는 방식으로 요청 파라미터를 조회
    @RequestMapping("/request-param-v1")
    public void reqeustParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username= {} , age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String userName,
            @RequestParam("age") int memberAge
    ){

        log.info("username = {} , age = {}",userName, memberAge);
        return "ok";
    }

    @ResponseBody //View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, //변수명이랑 파라미터명이 같으면 이렇게 축약가능
            @RequestParam int age
    ){

        log.info("username = {} , age = {}",username, age);
        return "ok";
    }

    @ResponseBody //View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){ //요청 파라미터 이름과 변수명이 같으면 requestparam 생략 가능
        log.info("username = {} , age = {}",username, age);
        return "ok";
    }

    //필수 파라미터 여부
    //required = true 없으면 오류남. 기본값.
    //required = false 없어도 됨
    @ResponseBody //View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){
        //Integer 객체는 null 가능, int는 불가.
        log.info("username = {} , age = {}",username, age);
        return "ok";
    }

    @ResponseBody //View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "quest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age){
        //default값이 있기 때문에 int형 사용 가능
        log.info("username = {} , age = {}",username, age);
        return "ok";
    }

    //요청파라미터를 map으로 조회하는 법
    @ResponseBody //View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String , Object> paramMap){
        log.info("username = {} , age = {}",paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
       //객체에 getUsername() , setUsername() 메서드가 있으면, 이 객체는 username 이라는 프로퍼티를 가지고 있다.
        log.info("username = {} , age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /*
    * 스프링은 해당 생략시 다음과 같은 규칙을 적용한다.
    * String , int , Integer 같은 단순 타입 = @RequestParam
    * 나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외)
    * argument resolver ? httpservletrequest같은 거
    * */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){ //@ModelAttribute 생략 가능
        //객체에 getUsername() , setUsername() 메서드가 있으면, 이 객체는 username 이라는 프로퍼티를 가지고 있다.
        log.info("username = {} , age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

}
