package com.reddit.repository;

import com.reddit.model.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubRedditRepository extends JpaRepository<SubReddit, Long> {
}
