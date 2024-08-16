package com.accenture.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
        description = "esquema para almacenar los datos de los clientes"
)
public class CustomerDTO {
    @Schema(
            description = "Número del documento de identifación del cliente"
    )
    @NotEmpty( message = "El campo numero de documento no puede ser vacío")
    @Size( min = 5, max = 20, message = "El documento debe ocupar de 5 a 20 caracteres")
    private String document;

    @Schema(
            description = "Nombre del cliente"
    )
    private String name;

    @Schema(
            description = "Correo electrónico del cliente"
    )
    private String email;

    @Schema(
            description = "Número de telefono del cliente"
    )
    @NotEmpty( message = "El numero de telefono no puede estar vacío")
    @Pattern( regexp = "(^$|[0-9]{10})", message = "El numero telefonico es inválido")
    private String phone;

    @Schema(
            description = "Dirección del cliente"
    )
    private String address;
}
