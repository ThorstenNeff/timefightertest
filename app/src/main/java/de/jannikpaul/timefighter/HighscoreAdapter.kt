package de.jannikpaul.timefighter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class HighscoreAdapter(val context: Context, var datasource: MutableList<String>) : BaseAdapter(){

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return datasource.size
    }

    override fun getItem(position: Int): String {
        return datasource.get(position)
    }

    override fun getItemId(position: Int): Long {
            return position.toLong()
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item, parent, false)
        val textView = rowView.findViewById<TextView>(R.id.textView)
        textView.text = "Hallo"
        textView.setTextColor(Color.MAGENTA)
        return rowView
    }

}