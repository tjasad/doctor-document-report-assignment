package si.marand.naloga.cotroller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import si.marand.naloga.dto.DoctorDto;

/**
 * Controller class for the Doctor entity.
 */
public interface DoctorController {
    /**
     * Retrieves all doctors.
     *
     * @return doctors
     */
    @Operation(summary = "Get all doctors.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found doctors",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DoctorDto.class))})})
    @GetMapping("/doctor")
    List<DoctorDto> getAll();

    /**
     * Get doctor by ID.
     *
     * @param id id
     * @return doctor
     */
    @Operation(summary = "Get doctor by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found doctor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DoctorDto.class))}),
            @ApiResponse(responseCode = "404", description = "Doctor not found",
                    content = @Content)})
    @GetMapping("/doctor/{id}")
    ResponseEntity<DoctorDto> get(@PathVariable Long id);

    /**
     * Creates a new doctor.
     *
     * @param doctor doctor
     * @return created doctor
     */
    @Operation(summary = "Save doctor entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doctor created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DoctorDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
    @PostMapping("/doctor")
    ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto doctor);
}
