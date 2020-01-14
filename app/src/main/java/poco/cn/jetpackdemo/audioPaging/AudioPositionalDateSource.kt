package poco.cn.jetpackdemo.audioPaging

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import androidx.paging.PositionalDataSource


/**
 * Created by lgd on 2020/1/7.
 */
abstract class AudioPositionalDateSource(val context: Context, val key: String?) : PositionalDataSource<AudioInfo>() {
    private val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.IS_MUSIC
    )

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<AudioInfo>) {
        callback.onResult(loadRang(params.startPosition, params.loadSize))
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<AudioInfo>) {
        var list: List<AudioInfo> = emptyList()
        val totalCount = countItems()
        var firstLoadPosition = 0
        if(totalCount != 0){
           firstLoadPosition = computeInitialLoadPosition(params,totalCount)
            val firstLoadSize = computeInitialLoadSize(params, firstLoadPosition, totalCount)
            list = loadRang(firstLoadPosition,firstLoadSize)
        }
        callback.onResult(list,firstLoadPosition,totalCount)
    }

    private val ORDER_BY = MediaStore.Files.FileColumns.DATE_ADDED + " DESC"

    private fun getOrderByString(offset:Int, limit:Int): String {
        return if(limit == -1){
            ORDER_BY
        }else{
//            return ORDER_BY + " limit " + limit + " offset " + offset
            "$ORDER_BY limit $limit offset $offset"
        }
    }

    private fun countItems(): Int {
        var cursor : Cursor = if(key == null || key == ""){
            context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    projection,null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER)!!
        }else{
            val selection = MediaStore.Audio.Media.TITLE + " " + "like  '%" + "?" + "%'"
            val selectionArgs = arrayOf(key)
            context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    projection,selection,selectionArgs,MediaStore.Audio.Media.DEFAULT_SORT_ORDER)!!
        }
        return try {
            if (cursor.moveToFirst()) {
                cursor.getInt(0)
            } else 0
        } finally {
            cursor.close()
        }
        return 0
    }


    protected abstract fun convertRows(cursor: Cursor): List<AudioInfo>

    public fun loadRang(startPosition: Int, loadCount: Int): List<AudioInfo> {
        var cursor : Cursor = if(key == null || key == ""){
            context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    projection,null,null,getOrderByString(startPosition, loadCount))!!
        }else{
            val selection = MediaStore.Audio.Media.TITLE + " " + "like  '%" + "?" + "%'"
            val selectionArgs = arrayOf(key)
            context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    projection,selection,selectionArgs,getOrderByString(startPosition, loadCount))!!
        }
        try {
            return convertRows(cursor)
        } finally {
            cursor.close()
        }
        return emptyList()
    }
}