package com.rms;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.controller.MenuController;
import com.rms.model.Menu;
import com.rms.service.MenuService;

@WebMvcTest(MenuController.class)
@ActiveProfiles("Test")
public class MenuWebLayerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	MenuService menuService;

	Menu menu1 = new Menu(30L, "HAWAIIAN PIZZA", "All-time favorite topping, Hawaiian pizza in Topical Hawaii style",
			"https://TestLink/menu1.jpg", "Italian,Ham,Pineapple", 300);
	Menu menu2 = new Menu(31L, "CHICKEN TOM YUM PIZZA",
			"Best Marinated chicken with pineapple and mushroom on spicy lemon sauce, Thai pizza style",
			"https://menu2.jpg", "Italian,Thai,Chicken,Mushroom,Hot", 300);
	Menu menu3 = new Menu(32L, "BEER", "Fantastic flavors and authentic regional appeal beer", "https://menu3.jpg",
			"Alcohol", 300);

	@Test
	public void getAllMenuTest() throws Exception {
		List<Menu> menus = new ArrayList<Menu>();
		menus.add(menu1);
		menus.add(menu2);
		menus.add(menu3);
		Mockito.when(menuService.getAllMenu()).thenReturn(menus);

		mockMvc.perform(MockMvcRequestBuilders.get("/menu").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].id", is(30)))
				.andExpect(jsonPath("$[0].name", is("HAWAIIAN PIZZA"))).andExpect(jsonPath("$[1].id", is(31)))
				.andExpect(jsonPath("$[1].price", is(300)));

	}

	@Test
	public void getMenuById_successTest() throws Exception {
		Mockito.when(menuService.getMenuById(31L)).thenReturn(Optional.of(menu2));
		mockMvc.perform(MockMvcRequestBuilders.get("/menu/31").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(31)))
				.andExpect(jsonPath("$.name", is("CHICKEN TOM YUM PIZZA"))).andExpect(jsonPath("$.price", is(300)));

	}

	@Test
	public void getMenuById_ExceptionTest() throws Exception {
		Mockito.when(menuService.getMenuById(300L)).thenReturn(Optional.empty());
		mockMvc.perform(MockMvcRequestBuilders.get("/menu/300").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound());

	}

	@Test
	public void saveMenuTest() throws Exception {
		Menu menu = new Menu(35L, "test", "test", "test", "test", 0);
		Mockito.when(menuService.saveMenu(menu)).thenReturn(menu);
		MockHttpServletRequestBuilder mockrequest = MockMvcRequestBuilders.post("/menu")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(menu));
		mockMvc.perform(mockrequest).andExpect(status().isCreated()).andExpect(jsonPath("$.name", is("test")));

	}

	@Test
	public void saveMenu_ExceptionTest() throws Exception {
		Menu menu = new Menu(35L, "", "test", "test", "test", 0);
		Mockito.when(menuService.saveMenu(menu)).thenReturn(menu);
		MockHttpServletRequestBuilder mockrequest = MockMvcRequestBuilders.post("/menu")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(menu));
		mockMvc.perform(mockrequest).andExpect(status().isBadRequest());

	}

	@Test
	public void getAllMenu_Exception() throws Exception {
		Mockito.when(menuService.getAllMenu()).thenReturn(new ArrayList<Menu>());

		mockMvc.perform(MockMvcRequestBuilders.get("/menu")).andExpect(status().isNotFound());

	}

	@Test
	public void updateMenuTest() throws Exception {
		Menu menu = new Menu(35L, "test", "test", "test", "test", 0);
		Mockito.when(menuService.saveMenu(menu)).thenReturn(menu);
		MockHttpServletRequestBuilder mockrequest = MockMvcRequestBuilders.put("/menu/35")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(menu));
		mockMvc.perform(mockrequest).andExpect(status().isAccepted());

	}

	@Test
	public void deleteMenu_ExceptionTest() throws Exception {
		Mockito.when(menuService.deleteMenu(300L)).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.delete("/menu/300").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound());

	}

	@Test
	public void deleteMenuTest() throws Exception {
		Mockito.when(menuService.deleteMenu(31L)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.delete("/menu/31").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNoContent());

	}

	@Test
	public void searchMenuByKeywordTest() throws Exception {
		List<Menu> menu = new ArrayList<Menu>();
		menu.add(menu2);
		Mockito.when(menuService.searchMenuByKeyword("HAWAIIAN")).thenReturn(menu);

		mockMvc.perform(MockMvcRequestBuilders.get("/menu/search/HAWAIIAN")).andExpect(status().isOk());

	}

	@Test
	public void searchMenuByKeyword_ExceptionTest() throws Exception {
		Mockito.when(menuService.searchMenuByKeyword("HAWAIIAN")).thenReturn(new ArrayList<>());

		mockMvc.perform(MockMvcRequestBuilders.get("/menu/search/HAWAIIAN")).andExpect(status().isNotFound());

	}

}
