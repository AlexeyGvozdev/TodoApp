package com.sinx.project.data

import com.sinx.coredbinterface.dao.ProjectDAO
import com.sinx.coredbinterface.entity.ProjectDbModel
import com.sinx.project.domain.ProjectRepository
import kotlinx.coroutines.flow.Flow

internal class ProjectRepositoryImpl(
    private var projectDao: ProjectDAO
) : ProjectRepository {

    override suspend fun addNewProject(projectDB: ProjectDbModel) {
        projectDao.addTask((projectDB))
    }

    override suspend fun listTasksFlow(): Flow<List<ProjectDbModel>> {
        return projectDao.getTaskList()
    }
}