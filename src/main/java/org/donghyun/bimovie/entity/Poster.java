package org.donghyun.bimovie.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString(exclude = "movie")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String fname;

    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    public void setIdx(int idx){
        this.idx =  idx;
    }

    public void setMovie(Movie movie){
        this.movie = movie;
    }
}
