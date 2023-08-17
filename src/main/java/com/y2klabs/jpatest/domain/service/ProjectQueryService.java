package com.y2klabs.jpatest.domain.service;

import com.y2klabs.jpatest.domain.dto.ProjectResponseDto;

public interface ProjectQueryService {

    ProjectResponseDto fetchWithMembersAndGroups(Long id);
}
