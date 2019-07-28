package com.example.grofersassignment.ui.monument

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.grofersassignment.R
import com.example.grofersassignment.databinding.ItemMonumentBinding
import com.example.grofersassignment.model.MonumentEntity


class MonumentListAdapter: RecyclerView.Adapter<MonumentListAdapter.ViewHolder>() {
    private lateinit var monumentList:List<MonumentEntity>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMonumentBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_monument, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(monumentList[position])
    }

    override fun getItemCount(): Int {
        return if(::monumentList.isInitialized) monumentList.size else 0
    }

    fun updateMonumentList(postList:List<MonumentEntity>){
        this.monumentList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMonumentBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = MonumentViewModel()

        fun bind(monumentEntity: MonumentEntity){
            viewModel.bind(monumentEntity)
            binding.viewModel = viewModel
        }
    }
}