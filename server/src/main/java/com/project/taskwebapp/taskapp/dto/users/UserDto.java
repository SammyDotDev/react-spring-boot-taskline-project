package com.project.taskwebapp.taskapp.dto.users;

import com.project.taskwebapp.taskapp.models.Project;

import java.util.List;

public record UserDto(
        Integer id,
        String username,
        String email,
        List<Project>projects
) {
}
