package com.sinx.coredbinterface

import com.sinx.coredbinterface.dao.TaskDAO

interface DbProvider {

    fun getTaskDAO(): TaskDAO
}