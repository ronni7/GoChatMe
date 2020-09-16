package hello.controllers;

import hello.DTO.MessageOutputDTO;
import hello.TO.PrivateChannelTO;
import hello.entities.Channel;
import hello.services.ChannelServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/goChatMe/channel")
@CrossOrigin(origins = "*")
public class ChannelController {
    private final ChannelServiceImpl channelService;

    public ChannelController(ChannelServiceImpl channelService) {
        this.channelService = channelService;
    }

    @GetMapping(path = "/lastMessagesByChannelID")
    public @ResponseBody
    List<MessageOutputDTO> getChannelMessages(@RequestParam long channelID) {
        return channelService.getChannelMessages(channelID);
    }

    @GetMapping(path = "/lastPrivateMessagesByChannelID")
    public @ResponseBody
    List<MessageOutputDTO> getPrivateChannelMessages(@RequestParam long channelID) {
        return channelService.getPrivateChannelMessages(channelID);
    }

    @GetMapping(path = "/channels")
    public @ResponseBody
    List<Channel> getChannels() {
        return channelService.getChannels();
    }

    @PostMapping(path = "/createPrivateChannel")
    public @ResponseBody
    PrivateChannelTO createPrivateChannel(long senderID, String destinationUserNickname) {
        return channelService.createPrivateChannel(senderID, destinationUserNickname);
    }

}
