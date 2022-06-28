package com.example.recyclerview

data class Data(
    val id: Int = 0,
    val type: Int = TYPE_CAR,
                val someText: String = "Text",
                val someDescription: String? = "Description"
) {
    companion object {
        const val TYPE_CAR = 0
        const val TYPE_BUS = 1
        const val TYPE_HEADER = 2
    }
}