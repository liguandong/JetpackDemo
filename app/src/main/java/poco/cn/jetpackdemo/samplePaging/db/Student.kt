package poco.cn.jetpackdemo.samplePaging.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by lgd on 2019/12/27.
 */
@Entity
data class Student(@PrimaryKey(autoGenerate = true)
                   val id: Int,
                   @ColumnInfo(name = "_name") val name: String) {
//    @PrimaryKey(autoGenerate = true)
//    val id: Int
//    val name: String
//
//    constructor(id: Int, name: String) {
//        this.id = id
//        this.name = name
//    }
}
