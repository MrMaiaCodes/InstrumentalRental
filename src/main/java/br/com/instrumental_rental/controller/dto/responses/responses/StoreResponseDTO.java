package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.StoreDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreResponseDTO {

    public StoreDTO data;

    public String saveSuccessMessage;
}
