package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //테스트를 실행할때 트랙잭션을 먼저 실행하고 db에 데이터를 insert를 한 다음 롤백을 해준다. 이렇게 하면 테스트를 반복할 수 있다.
class MemberServiceIntegrationTest {
    //스프링 컨테이너 한테 멤버에 대한 정보 내놓으라고 해야함
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    // 제목을 한글로 바꿔도 상관없다!, 빌드 될때 실제 코드에 포함이 되지 않는다.
    void join() {
        //given : 이런상환이 주어짐
        Member member = new Member();
        member.setName("spring");
        //when:
        Long saveId = memberService.join(member);
        //then : 이런결과가 나와야한다
        Member findMember =  memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}