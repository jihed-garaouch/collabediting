package com.collab.collabediting;

import com.collab.collabediting.models.Task;
import com.collab.collabediting.repository.TaskRepo;
import com.collab.collabediting.services.impl.TaskService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class TaskServiceImplTest {
    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskService taskService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getTasksByRepoName_ShouldReturnTasks() {
        List<Task> mockTasks = List.of( Task.builder().id(1).repoName("repoTest").title("task1").build());
        when(taskRepo.findAllByRepoName("repoTest")).thenReturn(mockTasks);
        List<Task> tasks = taskService.getTasksByRepoName("repoTest");
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        assertEquals("task1", tasks.get(0).getTitle());

    }
}
