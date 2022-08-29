package ro.dragomiralin.ecommerce.repository.user.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;
import ro.dragomiralin.ecommerce.domain.user.port.UserPort;
import ro.dragomiralin.ecommerce.repository.user.mapper.UserDOMapper;
import ro.dragomiralin.ecommerce.repository.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAdapter implements UserPort {
    private final UserDOMapper mapper;
    private final UserRepository userRepository;

    @Override
    public long create(UserDO userDO) {
        var user = mapper.toUser(userDO);
        var u = userRepository.save(user);
        return u.getId();
    }

    @Override
    public Optional<UserDO> findById(long id) {
        return userRepository.findById(id)
                .map(mapper::toUserDO);
    }

    @Override
    public Optional<UserDO> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::toUserDO);
    }
}
