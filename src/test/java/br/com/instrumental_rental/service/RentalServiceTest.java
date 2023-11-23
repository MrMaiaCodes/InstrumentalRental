package br.com.instrumental_rental.service;

import br.com.instrumental_rental.models.AttendantBuilder;
import br.com.instrumental_rental.models.CustomerBuilder;
import br.com.instrumental_rental.models.InstrumentBuilder;
import br.com.instrumental_rental.models.RentalBuilder;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.interfaces.IRentalRepository;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalServiceTest {

    @Mock
    IRentalRepository rentalRepository;

    @Mock
    ICustomerService customerService;

    @Mock
    IInstrumentService instrumentService;

    @Mock
    IAttendantService attendantService;

    @InjectMocks
    IRentalService rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);}

    Customer customerBuilder = CustomerBuilder.customerBuilder(
            "01", "John", LocalDate.parse("1992-08-23"),
            "123456789", "1234567",
            BigDecimal.valueOf(500)
    );
    Instrument instrumentBuilder = InstrumentBuilder.instrumentBuilder(
            "1", "microphone", "Yamaha", "Supermic 4000",
            BigDecimal.valueOf(4000), LocalDate.parse("2000-12-31",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    );
    Attendant attendantBuilder = AttendantBuilder.attendantBuilder(

    )


    @Test
    void testSaveSuccess() {

    }
}
