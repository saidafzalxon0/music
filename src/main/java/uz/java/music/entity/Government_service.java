package uz.java.music.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Government_services")
public class Government_service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "link",nullable = false)
    private String link;

    @OneToOne(optional = false)
    @JoinColumn(name = "file_id",nullable = false,unique = true)
    private File file;

}
