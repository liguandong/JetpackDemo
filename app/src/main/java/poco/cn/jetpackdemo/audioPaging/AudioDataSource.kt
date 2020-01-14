package poco.cn.jetpackdemo.audioPaging

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import androidx.paging.ItemKeyedDataSource
import java.util.*

/**
 * Created by lgd on 2020/1/2.
 *
 */
@Deprecated("未完成")
abstract class AudioDataSource(val context: Context, val key: String) : ItemKeyedDataSource<Int, AudioInfo>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<AudioInfo>) {
//        positionKey.clear()

        if (params.requestedLoadSize == 0) {
            callback.onResult(emptyList(), 0, 0)
            return
        }
        var startPosition = 0
        if (params.requestedInitialKey != null) {
            startPosition = params.requestedInitialKey!!
        }
        val audioInfos = loadRange(startPosition, params.requestedLoadSize)
        callback.onResult(audioInfos,startPosition,audioInfos.size)
//        val firstLoadPosition = computeInitialLoadPosition(startPosition, params.requestedLoadSize, totalCount)
//        val firstLoadSize = computeInitialLoadSize(firstLoadPosition, totalCount, params.requestedLoadSize)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<AudioInfo>) {
        val startPosition = params.key + 1

        val list = loadRange(startPosition, params.requestedLoadSize)
        callback.onResult(list)

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<AudioInfo>) {

    }

    override fun getKey(item: AudioInfo): Int {
        return item.index
    }

    private fun loadRange(startPosition: Int, requestedLoadSize: Int): List<AudioInfo> {
        val selectionAndArgs = getSelection(null)
        val mediaList = ArrayList<AudioInfo>()
        while(mediaList.size < requestedLoadSize){
//            var cursor: Cursor? = null
            var cursor: Cursor? = null
            var tempList : List<AudioInfo>
            try {
                cursor = context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selectionAndArgs[0]?.get(0),
                        selectionAndArgs[1], getOrderByString(startPosition, requestedLoadSize))!!
                tempList = convertRows(cursor,startPosition)

            } catch (e: Exception) {
                e.printStackTrace()
                break
            }finally {
                cursor?.close()
            }
            mediaList.addAll(tempList)
            if(tempList.isEmpty()){
                break
            }
        }
//        var cursor: Cursor? = null
//        try {
////            cursor = context.getContentResolver().query(QUERY_URI, PROJECTION, selectionAndArgs[0][0],
////                    selectionAndArgs[1], getOrderByString(realStartPosition, loadCount))
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

        return mediaList
    }

//    protected override fun convertRows(cursor: Cursor): List<Student> {
//        val _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id")
//        val _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(cursor, "_name")
//        val _res = ArrayList<Student>(cursor.count)
//        while (cursor.moveToNext()) {
//            val _item: Student
//            val _tmpId: Int
//            _tmpId = cursor.getInt(_cursorIndexOfId)
//            val _tmpName: String
//            _tmpName = cursor.getString(_cursorIndexOfName)
//            _item = Student(_tmpId, _tmpName)
//            _res.add(_item)
//        }
//        return _res
//    }
//};

    private val ORDER_BY = MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
    fun getOrderByString(offset: Int, limit: Int): String {
        return if (limit == -1) {
            ORDER_BY
        } else "$ORDER_BY limit $limit offset $offset"

    }



    fun getSelection(key: String?): Array<Array<String>?> {
        var selection: String? = null
        val selectionArgs: Array<String>? = null
        if (key != null) {
            selection = MediaStore.Audio.Media.TITLE + " " + "like  '%" + key + "%'"
        }
        val out = arrayOfNulls<Array<String>>(2)
        out[0] = selection?.let { arrayOf<String>(it) }
        return out
    }

    internal val projection = arrayOf(MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.SIZE, MediaStore.Audio.Media.DATE_ADDED, MediaStore.Audio.Media.IS_MUSIC)


    private val PAGE_SIZE = 20
    private val PREFETCH_DISTANCE = 40

    internal abstract fun convertRows(cursor: Cursor, offset: Int): List<AudioInfo>
}