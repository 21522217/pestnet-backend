package uit.app.com.pestnet.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author trong-khiem
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class User {

 @Id
 @GeneratedValue
 private UUID id;

 @Column(unique = true, nullable = false)
 private String username;

 @Column(unique = true, nullable = false)
 private String email;

 @Column(name = "password_hash", nullable = false)
 private String passwordHash;

 @Builder.Default
 @Column(nullable = false)
 private String role = "user";

 @Builder.Default
 @Column(name = "is_deleted", nullable = false)
 private boolean deleted = false;

 @Column(name = "created_at", updatable = false)
 @CreationTimestamp
 private LocalDateTime createdAt;
}

