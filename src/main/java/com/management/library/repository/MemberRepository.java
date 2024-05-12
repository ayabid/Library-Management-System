package com.management.library.repository;

import com.management.library.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.name LIKE %?1%" +
            " OR m.idCard LIKE %?1%" +
            " OR m.address LIKE %?1%" +
            " OR m.email LIKE %?1%" +
            " OR m.phone LIKE %?1%")
    List<Member> search(String keyword);
}

