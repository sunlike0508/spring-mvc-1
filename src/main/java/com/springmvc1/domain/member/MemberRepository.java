package com.springmvc1.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음. 실무에서는 concurrentHashMap, AtomicLong 사용고려
 */

public class MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static final MemberRepository instance = new MemberRepository();
    private static long sequence = 0L;


    private MemberRepository() {

    }


    public static MemberRepository getInstance() {
        return instance;
    }


    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }


    public Member findById(Long id) {
        return store.get(id);
    }


    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore() {
        store.clear();
    }
}
