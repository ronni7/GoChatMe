import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import hello.Application;
import hello.entities.MessageOutputDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@Transactional
public class ChannelControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void ShouldReturnAllChannels() throws Exception {
        this.mockMvc.perform(get("https://localhost:8444/goChatMe/channel/channels"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void ShouldReturnAllMessagesByChannelID() throws Exception {
        String json = this.mockMvc.perform(get("https://localhost:8444/goChatMe/channel/messagesByChannelID?channelID=1"))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$").isArray()).andReturn().getResponse().getContentAsString();
        TypeFactory mapCollectionType = mapper.getTypeFactory();
        List<MessageOutputDTO> messageOutputDTOS = mapper.readValue(json, mapCollectionType.constructCollectionType(List.class, MessageOutputDTO.class));
        Assert.assertTrue(!messageOutputDTOS.isEmpty());

    }

}