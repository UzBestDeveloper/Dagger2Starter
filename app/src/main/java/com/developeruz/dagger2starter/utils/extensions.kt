package com.developeruz.dagger2starter.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Environment
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.util.Size
import android.util.TypedValue
import android.view.Display
import android.view.View
import android.view.ViewConfiguration
import android.view.WindowManager
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import kotlin.math.roundToInt


fun Fragment.hideKeyboard() {
    val imm: InputMethodManager =
        activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view: View? = activity?.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.hideKeyboard() {
    val imm: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showSoftInput() {
    val imm: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
}


@Suppress("DEPRECATION")
val Context.screenResolution: Size
    get() {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) display
        else wm.defaultDisplay

        val metrics = DisplayMetrics()
        display?.getRealMetrics(metrics)

        val width = metrics.widthPixels
        val height = metrics.heightPixels

        return Size(width, height)
    }

fun Context.px(dp: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun Context.px(dp: Int) = px(dp.toFloat()).toInt()

fun Context.getOutputDirectory(): File {
    val appContext = applicationContext
    val mediaDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return if (mediaDir != null && mediaDir.exists())
        mediaDir else appContext.filesDir
}

fun View.visible() {
    if (visibility != View.VISIBLE)
        visibility = View.VISIBLE
}

fun View.invisible() {
    if (visibility != View.INVISIBLE)
        visibility = View.INVISIBLE
}

fun View.gone() {
    if (visibility != View.GONE)
        visibility = View.GONE
}

fun View.notClickable() {
    isEnabled = false
    alpha = 0.5f
    isClickable = false
    isFocusable = false
}

fun View.clickable() {
    isEnabled = true
    alpha = 1f
    isClickable = true
    isFocusable = true
}

fun Fragment.drawable(@DrawableRes d: Int) = ContextCompat.getDrawable(requireContext(), d)

fun Fragment.color(@ColorRes c: Int) = ContextCompat.getColor(requireContext(), c)

fun Fragment.toast(message: String, isLong: Boolean = false) = if (isLong) Toast.makeText(
    requireContext(),
    message,
    Toast.LENGTH_LONG
).show()
else Toast.makeText(
    requireContext(),
    message,
    Toast.LENGTH_SHORT
).show()

fun View.singleClickable(l: (View?) -> Unit) {
    setOnClickListener(SingleClickListener(l))
}

class SingleClickListener(private val click: (v: View) -> Unit) : View.OnClickListener {

    companion object {
        private val DOUBLE_CLICK_TIMEOUT = ViewConfiguration.getDoubleTapTimeout()
    }

    private var lastClick: Long = 0

    override fun onClick(v: View) {
        if (getLastClickTimeout() > DOUBLE_CLICK_TIMEOUT) {
            lastClick = System.currentTimeMillis()
            click(v)
        }
    }

    private fun getLastClickTimeout(): Long {
        return System.currentTimeMillis() - lastClick
    }
}

