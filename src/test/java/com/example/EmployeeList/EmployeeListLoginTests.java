package com.example.EmployeeList;

import com.example.EmployeeList.controllers.MainController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeListLoginTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private MainController controller;

	@Test
	void contextLoads() throws Exception {
		this.mockMvc.perform(get("/api/v1/main"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void accessDeniedTest() throws Exception {
		this.mockMvc.perform(post("/api/v1/auth/login"))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void correctLoginTest() throws Exception {
		this.mockMvc.perform(formLogin().user("dru").password("1"))
				.andExpect(status().is3xxRedirection());
	}

	@Test
	public void badCredentials() throws Exception {
		this.mockMvc.perform(post("/api/v1/auth/addEmployee").param("Mike", "Rabotyaga"))
				.andDo(print())
				.andExpect(status().isOk());
	}
}

