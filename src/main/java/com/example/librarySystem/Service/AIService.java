package com.example.librarySystem.Service;


import com.example.librarySystem.Dto.AssistantRequest;
import com.example.librarySystem.Dto.StudyPlanRequest;
import com.example.librarySystem.Entity.Fee;
import com.example.librarySystem.Entity.Student;
import com.example.librarySystem.Exceptions.AIServiceExcpetion;
import com.example.librarySystem.Exceptions.StudentNotFoundException;
import com.example.librarySystem.Repository.FeeRepository;
import com.example.librarySystem.Repository.SeatRepository;
import com.example.librarySystem.Repository.StudentRepository;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AIService {
    private final StudentRepository studentRepository;
    private final FeeRepository feeRepository;
    private final SeatRepository seatRepository;

    public AIService(StudentRepository studentRepository, FeeRepository feeRepository, SeatRepository seatRepository) {
        this.studentRepository = studentRepository;
        this.feeRepository = feeRepository;
        this.seatRepository = seatRepository;
    }

    private final String OLLAMA_URL="http://localhost:11434/api/generate";
    private final RestTemplate restTemplate = buildRestTemplate();

    private static RestTemplate buildRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(5));
        factory.setReadTimeout(Duration.ofSeconds(60));
        return new RestTemplate(factory);
    }
    public String generateResponse(String prompt){

        Map<String,Object> request=new HashMap<>();
        request.put("model", "llama3.2");
        request.put("prompt", prompt);
        request.put("stream", false);

        Map<String, Object> response;
        try{
            response=restTemplate.postForObject(OLLAMA_URL, request, Map.class);
         }
        catch (RestClientException e){
            throw new AIServiceExcpetion("Ollama is unavailable, try again later");

        }
        if (response == null || response.get("response") == null) {
            throw new AIServiceExcpetion("Ollama returned an empty response");
        }

        return (String) response.get("response");



    }
    public String generateStudyPlan(StudyPlanRequest request) {

        // Prompt banao
        String prompt = "Generate a day-by-day study plan for a student.\n" +
                "Subjects: " + request.getSubjects() + "\n" +
                "Total Days: " + request.getDays() + "\n" +
                "Hours per day: " + request.getHoursPerDay() + "\n" +
                "Format: Day 1: 2h Java, 1h DSA\n" +
                "Day 2: ...\n" +
                "Keep it simple and structured.";

        return generateResponse(prompt);
    }
    public String askLibraryAssistant(AssistantRequest assistantRequest){
        Student student=studentRepository.findById(assistantRequest.getStudentId()).orElseThrow(() -> new StudentNotFoundException("Student with Id "+assistantRequest.getStudentId()+" not found"));

        List<Fee> fees=feeRepository.findByStudent(student);
        StringBuilder sb=new StringBuilder();
        sb.append("Student Name: ").append(student.getName()).append("\n");
        sb.append("Email: ").append(student.getEmail()).append("\n");
        if(student.getSeat() != null) {
            sb.append("Seat: ").append(student.getSeat().getSeatNumber()).append("\n");
        }

        for(Fee fee : fees) {
            sb.append("Fee Amount: ").append(fee.getAmount()).append("\n");
            sb.append("Due Date: ").append(fee.getDueDate()).append("\n");
            sb.append("Status: ").append(fee.getStatus()).append("\n");
        }
        String prompt = "You are a library assistant.\n" +
                "Here is the student information:\n" +
                sb.toString() +
                "\nStudent Question: " + assistantRequest.getQuestion() +
                "\nAnswer in one or two natural sentences only.";

        return generateResponse(prompt);


    }
    public String generateMonthlyReport(){
        long totalStudents=studentRepository.count();
        long totalSeats=seatRepository.count();
        long occupiedSeats=seatRepository.countByAvailable(false);
        List<Fee> fees=feeRepository.findAll();
        double totalFeesCollected=fees.stream().filter(f-> f.getStatus().equals("PAID")).mapToDouble(Fee::getAmount)
                .sum();
        double pendingFees=fees.stream().filter(f-> f.getStatus().equals("PENDING")).mapToDouble(Fee::getAmount).sum();

        String prompt = "Generate a professional monthly library report.\n" +
                "Library Statistics:\n" +
                "Total Students: " + totalStudents + "\n" +
                "Total Seats: " + totalSeats + "\n" +
                "Occupied Seats: " + occupiedSeats + "\n" +
                "Total Fees Collected: ₹" + totalFeesCollected + "\n" +
                "Pending Fees: ₹" + pendingFees + "\n" +
                "Write a 3-4 line professional summary report.";

        return generateResponse(prompt);
    }

}
