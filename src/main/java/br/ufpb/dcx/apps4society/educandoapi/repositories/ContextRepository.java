package br.ufpb.dcx.apps4society.educandoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpb.dcx.apps4society.educandoapi.domain.Context;


@Repository
public interface ContextRepository extends JpaRepository<Context, Integer> {
	
}
