package ru.job4j.forum.control;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.job4j.forum.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Класс PostControlTest
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@Sql(scripts = "classpath:schema.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        MockHttpServletRequestBuilder request = get("/post");

        request.param("id", "1");
        this.mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Ignore
    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageWhenDelete() throws Exception {
        MockHttpServletRequestBuilder request = get("/delete");
        request.param("id", "1");
        this.mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index?login=true"));
    }

    @Ignore
    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageWhenDeleteAnswer() throws Exception {
        MockHttpServletRequestBuilder request = get("/deleteAnswer");
        request.param("answer_id", "1").param("post_id", "1");
        this.mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/post?id=1"));
    }

}