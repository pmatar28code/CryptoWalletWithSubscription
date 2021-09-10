package com.durangoCodeRocks.cryptowallet.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.durangoCodeRocks.cryptowallet.Repository
import com.durangoCodeRocks.cryptowallet.adapter.TransactionsAdapter
import com.durangoCodeRocks.cryptowallet.network.networkcalls.ListTransactionsNetwork
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.durangoCodeRocks.cryptowallet.R
import com.durangoCodeRocks.cryptowallet.databinding.FragmentTransactionsDetailsDialogBinding

class TransactionsDetailDialog:DialogFragment() {
    companion object {
        fun create(listener: () -> Unit): TransactionsDetailDialog {
            return TransactionsDetailDialog().apply {

            }
        }
    }
    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val binding = FragmentTransactionsDetailsDialogBinding.inflate(inflater)

        val transactionsDetailsAdapter = TransactionsAdapter{

        }
        binding.apply {
            Glide.with(requireContext())
                .load(
                    "https://api.coinicons.net/icon/${Repository.setTransactionCurrencyForIcon}/128x128"
                )
                .into(walletTransactionDetailsImage)
                recyclerTransactionDetailsDialog.apply {
                    ListTransactionsNetwork.getTransactions {
                    adapter = transactionsDetailsAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                    transactionsDetailsAdapter.submitList(it)
                    transactionsDetailsAdapter.notifyDataSetChanged()
                }
            }
        }

        return MaterialAlertDialogBuilder(
            requireContext(), R.style.MyRounded_MaterialComponents_MaterialAlertDialog)
            .setView(binding.root)
            .setPositiveButton(getString(R.string.accept_button)){_,_ ->

            }
            .create()
    }
}