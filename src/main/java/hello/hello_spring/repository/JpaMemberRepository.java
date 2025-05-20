package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //jpql --> pk 기반이 아닌 데이터를 찾을때
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("mane", name).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //객채를 대상으로 쿼리를 날리는 거다. select는 m=em 자체를 날린다
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
