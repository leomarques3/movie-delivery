package com.chassot.repositories;

import com.chassot.commons.dto.MoviesAvailableDto;
import com.chassot.commons.enumerations.MovieStatusEnum;
import com.chassot.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select new com.chassot.commons.dto.MoviesAvailableDto(a.title, b.name, count(1)) from movie a, movie_director b where a.status = :status and a.movieDirector = b.id group by a.title")
    List<MoviesAvailableDto> findByStatus(@Param("status") MovieStatusEnum status);

    Movie findByIdAndStatus(Long id, MovieStatusEnum status);

    List<Movie> findByTitleContaining(String title);

}
