package poco.cn.jetpackdemo.samplePaging.basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_basic_usage.*
import poco.cn.jetpackdemo.R
import poco.cn.jetpackdemo.samplePaging.viewmodel.CommonViewModel

class BasicUsageActivity : AppCompatActivity() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CommonViewModel(application) as T
            }
        }).get(CommonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_usage)

        val adapter = BasicSudentAdapter()
        recyclerView.adapter = adapter

        viewModel.getRefreshLiveData().observe(this, Observer { adapter.submitList(it) })
    }

}
