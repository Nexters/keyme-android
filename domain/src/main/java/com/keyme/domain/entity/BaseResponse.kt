package com.keyme.domain.entity

abstract class BaseResponse<T : Any> {
    lateinit var code: String
    lateinit var constant: String
    lateinit var message: String
    lateinit var data: T
}
