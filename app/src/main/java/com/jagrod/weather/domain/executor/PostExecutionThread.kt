package com.jagrod.weather.domain.executor

import io.reactivex.Scheduler

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}