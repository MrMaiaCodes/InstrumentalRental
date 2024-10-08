package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.Mappers.IRentalMapper;
import br.com.instrumental_rental.controller.IRentalAPI;
import br.com.instrumental_rental.controller.dto.requests.RentalDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.RentalListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.RentalResponseDTO;
import br.com.instrumental_rental.exceptions.*;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("V1/rental")
public class RentalAPI implements IRentalAPI {

    private final IRentalService rentalService;

    private final IRentalMapper rentalMapper;

    @Autowired
    public RentalAPI(IRentalService rentalService, IRentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
    }

    @PostMapping("/add")
    public RentalResponseDTO add(@RequestBody RentalDTO rentalDTO)
            throws CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException, StoreNotFoundException, RentalNotFoundException {
        return RentalResponseDTO.builder().data(
                rentalMapper.convertToDTO(rentalService.save(rentalMapper.convertToEntity(rentalDTO),
                        rentalDTO.getCustomerId()
                        )
                )
        ).build();
    }

    /*
    for saveFirstTime, a list of customers will be introduced, each with a rental list.
    A list of Customer entities, each with a list of Rentals in them, is saved.
    Now when we save customer, we save its rental list too.
    when we save a new rental, we use the data from the rental that is the attribute of customer.

    This makes it so that we have to constantly have a RentalList to look for customerIds that
    may not exist
     */


    @GetMapping("/find/{keyword}")
    public RentalListResponseDTO find(@PathVariable("rentalId") String keyWord) throws RentalNotFoundException {
        return RentalListResponseDTO.builder()
                .data(
                        rentalMapper.convertToListDto(rentalService.findRentalListByWord(keyWord))
                ).build();
    }

    @PostMapping("/listAll")
    public RentalListResponseDTO findAll() {
        return RentalListResponseDTO.builder()
                .data(
                        rentalMapper.convertToListDto(rentalService.findAll())
                ).build();
    }

    @PutMapping("/update/{rentalId}")
    public RentalResponseDTO update(@PathVariable("rentalId") Long rentalId,
                                    @RequestBody RentalDTO rentalDTO) throws RentalNotFoundException {
        return RentalResponseDTO.builder()
                .data(
                        rentalMapper.convertToDTO(rentalService.update(rentalMapper.convertToEntity(rentalDTO)))
                ).build();
    }

    @DeleteMapping("/delete/{rentalId}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable("rentalId") Long rentalId) throws RentalNotFoundException {
        rentalService.delete(rentalId);
        return ResponseEntity.ok(DeleteResponseDTO.builder().deleteSuccessMessage("Rental deleted successfully").build());
    }
}
