package com.jagrod.weather.presentation.base

import com.jagrod.weather.domain.interactor.UseCase

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
open class Presenter(vararg useCases: UseCase<*, *>) {

    private val useCasesList: List<UseCase<*, *>> = ArrayList()

    init {
        for (userCase in useCases) {
            useCasesList.plus(userCase)
        }
    }

    open fun onResume(view: View) {}

    open fun onPause() {

    }

    open fun onDestroy() {
        for (userCase in useCasesList) {
            userCase.dispose()
        }
    }
}