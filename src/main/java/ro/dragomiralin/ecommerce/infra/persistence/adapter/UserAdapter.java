package ro.dragomiralin.ecommerce.infra.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.service.ports.UserPort;
import ro.dragomiralin.ecommerce.infra.persistence.entity.User;
import ro.dragomiralin.ecommerce.infra.persistence.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAdapter implements UserPort {
    private final UserRepository userRepository;

    @Override
    public long create(User user) {
        var u = userRepository.save(user);
        return u.getId();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
