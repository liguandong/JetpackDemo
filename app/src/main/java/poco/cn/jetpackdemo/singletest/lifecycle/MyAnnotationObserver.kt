package poco.cn.jetpackdemo.singletest.lifecycle

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by lgd on 2020/1/9.
 *
 * java 8 优先使用DefaultLifecycleObserver
 *
 */
class MyAnnotationObserver(private val context: Context, private val lifecycle: Lifecycle) : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()
    {
        Log.d(LifecycleActivity.TAG,"AnnotationObserver create")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()
    {
        if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            Log.d(LifecycleActivity.TAG,"AnnotationObserver start")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()
    {
        Log.d(LifecycleActivity.TAG,"AnnotationObserver onStop")
    }

}