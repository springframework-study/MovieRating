package com.example.demo.core;

import com.example.demo.core.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByQuery(String query);
}
