package com.example.engineeringthesis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringthesis.R
import com.example.engineeringthesis.model.Account
import java.util.*

open class AccountAdapter(private val monAccountListener: AccountAdapter.OnAccountItemListener): RecyclerView.Adapter<AccountAdapter.ViewHolder>() {
    var accountDetails: List<Account> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.select_account_list, parent, false)
        return AccountAdapter.ViewHolder(itemView, monAccountListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.accountName.text = "Nazwa konta: " + accountDetails.get(position).accountName
    }

    override fun getItemCount(): Int {
       return accountDetails.size
    }

    fun getItem(position: Int): Account
    {
        return  accountDetails.get(position)
    }

    fun setAccountList(accAdapterlist: List<Account>) {
        accountDetails = accAdapterlist
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, onAccountListener: OnAccountItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var accountName: TextView
        var accountDeleteButton: ImageButton
        var onAccountListener: OnAccountItemListener
        override fun onClick(view: View) {
            onAccountListener.onChildResultsItemClick(adapterPosition)
        }

        init {
            accountName = itemView.findViewById<TextView>(R.id.accountName) as TextView
            accountDeleteButton = itemView.findViewById<ImageButton>(R.id.accountDeleteButton) as ImageButton
            this.onAccountListener = onAccountListener
            accountDeleteButton.setOnClickListener(this)
        }
    }

    interface OnAccountItemListener {
        fun onChildResultsItemClick(position: Int)
    }

}