package com.example.librarySystem.Controller;

import com.example.librarySystem.Dto.AssistantRequest;
import com.example.librarySystem.Dto.StudyPlanRequest;
import com.example.librarySystem.Service.AIService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ai")
@RestController
public class AIController {
    private AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }


    @PostMapping("/studyPlanner")
    public ResponseEntity<String> generateStudyPlan(@Valid @RequestBody StudyPlanRequest request){
        return ResponseEntity.ok(aiService.generateStudyPlan(request));
    }

    @PostMapping("/aiAssistant")
    public ResponseEntity<String> askLibraryAssistant(@Valid @RequestBody AssistantRequest assistantRequest){
        return ResponseEntity.ok(aiService.askLibraryAssistant(assistantRequest));
    }

    @PostMapping("/monthly-report")
    public ResponseEntity<String> getMonthlyReport(){
        return ResponseEntity.ok(aiService.generateMonthlyReport());
    }


}
