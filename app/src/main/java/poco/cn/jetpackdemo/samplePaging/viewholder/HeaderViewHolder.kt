package poco.cn.jetpackdemo.samplePaging.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import poco.cn.jetpackdemo.R

class HeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)) {

    fun bindsHeader() {
        // empty implementation
    }
}