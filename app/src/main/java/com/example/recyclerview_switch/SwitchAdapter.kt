package com.example.recyclerview_switch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_switch.databinding.ItemSwitchBinding

class SwitchAdapter(private val switchlist: List<Switch>): RecyclerView.Adapter<SwitchAdapter.SwitchViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SwitchViewHolder {
        return SwitchViewHolder(
            ItemSwitchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: SwitchAdapter.SwitchViewHolder, position: Int) {
        holder.bind(switchlist[position])
    }

    override fun getItemCount(): Int {
        return switchlist.size
    }

    class SwitchViewHolder(private val binding: ItemSwitchBinding)
        :RecyclerView.ViewHolder(binding.root) {
       fun bind(switch: Switch) {
           binding.switchTitleText.text = switch.title
           binding.switchSwitch.isChecked = switch.completed
       }
    }


}