package org.fantacy.casino.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<Account, Long> {
	fun findByPlayerUid(playerUid:String):List<Account>
}

@Repository
interface TransactionRepository: JpaRepository<Transaction, Long> {
	fun findFirstByAccountOrderByIdDesc(account: Account):Transaction?
}
