package com.example.task.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.RvItemBinding
import com.example.task.model.Repo


class RepoAdapter(var mContext: Context, private val onRemoveListener: OnClickListener) :
    ListAdapter<Repo, RepoAdapter.ViewHolder>(
        DiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val position = holder.adapterPosition
        val file = getItem(position)
        holder.binding.apply {
            tvRepoName.text = file.name
            tvDes.text = file.description
            clRepo.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(file.html_url))
                startActivity(mContext, browserIntent, null)
            }
            btnShare.setOnClickListener {
                onRemoveListener.onClick(file, position)
            }
        }
    }

    inner class ViewHolder(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem.name == newItem.name
    }

    interface OnClickListener {
        fun onClick(mediaFile: Repo, position: Int)
    }

}