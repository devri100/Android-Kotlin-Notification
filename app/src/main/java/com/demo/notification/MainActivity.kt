package com.demo.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alarmManager = getSystemService(Service.ALARM_SERVICE) as AlarmManager
        val newIntent = Intent(this, MyBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, newIntent, 0)

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, pendingIntent)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, FirstFragment())
                .commit()
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val fragmentId = intent?.extras?.get("fragment")

        when(fragmentId){
            "1" -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, SecondFragment())
                .addToBackStack(null)
                .commit()
        }
    }
    
}
