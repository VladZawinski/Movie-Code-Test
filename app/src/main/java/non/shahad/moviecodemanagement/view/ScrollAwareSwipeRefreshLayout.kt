package non.shahad.moviecodemanagement.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.math.abs

/**
 * @author Shahad Zawinski
 * SwipeRefreshLayout has issue with horizontal Recycler View.
 */
class ScrollAwareSwipeRefreshLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SwipeRefreshLayout(context,attrs){

    private var touchSlope = 0
    private var previousX = 0f

    init {
        touchSlope = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN -> {
                previousX = MotionEvent.obtain(ev).x
            }

            MotionEvent.ACTION_MOVE -> {
                val eventX = ev.x
                val xDiff = abs(eventX - previousX)

                if (xDiff > touchSlope){
                    return false
                }
            }
        }

        return super.onInterceptTouchEvent(ev)
    }

}