package com.wecanteen105.core.network.demo

import java.io.InputStream

fun interface DemoAssetManger {
    fun open(fileName: String): InputStream
}