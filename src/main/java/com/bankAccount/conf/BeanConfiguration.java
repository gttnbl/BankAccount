package com.bankAccount.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bankAccount.BankAccountApplication;
import com.bankAccount.domain.service.AccountService;
import com.bankAccount.infrastructure.adapter.AccountRepository;

@Configuration
@ComponentScan(basePackageClasses = BankAccountApplication.class)
public class BeanConfiguration {

	@Bean
	AccountService bankAccountService(AccountRepository accountRepository) {
		return new AccountService(accountRepository, accountRepository, accountRepository);
	}

}
