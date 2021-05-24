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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_player")
@NamedQueries({
    @NamedQuery(name = Player.GET_ALL_QUERY, query = "SELECT s FROM Player s"),
    @NamedQuery(name = Player.GET_BY_INSURANCE_NUMBER, query = "SELECT s FROM Player s WHERE s.socialInsuranceNumber =:playerInsuranceNumber")
})
public class Player {

    public static final String QUALIFIER = "com.axonactive.training.player.";

    public static final String GET_ALL_QUERY = QUALIFIER + "getAll";
    
    public static final String GET_BY_INSURANCE_NUMBER = QUALIFIER + "getByDob";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 10, nullable = false, unique = true)
    private String idNumber;
    
    @Column(name = "first_name", length = 20, nullable = false)
    private String fistName;

    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Column(name = "d_o_b", columnDefinition = "DATE")
    private LocalDate dob;

    @Column(name = "socail_insurance_number" , nullable = false)
    private String socialInsuranceNumber;

    @Convert(converter = GenderPersistenceConverter.class)
    private Gender gender = Gender.UNKNOWN;

    public Player(String idNumber, String fistName, String lastName, LocalDate dob, String socialInsuranceNumber,
            Gender gender ) {
        this.idNumber = idNumber;
        this.fistName = fistName;
        this.lastName = lastName;
        this.dob = dob;
        this.socialInsuranceNumber = socialInsuranceNumber;
        this.gender = gender;
    }

    public void updatePlayer(Player newPlayer){
        this.fistName = newPlayer.fistName;
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
        return StringUtils.join(" ", fistName, lastName);
    }

    public boolean isValid(){
        return Objects.nonNull(this.idNumber)
        &&StringUtils.isNotBlank(this.getFistName())
        &&Objects.nonNull(this.socialInsuranceNumber)
        &&Objects.nonNull(this.dob);
    }

    
}
