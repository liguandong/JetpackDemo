package poco.cn.jetpackdemo.singletest.liveData

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_live_data.*
import poco.cn.jetpackdemo.R

class LiveDataActivity : AppCompatActivity() {

    private lateinit var mode : NameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        // Get the ViewModel.
        mode = ViewModelProviders.of(this).get(NameViewModel::class.java)

        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            textView.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mode.currentName.observe(this,nameObserver)

        /**
         * 在传递 nameObserver 参数的情况下调用 observe() 后，系统会立即调用 onChanged()，
         * 从而提供 mCurrentName 中存储的最新值。
         * 如果 LiveData 对象尚未在 mCurrentName 中设置值，则不会调用 onChanged()。
         */


        button.setOnClickListener {
            val name = "name2"
            mode.currentName.value = name

            Thread(Runnable {
                Thread.sleep(1000)
                mode.currentName.postValue("name3_delay1000ms")
            }).start()
            //非主线程用post
//            mode.currentName.postValue(name)
        }
        removeObserver.setOnClickListener{
            mode.currentName.value = "name1"
            mode.currentName.removeObservers(this)
        }

        /**
         * 调用 setValue(T) 导致观察者使用值 John Doe 调用其 onChanged() 方法。
         * 本例中演示的是按下按钮的方法，但也可以出于各种各样的原因调用 setValue() 或 postValue() 来更新 mName，
         * 这些原因包括响应网络请求或数据库加载完成。在所有情况下，调用 setValue() 或 postValue() 都会触发观察者并更新界面。
         */



    }
}
