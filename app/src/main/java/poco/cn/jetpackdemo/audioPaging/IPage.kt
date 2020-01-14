package poco.cn.jetpackdemo.audioPaging

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * Created by lgd on 2020/1/8.
 */
class IPage(context: Context, attrs: AttributeSet?, defStyleAttr: Int
        ) : FrameLayout(context, attrs, defStyleAttr), LifecycleOwner
{
    private val mLifecycleRegistry = LifecycleRegistry(this)

    init {
        mLifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }
    //


    public fun initData()
    {
        //init
        mLifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    public fun onPause()
    {
        mLifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }


    public fun onResum()
    {
        mLifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    public fun onClose()
    {
        mLifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        //onclose
    }



}