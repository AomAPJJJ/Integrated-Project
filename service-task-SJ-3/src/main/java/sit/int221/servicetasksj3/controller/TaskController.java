package sit.int221.servicetasksj3.controller;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int221.servicetasksj3.dtos.SimpleTaskDTO;
import sit.int221.servicetasksj3.dtos.TaskDTO;
import sit.int221.servicetasksj3.dtos.TaskDTOTwo;
import sit.int221.servicetasksj3.dtos.TaskNewDTO;
import sit.int221.servicetasksj3.entities.Task;
import sit.int221.servicetasksj3.services.TaskService;

import java.util.List;


@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = { "http://localhost:5173/", "http://ip23sj3.sit.kmutt.ac.th", "http://intproj23.sit.kmutt.ac.th" } )
//@CrossOrigin(origins = "http://localhost:5173/")
//@CrossOrigin(origins = "http://ip23sj3.sit.kmutt.ac.th")
public class TaskController {
    @Autowired
    private TaskService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public List<TaskDTO> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public SimpleTaskDTO getTaskById(@PathVariable Integer id){
        Task task = service.findByID(id);
        SimpleTaskDTO simpleTaskDTO = modelMapper.map(task, SimpleTaskDTO.class);
        return simpleTaskDTO;
    }

    // ADD
    @PostMapping("")
    public ResponseEntity<TaskDTOTwo> createNewTasks(@Valid @RequestBody TaskNewDTO task){
        Task createTask = service.createNewTasks(task);
        TaskDTOTwo createdTaskDTO = modelMapper.map(createTask, TaskDTOTwo.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDTO);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> removeTasks(@PathVariable Integer id){
        TaskDTO deletedTaskDTO = service.removeTasks(id);
        return ResponseEntity.ok().body(deletedTaskDTO);
    }
    // EDIT
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTOTwo> updateTasks(@Valid @RequestBody TaskNewDTO task, @PathVariable Integer id) {
        Task updatedTask = service.updateTask(id, task);
        TaskDTOTwo updatedTaskDTO = modelMapper.map(updatedTask, TaskDTOTwo.class);
        return ResponseEntity.ok(updatedTaskDTO);
    }
}
