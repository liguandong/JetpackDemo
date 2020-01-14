package poco.cn.jetpackdemo.singletest.liveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by lgd on 2020/1/9.
 *
 *
 */
class NameViewModel : ViewModel() {
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}