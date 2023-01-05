package io.javabrains.moviecatalogueservice.resources;

import io.javabrains.moviecatalogueservice.models.Catalogitem;
import io.javabrains.moviecatalogueservice.models.Movie;
import io.javabrains.moviecatalogueservice.models.Rating;
import io.javabrains.moviecatalogueservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/{id}")
    public List<Catalogitem> getCatalog(@PathVariable("id") String id){

        //calling rating-data-services

        UserRating userRating   =   restTemplate.getForObject("http://localhost:8082/ratingsdata/user/"+id, UserRating.class);



        return userRating.getRatings().stream().map(rating -> {
            //calling Movie-info-services
            Movie movie =   restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(), Movie.class);
//            Movie movie =   webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8081/movies/"+rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();
            return new Catalogitem(movie.getName(), "description", rating.getRating());
        }).collect(Collectors.toList());


    }
}
