package com.durangoCodeRocks.cryptowallet.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.durangoCodeRocks.cryptowallet.R
import com.durangoCodeRocks.cryptowallet.Repository
import com.durangoCodeRocks.cryptowallet.adapter.WalletsAdapter
import com.durangoCodeRocks.cryptowallet.databinding.FragmentWalletBinding
import com.durangoCodeRocks.cryptowallet.dialog.WalletDetailsDialog
import com.durangoCodeRocks.cryptowallet.network.classesapi.ListAccounts
import com.durangoCodeRocks.cryptowallet.network.networkcalls.ListAccountsNetwork

class WalletFragment: Fragment(R.layout.fragment_wallet) {
    companion object{
        var listOfAccountsWithCondition = mutableListOf<ListAccounts.Data>()
        var walletAdapter:WalletsAdapter ?= null
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentWalletBinding.bind(view)

        justWalletAccountsWithCurrencyBTCAndWLUNA()

        walletAdapter = WalletsAdapter {
            Repository.walletDetailsAccountId = it.id.toString()
            WalletDetailsDialog.create {
            }.show(parentFragmentManager,"to open wallet details dialog")
        }
        binding.walletsRecyclerView.apply {
            adapter = walletAdapter
            layoutManager = LinearLayoutManager(requireContext())
            walletAdapter!!.submitList(Repository.accounts.reversed())
            walletAdapter!!.notifyDataSetChanged()

        }
    }
    private fun justWalletAccountsWithCurrencyBTCAndWLUNA(){
        for(account in Repository.accounts){
            if(account.balance?.currency == "BTC" || account.balance?.currency == "WLUNA"){
                listOfAccountsWithCondition.add(account)
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        ListAccountsNetwork.getAccounts {
            Repository.accounts = it as MutableList<ListAccounts.Data>
            walletAdapter?.submitList(Repository.accounts.reversed())
            walletAdapter?.notifyDataSetChanged()
        }
    }
}