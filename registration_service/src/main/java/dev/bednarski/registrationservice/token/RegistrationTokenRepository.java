package dev.bednarski.registrationservice.token;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {

  Optional<RegistrationToken> findByToken(String token);

  Optional<RegistrationToken> findByUserId(Long userId);
}
