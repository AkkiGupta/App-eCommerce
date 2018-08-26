package app.ecomm.util.binding;

import android.databinding.BindingAdapter;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Data Binding adapters specific to the app.
 */
public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("visibleInvisible")
    public static void visibleInvisible(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter("progressColor")
    public static void setProgressColor(ProgressBar progressBar, int color) {
        if (progressBar != null && color != 0) {
            progressBar.getIndeterminateDrawable()
                    .setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }
    }
}
