package com.management.library.service;

import com.management.library.entity.Member;
import com.management.library.exception.NotFoundException;
import com.management.library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public List<Member> searchMembers(String keyword) {
        if (keyword != null) {
            return memberRepository.search(keyword);
        }
        return findAllMembers();
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Member not found with ID %d", id)));
    }

    @Override
    public void createMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void updateMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Member not found with ID %d", id)));

        memberRepository.delete(member);
    }


}
