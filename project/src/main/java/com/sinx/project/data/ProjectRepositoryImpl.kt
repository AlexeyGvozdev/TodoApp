package com.sinx.project.data

import com.sinx.project.domain.ProjectRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

internal class ProjectRepositoryImpl : ProjectRepository {

    private var projectList = mutableListOf<ProjectListModel>()
    private val projectFlow = MutableSharedFlow<List<ProjectListModel>>(replay = 1)
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun addNewProject(newProject: ProjectListModel) {
        projectList.add(newProject)
        scope.launch {
            projectFlow.emit(projectList)
        }
    }

    override suspend fun listTasksFlow(): Flow<List<ProjectListModel>> {
        if (projectList.isEmpty()) {
            for (i in 0..2) {
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