package sit.int221.servicetasksj3.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.servicetasksj3.dtos.TaskDTO;
import sit.int221.servicetasksj3.dtos.TaskDTOTwo;
import sit.int221.servicetasksj3.entities.Task;
import sit.int221.servicetasksj3.exceptions.ItemNotFoundException;
import sit.int221.servicetasksj3.repositories.TaskRepository;

import java.util.List;


@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;
    public List<TaskDTO> getAllTasks(){
        return listMapper.mapList(repository.findAll(), TaskDTO.class, modelMapper);
    }

    public Task findByID(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Task id "+ id + " does not exist !!!"));
    }

    // ADD
    @Transactional
    public Task createNewTasks(Task task){
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new ItemNotFoundException("Title is required");
        }
        if (task.getTitle().length() > 100) {
            throw new ItemNotFoundException("Title cannot exceed 100 characters");
        }
        if (task.getDescription() != null && task.getDescription().length() > 500) {
            throw new ItemNotFoundException("Description cannot exceed 500 characters");
        }
        if (task.getAssignees() != null && task.getAssignees().length() > 30) {
            throw new ItemNotFoundException("Assignees cannot exceed 30 characters");
        }
        try {
            return repository.save(task);
        } catch (Exception exception) {
            throw new ItemNotFoundException("Failed to save task");
        }
    }
    // DELETE
    @Transactional
    public TaskDTO removeTasks(Integer id){
        Task task = repository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("NOT FOUND"));
        TaskDTO deletedTaskDTO = modelMapper.map(task, TaskDTO.class);
        repository.delete(task);
        return deletedTaskDTO;
    }
    // EDIT
    @Transactional
    public Task updateTakes(Integer id, Task task) {
        Task existingTask = repository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("NOT FOUND"));
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new ItemNotFoundException("Title is required");
        } else {
            task.setId(id);
            return repository.save(task);
         }
    }
}
