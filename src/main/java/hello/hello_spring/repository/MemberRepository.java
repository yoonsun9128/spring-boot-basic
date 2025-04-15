package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장
    Optional<Member> findById(Long id); //null 처리하는 방법
    Optional<Member> findByName(String name);
    List<Member> findAll(); //저장된 리스트 모두 변환
    Void clearStore();
}
