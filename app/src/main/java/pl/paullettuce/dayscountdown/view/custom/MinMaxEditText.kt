package pl.paullettuce.dayscountdown.view.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.core.widget.addTextChangedListener
import pl.paullettuce.dayscountdown.commons.logd

class MinMaxEditText : androidx.appcompat.widget.AppCompatEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val minMaxCappingTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            try {
                val number = s.toString().toInt()
                if (number > max) replaceAll(s, max)
                else if (number < min) replaceAll(s, min)
            } catch (e: NumberFormatException) {
                replaceAll(s, min)
            }
        }

        private fun replaceAll(s: Editable?, replacement: Int) {
            logd("replaceAll: $s -> $replacement")
            val endIndex = s?.length ?: 0
            s?.replace(0, endIndex, replacement.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    var min: Int = Int.MIN_VALUE
        private set(value) {
            field = value
            capValueIfLowerThanMin()
        }
    var max: Int = Int.MAX_VALUE
        private set(value) {
            field = value
            capValueIfHigherThanMax()
        }

    fun setValueRange(min: Int, max: Int) {
        this.min = min
        this.max = max
        insertMinIfEmpty()

        removeTextChangedListener(minMaxCappingTextWatcher)
        addTextChangedListener(minMaxCappingTextWatcher)
    }

    private fun insertMinIfEmpty() {
        if (text.isNullOrEmpty())
            setNumber(min)
    }

    private fun capValueIfLowerThanMin() {
        tryActionOnCurrentNumber {
            if (it < min) {
                setNumber(min)
            }
        }
    }

    private fun capValueIfHigherThanMax() {
        tryActionOnCurrentNumber {
            if (it > max) {
                setNumber(max)
            }
        }
    }

    private fun tryActionOnCurrentNumber(action: (number: Int) -> Unit) {
        try {
            val currentNumber = parseTextField()
            action(currentNumber)
        } catch (e: NumberFormatException) {
        }
    }

    private fun setNumber(number: Int) {
        setText(number.toString())
    }

    @Throws(NumberFormatException::class)
    private fun parseTextField() = text.toString().toInt()

}