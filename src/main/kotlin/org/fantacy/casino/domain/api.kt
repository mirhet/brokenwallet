package org.fantacy.casino.domain

data class CreateAccountCommand(
    val playerUid: String
)

data class CreateAccountDocument(
    val account: Long
)

data class AccountBalanceQuery(
    val playerUid: String
)

data class CreditAccountCommand(
    val account: Long,
    val amount: Double,
    val externalUid: String
)

data class DebitAccountCommand(
    val account: Long,
    val amount: Double,
    val externalUid: String
)

data class ListTransactionsQuery(
    val playerUid: String
)

data class AccountBalanceDTO(val account: Long, val balance: Double)

data class TransactionDTO(
    val account: Long,
    val direction: String,
    val externalUid: String,
    val amount: Double
)
