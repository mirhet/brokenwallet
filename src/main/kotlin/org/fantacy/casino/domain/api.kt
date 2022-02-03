package org.fantacy.casino.domain

import java.math.BigDecimal

data class CreateAccountCommand(val playerUid:String, val type: String)
data class CreateAccountDocument(val account:Long)

data class AccountBalanceQuery(val playerUid: String)
data class CreditAccountCommand(
	val account:Long,
	val amount:BigDecimal,
	val externalUid: String)

data class DebitAccountCommand(
	val account: Long,
	val amount: BigDecimal,
	val externalUid: String)

data class AccountBalanceDTO(val account: Long, val balance:BigDecimal)
data class Paging(val skip:Int, val take:Int)
