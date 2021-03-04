package com.example.demo.web;

import com.example.demo.core.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/search")
public class SearchController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> getMovieByQuery(@RequestParam(name = "q") String query) {

        return movieService.search(query);
    }

    @GetMapping("/recommend-movie")
    public Movie getRecommendMovie() {

        return movieService.recommendTodayMovie();
    }
}
