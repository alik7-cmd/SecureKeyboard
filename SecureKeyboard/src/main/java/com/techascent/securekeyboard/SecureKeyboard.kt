package com.techascent.securekeyboard

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.RelativeSizeSpan
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import java.util.ArrayList

class SecureKeyboard : LinearLayout, View.OnClickListener{

    var listeners: MutableList<OnInsertListener> = ArrayList()
    private var num0: MaterialButton? = null
    private var num1: MaterialButton? = null
    private var num2: MaterialButton? = null
    private var num3: MaterialButton? = null
    private var num4: MaterialButton? = null
    private var num5: MaterialButton? = null
    private var num6: MaterialButton? = null
    private var num7: MaterialButton? = null
    private var num8: MaterialButton? = null
    private var num9: MaterialButton? = null
    private var numDot: MaterialButton? = null
    private var numDelete: ImageButton? = null
    private var showAlphabet = false

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val root = LinearLayout.inflate(context, R.layout.pin_pad, this)
        val a = context.obtainStyledAttributes(attrs, R.styleable.BRKeyboard)
        val N = a.indexCount
        for (i in 0 until N) {
            val attr = a.getIndex(i)
            if (attr == R.styleable.BRKeyboard_showAlphabet) {
                showAlphabet = a.getBoolean(attr, false)
            }
        }
        a.recycle()
        setWillNotDraw(false)
        num0 = root.findViewById<View>(R.id.num0) as MaterialButton
        num1 = root.findViewById<View>(R.id.num1) as MaterialButton
        num2 = root.findViewById<View>(R.id.num2) as MaterialButton
        num3 = root.findViewById<View>(R.id.num3) as MaterialButton
        num4 = root.findViewById<View>(R.id.num4) as MaterialButton
        num5 = root.findViewById<View>(R.id.num5) as MaterialButton
        num6 = root.findViewById<View>(R.id.num6) as MaterialButton
        num7 = root.findViewById<View>(R.id.num7) as MaterialButton
        num8 = root.findViewById<View>(R.id.num8) as MaterialButton
        num9 = root.findViewById<View>(R.id.num9) as MaterialButton
        numDot = root.findViewById<View>(R.id.numDot) as MaterialButton
        numDelete = root.findViewById<View>(R.id.numDelete) as ImageButton
        num0!!.setOnClickListener(this)
        num1!!.setOnClickListener(this)
        num2!!.setOnClickListener(this)
        num3!!.setOnClickListener(this)
        num4!!.setOnClickListener(this)
        num5!!.setOnClickListener(this)
        num6!!.setOnClickListener(this)
        num7!!.setOnClickListener(this)
        num8!!.setOnClickListener(this)
        num9!!.setOnClickListener(this)
        numDot!!.setOnClickListener(this)
        numDelete!!.setOnClickListener(this)
        num0!!.text = getText(0)
        num1!!.text = getText(1)
        num2!!.text = getText(2)
        num3!!.text = getText(3)
        num4!!.text = getText(4)
        num5!!.text = getText(5)
        num6!!.text = getText(6)
        num7!!.text = getText(7)
        num8!!.text = getText(8)
        num9!!.text = getText(9)
        if (showAlphabet) {
            val dp8 = getPixelsFromDps(context, 8)
            num0!!.setPadding(0, 0, 0, dp8)
            num1!!.setPadding(0, 0, 0, dp8)
            num2!!.setPadding(0, 0, 0, dp8)
            num3!!.setPadding(0, 0, 0, dp8)
            num4!!.setPadding(0, 0, 0, dp8)
            num5!!.setPadding(0, 0, 0, dp8)
            num6!!.setPadding(0, 0, 0, dp8)
            num7!!.setPadding(0, 0, 0, dp8)
            num8!!.setPadding(0, 0, 0, dp8)
            num9!!.setPadding(0, 0, 0, dp8)
        }
        invalidate()
    }

    fun getPixelsFromDps(context: Context, dps: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dps * scale + 0.5f).toInt()
    }

    private fun getText(index: Int): CharSequence {
        val span1 = SpannableString(index.toString())
        return if (showAlphabet) {
            val span2: SpannableString
            span2 = when (index) {
                2 -> SpannableString("ABC")
                3 -> SpannableString("DEF")
                4 -> SpannableString("GHI")
                5 -> SpannableString("JKL")
                6 -> SpannableString("MNO")
                7 -> SpannableString("PQRS")
                8 -> SpannableString("TUV")
                9 -> SpannableString("WXYZ")
                else -> SpannableString(" ")
            }
            span1.setSpan(RelativeSizeSpan(1f), 0, 1, 0)
            span2.setSpan(RelativeSizeSpan(0.35f), 0, span2.length, 0)
            TextUtils.concat(span1, "\n", span2)
        } else {
            span1
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        invalidate()
    }

    public override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    fun addOnInsertListener(listener: OnInsertListener) {
        listeners.add(listener)
    }

    override fun onClick(v: View) {
        for (listener in listeners) {
            listener.onClick(if (v is ImageButton) "" else (v as Button).text.toString())
        }
    }

    interface OnInsertListener {
        fun onClick(key: String?)
    }

    fun setBRKeyboardColor(color: Int) {
        setBackgroundColor(ContextCompat.getColor(context, color))
    }

    fun setBRButtonTextColor(color: Int) {
        num0!!.setTextColor(ContextCompat.getColor(context, color))
        num1!!.setTextColor(ContextCompat.getColor(context, color))
        num2!!.setTextColor(ContextCompat.getColor(context, color))
        num3!!.setTextColor(ContextCompat.getColor(context, color))
        num4!!.setTextColor(ContextCompat.getColor(context, color))
        num5!!.setTextColor(ContextCompat.getColor(context, color))
        num6!!.setTextColor(ContextCompat.getColor(context, color))
        num7!!.setTextColor(ContextCompat.getColor(context, color))
        num8!!.setTextColor(ContextCompat.getColor(context, color))
        num9!!.setTextColor(ContextCompat.getColor(context, color))
        numDot!!.setTextColor(ContextCompat.getColor(context, color))
        //        numDelete.setColorFilter(getContext().getColor(color));
        invalidate()
    }

    fun setBRButtonBackgroundResId(resId: Int) {
        num0!!.setBackgroundResource(resId)
        num1!!.setBackgroundResource(resId)
        num2!!.setBackgroundResource(resId)
        num3!!.setBackgroundResource(resId)
        num4!!.setBackgroundResource(resId)
        num5!!.setBackgroundResource(resId)
        num6!!.setBackgroundResource(resId)
        num7!!.setBackgroundResource(resId)
        num8!!.setBackgroundResource(resId)
        num9!!.setBackgroundResource(resId)
        numDot!!.setBackgroundResource(resId)
        numDelete!!.setBackgroundResource(resId)
        invalidate()
    }

    fun setShowDot(b: Boolean) {
        numDot!!.visibility = if (b) LinearLayout.VISIBLE else LinearLayout.GONE
        invalidate()
    }

    fun setBreadground(drawable: Drawable?) {
        background = drawable
        invalidate()
    }

    /**
     * Change the background of a specific button
     *
     * @param index    the index of the button (10 - delete, 11 - dot)
     * @param drawable the drawable to be used
     */
    fun setCustomButtonBackgroundDrawable(index: Int, drawable: Drawable?) {
        when (index) {
            0 -> num0!!.background = drawable
            1 -> num1!!.background = drawable
            2 -> num2!!.background = drawable
            3 -> num3!!.background = drawable
            4 -> num4!!.background = drawable
            5 -> num5!!.background = drawable
            6 -> num6!!.background = drawable
            7 -> num7!!.background = drawable
            8 -> num8!!.background = drawable
            9 -> num9!!.background = drawable
            10 -> numDelete!!.background = drawable
            11 -> numDot!!.background = drawable
        }
    }

    /**
     * Change the background of a specific button
     *
     * @param index the index of the button (10 - delete, 11 - dot)
     * @param color the color to be used
     */
    fun setCustomButtonBackgroundColor(index: Int, color: Int) {
        when (index) {
            0 -> num0!!.setBackgroundColor(color)
            1 -> num1!!.setBackgroundColor(color)
            2 -> num2!!.setBackgroundColor(color)
            3 -> num3!!.setBackgroundColor(color)
            4 -> num4!!.setBackgroundColor(color)
            5 -> num5!!.setBackgroundColor(color)
            6 -> num6!!.setBackgroundColor(color)
            7 -> num7!!.setBackgroundColor(color)
            8 -> num8!!.setBackgroundColor(color)
            9 -> num9!!.setBackgroundColor(color)
            10 -> numDelete!!.setBackgroundColor(color)
            11 -> numDot!!.setBackgroundColor(color)
        }
    }

    fun setDeleteImage(res: Drawable?) {
        numDelete!!.setImageDrawable(res)
    }

    companion object {
        val TAG = SecureKeyboard::class.java.name
    }
}