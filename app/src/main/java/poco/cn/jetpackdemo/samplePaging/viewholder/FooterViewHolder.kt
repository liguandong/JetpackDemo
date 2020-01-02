package poco.cn.jetpackdemo.samplePaging.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import poco.cn.jetpackdemo.R

/**
 * Created by lgd on 2020/1/2.
 */
class FooterViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
) {
    fun bindsFooter() {
        // empty implementation
    }
}