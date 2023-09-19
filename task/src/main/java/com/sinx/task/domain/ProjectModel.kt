package com.sinx.task.domain

import com.sinx.coredbinterface.entity.ProjectDbModel

data class ProjectModel(val nameProject: String, val dataProject: String)

fun ProjectDbModel.toDomain() = ProjectModel(nameProject, dataProject)

fun ProjectModel.toDb() = ProjectDbModel(nameProject, dataProject)