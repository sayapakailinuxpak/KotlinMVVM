package com.eldisproject.kotlinmvvm.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eldisproject.kotlinmvvm.R
import com.eldisproject.kotlinmvvm.data.model.User
import kotlinx.android.synthetic.main.list_item.view.*

class MainRecyclerViewAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textview_name.text = user.name
            itemView.textview_email.text = user.email
            Glide.with(itemView.imageview_user.context)
                .load(user.avatar)
                .circleCrop()
                .into(itemView.imageview_user)
        }
    }

    fun addData(list: List<User>) {
        users.addAll(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainRecyclerViewAdapter.DataViewHolder {
        //Java Style
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//        return DataViewHolder(view)

        //Kotlin Style
        return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainRecyclerViewAdapter.DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
}