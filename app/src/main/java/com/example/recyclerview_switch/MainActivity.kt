package com.example.recyclerview_switch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview_switch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val switchlist = listOf(
        Switch("1번째 스위치", false),
        Switch("2번째 스위치", false),
        Switch("3번째 스위치", false),
        Switch("4번째 스위치", false),
        Switch("5번째 스위치", false),
        Switch("6번째 스위치", false),
        Switch("7번째 스위치", false),
        Switch("8번째 스위치", false),
        Switch("9번째 스위치", false),
        Switch("10번째 스위치", false),
        Switch("11번째 스위치", false),
        Switch("12번째 스위치", false),
        Switch("13번째 스위치", false),
        Switch("14번째 스위치", false),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        binding.switchList.layoutManager = LinearLayoutManager(this)
        binding.switchList.adapter = SwitchAdapter(switchlist)
    }
}