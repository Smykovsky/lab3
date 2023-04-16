package pl.smyk.lab3.model;

import javax.persistence.*;

@Entity
public class Code {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "adress")
    private String adress;
    @Column(name = "palce")
    private String place;
    @Column(name = "voivoship")
    private String voivoship;
    @Column(name = "county")
    private String county;

}
