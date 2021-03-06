package com.dreamsunited.falconbrick.utils

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dreamsunited.falconbrick.utils.helper.OnKeyEnter
import com.dreamsunited.falconbrick.utils.helper.TabClick
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

@BindingAdapter("tabItems")
fun setTabItems(view: TabLayout, list: List<String>?) {
    view.removeAllTabs()
    list?.forEach {
        view.addTab(view.newTab().setText(it))
    }
}

@BindingAdapter("onSelectTab")
fun setOnSelectTab(view: TabLayout, tabClick: TabClick) {
    view.addOnTabSelectedListener(object : OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            tabClick.onTabClick(tab.position)
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {}
        override fun onTabReselected(tab: TabLayout.Tab) {}
    })
}

@BindingAdapter("onEditTextDone")
fun setOnEditTextDone(view: EditText, listener: OnKeyEnter) {
    view.setOnKeyListener { _, keyCode, event ->
        if (event != null && event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
            listener.onBackPressed()
        }
        false
    }
    view.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            listener.onDone()
        }
        false
    }
}

@BindingAdapter("fullText", "subText", "subColor", requireAll = false)
fun showDifferentColorText(
    textView: TextView,
    fullText: String?,
    subText: String?,
    subColor: Int
) {
    if (fullText != null && subText != null) {
        val spannableString = SpannableStringBuilder(fullText)
        val startIndex = fullText.indexOf(subText)
        val subTextLength = subText.length
        if (startIndex >= -1 && subColor != 0) {
            spannableString.setSpan(
                ForegroundColorSpan(subColor),
                startIndex,
                startIndex + subTextLength,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    } else {
        fullText?.let {
            textView.text = it
        }
    }
}

@BindingAdapter("adapter")
fun <T : RecyclerView.ViewHolder> setAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<T>
) {
    recyclerView.adapter = adapter
}
