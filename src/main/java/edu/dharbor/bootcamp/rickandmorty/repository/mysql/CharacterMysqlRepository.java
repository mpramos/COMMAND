package edu.dharbor.bootcamp.rickandmorty.repository.mysql;

import edu.dharbor.bootcamp.rickandmorty.repository.mysql.entity.CharacterMysqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterMysqlRepository extends JpaRepository<CharacterMysqlEntity, Integer> {
    Optional<CharacterMysqlEntity> findByRegistrationId(@NonNull Integer characterId);

    @Query(value = "SELECT A FROM CharacterMysqlEntity A WHERE LOWER(A.name) LIKE LOWER(?1) AND LOWER(A.status) LIKE LOWER(?2) AND LOWER(A.species) LIKE LOWER(?3) AND LOWER(A.gender) LIKE LOWER(?4)")
    List<CharacterMysqlEntity> findBy(@NonNull String name, @NonNull String status, @NonNull String species, @NonNull String gender);
}
