package jeju.oneroom.message.service;

import jeju.oneroom.message.dto.MessageDto;
import jeju.oneroom.message.entity.Message;
import jeju.oneroom.message.mapper.MessageMapper;
import jeju.oneroom.message.repository.MessageRepository;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserRepository userRepository;

    //메시지 생성
    public MessageDto.Response createMessage(MessageDto.Post postDto) {
        Message saveMessage = messageRepository.save(messageMapper.postDtoToMessage(postDto));
        return messageMapper.messageToResponseDto(saveMessage);
    }

    //채팅방 목록 가져오기
    public Page<MessageDto.Response> findMessageList(long receiverId, int page, int size) {
        User receiver = userRepository.findById(receiverId).orElse(null);
        Page<MessageDto.Response> messages = messageRepository.findByReceiver(receiver, PageRequest.of(page - 1, size, Sort.by("createdAt").descending()))
                .map(m -> messageMapper.messageToResponseDto(m));
        return messages;
    }

    //주고 받은 메시지 가져오기
    public List<MessageDto.Response> findAllMessages(long receiverId, long senderId) {
        User receiver = userRepository.findById(receiverId).orElse(null);
        User sender = userRepository.findById(senderId).orElse(null);
        readMessage(sender, receiver); // 메시지 읽음 처리
        List<Message> messages = messageRepository.findByReceiverAndSender(receiver, sender);
        List<MessageDto.Response> result = messages.stream().map(m -> messageMapper.messageToResponseDto(m)).collect(Collectors.toList());
        return result;
    }

    //메시지 삭제
    public void deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    // 메시지 읽음 유뮤 메서드
    public void readMessage(User sender, User receiver) {

    }
}
