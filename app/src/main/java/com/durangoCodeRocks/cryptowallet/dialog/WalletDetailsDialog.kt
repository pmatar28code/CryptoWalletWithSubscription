package com.durangoCodeRocks.cryptowallet.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.durangoCodeRocks.cryptowallet.Repository
import com.durangoCodeRocks.cryptowallet.network.networkcalls.ListAccountsNetwork
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.durangoCodeRocks.cryptowallet.R
import com.durangoCodeRocks.cryptowallet.databinding.FragmentWalletDetailsBinding

class WalletDetailsDialog: DialogFragment() {
    companion object {
        fun create(listener: () -> Unit): WalletDetailsDialog {
            return WalletDetailsDialog().apply {

            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val binding = FragmentWalletDetailsBinding.inflate(inflater)

        ListAccountsNetwork.getAccounts { ListOfAccounts ->
            for(wallet in ListOfAccounts){
                if(wallet.id == Repository.walletDetailsAccountId){
                    binding.walletDetailsNameText.text = wallet.name
                    binding.walletDetailsBalanceAmount.text = "Balance: ${wallet.balance?.amount}"
                    binding.walletDetailsCurrency.text = "Currency: ${wallet.balance?.currency}"
                }
            }
        }

        return MaterialAlertDialogBuilder(
            requireContext(), R.style.MyRounded_MaterialComponents_MaterialAlertDialog
        )
            .setView(binding.root)
            .setPositiveButton(getString(R.string.dialog_close)) { _, _ ->
            }
            .create()
    }
}