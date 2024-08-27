package com.springmvc1.domain.member;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();


    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void save() {

        Member member = new Member("shin", 20);

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId());

        assertThat(savedMember).isEqualTo(findMember);
    }


    @Test
    void findAll() {
        Member member1 = new Member("shin1", 10);
        Member member2 = new Member("shin2", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        assertThat(result).hasSize(2);
    }
}