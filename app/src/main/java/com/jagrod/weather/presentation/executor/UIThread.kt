package com.jagrod.weather.presentation.executor

import com.jagrod.weather.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
class UIThread @Inject internal constructor() : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}