package jeju.oneroom.user.service;

import jeju.oneroom.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    //회원 생성
    public User createUser(User user) {
        return user;
    }


    //회원 정보 수정
    public User updateUser(User user) {
        return user;
    }

    //회원 탈퇴
    public void deleteUser(long userId) {

    }

}
