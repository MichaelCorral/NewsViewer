package com.michaelcorral.newsviewer.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

val EditText.value
    get() = text.toString()

fun EditText.onAfterTextChanged(callBack: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(text: Editable?) {
            callBack(text.toString())
        }

        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            // To be implemented
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            // To be implemented
        }
    })
}

fun EditText.onBeforeTextChanged(callBack: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(text: Editable?) {
            // To be implemented
        }

        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            callBack(text.toString())
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            // To be implemented
        }
    })
}

fun EditText.onTextChanged(callBack: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(text: Editable?) {
            // To be implemented
        }

        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            // To be implemented
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            callBack(text.toString())
        }
    })
}