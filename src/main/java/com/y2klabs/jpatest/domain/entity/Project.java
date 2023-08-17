package com.y2klabs.jpatest.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProjectGroup> groups = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProjectMember> projectMembers = new ArrayList<>();

    @Builder
    public Project(
        String name
    ) {
        this.name = name;
    }

    public void addGroup(ProjectGroup group) {
        this.groups.add(group);
        group.setProject(this);
    }

    public void removeGroup(ProjectGroup group) {
        this.groups.remove(group);
        group.setProject(null);
    }

    public void addProjectMember(ProjectMember projectMember) {
        this.projectMembers.add(projectMember);
        projectMember.setProject(this);
    }

    public void removeProjectMember(ProjectMember projectMember) {
        this.projectMembers.remove(projectMember);
        projectMember.setProject(null);
    }
}
