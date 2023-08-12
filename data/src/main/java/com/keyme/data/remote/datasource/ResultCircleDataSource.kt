package com.keyme.data.remote.datasource

import com.keyme.domain.entity.response.Circle
import com.keyme.domain.entity.response.ResultCircleResponse
import javax.inject.Inject

class ResultCircleDataSource @Inject constructor() {
    fun getSample(): ResultCircleResponse {
        return ResultCircleResponse().apply {
            data = circleDummy()
        }
    }
}

private fun circleDummy(): List<Circle> {
    return listOf(
        Circle(
            x = -0.633232604611031,
            y = -0.47732413442115296,
            r = 0.09460444572843042,
            level = 1,
            ex = mapOf("datum" to 1),
        ),
        Circle(
            x = -0.7720311587589236,
            y = 0.19946176418549022,
            r = 0.13379089020993573,
            level = 1,
            ex = mapOf("datum" to 2),
        ),
        Circle(
            x = -0.43168871955473165,
            y = -0.6391381648617572,
            r = 0.16385970662353394,
            level = 1,
            ex = mapOf("datum" to 3),
        ),
        Circle(
            x = 0.595447603036083,
            y = 0.5168251295666467,
            r = 0.21154197162246005,
            level = 1,
            ex = mapOf("datum" to 5),
        ),
        Circle(
            x = -0.5480911056188739,
            y = 0.5115139053491098,
            r = 0.2502998363185337,
            level = 1,
            ex = mapOf("datum" to 7),
        ),
        Circle(
            x = 0.043747233552068686,
            y = -0.6848366902134195,
            r = 0.31376744998074435,
            level = 1,
            ex = mapOf("datum" to 11),
        ),
        Circle(
            x = 0.04298737651230445,
            y = 0.5310431146935967,
            r = 0.34110117996070605,
            level = 1,
            ex = mapOf("datum" to 13),
        ),
        Circle(
            x = -0.3375943908160698,
            y = -0.09326467617622711,
            r = 0.39006412239133215,
            level = 1,
            ex = mapOf("datum" to 17),
        ),
        Circle(
            x = 0.46484095011516874,
            y = -0.09326467617622711,
            r = 0.4123712185399064,
            level = 1,
            ex = mapOf("datum" to 19),
        ),
    )
}
