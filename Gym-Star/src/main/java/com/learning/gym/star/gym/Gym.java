package com.learning.gym.star.gym;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.gym.star.trainer.trainerdb.TrainerDB;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "Gym")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gym_id")
    private String gymId;

    @Column(name = "gym_name")
    @NotEmpty
    @Length(min = 4, message = "Gym name must have at least 4 characters")
    private String gymName;

    @Column(name = "street")
    @NotEmpty
    private String street;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "building_number")
    @NotEmpty
    private String buildingNumber;

    @ManyToMany(mappedBy = "gym")
    private List<TrainerDB> trainers;

    //This field was created to simulate some property not passed to database for learning reason.
    @Transient
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private String auxiliary;

    public String[] toStringArray(){
        return new String[] {gymId, gymName, street, city, buildingNumber};
    }
}
