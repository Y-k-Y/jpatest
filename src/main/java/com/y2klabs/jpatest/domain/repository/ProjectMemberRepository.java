package com.y2klabs.jpatest.domain.repository;

import com.y2klabs.jpatest.domain.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

}
