package poco.cn.jetpackdemo

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import kotlinx.android.synthetic.main.activity_main.*
import poco.cn.jetpackdemo.audioPaging.AudioPagingActivity
import poco.cn.jetpackdemo.samplePaging.basic.BasicUsageActivity
import poco.cn.jetpackdemo.samplePaging.proxy.HeaderProxyActivity
import poco.cn.jetpackdemo.singletest.lifecycle.LifecycleActivity
import poco.cn.jetpackdemo.singletest.liveData.LiveDataActivity

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

        mBtnLifecycle.setOnClickListener {
            startActivity(Intent(this, LifecycleActivity::class.java))
        }

        mBtnLifeData.setOnClickListener {
            startActivity(Intent(this, LiveDataActivity::class.java))
        }

        mBtnAudio.setOnClickListener {

            val permission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            val p = Manifest.permission.WRITE_EXTERNAL_STORAGE
            if(PermissionChecker.checkSelfPermission(this,p) == PermissionChecker.PERMISSION_GRANTED) {
                startActivity(Intent(this, AudioPagingActivity::class.java))
            }else{
                ActivityCompat.requestPermissions(this,permission,1)
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            if(grantResults[0] == PermissionChecker.PERMISSION_GRANTED){
                startActivity(Intent(this, AudioPagingActivity::class.java))
            }
        }
    }
}
