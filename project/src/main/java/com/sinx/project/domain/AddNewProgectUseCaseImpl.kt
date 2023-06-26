package com.sinx.project.domain

import com.sinx.project.data.ProjectListModel

internal class AddNewProjectUseCaseImpl(private val projectRepository: ProjectRepository) :
    AddNewProjectUseCase {
    override fun invoke(newProject: ProjectListModel) = projectRepository.addNewProject(newProject)
}

internal interface AddNewProjectUseCase {
    operator fun invoke(newProject: ProjectListModel)
}