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

//    필드 주입 -> 뭔가 다른 활용할 수 있는 방법이 없어서 별로 선호 하지 않는다.
//    @Autowired private MemberService memberService;

    // setter 주입 -> 누군가가 멤버 컨트롤을 호출할때 public으로 노출이 된 상태라서 다른 누군가가 기본 세팅을 변경할 수 있는 단점이 있다.
//    private MemberService memberService;
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
}
