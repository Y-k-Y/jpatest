package com.y2klabs.jpatest.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "project_groups")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectGroup extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_PROJECT_GROUP_PROJECT"))
    private Project project;

    @Builder
    public ProjectGroup(String name) {
        this.name = name;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
