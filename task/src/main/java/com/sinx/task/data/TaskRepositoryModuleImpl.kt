package com.sinx.task.data

import com.sinx.coredbinterface.dao.ProjectDAO
import com.sinx.coredbinterface.entity.ProjectDbModel
import com.sinx.task.domain.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryModuleImpl(
    private var projectDao: ProjectDAO
): TaskRepository {

    override suspend fun listTasksFlow(): Flow<List<ProjectDbModel>> {
        return projectDao.getProjectList()
    }
}