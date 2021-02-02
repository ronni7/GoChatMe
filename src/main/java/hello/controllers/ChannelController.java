package hello.controllers;

import hello.DTO.MessageOutputDTO;
import hello.TO.PrivateChannelTO;
import hello.entities.Channel;
import hello.requestBody.CreatePrivateChannelRequestBody;
import hello.services.ChannelServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/goChatMe/channel")
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

    @GetMapping(path = "/channelsByName")
    public @ResponseBody
    List<Channel> getChannelsByName(@RequestParam String name) {
        return channelService.getChannelsByName(name);
    }

    @PostMapping(path = "/createPrivateChannel")
    public @ResponseBody
    PrivateChannelTO createPrivateChannel(@RequestBody CreatePrivateChannelRequestBody createPrivateChannelRequestBody) {
        return channelService.createPrivateChannel(createPrivateChannelRequestBody.getSenderID(), createPrivateChannelRequestBody.getDestinationUserNickname());
    }

    @PostMapping(path = "/addChannel")
    public @ResponseBody
    Channel addChannel(@RequestBody Channel channel) {
        return channelService.addChannel(channel);
    }
    @PostMapping(path = "/accept")
    public @ResponseBody
    void accept(@RequestBody String token) {
        channelService.accept(token);
    }

}
