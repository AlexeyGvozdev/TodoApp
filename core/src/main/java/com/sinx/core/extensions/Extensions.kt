package com.sinx.core.extensions

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest

fun NavController.navigateTo(uri: String) {
    val request = NavDeepLinkRequest.Builder
        .fromUri(uri.toUri())
        .build()
    this.navigate(request)
}