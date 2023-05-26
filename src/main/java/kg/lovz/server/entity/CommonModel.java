package kg.lovz.server.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CommonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String header;
    @Column(columnDefinition = "TEXT")
    String text;
    String photoUrl;
    String photoAltText;
}
