package com.storeapp.utils

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.Window
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.storeapp.R

class ProgressUtils {
    private var dialog: Dialog? = null
    fun showProgress(activity: Activity): Dialog {
        if (dialog == null) {
            dialog = Dialog(activity, R.style.blurTheme).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                window?.setBackgroundDrawable(0.toDrawable())
                setContentView(R.layout.dialog_progress)
                setCancelable(false)

                setOnShowListener {
                    findViewById<TextView>(R.id.txtLoading)?.startAnimation(
                            AlphaAnimation(0.0f, 1.0f).apply {
                                duration = 800
                                startOffset = 200
                                repeatMode = Animation.REVERSE
                                repeatCount = Animation.INFINITE
                            })
                }
            }
        }
        return dialog!!
    }

    fun dismissProgress() {
        dialog?.dismiss()
        dialog = null
    }
}
fun View.applySystemWindowInsets() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        WindowInsetsCompat.CONSUMED
    }
}