package com.tambola.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.tambola.Entity.Tambola;
import com.tambola.Repository.TambolaRepository;

@Service
public class TambolaServiceImpl implements TambolaService {

    private static final Logger LOGGER = Logger.getLogger(TambolaServiceImpl.class.getName());

    @Autowired
    private TambolaRepository tambolaRepo;

    @Override
    public Tambola addTambola() {
        try {
            Random random = new Random();
            List<List<Integer>> list = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                List<Integer> row = new ArrayList<>();
                int c = 0;
                for (int i = 1; i <= 9; i++) {
                    // Define the range for each iteration
                    int min = (i - 1) * 10 + 1; // Minimum value for the range
                    int max = i * 10;           // Maximum value for the range
                    int randomNumber = random.nextInt(max - min + 1) + min; // Generate
                    int size = row.size();

                    if (random.nextInt(10) > 4 && size - c != 4) {

                        if (list.size() == 0) {
                            row.add(randomNumber);
                        } else if (list.size() == 1) {
                            if (randomNumber == list.get(0).get(i - 1)) {
                                row.add(random.nextInt(max - min + 1) + min);
                            } else {
                                row.add(randomNumber);
                            }
                        } else {
                            if (randomNumber == list.get(1).get(i - 1)) {
                                row.add(random.nextInt(max - min + 1) + min);
                            } else {
                                row.add(randomNumber);
                            }
                        }
                    } else if (c == 4) {
                        if (list.size() == 0) {
                            row.add(randomNumber);
                        } else if (list.size() == 1) {
                            if (randomNumber == list.get(0).get(i - 1)) {
                                row.add(random.nextInt(max - min + 1) + min);
                            } else {
                                row.add(randomNumber);
                            }
                        } else {
                            if (randomNumber == list.get(1).get(i - 1)) {
                                row.add(random.nextInt(max - min + 1) + min);
                            } else {
                                row.add(randomNumber);
                            }
                        }
                    } else {
                        c++;
                        row.add(0);
                    }
                }
                list.add(row);
            }

            Tambola tambola = new Tambola();
            tambola.setTicket(list);
            tambolaRepo.save(tambola);
            return tambola;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding Tambola", ex);
            throw new RuntimeException("Error occurred while adding Tambola", ex);
        }
    }

    @Override
    public Page<Map<Integer, List<List<Integer>>>> getTambola(Pageable pageable) {
        try {
            Page<Tambola> allTambola = tambolaRepo.findAll(pageable);

            Page<Map<Integer, List<List<Integer>>>> resultPage = allTambola.map(tambola -> {
                Map<Integer, List<List<Integer>>> map = new HashMap<>();
                map.put(tambola.getId(), tambola.getTicket());
                return map;
            });

            return resultPage;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while getting Tambola", ex);
            throw new RuntimeException("Error occurred while getting Tambola", ex);
        }
    }
}
