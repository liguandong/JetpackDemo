package poco.cn.jetpackdemo.audioPaging.proxy

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import poco.cn.jetpackdemo.audioPaging.AudioInfo
import poco.cn.jetpackdemo.samplePaging.viewholder.FooterViewHolder
import poco.cn.jetpackdemo.samplePaging.viewholder.HeaderViewHolder
import poco.cn.jetpackdemo.samplePaging.viewholder.StudentViewHolder

/**
 * Created by lgd on 2020/1/2.
 */
class HeaderProxyAdapter : PagedListAdapter<AudioInfo, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_HEADER -> HeaderViewHolder(parent)
            ITEM_TYPE_FOOTER -> FooterViewHolder(parent)
            else -> StudentViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HeaderViewHolder -> holder.bindsHeader()
            is FooterViewHolder -> holder.bindsFooter()
            is StudentViewHolder -> holder.bindTo(getStudentItem(position)?.title)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_TYPE_HEADER
            itemCount - 1 -> ITEM_TYPE_FOOTER
            else -> super.getItemViewType(position)
        }
    }


    private fun getStudentItem(position: Int): AudioInfo? {
        return getItem(position - 1)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 2
    }



    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer, 1))
    }


    companion object{
        private val diffCallback = object : DiffUtil.ItemCallback<AudioInfo>(){
            override fun areItemsTheSame(oldItem: AudioInfo, newItem: AudioInfo): Boolean {
                return newItem.id == oldItem.id
            }


            override fun areContentsTheSame(oldItem: AudioInfo, newItem: AudioInfo): Boolean {
                return oldItem == newItem
            }
        }
        private const val ITEM_TYPE_HEADER = 99
        private const val ITEM_TYPE_FOOTER = 100
    }
}