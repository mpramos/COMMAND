package edu.dharbor.bootcamp.rickandmorty.repository.mongodb.document;

import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterGender;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterStatus;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.util.Date;

@Data
@Document(collection = "character_")
public class CharacterMongoDocument {
    @Id
    private ObjectId _id;
    @Column(name = "registrationId_")
    private Integer registrationId;
    @Field("name_")
    private String name;
    @Field("status_")
    @Enumerated(EnumType.STRING)
    private CharacterStatus status;
    @Field("species_")
    private String species;
    @Field("type_")
    private String type;
    @Field("gender_")
    @Enumerated(EnumType.STRING)
    private CharacterGender gender;
    @Field("url_")
    private String url;
    @Field("created_")
    @Temporal(TemporalType.DATE)
    private Date created;
}
