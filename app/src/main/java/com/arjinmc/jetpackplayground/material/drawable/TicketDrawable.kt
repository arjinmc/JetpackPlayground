package com.arjinmc.jetpackplayground.material.drawable

import android.R
import android.content.res.ColorStateList
import android.graphics.Color
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel


/**
 * Created by Eminem Lo on 6/29/22
 * email: arjinmc@hotmail.com
 * reference: https://levelup.gitconnected.com/android-tips-advanced-shapes-for-your-drawables-5a7186e68fd
 */
val ticketShapePathModel = ShapeAppearanceModel
    .Builder()
    .setAllCorners(CornerFamily.ROUNDED, 36f)
    .setLeftEdge(TicketEdgeTreatment(36f))
    .setRightEdge(TicketEdgeTreatment(36f))
    .build()

class TicketDrawable : MaterialShapeDrawable(ticketShapePathModel) {
    init {
        // set the color what ever your want
        val states = arrayOf(intArrayOf(R.attr.state_hovered))
        val colors = intArrayOf(Color.parseColor("#ff2332"))
        val myColorList = ColorStateList(states, colors)
        fillColor = myColorList
        state = states[0]

        // add shadow
        shadowCompatibilityMode = SHADOW_COMPAT_MODE_ALWAYS
        elevation = 10f
        shadowVerticalOffset = 6
        setUseTintColorForShadow(false)
        setShadowColor(Color.BLACK)
    }
}