package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.Application;

import hello.entities.User;
import hello.utilities.enums.GENDER;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@Transactional
public class MainControllerTests {

 @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

       @Test public void ShouldReturnUser() throws Exception {
           User u=new User(
                   "imie",
                   "nazwisko",
                   "login",
                   "nick",
                   "TajneHaslo1".toCharArray(),
                   "email@johnny.com",
                   GENDER.MALE
           );

                  mockMvc.perform(post("http://localhost:8080/goChatMe/register")
                .param("s",mapper.writeValueAsString(u) ))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").value(Matchers.instanceOf(LinkedHashMap.class)));



    }
    @Test
    public void ShouldReturnAllUsers() throws Exception {

        this.mockMvc.perform(get("http://localhost:8080/goChatMe/all"))
                              .andDo(print()).andExpect(status().isOk());

    }
    @Test
    public void ShouldReturnTrueWhenUserHasBeenLoggedSuccessfully() throws Exception {

        this.mockMvc.perform(post("http://localhost:8080/goChatMe/login")
                .param("password", "TajneHaslo1")
                .param("login", "login"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(Matchers.equalTo(true))); //returns true if validation is successful
    }
}