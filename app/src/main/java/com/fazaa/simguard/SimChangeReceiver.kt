package com.fazaa.simguard

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import java.net.URL
import java.net.URLEncoder
import java.util.Date

class SimChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val prefs = context.getSharedPreferences("guard", Context.MODE_PRIVATE)
        val oldSim = prefs.getString("old_sim", null)
        val botToken = prefs.getString("bot_token", "")
        val chatId = prefs.getString("chat_id", "")
        if (botToken.isNullOrEmpty() || chatId.isNullOrEmpty()) return

        try {
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val newSim = tm.simSerialNumber ?: "غير معروف"
            
            if (oldSim == null) {
                prefs.edit().putString("old_sim", newSim).apply()
                return
            }
            if (oldSim != newSim) {
                val msg = "🚨 تنبيه: تم تغيير شريحة هاتفك!\nالجديدة: $newSim\nالوقت: $Date()\n\nهذا تنبيه لحماية جهازك الشخصي."
                Thread {
                    try {
                        val url = URL("https://api.telegram.org/bot$botToken/sendMessage?chat_id=$chatId&text=${URLEncoder.encode(msg, "UTF-8")}")
                        url.openConnection().getInputStream().close()
                    } catch (e: Exception) {}
                }.start()
                prefs.edit().putString("old_sim", newSim).apply()
            }
        } catch (e: Exception) {}
    }
}
