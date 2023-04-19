package jeju.oneroom.message.repository;

import jeju.oneroom.message.entity.Message;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findByReceiver(User user, Pageable pageable);
}
