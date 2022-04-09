package dev.bednarski.userservice.user;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_APP_USER")
public class User {

  @Id
  @SequenceGenerator(
      name = "APP_USER_ID_SEQUENCE", sequenceName = "APP_USER_ID_SEQUENCE", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_USER_ID_SEQUENCE")
  private Long id;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @NotNull
  private String username;
  @NotNull
  private String email;
  @NotNull
  private String password;

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    User user = (User) o;
    return id != null && Objects.equals(id, user.id);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
