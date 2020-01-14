package poco.cn.jetpackdemo.singletest.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import poco.cn.jetpackdemo.R


class LifecycleActivity : AppCompatActivity() {

    companion object{
        val TAG = "LifecycleActivity"
    }
    private lateinit var annotationObserver: MyAnnotationObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)


        annotationObserver = MyAnnotationObserver(this,lifecycle)

        lifecycle.addObserver(MyDefaultLifecycleObserver())
        lifecycle.addObserver(MyAnnotationObserver(this,lifecycle))

    }
}
