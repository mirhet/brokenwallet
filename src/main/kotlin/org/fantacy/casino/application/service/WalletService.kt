package org.fantacy.casino.application.service

import org.fantacy.casino.domain.Account
import org.fantacy.casino.domain.AccountBalanceDTO
import org.fantacy.casino.domain.AccountBalanceQuery
import org.fantacy.casino.domain.AccountRepository
import org.fantacy.casino.domain.CreateAccountCommand
import org.fantacy.casino.domain.CreateAccountDocument
import org.fantacy.casino.domain.CreditAccountCommand
import org.fantacy.casino.domain.DebitAccountCommand
import org.fantacy.casino.domain.ListTransactionsQuery
import org.fantacy.casino.domain.Transaction
import org.fantacy.casino.domain.TransactionDTO
import org.fantacy.casino.domain.TransactionRepository
import org.springframework.stereotype.Service

@Service
class WalletService(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {

    fun createAccount(command: CreateAccountCommand): CreateAccountDocument {
        var account = Account(null, command.playerUid)
        account = accountRepository.saveAndFlush(account)

        return CreateAccountDocument(account.id!!)
    }

    fun accountBalance(query: AccountBalanceQuery): List<AccountBalanceDTO> {
        val accounts = accountRepository.findByPlayerUid(query.playerUid)

        return accounts.map({ account ->
            transactionRepository.findFirstByAccountOrderByIdDesc(account)
        }).filterNotNull()
            .map { transaction ->
                AccountBalanceDTO(transaction.account.id!!, transaction.balanceAfter)
            }
    }

    // add money
    fun creditAccount(command: CreditAccountCommand): AccountBalanceDTO {
        val account = accountRepository.getById(command.account)
        val lastTransaction = transactionRepository.findFirstByAccountOrderByIdDesc(account)

        val transaction = Transaction(
            null,
            account,
            "credit",
            command.externalUid,
            command.amount,
            lastTransaction?.balanceAfter ?: 0.0,
            lastTransaction?.balanceAfter?.plus(command.amount) ?: command.amount
        )

        transactionRepository.saveAndFlush(transaction)

        return AccountBalanceDTO(account.id!!, transaction.balanceAfter)
    }

    // remove money
    fun debitAccount(command: DebitAccountCommand): AccountBalanceDTO {
        val account = accountRepository.getById(command.account)
        val lastTransaction = transactionRepository.findFirstByAccountOrderByIdDesc(account)

        val transaction = Transaction(
            null,
            account,
            "debit",
            command.externalUid,
            command.amount,
            lastTransaction?.balanceAfter ?: 0.0,
            lastTransaction?.balanceAfter?.minus(command.amount) ?: command.amount
        )

        transactionRepository.saveAndFlush(transaction)

        return AccountBalanceDTO(account.id!!, transaction.balanceAfter)
    }

    fun listTransactions(query: ListTransactionsQuery): List<TransactionDTO> {
        val accounts = accountRepository.findByPlayerUid(query.playerUid)

        return accounts.flatMap({ account ->
            transactionRepository.findByAccount(account)
        }).map { transaction ->
        TransactionDTO(
            transaction.account.id!!,
            transaction.direction,
            transaction.externalUid,
            transaction.amount
        )
    }
    }
}
