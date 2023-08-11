package com.keyme.domain.entity

abstract class BaseResponse<T : Any> {
    lateinit var statusCode: String
    lateinit var message: String
    lateinit var data: T
}
