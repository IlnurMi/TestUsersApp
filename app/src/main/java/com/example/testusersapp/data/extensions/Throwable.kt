package com.example.testusersapp.data.extensions

import com.example.testusersapp.data.utils.ConstantUtils
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.returnMessage(): String {
    return when (this) {
        is HttpException -> {
            when (this.code()) {
                ConstantUtils.SERVER_CODE_500 -> "Ошибка сервера: 500."
                else -> "Ошибка соединения с сервером:${this.code()}."
            }
        }
        is SocketTimeoutException -> {
            "Превышено время ожидания запроса."
        }
        is UnknownHostException -> {
            "Не удалось подключиться к серверу."
        }
        else -> {
            "Неизвестная ошибка соединения."
        }
    }
}