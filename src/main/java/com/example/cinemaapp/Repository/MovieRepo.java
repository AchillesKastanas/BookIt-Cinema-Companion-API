package com.example.cinemaapp.Repository;

import com.example.cinemaapp.Model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movies, Integer> {
}
