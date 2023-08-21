package com.sinx.project.domain

import com.sinx.coredbinterface.entity.ProjectDbModel

data class ProjectListModel(val nameProject: String, val dataProject: String)

fun ProjectDbModel.toDomain() = ProjectListModel(nameProject, dataProject)

fun ProjectListModel.toDb() = ProjectDbModel(nameProject, dataProject)