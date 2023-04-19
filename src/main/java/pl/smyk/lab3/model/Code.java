package pl.smyk.lab3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Code")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Code {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "adress")
    private String adress;
    @Column(name = "place")
    private String place;
    @Column(name = "voivoship")
    private String voivoship;
    @Column(name = "county")
    private String county;
}
