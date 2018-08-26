package app.ecomm.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import app.ecomm.R

class ProgressBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    val view = View.inflate(context, R.layout.view_progress_bar, this)
}
