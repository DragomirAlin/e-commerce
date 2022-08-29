package ro.dragomiralin.ecommerce.domain.user.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.user.UserService;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;
import ro.dragomiralin.ecommerce.domain.user.error.UserAlreadyExistsException;
import ro.dragomiralin.ecommerce.domain.user.port.UserPort;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserPort userPort;

    @Override
    public long create(UserDO user) {
        userPort.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new UserAlreadyExistsException("User with email " + u.getEmail() + " already exists");
        });

        return userPort.create(user);
    }

    @Override
    public Optional<UserDO> findById(long id) {
        return userPort.findById(id);
    }

    @Override
    public Optional<UserDO> findByEmail(String email) {
        return userPort.findByEmail(email);
    }

    @Override
    public Optional<UserDO> findBySub(String sub) {
        return Optional.empty();
    }
}