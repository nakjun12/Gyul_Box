package jeju.oneroom.message.mapper;

import jeju.oneroom.message.dto.*;
import jeju.oneroom.message.entity.Message;
import jeju.oneroom.user.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface MessageMapper {
    Message postDtoToMessage(MessageDto.Post postDto);

    @Mapping(target = "user", source = "sender")
    MessageDto.Response messageToResponseDto(Message message);
}
