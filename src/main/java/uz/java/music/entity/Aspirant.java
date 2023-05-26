package uz.java.music.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "aspirant")
public class Aspirant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "full_name",nullable = false)
    private String full_name;
    @Column(name = "about",nullable = false,length = 5000)
    private String about;
    @ManyToOne
    @JoinColumn(name = "position_id",nullable = false)
    private Position position;
    @OneToOne(optional = false)
    @JoinColumn(name = "file_id",nullable = false)
    private File file;
}
