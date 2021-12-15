package com.rms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.exception.MenuNotFoundException;
import com.rms.model.Menu;
import com.rms.repository.MenuRepository;

@Service
public class MenuService {
	@Autowired
	MenuRepository menuRepository;

	public Menu saveMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	public List<Menu> getAllMenu() {
		return menuRepository.findAll();

	}

	public Optional<Menu> getMenuById(Long id) {
		return menuRepository.findById(id);
	}

	public boolean deleteMenu(Long id) {
		if (menuRepository.existsById(id)) {
			menuRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public List<Menu> searchMenuByKeyword(String keyword) {
		return menuRepository.findMenusByKeyword(keyword);

	}

	public Menu updateMenu(Long id, Menu menu) throws MenuNotFoundException {
		if (!menuRepository.existsById(id)) {
			throw new MenuNotFoundException("No Menu record found with id " + id);
		}
		return menuRepository.save(menu);

	}

}
