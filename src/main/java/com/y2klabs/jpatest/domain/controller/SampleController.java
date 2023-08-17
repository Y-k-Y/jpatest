package com.y2klabs.jpatest.domain.controller;

import com.y2klabs.jpatest.domain.dto.ProjectResponseDto;
import com.y2klabs.jpatest.domain.service.ProjectQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SampleController {

    private final ProjectQueryService projectQueryService;

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectResponseDto> fetch(@PathVariable Long id) {
        ProjectResponseDto project = projectQueryService.fetchWithMembersAndGroups(id);

        return ResponseEntity.ok(project);
    }

}
