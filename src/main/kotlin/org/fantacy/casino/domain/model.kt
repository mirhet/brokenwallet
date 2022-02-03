package org.fantacy.casino.domain

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Account(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	val id:Long?,
	val playerUid:String)

@Entity
data class Transaction(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	val id:Long?,
	@ManyToOne(optional = false, fetch = EAGER)
	@JoinColumn(name = "account_id")
	val account:Account,
	val externalUid: String,
	val amount:BigDecimal,
	val balanceBefore:BigDecimal,
	val balanceAfter: BigDecimal)
