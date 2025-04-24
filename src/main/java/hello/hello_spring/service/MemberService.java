package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return id
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        // optional을 바로 꺼내는걸 별로 안좋다.
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // get을 이용해서 바로바로 꺼내지는 않는다.
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체리스트 조회
     * @return list
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memeberId) {
        return memberRepository.findById(memeberId);
    }
}

//서비스는 비즈니스에 의존적으로 보통 설계하고 리포지토리 같은 경우에는 약간 더 서비스 보다는 기계적으로 개발스럽게 용어를 정한다