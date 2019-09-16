import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import hello.Application;
import hello.entities.User;
import hello.utilities.enums.GENDER;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
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
    @Test
    public void ShouldReturnUser() throws Exception {
        User user = new User(
                "imieee",
                "nazwiskaso",
                "SpecjalnyTestowy",
                "Nickname",
                "hasloJestTajne".toCharArray(),
                "emaissl@johnny.com",
                GENDER.MALE

        );
        mockMvc.perform(post("https://localhost:8444/goChatMe/register").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user).getBytes()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").value(Matchers.instanceOf(LinkedHashMap.class)));


    }
    @Test
    public void ShouldReturnAllUsers() throws Exception {

        this.mockMvc.perform(get("https://localhost:8444/goChatMe/all"))
                              .andDo(print()).andExpect(status().isOk());

    }
    @Test
    public void ShouldReturnTrueWhenUserHasBeenLoggedSuccessfully() throws Exception {

        String json =  this.mockMvc.perform(post("https://localhost:8444/goChatMe/login")
                .param("password", "TajneHaslo1")
                .param("login", "login"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty()).andReturn().getResponse().getContentAsString();
        TypeFactory mapCollectionType;
        mapCollectionType = mapper.getTypeFactory();
        User user =  mapper.readValue(json, mapCollectionType.constructType(User.class));
        Assert.assertEquals(1, (long) user.getId());

    }
}