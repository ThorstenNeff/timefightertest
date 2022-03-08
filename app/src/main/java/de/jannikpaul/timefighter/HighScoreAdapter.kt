package de.jannikpaul.timefighter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class HighScoreAdapter(context: Context, var datasource: MutableList<HighscoreEntry>) : BaseAdapter(){

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return datasource.size
    }

    override fun getItem(position: Int): HighscoreEntry {
        return datasource[position]
    }

    override fun getItemId(position: Int): Long {
            return position.toLong()
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item, parent, false)
        val playerNameTextView = rowView.findViewById<TextView>(R.id.playerNameTextView)
        val scoreTextView = rowView.findViewById<TextView>(R.id.scoreTextView)
        val singleHighScoreEntry = datasource[position]
        playerNameTextView.text = singleHighScoreEntry.playerName
        scoreTextView.text = singleHighScoreEntry.score.toString()
        return rowView
    }

}