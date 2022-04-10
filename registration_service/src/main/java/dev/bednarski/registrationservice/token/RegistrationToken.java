package dev.bednarski.registrationservice.token;

import javax.validation.constraints.NotNull;
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

  private static final String SEQUENCE_NAME = "REGISTRATION_TOKEN_ID_SEQUENCE";

  @Id
  @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
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
