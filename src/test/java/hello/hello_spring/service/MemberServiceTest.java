package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

// command+shift+t => 테스트 코드의 기본 틀을 자동으로 만들어준다
class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
//    MemberService memberService = new MemberService();
//    같은건데 이걸 구지 또 new로 생성할 필요는 없지 않을까?
//    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    // 중복 코드를 줄이기 위한 방법중 하나인것같음
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

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
//        assertThrows(NullPointerException.class, () -> memberService.join(member2));
//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}