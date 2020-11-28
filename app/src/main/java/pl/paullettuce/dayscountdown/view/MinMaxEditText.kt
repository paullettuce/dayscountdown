package pl.paullettuce.dayscountdown.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import pl.paullettuce.dayscountdown.commons.logd

class MinMaxEditText : androidx.appcompat.widget.AppCompatEditText {
    interface OnChange {
        fun onValueChange(number: Int)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val minMaxCappingTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            logd("aftertextchanged: $s")
            try {
                val number = s.toString().toInt()
                when {
                    number > max -> replaceAll(s, max)
                    number < min -> replaceAll(s, min)
                    else -> notifyIfChanged(number)
                }
            } catch (e: NumberFormatException) {
                replaceAll(s, min)
            }
        }

        private fun replaceAll(s: Editable?, replacement: Int) {
            logd("replaceAll: $s -> $replacement")
            val endIndex = s?.length ?: 0
            s?.replace(0, endIndex, replacement.toString())
            notifyIfChanged(replacement)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private var oldValue: Int = 0

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
    var onChangeCallback: OnChange? = null

    fun setValueRange(min: Int, max: Int) {
        this.min = min
        this.max = max

        removeTextChangedListener(minMaxCappingTextWatcher)
        addTextChangedListener(minMaxCappingTextWatcher)
    }

    fun setValue(number: Int) {
        setText(number.toString())
    }

    @Throws(NumberFormatException::class)
    fun getValue(): Int = parseTextField()

    private fun capValueIfLowerThanMin() {
        tryParsingTextField { number ->
            if (number < min) {
                setNumber(min)
            }
        }
    }

    private fun capValueIfHigherThanMax() {
        tryParsingTextField { number ->
            if (number > max) {
                setNumber(max)
            }
        }
    }

    private fun tryParsingTextField(action: (number: Int) -> Unit) {
        try {
            val currentNumber = parseTextField()
            action(currentNumber)
        } catch (e: NumberFormatException) {
            insertMinIfEmpty()
        }
    }

    private fun insertMinIfEmpty() {
        if (text.isNullOrEmpty())
            setNumber(min)
    }

    private fun setNumber(number: Int) {
        setText(number.toString())
        notifyIfChanged(number)
    }

    private fun notifyIfChanged(newValue: Int) {
        logd("NotifyIfChanged: oldValue=$oldValue, newValue=$newValue")
        if (newValue != oldValue) {
            oldValue = newValue
            onChangeCallback?.onValueChange(newValue)
        }
    }

    @Throws(NumberFormatException::class)
    private fun parseTextField() = text.toString().toInt()

}