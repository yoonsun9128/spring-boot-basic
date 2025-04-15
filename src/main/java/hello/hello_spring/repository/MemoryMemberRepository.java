package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    //저장담을 메모리, 단순한 형태이기 때문에 hashmap이용
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //동시성 문제를 고려해서 어텀롱을 사용한다

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id를 세팅을 하고
        store.put(member.getId(), member); //store에 저장을해준다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //객체가 없을경우 null에 대한 처리를 위해
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나라도 찾는것
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public void clearStore() {
        store.clear();
    }
}
