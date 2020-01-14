package poco.cn.jetpackdemo.audioPaging.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import poco.cn.jetpackdemo.audioPaging.AudioInfo
import poco.cn.jetpackdemo.audioPaging.AudioPositionalDataSourceFactory

/**
 * Created by lgd on 2019/12/27.
 *
 * 架构组件为界面控制器提供了 ViewModel 辅助程序类，该类负责为界面准备数据。 在配置更改期间会自动保留 ViewModel 对象，以便它们存储的数据立即可供下一个 Activity 或 Fragment 实例使用
 *
 * 使用 ViewModel 可将界面控制器与数据加载操作分离
 *
 * ViewModel 的用途是封装界面控制器的数据，以使数据在配置更改后仍然存在
 *
 * https://developer.android.google.cn/topic/libraries/architecture/viewmodel#java
 */
class CommonViewModel(application: Application) : AndroidViewModel(application) {
    val factory = AudioPositionalDataSourceFactory(application)
    fun getRefreshLiveData(): LiveData<PagedList<AudioInfo>> =
            LivePagedListBuilder(factory, PagedList.Config.Builder()
                    .setPageSize(PAGE_SIZE).setEnablePlaceholders(ENABLE_PLACEHOLDERS)
                    .setInitialLoadSizeHint(PAGE_SIZE).build()).build()


    companion object {

        public const val PAGE_SIZE = 15

        public const val ENABLE_PLACEHOLDERS = false
    }
}
//class CommViewModel(application: Application) : AndroidViewModel(application) {
//    private val studentDb: StudentDb
//
//    internal val rereshLiveData: LiveData<PagedList<Student>>
//        get() {
//            val config = PagedList.Config.Builder().setPageSize(15)
//                    .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
//                    .setInitialLoadSizeHint(PAGE_SIZE)
//                    .build()
//            return LivePagedListBuilder(studentDb.studentDao().allStudent, config).build()
//        }
//
//    init {
//        studentDb = StudentDb.get(application)
//
//    }
//
//    companion object {
//
//        val PAGE_SIZE = 15
//        val ENABLE_PLACEHOLDERS = false
//    }
//}
