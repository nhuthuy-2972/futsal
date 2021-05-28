package com.axonactive.training.player;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.axonactive.training.team.Team;

import org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_player")
@NamedQueries({
    @NamedQuery(name = Player.GET_ALL_QUERY, query = "SELECT s FROM Player s"),
    @NamedQuery(name = Player.GET_BY_INSURANCE_NUMBER, query = "SELECT s FROM Player s WHERE s.socialInsuranceNumber = :playerInsuranceNumber"),
    @NamedQuery(name = Player.GET_BY_FIRST_NAME, query = "SELECT s FROM Player s WHERE s.firstName = :firstName")
})
public class Player {

    public static final String QUALIFIER = "com.axonactive.training.player.";

    public static final String GET_ALL_QUERY = QUALIFIER + "getAll";
    
    public static final String GET_BY_INSURANCE_NUMBER = QUALIFIER + "getByInsurance";

    public static final String GET_BY_FIRST_NAME = QUALIFIER + "getByFirstName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "code", length = 10, nullable = false, unique = true)
    // private String idNumber;
    
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Column(name = "d_o_b", columnDefinition = "DATE")
    private LocalDate dob;

    @InsuraneUniqued 
    @Column(name = "socail_insurance_number" , nullable = false,unique = true)
    private String socialInsuranceNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "play_for_id",nullable = false)
    private Team playFor;

    @Convert(converter = GenderPersistenceConverter.class)
    private Gender gender = Gender.UNKNOWN;

    public Player( String fistName, String lastName, LocalDate dob, String socialInsuranceNumber,
            Gender gender ) {
        this.firstName = fistName;
        this.lastName = lastName;
        this.dob = dob;
        this.socialInsuranceNumber = socialInsuranceNumber;
        this.gender = gender;
    }

    public void updatePlayer(Player newPlayer){
        this.firstName = newPlayer.firstName;
        this.lastName = newPlayer.lastName;
        this.dob = newPlayer.dob;
        this.socialInsuranceNumber = newPlayer.socialInsuranceNumber;
        this.gender = newPlayer.gender;
    }

    public int getAge(){
        if(Objects.isNull(this.dob)){
            throw new IllegalArgumentException("Date of birth of the player is missing");
        }

        LocalDate currentDate = LocalDate.now();
        return Period.between(this.dob, currentDate).getYears();
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public boolean isValid1(){
        return 
        StringUtils.isNotBlank(this.getFirstName())
        &&StringUtils.isNotBlank(this.getLastName())
        &&StringUtils.isNotBlank(this.socialInsuranceNumber)
        &&Objects.nonNull(this.dob);
    }
}
