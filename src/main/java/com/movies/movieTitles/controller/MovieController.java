package com.movies.movieTitles.controller;

import com.movies.movieTitles.model.Error;
import com.movies.movieTitles.model.Response;
import com.movies.movieTitles.model.TransactionContext;
import com.movies.movieTitles.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("v1")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(value="get/movieTitles", produces = "application/json", method = RequestMethod.GET)
    public Object getMovieTitles(@RequestParam(value="Title", defaultValue="") String Title,
                                                   @RequestParam(value="page", required = false) String page) {
        try {
            return movieService.getMovieList(Title, page);
        } catch (Exception e) {
            return errorResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<Response> errorResponse(Exception exception, HttpStatus httpStatus){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Error error = new Error();
        error.setCode(httpStatus.toString() + "0001");
        error.setReason(exception.getMessage());
        Response response = new Response();
        response.setError(error);
        response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
        return responseEntity;
    }

}
