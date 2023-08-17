package com.y2klabs.jpatest.domain.dto;

import com.y2klabs.jpatest.domain.entity.Project;
import com.y2klabs.jpatest.domain.entity.ProjectGroup;
import com.y2klabs.jpatest.domain.entity.ProjectMember;
import com.y2klabs.jpatest.global.util.DtoUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectResponseDto {

    private Long id;
    private String name;
    private List<ProjectGroup> groups;
    private List<ProjectMember> projectMembers;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ProjectResponseDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.groups = project.getGroups();
        this.projectMembers = DtoUtils.isNotYetLoadedOrEmpty(project.getProjectMembers()) ? Collections.emptyList() : project.getProjectMembers();
        this.createdAt = project.getCreatedAt();
        this.modifiedAt = project.getModifiedAt();
    }
}
