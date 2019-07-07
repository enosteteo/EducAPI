package br.ufpb.dcx.apps4society.educapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpb.dcx.apps4society.educapi.domain.Challenge;;


@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
	
}
