package com.y2klabs.jpatest.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "project_members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_PROJECT_MEMBER_PROJECT"))
    private Project project;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Builder
    public ProjectMember(
        String nickname,
        boolean isDeleted
    ) {
        this.nickname = nickname;
        this.isDeleted = isDeleted;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
