package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.interfaces.IAttendantRepository;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AttendantService implements IAttendantService {

    @Autowired
    private IAttendantRepository attendantRepository;



    @Override
    public List<Attendant> findAttendantByNumberProvided(String numberProvided) throws AttendantNotFoundException {
        var attendantSought = attendantRepository.findAttendantByNumberProvided(numberProvided);
        if (attendantSought.isEmpty()) {
            throw new AttendantNotFoundException("A01", "Attendant not found");
        }
        return attendantSought;
    }

    @Override
    public Attendant findAttendantById(Long id) throws AttendantNotFoundException {
        return attendantRepository.findById(id).orElseThrow(
                () -> new AttendantNotFoundException("A01", "Attendant not found"));
    }

    @Override
    public Attendant save(Attendant attendant) {
        return attendantRepository.save(attendant);
    }

    @Override
    public void delete(Attendant attendant) throws AttendantNotFoundException {

        attendantRepository.delete(findAttendantById(attendant.getAttendantId()));

    }

    @Override
    public List<Attendant> findAll() {
        return attendantRepository.findAll();
    }

    @Override
    public Attendant update(Attendant attendant) throws AttendantNotFoundException {
        var attendantToUpdate = findAttendantById(attendant.getAttendantId());
        attendantToUpdate.setName(attendant.getName());
        attendantToUpdate.setContacts(attendant.getContacts());
        attendantToUpdate.setDateOfBirth(attendant.getDateOfBirth());
        attendantToUpdate.setSocialSecurityNumber(attendant.getSocialSecurityNumber());
        attendantToUpdate.setDriversLicenseNumber(attendant.getDriversLicenseNumber());
        attendantRepository.save(attendantToUpdate);
        return attendantToUpdate;
    }
}
