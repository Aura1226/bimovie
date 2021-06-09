package org.donghyun.bimovie.repository;

import org.donghyun.bimovie.entity.Movie;
import org.donghyun.bimovie.entity.Poster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void insertMovies(){

        Movie movie = Movie.builder().title("귀멸의 칼날").build();

        Poster p1 = Poster.builder().fname("귀멸의 칼날 포스터1.jpg").build();
        Poster p2 = Poster.builder().fname("귀멸의 칼날 포스터2.jpg").build();

        movie.addPoster(p1);
        movie.addPoster(p2);

        movieRepository.save(movie);

    }

    @Test
    public void addPoster(){

        Movie movie = movieRepository.findById(1L).get(); //optional이라 get으로 꺼내준다

        movie.addPoster(Poster.builder().fname("3번 포스터").build());

        movieRepository.save(movie);
    }

    @Test
    @Transactional
    @Commit
    public void testRemovePoster() {

        Movie movie = movieRepository.getOne(1L);

        movie.removePoster(2L);

        movieRepository.save(movie);

    }

//    @Test
//    public void insertMovies() {
//
//        IntStream.rangeClosed(10,100).forEach(i -> {
//            Movie movie = Movie.builder().title("세계 명작"+i).build();
//            movie.addPoster(Poster.builder().fname("세계 명작"+i+" 포스터1.jpg").build());
//            movie.addPoster(Poster.builder().fname("세계 명작"+i+" 포스터2.jpg").build());
//
//            movieRepository.save(movie);
//        });
//    }

    @Test
    public void testPaging1() { //이 방식으로 하면 망가진다

        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());

        Page<Movie> result = movieRepository.findAll(pageable);


        result.getContent().forEach(m -> {
            System.out.println(m.getMno());
            System.out.println(m.getTitle());
            System.out.println(m.getPosterList().size());
            System.out.println("----------------------------------");
        });
    }

//    @Test
//    public void testPaging2All() {
//
//        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());
//
//        Page<Movie> result = movieRepository.findAll2(pageable);
//
//
//        result.getContent().forEach(m -> {
//            System.out.println(m.getMno());
//            System.out.println(m.getTitle());
//            System.out.println(m.getPosterList());
//            System.out.println("----------------------------------");
//        });
//    }
//
//    @Test
//    public void testPaging3All() {
//
//        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());
//
//        Page<Object[]> result = movieRepository.findAll3(pageable);
//
//
//        result.getContent().forEach(arr -> {
//            log.info(Arrays.toString(arr));
//            log.info("----------------------------------");
//        });
//    }
}
