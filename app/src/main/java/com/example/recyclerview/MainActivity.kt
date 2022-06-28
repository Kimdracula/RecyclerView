package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var itemTouchHelper: ItemTouchHelper
    private var isNewList = false
    private lateinit var adapter: ActivityRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val data = arrayListOf(
            Pair(
                Data(1,Data.TYPE_BUS, "Машина"), true
            )
        )

        adapter = ActivityRecyclerAdapter(
            object : OnListItemClickListener {
                override fun onItemClick(data: Data) {
                    Toast.makeText(
                        applicationContext, data.someText,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            data,
            object : OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }
            }

        )
        binding.recyclerView.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener { adapter.appendItem() }
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerActivityDiffUtilFAB.setOnClickListener { changeAdapterData() }
    }
    private fun changeAdapterData() {
        adapter.setItems(createItemList(isNewList).map { it })
        isNewList = !isNewList
    }


    private fun createItemList(instanceNumber: Boolean): List<Pair<Data, Boolean>> {
        return when (instanceNumber) {
            false -> listOf(
                Pair(Data(0, Data.TYPE_HEADER, "Header"), false),
                Pair(Data(1, Data.TYPE_BUS, "Mars", ""), false),
                Pair(Data(2, Data.TYPE_BUS, "Mars", ""), false),
                Pair(Data(3, Data.TYPE_BUS, "Mars", ""), false),
                Pair(Data(4, Data.TYPE_BUS, "Mars", ""), false),
                Pair(Data(5, Data.TYPE_BUS, "Mars", ""), false),
                Pair(Data(6, Data.TYPE_BUS, "Mars", ""), false)
            )
            true -> listOf(
                Pair(Data(0, Data.TYPE_HEADER, "Header"), false),
                Pair(Data(1, Data.TYPE_BUS, "Mars", ""), false),
                Pair(Data(2, Data.TYPE_BUS, "Jupiter", ""), false),
                Pair(Data(3, Data.TYPE_BUS, "Mars", ""), false),
                Pair(Data(4, Data.TYPE_BUS, "Neptune", ""), false),
                Pair(Data(5, Data.TYPE_BUS, "Saturn", ""), false),
                Pair(Data(6, Data.TYPE_BUS, "Mars", ""), false)
            )
        }
    }
}