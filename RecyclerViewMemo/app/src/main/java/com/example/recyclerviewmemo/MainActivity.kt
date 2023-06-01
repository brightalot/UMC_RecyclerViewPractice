package com.example.recyclerviewmemo

import MemoAdapter
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
    private val memos: ArrayList<Memo> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Pref.getContext(this)

        initializeViews()

        memoActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val memoString = data?.getStringExtra("memo") ?: ""
                val position = data?.getIntExtra("position", -1) ?: -1
                if (position == -1) {
                    val memo = Memo(memoString)
                    memos.add(memo)
                    adapter.notifyDataSetChanged()
                } else {
                    val modifiedMemo = Memo(memoString)
                    memos[position] = modifiedMemo
                    adapter.notifyItemChanged(position)
                }
                Pref.saveMemos(memos)
            }
        }

        adapter = MemoAdapter(memos, this.memoActivityLauncher)

        binding.rvMemo.adapter = adapter

        binding.btnAddMemo.setOnClickListener{
            val intent = Intent(this, MemoActivity::class.java)
            memoActivityLauncher.launch(intent)
        }
        Pref.loadMemos(memos)
        adapter.notifyDataSetChanged()
    }
    private fun initializeViews() {
        binding.rvMemo.layoutManager = LinearLayoutManager(this)
    }


    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}