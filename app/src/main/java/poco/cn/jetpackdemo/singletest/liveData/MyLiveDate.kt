package poco.cn.jetpackdemo.singletest.liveData

import androidx.lifecycle.LiveData

/**
 * Created by lgd on 2020/1/9.
 */
class MyLiveDate : LiveData<String>()
{
//    private val stockManager = StockManager(symbol)
    //
    override fun setValue(value: String?) {
        super.setValue(value)
    }

    /**
     * 当 LiveData 对象具有活跃观察者时，会调用 onActive() 方法。这意味着，您需要从此方法开始观察股价更新。
     */
    override fun onActive() {
        super.onActive()
        // Start listening
    }

    /**
     * 当 LiveData 对象没有任何活跃观察者时，会调用 onInactive() 方法。由于没有观察者在监听，因此没有理由与 StockManager 服务保持连接。
     */
    override fun onInactive() {
        super.onInactive()
        // Stop listening
    }
}