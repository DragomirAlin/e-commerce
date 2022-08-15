package ro.dragomiralin.ecommerce.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.service.UserService;
import ro.dragomiralin.ecommerce.domain.service.ports.UserPort;
import ro.dragomiralin.ecommerce.infra.api.rest.error.ServiceException;
import ro.dragomiralin.ecommerce.infra.persistence.entity.User;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserPort userPort;

    @Override
    public long create(User user) {
        userPort.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new ServiceException("User with email " + u.getEmail() + " already exists");
        });

        return userPort.create(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return userPort.findById(id);
    }
}
