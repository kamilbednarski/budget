package dev.bednarski.registrationservice.token;

import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_REGISTRATION_TOKEN")
public class RegistrationToken {

  @Id
  @SequenceGenerator(
      name = "REGISTRATION_TOKEN_ID_SEQUENCE",
      sequenceName = "REGISTRATION_TOKEN_ID_SEQUENCE",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTRATION_TOKEN_ID_SEQUENCE")
  private Long id;
  @NotNull
  private Long userId;
  @NotNull
  private String token;
  @NotNull
  private LocalDateTime creationDateTime;
  @NotNull
  private LocalDateTime expirationDateTime;
  private LocalDateTime confirmationDateTime;
}
