package com.example.desafiobackend.repository;

import com.example.desafiobackend.model.Event;
import com.example.desafiobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}

