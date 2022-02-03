package org.fantacy.casino.domain

import java.math.BigDecimal

data class CreateAccountCommand(
	val playerUid:String)

data class CreateAccountDocument(
	val account:Long)

data class AccountBalanceQuery(
	val playerUid: String)

data class CreditAccountCommand(
	val account:Long,
	val amount:BigDecimal,
	val externalUid: String)

data class DebitAccountCommand(
	val account: Long,
	val amount: BigDecimal,
	val externalUid: String)

data class ListTransactionsQuery(
	val playerUid: String)

data class AccountBalanceDTO(val account: Long, val balance:BigDecimal)
