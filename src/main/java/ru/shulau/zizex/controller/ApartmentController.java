package ru.shulau.zizex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.shulau.zizex.dto.ApartmentDto;
import ru.shulau.zizex.dto.ApartmentResponse;
import ru.shulau.zizex.model.Apartment;
import ru.shulau.zizex.model.User;
import ru.shulau.zizex.repository.ApartmentRepository;
import ru.shulau.zizex.util.ApartmentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/apartments")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentRepository repository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApartmentResponse> create(@AuthenticationPrincipal User user,
                                                    @RequestBody ApartmentDto apartmentDto) {
        log.info("create new apartment");
        Apartment apartment = Apartment.builder()
                .address(apartmentDto.getAddress())
                .owner(user)
                .build();
        repository.save(apartment);
        return ResponseEntity.ok(ApartmentMapper.getResponse(apartment, user));
    }

    @GetMapping
    public List<ApartmentResponse> getAllUserApartments(@AuthenticationPrincipal User user) {
        log.info("find all by owner {}", user.getName());
        return repository.getAllByOwner(user.id())
                .stream()
                .map(apartment -> ApartmentMapper.getResponse(apartment, user))
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/{apartmentId}")
    public ResponseEntity<ApartmentResponse> update(@AuthenticationPrincipal User user,
                                                    @RequestParam String address,
                                                    @PathVariable("apartmentId") Long apartmentId) {
        log.info("update apartment");
        Apartment toUpdate = repository.findById(apartmentId).orElseThrow();
        toUpdate.setAddress(address);
        repository.save(toUpdate);
        return ResponseEntity.ok(ApartmentMapper.getResponse(toUpdate, user));
    }

    @DeleteMapping(value = "/{apartmentId}")
    public void delete(@AuthenticationPrincipal User user,
                       @PathVariable("apartmentId") Long apartmentId) {
        log.info("delete apartment");
        Apartment apartment = repository.findById(apartmentId).orElseThrow();
        repository.delete(apartment);
    }
}