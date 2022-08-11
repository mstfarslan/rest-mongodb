package com.example.restapi.controller;

import com.example.restapi.exception.CityAlreadyExistsException;
import com.example.restapi.exception.CityNotFoundException;
import com.example.restapi.model.City;
import com.example.restapi.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>>getCities(@RequestParam(required = false)String name ){
        return new ResponseEntity<>(cityService.getCities(name ), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City>getCity(@PathVariable String id) {
        return new ResponseEntity<>(getCityById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City>createCity(@RequestBody City newCity){
        return new ResponseEntity<>(cityService.createCity(newCity),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void>getCity(@PathVariable String id, @RequestBody City newCity){
        cityService.updateCity(id, newCity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteCity(@PathVariable String id){
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private City getCityById(String id){
        return cityService.getCityById(id);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handleCityNotFoundException(CityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CityAlreadyExistsException.class)
    public ResponseEntity<String> handleCityIlAlreadyExistsException(CityAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }



}
