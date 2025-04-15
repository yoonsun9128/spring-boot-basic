package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    //새로운 객체
    MemberRepository repository = new MemoryMemberRepository();

    // 콜백함수
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        // get으로 바로 꺼낸느게 좋은 방법은 아니다. 우선 테스트코드에서는 보통 get이용한다고함 (optional 설정을하기때문에)
        Member result = repository.findById(member.getId()).get();

        // 매번 글자로 볼수는 없다!
//        System.out.println("result = " + (result == member));
        //(기대값, 실제값)
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }
    //모든 테스트는 각각이 영향을 받으면 안된다. 왜냐 실행하는데 순서가 보장되지 않음
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
