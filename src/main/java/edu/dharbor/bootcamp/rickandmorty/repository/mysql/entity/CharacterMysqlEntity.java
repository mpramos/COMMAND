package edu.dharbor.bootcamp.rickandmorty.repository.mysql.entity;

import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterGender;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "character_")
public class CharacterMysqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_", nullable = false)
    private Long id;
    @Column(name = "registrationId_")
    private Integer registrationId;
    @Column(name = "name_")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_")
    private CharacterStatus status;
    @Column(name = "species_")
    private String species;
    @Column(name = "type_")
    private String type;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender_")
    private CharacterGender gender;
    @Column(name = "url_")
    private String url;
    @Column(name = "created_")
    @Temporal(TemporalType.DATE)
    private Date created;
}
