package com.example.demo.service;

import com.example.demo.core.Movie;
import com.example.demo.core.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> search(final String query) {

        return movieRepository.findByQuery(query);
    }
}
