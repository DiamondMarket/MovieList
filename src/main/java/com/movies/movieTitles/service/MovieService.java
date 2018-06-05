package com.movies.movieTitles.service;

import com.movies.movieTitles.model.MovieList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class MovieService {

    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;


    public List<String> getMovieList(final String movieTitle, final String pageNumber) throws Exception {

        restTemplate = new RestTemplate();
        String url = environment.getProperty("movieUrl");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);


        builder.queryParam("Title", movieTitle);
        if (pageNumber != null) {
            builder.queryParam("page", pageNumber);
        }

        ResponseEntity<MovieList> movieListEntity =
                restTemplate.getForEntity(builder.build().toUriString(), MovieList.class);

        MovieList movieList = movieListEntity.getBody();
        List<String> stringList = new ArrayList<>();
        movieList.getData().stream().forEach(movie -> {
            stringList.add(movie.getTitle());
        });
        Collections.sort(stringList);

        return stringList;


    }

}
