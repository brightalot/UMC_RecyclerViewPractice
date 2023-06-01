import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewmemo.Memo
import com.example.recyclerviewmemo.MemoActivity
import com.example.recyclerviewmemo.Pref
import com.example.recyclerviewmemo.databinding.ItemMemoBinding

class MemoAdapter(
    private val memos: ArrayList<Memo>,
    private val memoActivityLauncher: ActivityResultLauncher<Intent>
) : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        return MemoViewHolder(
            ItemMemoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bind(memos[position])
        holder.binding.btnDelete.setOnClickListener {
            memos.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            Pref.saveMemos(memos)
        }
        holder.binding.tvMemo.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MemoActivity::class.java)
                .putExtra("memo", memos[holder.adapterPosition].text)
                .putExtra("position", holder.adapterPosition)
            memoActivityLauncher.launch(intent)
        }
    }

    override fun getItemCount(): Int {
        return memos.size
    }

    class MemoViewHolder(val binding: ItemMemoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: Memo) {
            binding.tvMemo.text = memo.text
        }
    }
}
