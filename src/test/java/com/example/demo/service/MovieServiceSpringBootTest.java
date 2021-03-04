package com.example.demo.service;

import com.example.demo.core.Movie;
import com.example.demo.core.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = MovieService.class)
public class MovieServiceSpringBootTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @DisplayName("기본 검색에서 평점 순으로 정렬되었는지 검증")
    @Test
    void shouldSortedInOrderOfGrade() {

        //given
        var query = "테스트_쿼리";
        var expectedTopRankingMovieTile = "평점1위";    //평점이 제일 높을 것으로 예상되는 영화의 제목
        given(movieRepository.findByQuery(anyString())).willReturn(getStubMovies());

        //when
        var actualMovies = movieService.search(query);

        //then
        assertEquals(expectedTopRankingMovieTile, actualMovies.stream().findFirst().get().getTitle());
    }

    List<Movie> getStubMovies() {
        return Arrays.asList(
                Movie.builder().title("평점0").link("http://test").userRating(0.0f).build(),
                Movie.builder().title("평점2위").link("http://test").userRating(9.3f).build(),
                Movie.builder().title("평점3위").link("http://test").userRating(8.7f).build(),
                Movie.builder().title("평점1위").link("http://test").userRating(9.7f).build()
        );
    }
}

