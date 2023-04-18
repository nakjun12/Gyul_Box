package jeju.oneroom.message.mapper;

import jeju.oneroom.message.dto.MessageDto;
import jeju.oneroom.message.entity.Message;
import jeju.oneroom.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    Message postDtoToMessage(MessageDto.Post postDto);

    UserDto.Response messageToResponseDto(Message message);

    List<MessageDto.Response> messagesToResponseDtos(List<Message> messages);
}
