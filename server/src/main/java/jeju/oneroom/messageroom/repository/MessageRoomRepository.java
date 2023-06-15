package jeju.oneroom.messageroom.repository;

import jeju.oneroom.messageroom.entity.MessageRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {

    @Query("SELECT mr FROM MessageRoom mr WHERE (mr.sender.id = :senderId AND mr.receiver.id = :receiverId) " +
            "OR (mr.sender.id = :receiverId AND mr.receiver.id = :senderId)")
    MessageRoom findMessageRoom(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

    @Query("SELECT mr FROM MessageRoom mr WHERE mr.sender.id = :userId OR mr.receiver.id = :userId")
    Page<MessageRoom> findMessageRoomsBySenderIdOrReceiverId(@Param("userId") Long userId, Pageable pageable);
}
