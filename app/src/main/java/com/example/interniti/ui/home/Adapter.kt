package com.example.interniti.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interniti.R

data class TrainItem(
    val CargoType: String = "",
    val Finish: String = "",
    val Start: String = "",
    val Time: String = "",
    val Train: String = "",
    val Van: String = "",
    val Widh: String = ""
)

class Adapter(private val list: List<TrainItem>):
    RecyclerView.Adapter<Adapter.TrainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_main, parent, false)
        return TrainViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        val item = list[position]

        holder.NameTrain.text = item.Train
        holder.WithCargo.text = item.Widh
        holder.Colvo.text = item.Van
        holder.TypeCargo.text = item.CargoType
        holder.Time.text = item.Time
        holder.Start.text = item.Start
        holder.Finish.text = item.Finish
    }

    class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NameTrain: TextView = itemView.findViewById(R.id.NameTrain)
        val WithCargo: TextView = itemView.findViewById(R.id.WithCargo)
        val Colvo: TextView = itemView.findViewById(R.id.Colvo)
        val TypeCargo: TextView = itemView.findViewById(R.id.TypeCargo)
        val Time: TextView = itemView.findViewById(R.id.Time)
        val Start: TextView = itemView.findViewById(R.id.Start)
        val Finish: TextView = itemView.findViewById(R.id.Finish)
    }
}