package hello.hello_spring.controller;
// hello.hello_spring 하위에 있는 애들만 빈 현성을 진행한다.(기본적으로)
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// spring container manage
@Controller
public class MemberController {
    // 이제 관리하게 되면 다 스프링 컨테이너에 등록하고 스프링 컨테이너로부터 변수? 받도록 바꿔야한다. 중복되는 부분을 최대한 줄이기 위해서 인것같다.
    private final MemberService memberService;

    //생성자 호출 @Autowired : 연결다리 역활
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
