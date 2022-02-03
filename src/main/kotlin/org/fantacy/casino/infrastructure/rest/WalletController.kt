package org.fantacy.casino.infrastructure.rest

import org.fantacy.casino.application.service.WalletService
import org.fantacy.casino.domain.AccountBalanceDTO
import org.fantacy.casino.domain.AccountBalanceQuery
import org.fantacy.casino.domain.CreateAccountCommand
import org.fantacy.casino.domain.CreateAccountDocument
import org.fantacy.casino.domain.CreditAccountCommand
import org.fantacy.casino.domain.DebitAccountCommand
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = ["/api/wallet"])
class WalletController(val walletService: WalletService) {

	@PostMapping(path = ["/create"])
	fun createAccount(command: CreateAccountCommand): CreateAccountDocument {
		return walletService.createAccount(command)
	}

	@PostMapping(path = ["/balance"])
	fun accountBalance(query: AccountBalanceQuery):List<AccountBalanceDTO> {
		return walletService.accountBalance(query)
	}

	@PostMapping(path = ["/credit"])
	fun creditAccount(command: CreditAccountCommand):AccountBalanceDTO {
		return walletService.creditAccount(command)
	}

	@PostMapping(path = ["/debit"])
	fun debitAccount(command: DebitAccountCommand):AccountBalanceDTO {
		return walletService.debitAccount(command)
	}
}