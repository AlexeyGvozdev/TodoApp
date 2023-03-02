package com.sinx.project.domain

import com.sinx.project.data.ProjectListModel

class GetNewProjectUseCase(private val projectRepository: ProjectRepository) {

    fun getProject(): ProjectListModel{
        return ProjectListModel("New Project", "01.02.1996")
    }
}