package com.example.restapi.service;

import com.example.restapi.exception.CityAlreadyExistsException;
import com.example.restapi.exception.CityNotFoundException;
import com.example.restapi.model.City;
import com.example.restapi.repository.CityRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    public List<City>getCities(String name) {
        if (name == null) {
            return cityRepository.findAll();
        } else {
         return  cityRepository.findAllByName(name);
        }
    }
    public City createCity( City newCity) {

        Optional<City>CityByName = cityRepository.findByName(newCity.getName());
        if (CityByName.isPresent()){
            throw new CityAlreadyExistsException("City already exists with name: " + newCity.getName());
        }
        return cityRepository.save(newCity);
    }

    public void deleteCity(String id) {
        cityRepository.deleteById(id);
    }

    public City getCityById(String id) {
        return cityRepository.findById(id).orElseThrow(()
                ->new CityNotFoundException("City not found with id:" + id));
    }

    public void updateCity(String id, City newCity){

        City oldCity = getCityById(id);
        oldCity.setName(newCity.getName());
        cityRepository.save(oldCity);
    }

}

