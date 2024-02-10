package com.tambola.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tambola.Entity.Tambola;
import com.tambola.Service.TambolaService;

@RestController
public class TambolaController {

    private static final Logger LOGGER = Logger.getLogger(TambolaController.class.getName());

    @Autowired
    TambolaService tambolaServices;

    @PostMapping("/addTambola/{n}")
    public ResponseEntity<List<Map<Integer, List<List<Integer>>>>> addTambola(@PathVariable int n) {
        List<Tambola> tambolas = new ArrayList<>();
        List<Map<Integer, List<List<Integer>>>> result = new ArrayList<>();

        try {
            for (int i = 0; i < n; i++) {
                Tambola tambola = tambolaServices.addTambola();
                tambolas.add(tambola);

                // Results
                List<List<Integer>> list = tambola.getTicket();
                int id = tambola.getId();

                // Add map to the result
                Map<Integer, List<List<Integer>>> map = new HashMap<>();
                map.put(id, list);
                result.add(map);
            }
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding Tambola", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTambola")
    public ResponseEntity<Page<Map<Integer, List<List<Integer>>>>> getTambola(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        try {
            Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, size, direction, sortField);

            Page<Map<Integer, List<List<Integer>>>> tambolasPage = tambolaServices.getTambola(pageable);
            return new ResponseEntity<>(tambolasPage, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while getting Tambola", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
