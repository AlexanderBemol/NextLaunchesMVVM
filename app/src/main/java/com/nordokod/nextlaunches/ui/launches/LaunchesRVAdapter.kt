package com.nordokod.nextlaunches.ui.launches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nordokod.nextlaunches.R
import com.nordokod.nextlaunches.model.entity.Launches

class LaunchesRVAdapter(private val launchesList : List<Launches>) : RecyclerView.Adapter<LaunchesRVAdapter.LaunchItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LaunchItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: LaunchItemViewHolder, position: Int) {
        val launch: Launches = launchesList[position]
        holder.bind(launch)
    }
    override fun getItemCount(): Int = launchesList.size


    class LaunchItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_launch, parent, false)) {
        private var tv_launch_name: TextView? = null
        private var tv_launch_date: TextView? = null
        init {
            tv_launch_name = itemView.findViewById(R.id.tv_launch_name)
            tv_launch_date = itemView.findViewById(R.id.tv_launch_date)
        }
        fun bind(launches: Launches) {
            tv_launch_name?.text = launches.name
            tv_launch_date?.text = launches.windowstart?.toString()
        }
    }

}