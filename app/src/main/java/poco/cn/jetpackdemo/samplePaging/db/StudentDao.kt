package poco.cn.jetpackdemo.samplePaging.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import io.reactivex.Flowable


/**
 * Created by lgd on 2019/12/27.
 *
 * 使用 Room DAO 访问数据
 *
 *  <a href="https://developer.android.google.cn/training/data-storage/room/accessing-data">
 */
@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Student::class)
    fun insert(student: MyStudent)

    @Insert
    fun insert(students: List<Student>)

    /**
     * 类似java  Student...students
     */
    @Insert
    fun insetAll(vararg users: Student)


//    /**
//     * 参数类和实体类 属性名相同的会自动匹配解析
//     */
//    @Update(entity = Student::class)
//    fun update(student: MyStudent)

    @Update
    fun update(student: Student)

    @Update
    fun update(vararg students: Student): Int

    @Delete
    fun delete(student: Student)

    @Delete(entity = Student::class)
    fun delete(student: MyStudent)

    @Delete
    fun delete(vararg student: Student): Int


    @Query("select * from Student")
    fun loadAll(): List<Student>

    /**
     * 含参数
     */
    @Query("select * from Student where _name like :name")
    fun loadSutdent(name: String): List<Student>


//    /**
//     * 返回列的子集
//     */
//    data class NameTuple(@ColumnInfo(name = "_name") val name: String)
//
//    @Query("select _name from Student")
//    fun loadStudent(name: String): List<NameTuple>


    /**
     *可观察查询
     * 应用的界面在数据发生变化时自动更新
     *
     * Room 会根据在查询中访问的表格列表决定是否更新 LiveData 实例。 ????
     */
    @Query("select * from student where _name like :name")
    fun loadStudents(name: String): LiveData<List<Student>>

    /**
     * 进行响应式查询
     */
    @Query("SELECT * from Student where id = :id LIMIT 1")
    fun loadStudentById(id: Int): Flowable<Student>


    /**
     * 多表查询
     */
//    @Dao
//    interface MyDao {
//        @Query(
//                "SELECT * FROM book " +
//                        "INNER JOIN loan ON loan.book_id = book.id " +
//                        "INNER JOIN user ON user.id = loan.user_id " +
//                        "WHERE user.name LIKE :userName"
//        )
//        fun findBooksBorrowedByNameSync(userName: String): List<Book>
//    }

    /**
     * 返回paging的可观察查询数据源
     */
    @Query("select * from student order by _name collate nocase asc")
    fun getAllStudent(): DataSource.Factory<Int, Student>


//    @get:Query("Select * from Student")
//    val all: List<Student>
//
//    /**
//     * 查看文档tip.md  的使用Room Dao
//     */
//    @get:Query("Select * from student order by my_name collate nocase asc")
//    val allStudent: DataSource.Factory<Int, Student>
//
//
//    @Query("Select * from Student where id(:ids)")
//    fun getAllByIds(ids: IntArray): List<Student>
//
//    @Query("Select * from Student where my_name like :first ")
//    fun finyByName(first: String): Student
//
//    @Insert
//    fun insert(student: Student)
//
//    @Insert
//    fun insetAll(vararg students: Student)


}
