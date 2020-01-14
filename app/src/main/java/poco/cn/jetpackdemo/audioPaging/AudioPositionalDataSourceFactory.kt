package poco.cn.jetpackdemo.audioPaging

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import androidx.paging.DataSource
import java.util.*


/**
 * Created by lgd on 2020/1/2.
 *
 *  //参考room的 StudentDao_Impl getAllStudent;
 */
public class AudioPositionalDataSourceFactory(val context: Context) : DataSource.Factory<Int, AudioInfo>() {
    override fun create(): DataSource<Int, AudioInfo> {
        return object : AudioPositionalDateSource(context = context, key = null) {
            override fun convertRows(cursor: Cursor): List<AudioInfo> {
                val medias: MutableList<AudioInfo> = ArrayList()
                while (cursor.moveToNext()) {
                    // 是否为音乐，魅族手机上始终为0
                    val path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                    val audioInfo = AudioInfo(0)
                    audioInfo.id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    audioInfo.title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    audioInfo.displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                    audioInfo.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
//                    medias.
//                    val medias: MutableList<AudioInfo> = ArrayList()
                    medias.add(audioInfo)
                }
                return medias
            }
        }
    }
}