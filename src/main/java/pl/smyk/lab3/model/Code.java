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
    public Long id;
    @Column(name = "post_code")
    public String postCode;
    @Column(name = "adress")
    public String adress;
    @Column(name = "palce")
    public String place;
    @Column(name = "voivoship")
    public String voivoship;
    @Column(name = "county")
    public String county;
}
