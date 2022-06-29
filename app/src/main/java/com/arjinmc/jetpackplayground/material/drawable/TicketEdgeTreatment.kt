package com.arjinmc.jetpackplayground.material.drawable

import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath

/**
 * source: https://levelup.gitconnected.com/android-tips-advanced-shapes-for-your-drawables-5a7186e68fd
 */
class TicketEdgeTreatment(
    private val size: Float
) : EdgeTreatment() {
    override fun getEdgePath(
        length: Float,
        center: Float,
        interpolation: Float,
        shapePath: ShapePath
    ) {
        val circleRadius = size * interpolation
//        shapePath.lineTo(center - circleRadius, 0f)
        shapePath.addArc(
            center - circleRadius, -circleRadius,
            center + circleRadius, circleRadius,
            180f,
            -180f
        )
//        shapePath.lineTo(length, 0f)
    }
}