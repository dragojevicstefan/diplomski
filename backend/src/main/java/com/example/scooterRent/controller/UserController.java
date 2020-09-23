package com.example.scooterRent.controller;

import com.example.scooterRent.dto.LoginDTO;
import com.example.scooterRent.dto.RegisterAdminDTO;
import com.example.scooterRent.dto.RegisterDTO;
import com.example.scooterRent.model.ConfirmationToken;
import com.example.scooterRent.model.User;
import com.example.scooterRent.repository.ConfirmationTokenRepo;
import com.example.scooterRent.repository.UserRepo;
import com.example.scooterRent.security.TokenUtils;
import com.example.scooterRent.service.EmailSenderService;
import com.example.scooterRent.service.PasswordsDoNotMatchException;
import com.example.scooterRent.service.UserDetailsServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private ConfirmationTokenRepo confirmationTokenRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) throws IOException {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmail(), loginDTO.getPassword());
            authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getEmail());
            System.out.println(details.isEnabled());
            User userr=userDetailsService.findUserByEmail(loginDTO.getEmail());
            if(!userr.isActive()){
                System.out.println(details.isEnabled());
                return new ResponseEntity<>("account is not active", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
        if (!registerDTO.getPassword1().equals(registerDTO.getPassword2())) throw new PasswordsDoNotMatchException();

        User user = new User(registerDTO.getEmail(), registerDTO.getPassword1(),false);
        userDetailsService.createUser(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("scooters@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"https://localhost:8443/confirm-account?token="+confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);
        return new ResponseEntity<String>("User successfully created", HttpStatus.CREATED);
    }

    @PostMapping("/api/registerAdmin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody RegisterAdminDTO registerDTO) {
        if (!registerDTO.getPassword1().equals(registerDTO.getPassword2())) throw new PasswordsDoNotMatchException();
        User user = new User(registerDTO.getEmail(), registerDTO.getPassword1(),true);
        user.setName(registerDTO.getName());
        user.setSurname(registerDTO.getSurname());
        user.setActive(true);
        userDetailsService.createUser(user);
        return new ResponseEntity<String>("Admin successfully created", HttpStatus.CREATED);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepo.findByEmail(token.getUser().getEmail());
            user.setActive(true);
            userRepo.save(user);
        }
        else
        {
            return new ResponseEntity<String>("Account is not active", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>("User account is active", HttpStatus.CREATED);
    }
//
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    @GetMapping("/profile")
//    public UserDetailsDTO profile() {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        User u = userDetailsService.findUserByEmail(email);
//        if (u == null) throw new NotFoundException("Invalid user");
//        return new UserDetailsDTO(u.getName(), u.getLastName(), u.getEmail());
//    }
//
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    @PutMapping("/reset")
//    public UserDetailsDTO changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) throws Exception {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        User u = userDetailsService.findUserByEmail(email);
//        User updatedUser = userDetailsService.changePassword(u, changePasswordDTO.getPassword1(), changePasswordDTO.getPassword2());
//        return new UserDetailsDTO(updatedUser.getName(), updatedUser.getLastName(), updatedUser.getEmail());
//    }
//
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    @PutMapping("/updateProfile")
//    public UserDetailsDTO updateProfile(@Valid @RequestBody UserDetailsDTO userDetailsDTO){
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        User u = userDetailsService.findUserByEmail(email);
//        User updatedUser = userDetailsService.updateProfile(u, userDetailsDTO.getEmail(), userDetailsDTO.getFirstName(), userDetailsDTO.getLastName());
//        return new UserDetailsDTO(updatedUser.getName(), updatedUser.getLastName(), updatedUser.getEmail());
//    }


}
