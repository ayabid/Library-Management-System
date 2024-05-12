package com.management.library.service;

import com.management.library.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAllMembers();

    List<Member> searchMembers(String keyword);

    Member findMemberById(Long id);

    void createMember(Member member);

    void updateMember(Member member);

    void deleteMember(Long id);

}
