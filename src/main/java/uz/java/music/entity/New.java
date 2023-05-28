package uz.java.music.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "news")
public class New {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title",nullable = false,length = 5000)
    private String title;

    @Column(name = "body",nullable = false,length = 10000)
    private String body;
    @Column(name = "who_from",nullable = false,length = 5000)
    private String who_from;

    @Column(name = "date",nullable = false)
    private Date date;

    @OneToOne(optional = false)
    @JoinColumn(name = "file_id",nullable = false,unique = true)
    private File file;
}
