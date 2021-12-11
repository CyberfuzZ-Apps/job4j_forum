package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Answer;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

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

    @MockBean
    private PostService postService;

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

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageWhenSaveOrEditPost() throws Exception {
        this.mockMvc.perform(post("/save")
                        .param("name", "Куплю ладу-грант. Дорого.")
                        .param("author", "user"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(postService).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageWhenSaveAnswerToPost() throws Exception {
        this.mockMvc.perform(post("/saveAnswer")
                        .param("answerName", "Ответ")
                        .param("post_id", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/post?id=1"));
        ArgumentCaptor<Answer> argumentAnswer = ArgumentCaptor.forClass(Answer.class);
        ArgumentCaptor<Integer> argumentInt = ArgumentCaptor.forClass(Integer.class);
        verify(postService).addAnswerToPost(argumentInt.capture(), argumentAnswer.capture());
        assertThat(argumentAnswer.getValue().getAnswerName(), is("Ответ"));
    }

}