package com.durangoCodeRocks.cryptowallet.oauth

import com.durangoCodeRocks.cryptowallet.network.classesapi.AccessToken

interface AccessTokenProvider {
    fun token(): AccessToken?
    fun refreshToken(refreshCallback: (Boolean) -> Unit)
}
