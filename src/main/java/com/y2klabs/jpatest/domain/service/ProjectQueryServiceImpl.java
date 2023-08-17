package com.y2klabs.jpatest.domain.service;

import com.y2klabs.jpatest.domain.dto.ProjectResponseDto;
import com.y2klabs.jpatest.domain.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectQueryServiceImpl implements ProjectQueryService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponseDto fetchWithMembersAndGroups(Long id) {
        return projectRepository.findWithProjectGroupsById(id)
            .map(ProjectResponseDto::new)
            .orElseThrow(EntityNotFoundException::new);
    }
}
