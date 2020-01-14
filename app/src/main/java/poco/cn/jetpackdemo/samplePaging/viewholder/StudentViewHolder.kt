package poco.cn.jetpackdemo.samplePaging.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import poco.cn.jetpackdemo.R
import poco.cn.jetpackdemo.samplePaging.db.Student

class StudentViewHolder(parent: ViewGroup) : androidx.recyclerview.widget.RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    private var student: Student? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(name :String?) {
//        this.student = student
//        nameView.text = student?.name
        nameView.text = name
    }
}