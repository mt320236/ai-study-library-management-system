package com.example.librarySystem.Controller;


import com.example.librarySystem.Entity.Fee;
import com.example.librarySystem.Service.FeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
public class FeeController {
    private FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }
    @PostMapping
    public ResponseEntity<Fee> createFee(@RequestBody Fee fee){
        return ResponseEntity.status(HttpStatus.CREATED).body(feeService.createFee(fee));
    }

    @GetMapping
    public ResponseEntity<List<Fee>> seeFee(){
        return ResponseEntity.status(HttpStatus.OK).body(feeService.seeFee());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Fee> seeFeeById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(feeService.seeFeeById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Fee> updateById(@RequestBody Fee fee,@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(feeService.updateFeeById(fee,id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        feeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Fee deleted");
    }
}
