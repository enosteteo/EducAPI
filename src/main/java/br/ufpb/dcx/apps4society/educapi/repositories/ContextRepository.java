package br.ufpb.dcx.apps4society.educapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.ufpb.dcx.apps4society.educapi.domain.User;
import br.ufpb.dcx.apps4society.educapi.domain.Context;
import java.util.List;

@Repository
public interface ContextRepository extends JpaRepository<Context, Long> {
	List<Context> findContextsByCreator(User creator);
	Page<Context> findAllByCreatorEmailLikeAndNameStartsWithIgnoreCase(String email, String name, Pageable pageable);

	@Query("SELECT c FROM Context c JOIN c.creator u " +
			"WHERE lower(c.name) LIKE lower(CONCAT(:name, '%')) " +
			"OR u.email = :email")
	Page<Context> findAllByEmailAndNameStartsWithIgnoreCase(@Param("name") String name, @Param("email") String email, Pageable pageable);
}