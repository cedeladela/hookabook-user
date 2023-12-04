package cedeladela.hookabook.controller;

import cedeladela.hookabook.entity.HbUser;
import cedeladela.hookabook.service.HbUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hbuser")
@Tag(name = "HbUserCRUD")
public class HbUserCRUDController {

    private final HbUserService hbUserService;

    @Autowired
    public HbUserCRUDController(HbUserService hbUserService) {
        this.hbUserService = hbUserService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find HbUser by ID", description = "Returns an HbUser as per the ID.")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(hbUserService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get All HbUsers", description = "Returns a list of all HbUsers.")
    public ResponseEntity<Object> getAll() {
        try {
            List<HbUser> hbUsers = hbUserService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(hbUsers);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create HbUser", description = "Creates a new HbUser.")
    public ResponseEntity<Object> create(@RequestBody HbUser hbUser) {
        try {
            HbUser createdUser = hbUserService.create(hbUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update HbUser", description = "Updates an existing HbUser.")
    public ResponseEntity<Object> update(@RequestBody HbUser hbUser) {
        try {
            HbUser updatedUser = hbUserService.update(hbUser);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete HbUser", description = "Deletes an HbUser by ID.")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            hbUserService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("HbUser with ID " + id + " deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

}
