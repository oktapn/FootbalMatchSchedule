package com.example.okta.footballmatchschedule.networking

import android.text.TextUtils
import com.gogobli.android.gogodeliv.networking.Response
import com.google.gson.Gson
import retrofit2.adapter.rxjava.HttpException

import java.io.IOException


import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

class NetworkError(val error: Throwable?) : Throwable(error) {

    val isAuthFailure: Boolean
        get() = error is HttpException && error.code() == HTTP_UNAUTHORIZED

    val isResponseNull: Boolean
        get() = error is HttpException && error.response() == null

    val appErrorMessage: String
        get() {
            if (this.error is IOException) return NETWORK_ERROR_MESSAGE
            if (this.error !is HttpException) return DEFAULT_ERROR_MESSAGE
            val response = this.error.response()
            if (response != null) {
                val status = getJsonStringFromResponse(response)
                if (!TextUtils.isEmpty(status)) return status.toString()

                val headers = response.headers().toMultimap()
                if (headers.containsKey(ERROR_MESSAGE_HEADER))
                    return headers[ERROR_MESSAGE_HEADER]?.get(0).toString()
            }

            return DEFAULT_ERROR_MESSAGE
        }

    protected fun getJsonStringFromResponse(response: retrofit2.Response<*>): String? {
        try {
            val jsonString = response.errorBody()?.string()
            val errorResponse = Gson().fromJson<Response>(jsonString, Response::class.java)
            return errorResponse.status
        } catch (e: Exception) {
            return null
        }

    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val that = o as NetworkError?

        return if (error != null) error == that?.error else that?.error == null

    }

    override fun hashCode(): Int {
        return error?.hashCode() ?: 0
    }

    companion object {
        private const val DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again."
        private const val NETWORK_ERROR_MESSAGE = "No Internet Connection!"
        private const val ERROR_MESSAGE_HEADER = "Error-Message"
    }
}
