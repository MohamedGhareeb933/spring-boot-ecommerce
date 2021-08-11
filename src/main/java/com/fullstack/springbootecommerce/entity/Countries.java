package com.fullstack.springbootecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="countries")
@Data @Getter @Setter
public class Countries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "sortname")
    private String sortName;
    @Column(name = "name")
    private String name;
    @Column(name = "phonecode")
    private String phoneCode;

    @OneToMany(mappedBy = "countries")
    private List<States> states;

}
