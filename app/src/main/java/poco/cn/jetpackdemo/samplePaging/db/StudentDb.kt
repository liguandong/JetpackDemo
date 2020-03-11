package poco.cn.jetpackdemo.samplePaging.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Created by lgd on 2019/12/27.
 * 注意：如果您的应用在单个进程中运行，则在实例化 AppDatabase 对象时应遵循单例设计模式。
 * 每个 RoomDatabase 实例的成本相当高，而您几乎不需要在单个进程中访问多个实例。
 *
 *
 * 如果您的应用在多个进程中运行，请在数据库构建器调用中包含 enableMultiInstanceInvalidation()。
 * 这样，如果您在每个进程中都有一个 AppDatabase 实例，就可以在一个进程中使共享数据库文件失效，
 * 并且这种失效会自动传播到其他进程中的 AppDatabase 实例。
 */
//@Database(entities = arrayOf(Student::class),version =  1)
@Database(entities = [Student::class], version = 1 ,exportSchema = false)
abstract class StudentDb : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {

        private var instance: StudentDb? = null

        @Synchronized
        fun get(context : Context): StudentDb{
            if(instance == null){
                instance = Room.databaseBuilder(context,StudentDb::class.java,"StudentDatabase")
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                ioThread {
                                    get(context).studentDao().insert(
                                            CHEESE_DATA.map { Student(id =  0,name = it) }
                                    )
                                }
                            }

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                            }

                            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                                super.onDestructiveMigration(db)
                            }
                        }).build()
            }
            return instance!!
        }
    }
}
private val CHEESE_DATA = arrayListOf(
        "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
        "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag",
        "Airedale", "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert",  // 15
        "American Cheese", "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro",
        "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String",
        "Aromes au Gene de Marc", "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", // 30
        "Avaxtskyr", "Baby Swiss", "Babybel", "Baguette Laonnaise", "Bakers",
        "Baladi", "Balaton", "Bandal", "Banon", "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
        "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
        "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
        "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
        "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
        "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)"
)


//@Database(entities = [Student::class], version = 1)
//abstract class StudentDb private constructor() : RoomDatabase() {
//
//    abstract fun studentDao(): StudentDao
//
//    private object Companion {
//        @Synchronized
//        operator fun get(context: Context): StudentDb {
//            if (StudentDb.instance == null) {
//                StudentDb.instance = Room.databaseBuilder(context.applicationContext,
//                        StudentDb::class.java, "StudentDatabase")
//                        .addCallback(object : RoomDatabase.Callback() {
//                            override fun onCreate(db: SupportSQLiteDatabase) {
//                                //                                super.onCreate(db);
//                                Thread(Runnable {
//                                    val strings = arrayOfNulls<Student>(CHEESE_DATA.size)
//                                    for (s in CHEESE_DATA) {
//                                        val student = Student()
//                                        student.name = s
//                                    }
//                                    get(context).studentDao().insetAll(
//                                            *strings)
//                                })
//                            }
//                        })
//                        .build()
//            }
//            return StudentDb.instance
//        }
//    }
//
//    companion object {
//
//        private var instance: StudentDb? = null
//
//        operator fun get(context: Context): StudentDb {
//            return Companion[context]
//        }
//
//
//        private val CHEESE_DATA = Arrays.asList(
//                "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
//                "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag",
//                "Airedale", "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", // 15
//                "American Cheese", "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro",
//                "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String",
//                "Aromes au Gene de Marc", "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", // 30
//                "Avaxtskyr", "Baby Swiss", "Babybel", "Baguette Laonnaise", "Bakers",
//                "Baladi", "Balaton", "Bandal", "Banon", "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
//                "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
//                "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
//                "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
//                "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
//                "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)"
//        ) as ArrayList<String>
//    }
//}
