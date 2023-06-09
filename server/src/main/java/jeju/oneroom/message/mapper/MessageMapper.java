package jeju.oneroom.message.mapper;

import jeju.oneroom.message.dto.MessageDto;
import jeju.oneroom.message.entity.Message;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    Message messagePostDtoToMessage(MessageDto.Post messagePostDto);

    @Mapping(target = "messageRoomId", source = "messageRoom.messageRoomId")
    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "senderName", source = "sender.nickname")
    @Mapping(target = "receiverId", source = "receiver.id")
    @Mapping(target = "receiverName", source = "receiver.nickname")
    MessageDto.Response messageToMessageResponseDto(Message message);
}
