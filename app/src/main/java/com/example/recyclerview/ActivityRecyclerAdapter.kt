package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Data.Companion.TYPE_BUS
import com.example.recyclerview.Data.Companion.TYPE_CAR

class ActivityRecyclerAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<Pair<Data, Boolean>>
) : RecyclerView.Adapter<BaseViewHolder>() {

    fun appendItem() {
        data.add(generateItem())
        notifyItemInserted(itemCount-1)
    }

    private fun generateItem() = Pair(Data(TYPE_BUS, "lalala", ""),false)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            TYPE_CAR ->  CarViewHolder(inflater.inflate(R.layout.car_item, parent, false) as View)
            TYPE_BUS->   BusViewHolder(inflater.inflate(R.layout.bus_item, parent, false) as View)
            else -> HeaderViewHolder(inflater.inflate(R.layout.header_item,parent,false))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.bind(data[position])

    }

    override fun getItemCount(): Int = data.size


    override fun getItemViewType(position: Int): Int = data[position].first.type

    inner class CarViewHolder(view: View) : BaseViewHolder(view) {

        override fun bind(data: Pair<Data, Boolean>) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.descriptionTextView).text =
                    data.first.someDescription
                itemView.findViewById<ImageView>(R.id.wikiImageView)
                    .setOnClickListener { onListItemClickListener.onItemClick(data.first) }
            }
        }
    }

    inner class BusViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Data, Boolean>) {
            itemView.findViewById<ImageView>(R.id.marsImageView).setOnClickListener {
                onListItemClickListener.onItemClick(data.first)
            }
            itemView.findViewById<ImageView>(R.id.addItemImageView).setOnClickListener {
                addItem() }
            itemView.findViewById<ImageView>(R.id.removeItemImageView).setOnClickListener{
                removeItem()
            }
            itemView.findViewById<ImageView>(R.id.moveItemDown).setOnClickListener {
                moveDown() }
            itemView.findViewById<ImageView>(R.id.moveItemUp).setOnClickListener {
                moveUp() }

            itemView.findViewById<TextView>(R.id.marsDescriptionTextView).visibility =
                if (data.second) View.VISIBLE else View.GONE
            itemView.findViewById<TextView>(R.id.marsTextView).setOnClickListener {
                toggleText() }
        }
        private fun toggleText() {
            data[layoutPosition] = data[layoutPosition].let {
                it.first to !it.second
            }
            notifyItemChanged(layoutPosition)
        }





    private fun moveUp() {
        layoutPosition.takeIf { it > 1 }?.also { currentPosition ->
            data.removeAt(currentPosition).apply {
                data.add(currentPosition - 1, this)
            }
            notifyItemMoved(currentPosition, currentPosition - 1)
        }
    }
    private fun moveDown() {
        layoutPosition.takeIf { it < data.size - 1 }?.also { currentPosition ->
            data.removeAt(currentPosition).apply {
                data.add(currentPosition + 1, this)
            }
            notifyItemMoved(currentPosition, currentPosition + 1)
        }
    }



        private fun addItem() {
            data.add(layoutPosition, generateItem())
            notifyItemInserted(layoutPosition)
        }
        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Data, Boolean>) {
            itemView.setOnClickListener {
                onListItemClickListener.onItemClick(data.first)
            }
        }
    }
}

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: Pair<Data, Boolean>)


}


