package jeju.oneroom.message.repository;

import jeju.oneroom.message.entity.Message;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverAndSender(User receiver, User sender);

    Page<Message> findByReceiver(User receiver, Pageable pageable);
}
