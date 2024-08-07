package br.com.instrumental_rental.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TB_CUSTOMER", schema = "instrumental_rental")

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends Person {

    @Column(name = "DS_ACCOUNT_BALANCE")
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "customer",  cascade = CascadeType.PERSIST)
    private List<Rental> rentals;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "STORE_ID")
    private Store store;
}
