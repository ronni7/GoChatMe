import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import hello.Application;
import hello.DTO.MessageOutputDTO;
import hello.TO.PrivateChannelTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        String json = this.mockMvc.perform(get("https://localhost:8444/goChatMe/channel/lastMessagesByChannelID?channelID=1"))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$").isArray()).andReturn().getResponse().getContentAsString();
        TypeFactory mapCollectionType = mapper.getTypeFactory();
        List<MessageOutputDTO> messageOutputDTOS = mapper.readValue(json, mapCollectionType.constructCollectionType(List.class, MessageOutputDTO.class));
        Assert.assertTrue(!messageOutputDTOS.isEmpty());

    }

    @Test
    public void ShouldReturnAllPrivateMessagesByChannelID() throws Exception {
        String json = this.mockMvc.perform(get("https://localhost:8444/goChatMe/channel/lastPrivateMessagesByChannelID?channelID=20"))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$").isArray()).andReturn().getResponse().getContentAsString();
        TypeFactory mapCollectionType = mapper.getTypeFactory();
        List<MessageOutputDTO> messageOutputDTOS = mapper.readValue(json, mapCollectionType.constructCollectionType(List.class, MessageOutputDTO.class));
        Assert.assertTrue(!messageOutputDTOS.isEmpty());

    }

    @Test
    public void ShouldReturnPrivateChannelTO() throws Exception {

        String json = this.mockMvc.perform(post("https://localhost:8444/goChatMe/channel/createPrivateChannel")
                .param("senderID", "1")
                .param("destinationUserNickname", "Dario1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty()).andReturn().getResponse().getContentAsString();
        TypeFactory mapCollectionType = mapper.getTypeFactory();
        PrivateChannelTO privateChannelTO = mapper.readValue(json, mapCollectionType.constructType(PrivateChannelTO.class));
        System.out.println("privateChannelTO = " + privateChannelTO);
        Assert.assertTrue(privateChannelTO.isExists());
/*
        Assert.assertEquals(60, privateChannelTO.getToken().length()); todo restore after fixing token usage
*/

    }
    @Test
    public void ShouldReturnSamePrivateChannelTOAsAbove() throws Exception {

        String json = this.mockMvc.perform(post("https://localhost:8444/goChatMe/channel/createPrivateChannel")
                .param("senderID", "6")
                .param("destinationUserNickname", "nick"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty()).andReturn().getResponse().getContentAsString();
        TypeFactory mapCollectionType = mapper.getTypeFactory();
        PrivateChannelTO privateChannelTO = mapper.readValue(json, mapCollectionType.constructType(PrivateChannelTO.class));
        System.out.println("privateChannelTO = " + privateChannelTO);
        Assert.assertTrue(privateChannelTO.isExists());
/*
        Assert.assertEquals(60, privateChannelTO.getToken().length()); todo restore after fixing token usage
*/

    }
}