package poco.cn.jetpackdemo.samplePaging.basic

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import poco.cn.jetpackdemo.samplePaging.db.Student
import poco.cn.jetpackdemo.samplePaging.viewholder.StudentViewHolder

/**
 * Created by lgd on 2020/1/2.
 */
class BasicSudentAdapter : PagedListAdapter<Student, StudentViewHolder>(diffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(parent)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindTo(getItem(position)?.name)
    }

    companion object {

        private val diffCallBack = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }

        }
    }
}