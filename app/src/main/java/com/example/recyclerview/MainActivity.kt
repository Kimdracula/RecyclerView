package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val data = arrayListOf(Pair(
            Data(Data.TYPE_BUS, "Машина"), true)
        )

      val adapter =ActivityRecyclerAdapter(
          object : OnListItemClickListener {
              override fun onItemClick(data: Data) {
                  Toast.makeText(applicationContext, data.someText,
                      Toast.LENGTH_SHORT).show()
              }
          },
          data
      )
binding.recyclerView.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener { adapter.appendItem() }

    }
}
