package com.example.springbootdemo.controller.restapi;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springbootdemo.exeption.MyResourceNotFoundException;
import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.UserService;

@RestController
@RequestMapping("/api")
public class RAUserController {

	public static final Logger logger = LoggerFactory.getLogger(RAUserController.class);

	// Service which will do all data
	@Autowired
	UserService userService;

	/**
	 * Retrieve All Users.
	 * @return ResponseEntity<List<User>> response list of User object.
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(users);
	}

	/**
	 * Retrieve Single User.
	 * @param id of user to retrieve.
	 * @return ResponseEntity<User> response entity of user object.
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		return  ResponseEntity.ok(user);
	}

	/**
	 * Create a User.
	 * @param user User data to create.
	 * @param ucBuilder refer to {@link UriComponentsBuilder}.
	 * @return ResponseEntity<String> header uri to response.
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update a User.
	 * @param id id of user to update.
	 * @param user object store data to update.
	 * @return ResponseEntity<User> response entity of user object updated.
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return ResponseEntity.ok(currentUser);
	}

	/**
	 * Delete a User.
	 * @param id of user to delete.
	 * @return ResponseEntity<User> response with no content after deleted.
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete All Users.
	 * @return ResponseEntity<User> response User object.
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Retrieve list of user paginated.
	 * @param page number of page to retrieve.
	 * @param size of list data on each page.
	 * @return Page<User> data list with paginated.
	 */
	@RequestMapping(value = "/user/get", params = { "page", "size" }, method = RequestMethod.GET)
	public Page<User> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
		Page<User> resultPage = userService.findPaginated(page, size);
		if (page > resultPage.getTotalPages()) {
			throw new MyResourceNotFoundException();
		}
		return resultPage;
	}
}
