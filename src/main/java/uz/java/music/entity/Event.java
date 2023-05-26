package uz.java.music.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title",nullable = false,length = 5000)
    private String title;

    @Column(name = "body",nullable = false,length = 10000)
    private String body;

    @Column(name = "date",nullable = false)
    private LocalDateTime date;

    @Column(name = "start_time",nullable = false,length = 5)
    private String start_time;

    @Column(name = "end_time",nullable = false,length = 5)
    private String end_time;

    @Column(name = "location",nullable = false,length = 500)
    private String location;

    @OneToOne(optional = false)
    @JoinColumn(name = "file_id",nullable = false,unique = true)
    private File file;

}
