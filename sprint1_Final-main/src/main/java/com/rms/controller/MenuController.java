package com.rms.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rms.exception.MenuNotFoundException;
import com.rms.model.Menu;
import com.rms.service.MenuService;

import io.swagger.annotations.ApiOperation;

@RestController
public class MenuController {
	@Autowired
	MenuService menuService;

	@ApiOperation(value = "Creates a new Menu record. JSON payload will be validated")
	@PostMapping(value = "/menu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Menu> saveMenu(@Valid @RequestBody Menu menu) {
		Menu savedMenu = menuService.saveMenu(menu);
		return new ResponseEntity<Menu>(savedMenu, HttpStatus.CREATED);

	}

	@ApiOperation(value = "Retrieves A list of all existing Menu records")
	@GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Menu>> getAllMenu() throws MenuNotFoundException {
		List<Menu> menus = menuService.getAllMenu();
		if (menus.isEmpty()) {
			throw new MenuNotFoundException("No Menu records found");
		}
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves a single Menu record by it's Id")
	@GetMapping(value = "/menu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Menu> getMenuById(@PathVariable("id") Long id) throws MenuNotFoundException {
		Optional<Menu> menu = menuService.getMenuById(id);
		if (!menu.isPresent()) {
			throw new MenuNotFoundException("No Menu record found with id " + id);
		}
		return new ResponseEntity<Menu>(menu.get(), HttpStatus.OK);

	}

	@ApiOperation(value = "Updates an existing Menu record by it's Id")
	@PutMapping(value = "/menu/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Menu> updateMenu(@PathVariable("id") Long id, @Valid @RequestBody Menu menu)
			throws MenuNotFoundException {
		Menu updatedMenu = menuService.updateMenu(id, menu);
		return new ResponseEntity<Menu>(updatedMenu, HttpStatus.ACCEPTED);

	}

	@ApiOperation(value = "Deletes an existing Menu record by it's Id")
	@DeleteMapping(value = "/menu/{id}")
	public ResponseEntity<Object> deleteMenu(@PathVariable("id") Long id) throws MenuNotFoundException {
		if (menuService.deleteMenu(id)) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		throw new MenuNotFoundException("No menu record found with id " + id);

	}

	@ApiOperation(value = "retrieves a list of Menu records with keyword")
	@GetMapping(value = "/menu/search/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Menu>> searchMenuByKeyword(@PathVariable("keyword") String keyword)
			throws MenuNotFoundException {
		List<Menu> menus = menuService.searchMenuByKeyword(keyword);
		if (menus.isEmpty()) {
			throw new MenuNotFoundException("No Menu record found with keyword " + keyword);
		}
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity Handler(MethodArgumentNotValidException m) {
		return new ResponseEntity(m.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MenuNotFoundException.class)
	public ResponseEntity HandleMenuNotFoundException(MenuNotFoundException menuNotFoundException) {
		return new ResponseEntity(menuNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

}
