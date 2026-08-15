package com.example.librarySystem.Controller;

import com.example.librarySystem.Entity.Seat;
import com.example.librarySystem.Service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat){
        Seat seat1=seatService.createSeat(seat);
        return ResponseEntity.status(HttpStatus.CREATED).body(seat1);

    }



    @GetMapping
    public ResponseEntity<List<Seat>> seeSeat(){
        return ResponseEntity.status(HttpStatus.OK).body(seatService.seeSeat());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Seat> seeSeatById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(seatService.seeSeatById(id));

    }
    @PutMapping
    public void updateSeat(@RequestBody Seat seat){
        seatService.updateSeat(seat);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long id){
        seatService.deleteSeat(id);
        return ResponseEntity.status(HttpStatus.OK).body("Seat deleted");
    }

}
