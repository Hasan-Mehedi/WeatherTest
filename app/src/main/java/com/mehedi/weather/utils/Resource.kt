package com.mehedi.weather.utils

import com.mehedi.weather.utils.Status.ERROR
import com.mehedi.weather.utils.Status.LOADING
import com.mehedi.weather.utils.Status.SUCCESS

/**
 * Created by mhasan2 on 2/20/21.
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(
                status = SUCCESS,
                data = data,
                message = null
            )

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(
                status = ERROR,
                data = data,
                message = message
            )

        fun <T> loading(data: T?): Resource<T> =
            Resource(
                status = LOADING,
                data = data,
                message = null
            )
    }
}