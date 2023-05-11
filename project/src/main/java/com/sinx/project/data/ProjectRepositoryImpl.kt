package com.sinx.project.data

import com.sinx.coredbinterface.dao.ProjectDAO
import com.sinx.project.domain.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ProjectRepositoryImpl(
    private var projectDao: ProjectDAO
) : ProjectRepository {

    private val mapper = Mapper()

//    private var projectList = mutableListOf<ProjectListModel>()
//    private val projectFlow = MutableSharedFlow<List<ProjectListModel>>(replay = 1)
//    private val scope = CoroutineScope(Dispatchers.IO)

    override suspend fun addNewProject(newProject: ProjectListModel) {
        projectDao.addTask(mapper.mapProjectItemToProjectDb(newProject))
    }

    override suspend fun listTasksFlow(): Flow<List<ProjectListModel>> {
//        if (projectList.isEmpty()) {
//            for (i in 0..2) {
//                val newProject = ProjectListModel(
//                    nameProject = "Project Main ${i + 1}",
//                    dataProject = "07 Jan 23"
//                )
//                projectList.add(newProject)
//            }
//        }
//        projectFlow.emit(projectList)
//        return projectFlow
        return projectDao.getTaskList().map {
            it.map {
                mapper.mapProjectDbToProjectItem(it)
            }
        }
    }
}