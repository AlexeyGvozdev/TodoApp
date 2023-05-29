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

tailrec fun <T : ComponentDependencies> findComponentDependencyRecursively(
    target: ComponentProvider<*>,
    dependencyClass: Class<T>
): T {
    if (dependencyClass.isInstance(target.component)) {
        return target.component as T
    }

    val parentComponentProvider = findParentComponentProvider(target)
    if (target == parentComponentProvider) { //dependency not found and we stuck in the loop -> error
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
        else -> throwNoSuitableProvider(dependencyHolder)
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

@Suppress("NOTHING_TO_INLINE")
inline fun throwNoSuitableProvider(target: Any): Nothing {
    throw IllegalStateException("Can not find suitable dagger provider of HasComponentDependencies for $target")
}
