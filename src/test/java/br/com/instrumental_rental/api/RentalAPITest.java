package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IRentalMapper;
import br.com.instrumental_rental.controller.api.RentalAPI;
import br.com.instrumental_rental.controller.dto.responses.responses.RentalListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.RentalResponseDTO;
import br.com.instrumental_rental.exceptions.*;
import br.com.instrumental_rental.models.*;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RentalAPITest {

    @Mock
    ICustomerService customerService;

    @Mock
    IInstrumentService instrumentService;

    @Mock
    IAttendantService attendantService;

    @Mock
    IRentalService rentalService;

    @Mock
    IRentalMapper rentalMapper;

    @InjectMocks
    RentalAPI rentalAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSuccess() throws CustomerNotFoundException, AttendantNotFoundException, InstrumentNotFoundException,
            EndDateNotAfterStartDateException, WithdrawalGreaterThanBalanceException, StoreNotFoundException, RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rentalNoId = RentalBuilder.rentalBuilderBeforeSave(instrument, attendant);
        var rental = RentalBuilder.rentalBuilder(instrument, attendant);
        var rentalDTONoId = RentalDTOBuilder.rentalDTOBuilderBeforeSave(
                customer.getPersonId(), instrument.getInstrumentId(), attendant.getPersonId());
        var rentalDTO = RentalDTOBuilder.rentalDTOBuilder(customer.getPersonId(),
                instrument.getInstrumentId(), attendant.getPersonId());
        when(rentalMapper.convertToEntity(rentalDTONoId)).thenReturn(rentalNoId);
        when(rentalService.save(rentalNoId, customer.getPersonId())).thenReturn(rental);
        when(rentalMapper.convertToDTO(rental)).thenReturn(rentalDTO);
        RentalResponseDTO result = rentalAPI.add(rentalDTONoId);
        Assertions.assertEquals(RentalResponseDTO.builder().data(rentalDTO).build(), result);
    }
    
    @Test
    void saveCustomerNotFoundException() throws CustomerNotFoundException, AttendantNotFoundException, InstrumentNotFoundException,
            EndDateNotAfterStartDateException, WithdrawalGreaterThanBalanceException, StoreNotFoundException, RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rentalNoId = RentalBuilder.rentalBuilderBeforeSave(instrument, attendant);
        var rentalDTONoId = RentalDTOBuilder.rentalDTOBuilderBeforeSave(
                customer.getPersonId(), instrument.getInstrumentId(), attendant.getPersonId());
        when(rentalMapper.convertToEntity(rentalDTONoId)).thenReturn(rentalNoId);
        when(rentalService.save(rentalNoId, customer.getPersonId())).thenThrow(new CustomerNotFoundException("C01", "Customer not found"));
        CustomerNotFoundException thrown = Assertions.assertThrows(CustomerNotFoundException.class, () ->
        {
            rentalAPI.add(rentalDTONoId);
        });
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }

    @Test
    void saveAttendantNotFoundException() throws CustomerNotFoundException, AttendantNotFoundException, InstrumentNotFoundException,
            EndDateNotAfterStartDateException, WithdrawalGreaterThanBalanceException, StoreNotFoundException, RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rentalNoId = RentalBuilder.rentalBuilderBeforeSave(instrument, attendant);
        var rentalDTONoId = RentalDTOBuilder.rentalDTOBuilderBeforeSave(
                customer.getPersonId(), instrument.getInstrumentId(), attendant.getPersonId());
        when(rentalMapper.convertToEntity(rentalDTONoId)).thenReturn(rentalNoId);
        when(rentalService.save(rentalNoId, customer.getPersonId())).thenThrow(new AttendantNotFoundException("A01", "Attendant not found"));
        AttendantNotFoundException thrown = Assertions.assertThrows(AttendantNotFoundException.class, () ->
        {
            rentalAPI.add(rentalDTONoId);
        });
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Attendant not found", thrown.getMessage());
    }

    @Test
    void saveInstrumentNotFoundException() throws CustomerNotFoundException, AttendantNotFoundException, InstrumentNotFoundException,
            EndDateNotAfterStartDateException, WithdrawalGreaterThanBalanceException, StoreNotFoundException, RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rentalNoId = RentalBuilder.rentalBuilderBeforeSave(instrument, attendant);
        var rentalDTONoId = RentalDTOBuilder.rentalDTOBuilderBeforeSave(
                customer.getPersonId(), instrument.getInstrumentId(), attendant.getPersonId());
        when(rentalMapper.convertToEntity(rentalDTONoId)).thenReturn(rentalNoId);
        when(rentalService.save(rentalNoId, customer.getPersonId())).thenThrow(new InstrumentNotFoundException("I01", "Instrument not found"));
        InstrumentNotFoundException thrown = Assertions.assertThrows(InstrumentNotFoundException.class, () ->
        {
            rentalAPI.add(rentalDTONoId);
        });
        Assertions.assertEquals("I01", thrown.getCode());
        Assertions.assertEquals("Instrument not found", thrown.getMessage());
    }

    @Test
    void saveWithdrawalGreaterThanBalanceException() throws CustomerNotFoundException,
            AttendantNotFoundException, InstrumentNotFoundException,
            EndDateNotAfterStartDateException, WithdrawalGreaterThanBalanceException, StoreNotFoundException, RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rentalNoId = RentalBuilder.rentalBuilderBeforeSave(instrument, attendant);
        var rentalDTONoId = RentalDTOBuilder.rentalDTOBuilderBeforeSave(
                customer.getPersonId(), instrument.getInstrumentId(), attendant.getPersonId());
        when(rentalMapper.convertToEntity(rentalDTONoId)).thenReturn(rentalNoId);
        when(rentalService.save(rentalNoId, customer.getPersonId()))
                .thenThrow(new WithdrawalGreaterThanBalanceException("R02", "Withdrawal greater than balance"));
        WithdrawalGreaterThanBalanceException thrown = Assertions.assertThrows(
                WithdrawalGreaterThanBalanceException.class, () -> {
                    rentalAPI.add(rentalDTONoId);
                }
        );
        Assertions.assertEquals("R02", thrown.getCode());
        Assertions.assertEquals("Withdrawal greater than balance", thrown.getMessage());
    }

    @Test
    void saveEndDateNotAfterStartDateException() throws CustomerNotFoundException,
            AttendantNotFoundException, InstrumentNotFoundException,
            EndDateNotAfterStartDateException, WithdrawalGreaterThanBalanceException, StoreNotFoundException, RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rentalNoId = RentalBuilder.rentalBuilderBeforeSave(instrument, attendant);
        var rentalDTONoId = RentalDTOBuilder.rentalDTOBuilderBeforeSave(
                customer.getPersonId(), instrument.getInstrumentId(), attendant.getPersonId());
        when(rentalMapper.convertToEntity(rentalDTONoId)).thenReturn(rentalNoId);
        when(rentalService.save(rentalNoId, customer.getPersonId()))
                .thenThrow(new EndDateNotAfterStartDateException("R03", "End date before start date"));
        EndDateNotAfterStartDateException thrown = Assertions.assertThrows(
                EndDateNotAfterStartDateException.class, () -> {
                    rentalAPI.add(rentalDTONoId);
                }
        );
        Assertions.assertEquals("R03", thrown.getCode());
        Assertions.assertEquals("End date before start date", thrown.getMessage());
    }

    @Test
    void listAll() {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rental = RentalBuilder.rentalBuilder(instrument, attendant);
        var rentalDTO = RentalDTOBuilder.rentalDTOBuilder(customer.getPersonId(),
                instrument.getInstrumentId(), attendant.getPersonId());
        when(rentalService.findAll()).thenReturn(List.of(rental));
        when(rentalMapper.convertToListDto(List.of(rental))).thenReturn(List.of(rentalDTO));
        RentalListResponseDTO result = rentalAPI.findAll();
        Assertions.assertEquals(RentalListResponseDTO.builder().data(List.of(rentalDTO)).build(), result);
    }

    @Test
    void findSuccess() throws RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rental = RentalBuilder.rentalBuilder(instrument, attendant);
        var rentalDTO = RentalDTOBuilder.rentalDTOBuilder(customer.getPersonId(),
                instrument.getInstrumentId(), attendant.getPersonId());
        when(rentalService.findRentalListByWord(customer.getName())).thenReturn(List.of(rental));
        when(rentalMapper.convertToListDto(List.of(rental))).thenReturn(List.of(rentalDTO));
        RentalListResponseDTO result = rentalAPI.find(customer.getName());
        Assertions.assertEquals(RentalListResponseDTO.builder().data(List.of(rentalDTO)).build(), result);
    }

    @Test
    void findRentalNotFoundException() throws RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        when(rentalService.findRentalListByWord(customer.getName()))
                .thenThrow(new RentalNotFoundException("R01", "Rental not found"));
        RentalNotFoundException thrown = Assertions.assertThrows(RentalNotFoundException.class, () ->
        {
            rentalAPI.find(customer.getName());
        });
        Assertions.assertEquals("R01", thrown.getCode());
        Assertions.assertEquals("Rental not found", thrown.getMessage());
    }

    @Test
    void updateSuccess() throws RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rental = RentalBuilder.rentalBuilder(instrument, attendant);
        var rentalDTO = RentalDTOBuilder.rentalDTOBuilder(customer.getPersonId(),
                instrument.getInstrumentId(), attendant.getPersonId());
        when(rentalMapper.convertToEntity(rentalDTO)).thenReturn(rental);
        when(rentalService.update(rental)).thenReturn(rental);
        when(rentalMapper.convertToDTO(rental)).thenReturn(rentalDTO);
        RentalResponseDTO result = rentalAPI.update(rental.getRentalId(), rentalDTO);
        Assertions.assertEquals(RentalResponseDTO.builder().data(rentalDTO).build(), result);
    }

    @Test
    void updateRentalNotFoundException() throws RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rental = RentalBuilder.rentalBuilder(instrument, attendant);
        var rentalDTO = RentalDTOBuilder.rentalDTOBuilder(customer.getPersonId(),
                instrument.getInstrumentId(), attendant.getPersonId());
        when(rentalMapper.convertToEntity(rentalDTO)).thenReturn(rental);
        when(rentalService.update(rental))
                .thenThrow(new RentalNotFoundException("R01", "Rental not found"));
        RentalNotFoundException thrown = Assertions.assertThrows(RentalNotFoundException.class, () ->
        {
            rentalAPI.update(rental.getRentalId(), rentalDTO);
        });
        Assertions.assertEquals("R01", thrown.getCode());
        Assertions.assertEquals("Rental not found", thrown.getMessage());
    }

    @Test
    void deleteSuccess() throws RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rental = RentalBuilder.rentalBuilder(instrument, attendant);
        doNothing().when(rentalService).delete(rental.getRentalId());
        rentalAPI.delete(rental.getRentalId());
        verify(rentalService, times(1)).delete(rental.getRentalId());
    }

    @Test
    void deleteRentalNotFoundException() throws RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rental = RentalBuilder.rentalBuilder(instrument, attendant);
        doThrow(new RentalNotFoundException("R01", "Rental not found")).when(rentalService).delete(rental.getRentalId());
        RentalNotFoundException thrown = Assertions.assertThrows(RentalNotFoundException.class, () ->
        {
            rentalAPI.delete(rental.getRentalId());
        });
        Assertions.assertEquals("R01", thrown.getCode());
        Assertions.assertEquals("Rental not found", thrown.getMessage());
    }
}
