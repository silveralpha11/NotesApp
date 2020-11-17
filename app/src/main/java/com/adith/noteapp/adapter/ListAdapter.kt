package com.adith.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adith.noteapp.databinding.RowLayoutBinding
import com.adith.noteapp.model.TodoData

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    var dataList = emptyList<TodoData>()

    class MyViewHolder(private val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(todoData: TodoData){
            binding.todoData = todoData
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder.from(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(todoData: List<TodoData>){
        val toDoDiffUtil = ToDoDiffUtil(dataList, todoData)
        val toDoDiffUtilResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = todoData
        toDoDiffUtilResult.dispatchUpdatesTo(this)
    }
}