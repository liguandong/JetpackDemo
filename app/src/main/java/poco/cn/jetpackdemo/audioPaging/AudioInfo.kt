package poco.cn.jetpackdemo.audioPaging

/**
 * Created by lgd on 2018/11/26.
 */
class AudioInfo(val index: Int) {
    var id: Long = 0 // id
    var title: String? = null // 标题
    var displayName: String? = null // 名称
    var path: String? = null // 路径
    var albumId: Long = 0 // 专辑id
    var album: String? = null // 专辑
    var coverPath: String? = null // 封面图
    var artist: String? = null // 艺术家
    var duration: Long = 0 // 时长
    var size: Long = 0 // 大小
    var addTime: Long = 0 // 添加时间

    private var hashCode: Int = 0



    override fun equals(obj: Any?): Boolean {
        if (obj === this) {
            return true
        }
        if (obj !is AudioInfo) {
            return false
        }

        val media = obj as AudioInfo?
        return id == media!!.id && path == media.path
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (path?.hashCode() ?: 0)
        result = 31 * result + (artist?.hashCode() ?: 0)
        result = 31 * result + hashCode
        return result
    }


//    override fun hashCode(): Int {
//        if (hashCode == 0) {
//            hashCode = 17
//            hashCode = hashCode * 31 + (id xor id.ushr(32)).toInt()
//            hashCode = hashCode * 31 + path!!.hashCode()
//        }
//        return hashCode
//    }
}
