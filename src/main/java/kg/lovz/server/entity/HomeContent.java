package kg.lovz.server.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class HomeContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String header;
    @Column(columnDefinition = "TEXT")
    String text;
    String photoUrl;
    String photoAltText;
}
