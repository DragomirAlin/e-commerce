package ro.dragomiralin.repository.user.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.domain.user.UserDO;
import ro.dragomiralin.domain.user.UserPort;
import ro.dragomiralin.repository.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAdapter implements UserPort {
    private final UserRepository userRepository;

    @Override
    public long create(UserDO userDO) {
        var u = userRepository.save(userDO);
        return u.getId();
    }

    @Override
    public Optional<UserDO> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserDO> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
