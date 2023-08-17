package com.y2klabs.jpatest.domain.repository;

import com.y2klabs.jpatest.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("""
        select distinct p
        from Project p
            left join fetch p.groups
        where p.id = :id
    """)
    Optional<Project> findWithProjectGroupsById(@Param("id") Long id);

}
