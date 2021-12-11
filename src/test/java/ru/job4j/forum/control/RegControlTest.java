package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Класс RegControlTest
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@Sql(scripts = "classpath:schema.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RegControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageWhenSaveUser() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "Ivan")
                        .param("password", "123"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

}