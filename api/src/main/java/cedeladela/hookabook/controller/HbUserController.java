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
@Tag(name = "HbUser")
public class HbUserController {

    private final HbUserService hbUserService;

    @Autowired
    public HbUserController(HbUserService hbUserService) {
        this.hbUserService = hbUserService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register HbUser", description = "Registers a new HbUser.")
    public ResponseEntity<Object> register(@RequestBody HbUser hbUser) {
        try {
            HbUser registeredUser = hbUserService.register(hbUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Logs in an HbUser.")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password) {
        try {
            HbUser loggedInUser = hbUserService.login(username, password);
            return ResponseEntity.status(HttpStatus.OK).body(loggedInUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authentication failed: " + e.getMessage());
        }
    }

    @PostMapping("/approve/{id}")
    @Operation(summary = "Approve HbUser", description = "Approves an HbUser by ID.")
    public ResponseEntity<Object> approveUser(@PathVariable Long id) {
        try {
            HbUser approvedUser = hbUserService.approveUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(approvedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }
}
