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
@SequenceGenerator(schema = "instrumental_rental", name = "SEQ_STORE", sequenceName = "SEQ_STORE", allocationSize = 1)

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    @Column(name = "ID_STORE")
    private Long storeId;

    @Column(name = "DS_NAME")
    private String name;

    @Column(name = "DS_CUSTOMER")
    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST)
    private List<Customer> customers;

    @Column(name = "DS_ATTENDANT")
    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST)
    private List<Attendant> attendants;

    @Column(name = "DS_INSTRUMENT")
    @OneToMany(mappedBy = "store",  cascade = CascadeType.MERGE)
    private List<Instrument> instruments;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ADDRESS_ID")
    private TheAddress theAddress;

    @Column(name = "DS_CONTACTS")
    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST)
    private List<Contact> contacts;


}
