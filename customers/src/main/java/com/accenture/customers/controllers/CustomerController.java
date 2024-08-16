package com.accenture.customers.controllers;


import com.accenture.customers.dto.CustomerDTO;
import com.accenture.customers.dto.ErrorResponseDTO;
import com.accenture.customers.dto.ResponseDTO;
import com.accenture.customers.dto.SupportInfoDTO;
import com.accenture.customers.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.time.LocalDateTime;

@Tag(
    name = "Rest para la gestión de clientes",
    description = "CRUD REST api para la creación, recuperación, gestión de clientes"
) @Validated @RestController @AllArgsConstructor
@RequestMapping(path="/api",produces= { MediaType.APPLICATION_JSON_VALUE })
public class CustomerController {

    @NonNull
    private ICustomerService iCustomerService;

    @NonNull
    private Environment environment;

    @NonNull
    private SupportInfoDTO supportInfoDto;

    @GetMapping( value = "/support-info")
    public ResponseEntity<SupportInfoDTO> supportInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(supportInfoDto);
    }
    /*@GetMapping( value = "/buildInfo", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseDTO> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO("200",buildVersion));
    }*/

    @GetMapping ( value = "/dateTime", produces = { MediaType.TEXT_PLAIN_VALUE } )
    public String getCurrentTime() {
        return LocalDateTime.now().toString();
    }

    @Operation(
            summary = "Creación de cliente",
            description = "Permite la creación de clientes en nuestro sistema bancario"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Http Status OK"
        ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ha ocurrido algún error con la solicitud",
                    content = @Content(
                            schema = @Schema( implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PostMapping( value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDto){
        iCustomerService.createCustomer(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO("201","Customer created Succesfully!!"));
    }
    @Operation(
            summary = "Consulta de clientes por número de documento",
            description = "Permite la consulta de clientes en nuestro sistema bancario"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK",
                    content = @Content(
                            schema = @Schema( implementation = CustomerDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "recurso no encontrado",
                    content = @Content(
                            schema = @Schema( implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<CustomerDTO> findById(
            @PathVariable
            Long id){
        CustomerDTO customerDTO = iCustomerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDTO);
    }
    @GetMapping(value = "/fetchByDocument/{document}")
    public ResponseEntity<CustomerDTO> fetchCustomerByDocument(
            @RequestParam
            @NotEmpty( message = "El campo numero de documento no puede ser vacío")
            @Size( min = 5, max = 20, message = "El documento debe ocupar de 5 a 20 caracteres")
            String document){
             CustomerDTO customerDTO = iCustomerService.fetchCustomerByDocument(document);
             return ResponseEntity.status(HttpStatus.OK)
                     .body(customerDTO);
    }

    @GetMapping(value = "/fetchByEmail/{email}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CustomerDTO> fetchByEmail(@PathVariable String email) {
        CustomerDTO customerDTO = iCustomerService.fetchCustomerByEmail(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDTO);
    }
    @PutMapping( value = "updateByDocument", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CustomerDTO> updateByDocument(@RequestBody CustomerDTO customerDto){
        CustomerDTO updatedCustomer = iCustomerService.updateCustomer(customerDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCustomer);
    }


    @DeleteMapping( value = "/deleteByDocument/{document}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseDTO> deleteByDocument(@PathVariable String document){
        iCustomerService.deleteCustomerByDocument(document);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO("200","Customer Deleted Succesfully!!"));
    }
}
