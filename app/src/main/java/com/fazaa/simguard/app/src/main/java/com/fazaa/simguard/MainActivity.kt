package com.fazaa.simguard

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("guard", MODE_PRIVATE)
        val tokenInput = findViewById<EditText>(R.id.token)
        val chatInput = findViewById<EditText>(R.id.chatId)
        val btn = findViewById<Button>(R.id.btnSave)

        tokenInput.setText(prefs.getString("bot_token",""))
        chatInput.setText(prefs.getString("chat_id",""))

        btn.setOnClickListener {
            prefs.edit()
                .putString("bot_token", tokenInput.text.toString())
                .putString("chat_id", chatInput.text.toString())
                .apply()
            Toast.makeText(this, "تم تفعيل الحماية بنجاح ✅", Toast.LENGTH_LONG).show()
        }
    }
}
