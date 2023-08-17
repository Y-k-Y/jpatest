package com.y2klabs.jpatest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.y2klabs.jpatest.domain.dto.ProjectResponseDto;
import com.y2klabs.jpatest.domain.entity.Project;
import com.y2klabs.jpatest.domain.entity.ProjectGroup;
import com.y2klabs.jpatest.domain.entity.ProjectMember;
import com.y2klabs.jpatest.domain.repository.ProjectGroupRepository;
import com.y2klabs.jpatest.domain.repository.ProjectMemberRepository;
import com.y2klabs.jpatest.domain.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectTest extends UnitTestBase {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private ProjectGroupRepository projectGroupRepository;

    @Test
    @DisplayName("그룹과 멤버를 함께 불러올 수 있다.")
    void canFetchGroupsWithMembers() throws Exception {
        // given
        Long projectId = createProject();
        Long projectMemberId = createProjectMember(projectId);
        Long projectGroupId = createProjectGroup(projectId);

        MvcResult result;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/projects/{id}", projectId);

        result = mvc.perform(requestBuilder).andReturn();
        ProjectResponseDto project = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
        assertThat(project).isNotNull();
        assertThat(project.getGroups().get(0).getId()).isEqualTo(projectGroupId);
        assertThat(project.getProjectMembers().get(0).getId()).isEqualTo(projectMemberId);
    }

    private Long createProject() throws Exception {
        Project project = Project.builder()
            .name("project")
            .build();

        return projectRepository.save(project).getId();
    }

    private Long createProjectMember(Long projectId) throws Exception {
        ProjectMember member = ProjectMember.builder()
            .nickname("nickname")
            .build();

        member.setProject(projectRepository.findById(projectId).get());
        return projectMemberRepository.save(member).getId();
    }

    private Long createProjectGroup(Long projectId) throws Exception {
        ProjectGroup group = ProjectGroup.builder()
            .name("group")
            .build();

        group.setProject(projectRepository.getReferenceById(projectId));
        return projectGroupRepository.save(group).getId();
    }
}
