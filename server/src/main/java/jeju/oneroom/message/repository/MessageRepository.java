package jeju.oneroom.message.repository;

import jeju.oneroom.message.entity.Message;
import jeju.oneroom.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
