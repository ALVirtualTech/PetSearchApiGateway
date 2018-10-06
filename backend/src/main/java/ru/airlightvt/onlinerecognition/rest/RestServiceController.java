package ru.airlightvt.onlinerecognition.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api")
public class RestServiceController {
    @RequestMapping(value = "/pets", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("petPhoto") MultipartFile petFoto) {

        return new ResponseEntity<>("Successfully uploaded - " +
                petFoto.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }
}
