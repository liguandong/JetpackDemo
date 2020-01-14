package poco.cn.jetpackdemo.audioPaging

import androidx.paging.DataSource


/**
 * Created by lgd on 2020/1/2.
 *
 *  //参考room的 StudentDao_Impl getAllStudent;
 */
@Deprecated("未完成")
class AudioDataSourceFactory : DataSource.Factory<Int,AudioInfo>(){
    override fun create(): DataSource<Int, AudioInfo> {

    }
}