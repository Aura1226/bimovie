package org.donghyun.bimovie.repository;

import org.donghyun.bimovie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {


}
