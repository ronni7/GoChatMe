package hello.controllers;

import hello.entities.Channel;
import hello.entities.MessageOutputDTO;
import hello.entities.PrivateChannelTO;
import hello.services.ChannelServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/goChatMe/channel")
@CrossOrigin(origins = "/*")
public class ChannelController {
    private final ChannelServiceImpl channelService;

    public ChannelController(ChannelServiceImpl channelService) {
        this.channelService = channelService;
    }

    @GetMapping(path = "/messagesByChannelID")
    public @ResponseBody
    List<MessageOutputDTO> getChannelMessages(@RequestParam long channelID) {
        return channelService.getChannelMessages(channelID);
    }

    @GetMapping(path = "/channels")
    public @ResponseBody
    List<Channel> getChannels() {
        return channelService.getChannels();
    }

    @PostMapping(path = "/createPrivateChannel")
    public @ResponseBody
    PrivateChannelTO createPrivateChannel(String user1ID, String user2ID) {
        return channelService.createPrivateChannel(user1ID, user2ID);
    }

}
