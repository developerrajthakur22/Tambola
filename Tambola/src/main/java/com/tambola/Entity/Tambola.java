package com.tambola.Entity;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tambola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String ticketJson; // Store ticket as JSON string

    public List<List<Integer>> getTicket() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ticketJson, List.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void setTicket(List<List<Integer>> ticket) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.ticketJson = objectMapper.writeValueAsString(ticket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
}
