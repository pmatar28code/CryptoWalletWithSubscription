package com.durangoCodeRocks.cryptowallet.network.apis

import com.durangoCodeRocks.cryptowallet.network.classesapi.ListTransactions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ListTransactionsApi {
    @GET("v2/accounts/{account_id}/transactions?&limit=199")
    fun getTransactions(
        @Header("Authorization") token:String,
        @Path("account_id") account_id:String
    ): Call<ListTransactions>
}