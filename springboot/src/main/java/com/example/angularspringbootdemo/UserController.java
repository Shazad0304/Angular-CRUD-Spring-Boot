package com.example.angularspringbootdemo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

  @PostMapping("/user")
  public ResponseEntity<?> login(@Validated @RequestBody User usr) {

    String pass = "admin";
    String passHash = MD5(pass);

    String reqPassHash = MD5(usr.getPassword());
    if(usr.getEmail().equals("admin") && passHash.equals(reqPassHash)){
      String token = getJWTToken(usr.getEmail());
      User user = new User();
      user.setEmail(usr.getEmail());
      user.setToken(token);
      return ResponseEntity.status(200).body(user);
    }

   return ResponseEntity.status(401).body("Invalid Credentials");
  }

  public String MD5(String md5) {
    try {
      java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
      byte[] array = md.digest(md5.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
      }
      return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
    }
    return null;
  }

  private String getJWTToken(String username) {
    String secretKey = "mySecretKey";
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
      .commaSeparatedStringToAuthorityList("ROLE_USER");

    String token = Jwts
      .builder()
      .setId("JWT")
      .setSubject(username)
      .claim("authorities",
        grantedAuthorities.stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.toList()))
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 600000))
      .signWith(SignatureAlgorithm.HS512,
        secretKey.getBytes()).compact();

    return token;
  }
}
