@file:Suppress("unused")

package com.sinx.core.di

import android.app.Activity
import android.app.Dialog
import android.app.Service
import android.content.ContentProvider
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment

interface ComponentDependencies

inline fun <reified T : ComponentDependencies> Context.findComponentDependencies(): T {
    return findComponentDependencyRecursively(findParentComponentProvider(this), T::class.java)
}

inline fun <reified T : ComponentDependencies> Fragment.findComponentDependencies(): T {
    return findComponentDependencyRecursively(findParentComponentProvider(this), T::class.java)
}

inline fun <reified T : ComponentDependencies> Activity.findComponentDependencies(): T {
    return findComponentDependencyRecursively(findParentComponentProvider(this), T::class.java)
}

inline fun <reified T : ComponentDependencies> View.findComponentDependencies(): T {
    return findComponentDependencyRecursively(findParentComponentProvider(this), T::class.java)
}

inline fun <reified T : ComponentDependencies> Dialog.findComponentDependencies(): T {
    return findComponentDependencyRecursively(findParentComponentProvider(this), T::class.java)
}

inline fun <reified T : ComponentDependencies> Service.findComponentDependencies(): T {
    return findComponentDependencyRecursively(findParentComponentProvider(this), T::class.java)
}

inline fun <reified T : ComponentDependencies> ContentProvider.findComponentDependencies(): T {
    return findComponentDependencyRecursively(findParentComponentProvider(this), T::class.java)
}

@Suppress("NOTHING_TO_INLINE")
inline fun checkNoSuitableProvider(target: Any): Nothing {
    error() { "Can not find suitable dagger provider of HasComponentDependencies for $target" }
}
