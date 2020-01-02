package poco.cn.jetpackdemo.samplePaging.db

import androidx.room.ColumnInfo

/**
 * Created by lgd on 2019/12/27.
 */
data class MyStudent(@ColumnInfo(name = "_name") val name: String)