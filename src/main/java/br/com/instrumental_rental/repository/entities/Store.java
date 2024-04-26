package br.com.instrumental_rental.repository.entities;


/*
@Entity
@Table(name = "TB_THEADDRESS", schema = "instrumental_rental")
@SequenceGenerator(name = "SEQ_THEADDRESS")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
 */

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_STORE", schema = "instrumental_rental")
@SequenceGenerator(name = "SEQ_STORE")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Store {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
    @Column(name = "ID_ADDRESS")
    private Long addressId;
     */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    @Column(name = "ID_STORE")
    private Long storeId;

    /*
    @Column(name = "DS_ADDRESS")
    @OneToMany(mappedBy = "person")
    private List<TheAddress> address;
     */

    @Column(name = "DS_CUSTOMER")
    @OneToMany(mappedBy = "store")
    private List<Customer> customer;


}