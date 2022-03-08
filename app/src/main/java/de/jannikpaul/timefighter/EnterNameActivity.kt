package de.jannikpaul.timefighter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EnterNameActivity : AppCompatActivity() {

    val gson = Gson()
    val highscoreEntryTypeToken = object : TypeToken<MutableList<HighscoreEntry>>() {}.type

    var highscore: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_name)

        highscore = intent.extras?.get("highscore") as Int

        val eingabeFeld = findViewById<EditText>(R.id.editTextTextPersonName)
        eingabeFeld.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(view: View?, keyCode: Int, keyEvent: KeyEvent?): Boolean {
                if (keyEvent?.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER) {
                    Log.d("Eingabe", "Enter")
                    storeHighscore(eingabeFeld.text.toString())
                    goToHighScoreList()
                    return true
                }
                return false
            }

        })

    }

    private fun goToHighScoreList() {
        val intent = Intent(this, HighScoreActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun storeHighscore(playerName: String) {
        // Load "old" list as string
        val pref: SharedPreferences = getSharedPreferences("Default", Context.MODE_PRIVATE)
        val loadedString = pref.getString(
            "highscorelist_list",
            "[]"
        )

        // Build MutableList from string with Gson
        val highScoreList = gson.fromJson(loadedString, highscoreEntryTypeToken) as MutableList<HighscoreEntry>

        // Add to list
        val eintrag = HighscoreEntry(
            highscore,
            playerName
        )
        highScoreList.add(eintrag)

        // Store list on device (in Shared Preferences)
        saveHighscoreList(highScoreList)
    }

    fun saveHighscoreList(highScoreList: MutableList<HighscoreEntry>) {
        val prefs: SharedPreferences = getSharedPreferences("Default", Context.MODE_PRIVATE)

        // Convert list back to string
        val stringToSave = gson.toJson(highScoreList)

        Log.d("highscore",stringToSave)

        // Save string in Shared Preferences
        prefs.edit {
            putString("highscorelist_key", stringToSave)
            commit()
        }
    }
}