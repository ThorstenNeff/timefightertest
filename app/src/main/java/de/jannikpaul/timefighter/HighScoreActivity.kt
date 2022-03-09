package de.jannikpaul.timefighter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HighScoreActivity : AppCompatActivity() {

    val gson = Gson()
    val highscoreEntryTypeToken = object : TypeToken<MutableList<HighscoreEntry>>() {}.type

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        listView = findViewById<ListView>(R.id.highscoreListe)

        val highScoreList = loadList()

        // Sort list
        highScoreList.sortWith(compareByDescending { it.score })

        // Pass highscoreEntryList to list builder (adapter)
        val adapter = HighScoreAdapter(this, highScoreList)

        listView.adapter = adapter
    }

    fun loadList(): MutableList<HighscoreEntry> {

        // Load "old" list as string
        val pref: SharedPreferences = getSharedPreferences("Default", Context.MODE_PRIVATE)
        val loadedString = pref.getString(
            "highscorelist_list",
            "[]"
        )

        // Build MutableList from string with Gson
        val highscoreEntryList = gson.fromJson(loadedString, highscoreEntryTypeToken) as MutableList<HighscoreEntry>
        return highscoreEntryList
    }

    // Show a menu, so we can go back to start  screen

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.highscore_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.backtostart) {
            finish()
        }
        return true
    }
}