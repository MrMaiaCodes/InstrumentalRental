package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "SELECT co from Customer cu JOIN Contact co ON cu.personId = co.person " +
            " JOIN Attendant at on co.person = at.personId WHERE lower(cu.name) = lower(:nameProvided) " +
            " OR lower(at.name) = lower(:nameProvided)")
    List<Contact> findContactByNameProvided(@Param("nameProvided")String nameProvided);
}
