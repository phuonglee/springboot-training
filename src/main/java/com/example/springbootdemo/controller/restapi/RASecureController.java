package com.example.springbootdemo.controller.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springbootdemo.model.Account;
import com.example.springbootdemo.service.SecureService;

@RestController
@RequestMapping("/api")
public class RASecureController {

	public static final Logger logger = LoggerFactory.getLogger(RASecureController.class);

	// Service which will do all data
	@Autowired
	SecureService secureService;

	/**
	 * Check user login.
	 * @param user User login data.
	 * @param ucBuilder refer to {@link UriComponentsBuilder}.
	 * @return ResponseEntity<User> header uri to response.
	 */
	@RequestMapping(value = "/secure/", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> doLogin(@RequestBody Account account, UriComponentsBuilder ucBuilder) {
		logger.info("Do login with User : {}", account);

		Account existed = secureService.findByUserNameAndPassword(account.getUserName(), account.getPassword());
		UserDetails user = null;
		if (existed != null) {
			user = secureService.loadUserByUsername(account.getUserName());
		}
		
		if (existed == null) {
			logger.error("Unable to login. User name {} or password {} incorrect", account.getUserName(), account.getPassword());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.ok(user);
	}

}
