package com.example.librarySystem.Service;

import com.example.librarySystem.Entity.Seat;

import com.example.librarySystem.Repository.SeatRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void updateSeat(Seat seat) {
        seatRepository.save(seat);
    }

    public  List<Seat> seeSeat() {
        return seatRepository.findAll();

    }

    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }

    public  Seat seeSeatById(Long id) {
        return seatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Seat not found"));
    }
}
