package com.wecanteen105.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val tmdDispatcher: TmdDispatchers)

enum class TmdDispatchers {
    Default,
    IO,
}