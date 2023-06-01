package com.sinx.core.di

import android.app.Activity
import android.app.Dialog
import android.app.Service
import android.content.ContentProvider
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment

tailrec fun <T : ComponentDependencies> findComponentDependencyRecursively(
    target: ComponentProvider<*>,
    dependencyClass: Class<T>
): T {
    if (dependencyClass.isInstance(target.component)) {
        return target.component as T
    }

    val parentComponentProvider = findParentComponentProvider(target)
    if (target == parentComponentProvider) { // зависимость не найдена и мы застряли в цикле -> ошибка
        throw MissingDependencyException(target, dependencyClass)
    }

    return findComponentDependencyRecursively(
        target = parentComponentProvider,
        dependencyClass = dependencyClass
    )
}

fun findParentComponentProvider(dependencyHolder: Any): ComponentProvider<*> {
    return when (dependencyHolder) {
        is Fragment -> findParentComponentProviderForFragment(dependencyHolder)
        is Activity -> findParentComponentProviderForContext(dependencyHolder)
        is Service -> findParentComponentProviderForContext(dependencyHolder)
        is ContentProvider -> findParentComponentProviderForContext(dependencyHolder.context!!)
        is View -> findParentComponentProviderForContext(dependencyHolder.context)
        is Dialog -> findParentComponentProviderForContext(dependencyHolder.context)
        is Context -> findParentComponentProviderForContext(dependencyHolder)
        else -> checkNoSuitableProvider(dependencyHolder)
    }
}

private fun findParentComponentProviderForContext(context: Context): ComponentProvider<*> {
    val application = context.applicationContext
    require(application is ComponentProvider<*>) { "Application must implement ComponentProvider interface!" }
    return application
}

private fun findParentComponentProviderForFragment(fragment: Fragment): ComponentProvider<*> {
    var current: Fragment? = fragment.parentFragment
    while (current !is ComponentProvider<*>?) {
        current = current?.parentFragment
    }
    val activity = fragment.activity
    return when {
        current != null -> current
        activity is ComponentProvider<*> -> activity
        else -> findParentComponentProviderForContext(fragment.requireContext())
    }
}

class MissingDependencyException(
    source: Any,
    dependency: Class<out ComponentDependencies>
) :
    RuntimeException(
        "Can't find ${dependency.canonicalName} dependency. " +
            "Probable reasons: \n" +
            "- ${source.javaClass.canonicalName} doesn't implement ComponentProvider<${dependency.canonicalName}>"
    )
