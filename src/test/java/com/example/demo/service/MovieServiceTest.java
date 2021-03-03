package com.example.demo.service;

import com.example.demo.client.MovieRepositoryImpl;
import com.example.demo.config.NaverProperties;
import com.example.demo.core.Movie;
import com.example.demo.core.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    //Mock 클래스 직접 생성하는 방법
    @DisplayName("평점 순으로 정렬되는지 검증")
    @Test
    void shouldSortedInOrderOfGrade() {

        //given
        var query = "테스트_쿼리";
        var expectedTopRankingMovieTitle = "영화3";
        MovieRepository movieRepository = new MockMovieRepositoryImpl(null, null);
        MovieService movieService = new MovieService(movieRepository);

        //when
        var actualMovies = movieService.search(query);

        //then
        assertEquals(expectedTopRankingMovieTitle, actualMovies.stream().findFirst().get().getTitle());
    }

    class MockMovieRepositoryImpl extends MovieRepositoryImpl {

        public MockMovieRepositoryImpl(RestTemplate restTemplate, NaverProperties naverProperties) {
            super(restTemplate, naverProperties);
        }

        @Override
        public List<Movie> findByQuery(final String query) {
            return Arrays.asList(
                    Movie.builder().title("영화1").link("http://test").userRating(9.3f).build(),
                    Movie.builder().title("영화2").link("http://test").userRating(8.3f).build(),
                    Movie.builder().title("영화3").link("http://test").userRating(9.7f).build()
            );
        }
    }

    // Mockito를 사용하는 방법
    @Mock
    private MovieRepository movieRepository;

    @DisplayName("평점 순으로 정렬되는 검증")
    @Test
    void shouldSortedInOrderOfGrade_02 () {

        //given
        var query = "테스트_쿼리";
        var expectedTopRankingMovieTitle = "영화3";
        given(movieRepository.findByQuery(anyString())).willReturn(getStubMovies());
        MovieService movieService = new MovieService(movieRepository);

        //when
        var actualMovies = movieService.search(query);

        //then
        assertEquals(expectedTopRankingMovieTitle, actualMovies.stream().findFirst().get().getTitle());
    }

    List<Movie> getStubMovies() {
        return Arrays.asList(
                Movie.builder().title("영화1").link("http://test").userRating(9.3f).build(),
                Movie.builder().title("영화2").link("http://test").userRating(8.3f).build(),
                Movie.builder().title("영화3").link("http://test").userRating(9.7f).build()
        );
    }
}
