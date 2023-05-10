package com.example.recyclerviewmemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewmemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var memoActivityLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: MemoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()

        val memos: ArrayList<Memo> = arrayListOf()
        adapter = MemoAdapter(memos)

        binding.rvMemo.adapter = adapter

        memoActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val memoString = data?.getStringExtra("memo") ?: ""
                val memo = Memo(memoString)
                memos.add(memo)
                adapter.notifyDataSetChanged()
            }
        }

        binding.btnAddMemo.setOnClickListener{
            val intent = Intent(this, MemoActivity::class.java)
            memoActivityLauncher.launch(intent)
        }
    }

    private fun initializeViews() {
        binding.rvMemo.layoutManager = LinearLayoutManager(this)
    }
}