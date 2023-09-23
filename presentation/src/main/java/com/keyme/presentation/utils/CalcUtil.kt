package com.keyme.presentation.utils

import kotlin.math.floor

fun Double.scale(value: Int) = (this * value).toFloat()

fun Double.scale(value: Float) = (this * value).toFloat()

fun Float.toKeymeScore() = floor(this * 10) / 10
