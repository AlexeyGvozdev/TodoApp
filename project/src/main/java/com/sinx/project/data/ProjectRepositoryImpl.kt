package com.sinx.project.data

import com.sinx.project.domain.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class ProjectRepositoryImpl: ProjectRepository {

    private var projectList = mutableListOf<ProjectListModel>()
    private val projectFlow = MutableSharedFlow<List<ProjectListModel>>(replay = 1)


    override suspend fun listTasksFlow(): Flow<List<ProjectListModel>> {
        if (projectList.isEmpty()) {
            for (i in 0..5) {
                val newProject = ProjectListModel(
                    nameProject = "Project Main ${i + 1}",
                    dataProject = "07 Jan 23"
                )
                projectList.add(newProject)
            }
        }
        projectFlow.emit(projectList)
        return projectFlow
    }


}