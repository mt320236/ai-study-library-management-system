package com.example.librarySystem.Service;

import com.example.librarySystem.Entity.Seat;

import com.example.librarySystem.Exceptions.SeatNotFoundException;
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
        Seat existingSeat=seatRepository.findById(seat.getId()).orElseThrow(() ->new SeatNotFoundException("Seat with Id "+seat.getId()+" not found"));
        existingSeat.setAvailable(seat.isAvailable());
        existingSeat.setSeatNumber(seat.getSeatNumber());
        existingSeat.setStudent(seat.getStudent());

        seatRepository.save(existingSeat);
    }

    public  List<Seat> seeSeat() {
        return seatRepository.findAll();

    }

    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public void deleteSeat(Long id) {
        seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException("Seat with Id "+id+" not found"));


        seatRepository.deleteById(id);
    }

    public  Seat seeSeatById(Long id) {
        return seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException("Seat with id "+id+" not found"));
    }
}
