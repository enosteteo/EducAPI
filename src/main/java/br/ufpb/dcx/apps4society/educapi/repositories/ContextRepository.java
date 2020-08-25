package br.ufpb.dcx.apps4society.educapi.repositories;

import br.ufpb.dcx.apps4society.educapi.dto.context.ContextDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.ufpb.dcx.apps4society.educapi.domain.User;
import br.ufpb.dcx.apps4society.educapi.domain.Context;
import java.util.Optional;
import java.util.List;

@Repository
public interface ContextRepository extends JpaRepository<Context, Long> {
	 List<Context> findContextsByCreator(User creator);
	 Optional<Context> findContextByName(String name);
}
