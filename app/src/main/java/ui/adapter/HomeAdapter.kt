package ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.upstocksapplication.databinding.HomeActivityItemViewBinding
import data.StocksViewData
import data.UserHolding

class HomeAdapter() : Adapter<HomeAdapter.HomeItemViewHolder>() {

    private var userHoldings: List<StocksViewData> = emptyList()

    fun setData(userHoldingList : List<StocksViewData>){
        this.userHoldings = userHoldingList
    }
    class HomeItemViewHolder(val binding : HomeActivityItemViewBinding) :  RecyclerView.ViewHolder(binding.root) {
        fun bind(data : StocksViewData) {
            binding.userHolding = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeActivityItemViewBinding.inflate(inflater, parent, false)
        return HomeItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userHoldings.size
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        val data = userHoldings[position]
       val binding = holder.binding
        if(position == userHoldings.size-1) {
            binding.divider.visibility = View.GONE
        }
        else {
            binding.divider.visibility = View.VISIBLE
        }

        holder.bind(data)
    }
}


