package com.sinx.project.domain

import com.sinx.project.data.ProjectListModel

class AddNewProjectUseCaseImpl(private val projectRepository: ProjectRepository) :
    AddNewProjectUseCase {
    override fun invoke(newProject: ProjectListModel) = projectRepository.addNewProject(newProject)
}

interface AddNewProjectUseCase {
    operator fun invoke(newProject: ProjectListModel)
}