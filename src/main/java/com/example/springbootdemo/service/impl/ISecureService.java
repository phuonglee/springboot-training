package com.example.springbootdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootdemo.model.Account;
import com.example.springbootdemo.repositories.AccountRepository;
import com.example.springbootdemo.service.SecureService;

@Service("secureService")
@Transactional
public class ISecureService implements SecureService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account findByUserNameAndPassword(String userName, String password) {
		return accountRepository.findByUserNameAndPassword(userName, password);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Account account = accountRepository.findByUserName(userName);
		if (account != null) {
			if (account.getUserName().equalsIgnoreCase("admin")) {
				return new User(account.getUserName(), account.getPassword(), true, true, true, true,
						AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
			} else {
				return new User(account.getUserName(), account.getPassword(), true, true, true, true,
						AuthorityUtils.createAuthorityList("ROLE_USER"));
			}
		} else {
			throw new UsernameNotFoundException("could not find the user '" + userName + "'");
		}
	}
}
