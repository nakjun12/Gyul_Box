package jeju.oneroom.messageroom.mapper;

import jeju.oneroom.message.mapper.MessageMapper;
import jeju.oneroom.messageroom.dto.MessageRoomDto;
import jeju.oneroom.messageroom.entity.MessageRoom;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MessageMapper.class)
public interface MessageRoomMapper {
    MessageRoom messageRoomPostDtoToMessageRoom(MessageRoomDto.Post postDto);

    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "senderName", source = "sender.nickname")
    @Mapping(target = "receiverId", source = "receiver.id")
    @Mapping(target = "receiverName", source = "receiver.nickname")
    MessageRoomDto.Response messageRoomToMessageRoomResponseDto(MessageRoom messageRoom);

    MessageRoomDto.SimpleResponse messageRoomToMessageRoomSimpleResponseDto(MessageRoom messageRoom);
}
