package poco.cn.jetpackdemo.singletest.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * Created by lgd on 2020/1/9.
 */
class MyDefaultLifecycleObserver : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.d(LifecycleActivity.TAG,"DefaultLifecycleObserver onCreate")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        if(owner.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)){
            Log.d(LifecycleActivity.TAG,"DefaultLifecycleObserver onStart")
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d(LifecycleActivity.TAG,"DefaultLifecycleObserver onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }
}