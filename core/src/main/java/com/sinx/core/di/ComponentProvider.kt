package com.sinx.core.di

interface ComponentProvider<C : ComponentDependencies> {
    val component: C
}
