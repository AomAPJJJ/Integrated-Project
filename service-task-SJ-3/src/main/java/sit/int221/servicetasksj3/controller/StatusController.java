package sit.int221.servicetasksj3.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int221.servicetasksj3.dtos.StatusDTO;
import sit.int221.servicetasksj3.dtos.TaskDTOTwo;
import sit.int221.servicetasksj3.entities.Task;
import sit.int221.servicetasksj3.entities.TaskStatus;
import sit.int221.servicetasksj3.services.StatusService;

import java.util.List;

@RestController
@RequestMapping("/v2/statuses")
@CrossOrigin(origins = { "http://localhost:5173/", "http://ip23sj3.sit.kmutt.ac.th", "http://intproj23.sit.kmutt.ac.th" } )

public class StatusController {
    @Autowired
    private StatusService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public List<StatusDTO> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskStatus getTaskById(@PathVariable Integer id){
        return service.findByID(id);
    }

    @PostMapping("")
    public ResponseEntity<Object> createNewStatuses(@RequestBody StatusDTO status) {
        List<StatusDTO> createdStatus = service.createNewStatuses(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStatuses(@PathVariable Integer id, @RequestBody TaskStatus task) {
        return ResponseEntity.ok(service.updateStatuses(id, task));
    }


}