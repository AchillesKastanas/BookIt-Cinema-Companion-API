package com.example.cinemaapp.Controllers;

import com.example.cinemaapp.DTO.LoginDTO;
import com.example.cinemaapp.Exceptions.CustomerException;
import com.example.cinemaapp.Exceptions.LoginException;
import com.example.cinemaapp.Exceptions.UserException;
import com.example.cinemaapp.Model.CurrentUserSession;
import com.example.cinemaapp.Model.Customer;
import com.example.cinemaapp.Model.User;
import com.example.cinemaapp.Repository.CurrentUserSessionRepo;
import com.example.cinemaapp.Service.CustomerService;
import com.example.cinemaapp.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CurrentUserSessionRepo currentUserSessionRepo;

    @PostMapping("/login")
    public ResponseEntity addUser(@RequestBody LoginDTO loginDTO) throws Exception {
        Map<String, String> model = new HashMap<>();
        Customer savedCustomer = customerService.getCustomerDetailsByUsername(loginDTO.getUsername());
        if (!savedCustomer.getPassword().equals(loginDTO.getPassword())) {
            throw new Exception("Invalid username/password");
        }
        model.put("message","Logged in Successfully");
        model.put("token",savedCustomer.getUsername());
        model.put("customer_id", String.valueOf(savedCustomer.getCustomerId()));
        LocalDateTime rightNow = LocalDateTime.now();
        CurrentUserSession currentUserSession= new CurrentUserSession(loginDTO.getCustomer_id(), loginDTO.getUsername(),rightNow );
        currentUserSessionRepo.save(currentUserSession);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestParam String key) throws UserException, LoginException {
        String response = loginService.signOut(key);
        return new ResponseEntity<String>(response,HttpStatus.OK) ;
    }
}
