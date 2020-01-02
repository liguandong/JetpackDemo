package poco.cn.jetpackdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import poco.cn.jetpackdemo.samplePaging.basic.BasicUsageActivity
import poco.cn.jetpackdemo.samplePaging.proxy.HeaderProxyActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mBtnBasicUsage.setOnClickListener {
            startActivity(Intent(this, BasicUsageActivity::class.java))
        }
        mBtnHeaderMultiType.setOnClickListener {
            startActivity(Intent(this, BasicUsageActivity::class.java))
        }
        mBtnHeaderProxy.setOnClickListener {
            startActivity(Intent(this, HeaderProxyActivity::class.java))
        }

    }
}
