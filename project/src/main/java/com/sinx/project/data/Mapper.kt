package com.sinx.project.data

import com.sinx.coredbinterface.entity.ProjectDbModel

class Mapper {

    fun mapProjectDbToProjectItem(projectDbModel: ProjectDbModel) = ProjectListModel(
        nameProject = projectDbModel.nameProject,
        dataProject = projectDbModel.dataProject
    )

    fun mapProjectItemToProjectDb(projectItem: ProjectListModel) = ProjectDbModel(
        nameProject = projectItem.nameProject,
        dataProject = projectItem.dataProject
    )
}