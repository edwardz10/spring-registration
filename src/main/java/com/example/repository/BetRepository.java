package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Bet;

public interface BetRepository extends JpaRepository<Bet, Long> {
	
	@Query("select b from Bet b where product_id=:productId")
	Page<Bet> getBetsByProductId(@Param("productId") long productId, Pageable pageable);

	@Query("select b from Bet b order by timestamp desc")
	Page<Bet> getLatestBets(Pageable pageable);

}
