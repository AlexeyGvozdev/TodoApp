package com.sinx.project.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AddNewProjectUseCaseImpl(
    private val projectRepository: ProjectRepository,
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO
) : AddNewProjectUseCase {
    override suspend fun invoke(newProject: ProjectListModel) = withContext(dispatchers){
        projectRepository.addNewProject(newProject.toDb())
    }
}

internal interface AddNewProjectUseCase {
    suspend operator fun invoke(newProject: ProjectListModel)
}