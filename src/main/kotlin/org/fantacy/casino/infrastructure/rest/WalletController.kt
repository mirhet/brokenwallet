package org.fantacy.casino.infrastructure.rest

import org.aspectj.weaver.tools.cache.SimpleCacheFactory.path
import org.fantacy.casino.application.service.WalletService
import org.fantacy.casino.domain.AccountBalanceDTO
import org.fantacy.casino.domain.AccountBalanceQuery
import org.fantacy.casino.domain.CreateAccountCommand
import org.fantacy.casino.domain.CreateAccountDocument
import org.fantacy.casino.domain.CreditAccountCommand
import org.fantacy.casino.domain.DebitAccountCommand
import org.fantacy.casino.domain.ListTransactionsQuery
import org.fantacy.casino.domain.ValidationException
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = ["/api/wallet"])
class WalletController(val walletService: WalletService) {

	@PostMapping(path = ["/create"])
	fun createAccount(command: CreateAccountCommand): CreateAccountDocument {
		if (command.playerUid.isNullOrEmpty()) {
			throw ValidationException("playerUid must not be null or empty")
		}

		return walletService.createAccount(command)
	}

	@PostMapping(path = ["/balance"])
	fun accountBalance(query: AccountBalanceQuery):List<AccountBalanceDTO> {
		if (query.playerUid.isNullOrEmpty()) {
			throw ValidationException("playerUid must not be null or empty")
		}

		return walletService.accountBalance(query)
	}

	@PostMapping(path = ["/credit"])
	fun creditAccount(command: CreditAccountCommand):AccountBalanceDTO {
		if (command.externalUid.isNullOrEmpty()) {
			throw ValidationException("externalUid must not be null or empty")
		}

		return walletService.creditAccount(command)
	}

	@PostMapping(path = ["/debit"])
	fun debitAccount(command: DebitAccountCommand):AccountBalanceDTO {
		if (command.externalUid.isNullOrEmpty()) {
			throw ValidationException("externalUid must not be null or empty")
		}

		return walletService.debitAccount(command)
	}

	@PostMapping(path = ["/list"])
	fun listTransactions(query:ListTransactionsQuery):List<AccountBalanceDTO> {
		if (query.playerUid.isNullOrEmpty()) {
			throw ValidationException("playerUid must not be null or empty")
		}

		return walletService.listTransactions(query)
	}
}