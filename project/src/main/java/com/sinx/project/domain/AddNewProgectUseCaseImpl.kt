package com.sinx.project.domain

internal class AddNewProjectUseCaseImpl(private val projectRepository: ProjectRepository) :
    AddNewProjectUseCase {
    override suspend fun invoke(newProject: ProjectListModel) {
        return projectRepository.addNewProject(newProject.toDb())
    }
}

internal interface AddNewProjectUseCase {
    suspend operator fun invoke(newProject: ProjectListModel)
}