package app.ecomm.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import app.ecomm.R
import kotlinx.android.synthetic.main.view_button.view.*

class Button @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {
    private var view: View? = View.inflate(context, R.layout.view_button, this)
    private var focusListener: OnFocusChangeListener? = null

    init {
        isFocusable = true
    }

    fun setText(text: CharSequence) {
        setText(text.toString())
    }

    fun setText(text: String) {
        tvLabel.text = text
    }

    fun setText(resId: Int) {
        tvLabel.setText(resId)
    }

    fun drawableLeft(resId: Int) {
        if (resId == 0) {
            imgvwIcon.visibility = View.GONE
        } else {
            imgvwIcon.visibility = View.VISIBLE
            imgvwIcon.setImageResource(resId)
        }
    }
}
