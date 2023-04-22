package edu.dharbor.bootcamp.rickandmorty.repository.mongodb;

import edu.dharbor.bootcamp.rickandmorty.repository.mongodb.document.CharacterMongoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterMongoRepository extends MongoRepository<CharacterMongoDocument, Integer> {
    @Query("{ 'registrationId': ?0 }")
    Optional<CharacterMongoDocument> findByRegistrationId(@NonNull Integer characterId);

    @Query("{$and:[{name_:{$regex:?0,$options:'i'}},{status_:{$regex:?1,$options:'i'}},{species_:{$regex:?2, $options:'i'}}, {gender_:{$regex:?3, $options:'i'}}]}")
    List<CharacterMongoDocument> findBy(@NonNull String name, @NonNull String status, @NonNull String species, @NonNull String gender);
}
