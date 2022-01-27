package com.repository;

import com.domain.MatchingAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingRepository extends JpaRepository<MatchingAd, Integer> {

}
