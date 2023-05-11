package com.sinx.project.data

import com.sinx.coredbinterface.dao.ProjectDAO
import com.sinx.project.domain.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ProjectRepositoryImpl(
    private var projectDao: ProjectDAO
) : ProjectRepository {

    private val mapper = Mapper()

    override suspend fun addNewProject(newProject: ProjectListModel) {
        projectDao.addTask(mapper.mapProjectItemToProjectDb(newProject))
    }

    override suspend fun listTasksFlow(): Flow<List<ProjectListModel>> {
        return projectDao.getTaskList().map {
            it.map {
                mapper.mapProjectDbToProjectItem(it)
            }
        }
    }
}