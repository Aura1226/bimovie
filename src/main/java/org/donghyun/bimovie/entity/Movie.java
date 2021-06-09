package org.donghyun.bimovie.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "tbl_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String title;

    //연관관계의 주어 - FK가 중심이기 때문에 PK는 부가적
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    //mappedBy는 ""안에 속한다. 여기선 movie // cascade = 얘가 바뀔때...  //orphanRemoval 연관관계가 없어진 객체를 자동으로 삭제할거?
    private List<Poster> posterList;

    public void addPoster(Poster poster){

        //교재 620P 방식과 다른 방식으로....@Query와 ToString에 exclude = 'poster'를 해주는 방법 자세한건 0503 영상참고
        if (posterList == null){
            posterList = new ArrayList<>();
        }
        poster.setIdx(posterList.size());
        poster.setMovie(this);
        posterList.add(poster); //주인공. 영화라는 도메인안에 영화와 영화 포스터가 들어가니까
    }

    public void removePoster(Long ino){

        Optional<Poster> result = posterList.stream().filter(p -> p.getIno() == ino).findFirst();

        result.ifPresent( p -> {
            p.setMovie(null);
            posterList.remove(p);
        });
        changeIdx();
    }

    private void changeIdx() {
        for (int i = 0; i < posterList.size(); i++){
            posterList.get(i).setIdx(i);
        }
    }
}
