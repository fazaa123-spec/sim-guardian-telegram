package com.fazaa.simguard
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val prefs = getSharedPreferences("guard", MODE_PRIVATE)
        prefs.edit()
            .putString("bot_token", "8982745979:AAG-SdnF_tkkYzoTvov8E7WJF_v0fW3lL4I")
            .putString("chat_id", "6635794096")
            .apply()

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            Toast.makeText(this, "تم تفعيل الحماية ✅", Toast.LENGTH_LONG).show()
        }
    }
}
