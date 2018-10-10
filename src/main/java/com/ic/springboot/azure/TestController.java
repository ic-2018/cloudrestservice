package com.ic.springboot.azure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.ic.springboot.azure.User.userNamed;

/**
  */
@RestController
public class TestController {

    @GetMapping("/host")
    public String hello() {
		InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
 
        } catch (UnknownHostException e) {
 
            e.printStackTrace();
        }
        return "hello azure, responding from host:" + hostname + "ip: " + ip;
    }

    @Autowired private UserRepository userRepository;

    @PostMapping("/user")
    public String register(@RequestParam String name) {
        userRepository.save(userNamed(name));
        return "registered";
    }

    @GetMapping("/user")
    public Iterable<User> userlist() {
        return userRepository.findAll();
    }
}