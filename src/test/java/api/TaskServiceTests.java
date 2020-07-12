package api;

import static api.data.TaskGenerator.getRandomTask;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.apache.hc.core5.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;

import api.dto.TaskCreateDto;
import api.dto.TaskGetDto;
import api.listeners.CreatedTaskDeleteListener;
import api.services.TaskService;
import io.qameta.allure.Severity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CreatedTaskDeleteListener.class)
public class TaskServiceTests {
  private TaskService taskService;

  @BeforeClass
  public void init() {
    taskService = new TaskService();
  }

  @Test(description = "Create task with valid fields")
  @Severity(CRITICAL)
  void createTaskValid() {
    TaskCreateDto task = getRandomTask();
    taskService.createTask(task).check()
        .statusCodeIs(SC_SUCCESS)
        .isCreated(task);
  }

  @Test(description = "Get one created task valid")
  @Severity(CRITICAL)
  void getTaskValid() {
    TaskGetDto createdTask = taskService.createTaskAsDto(getRandomTask());
    taskService.getTask(createdTask.getId()).check()
        .statusCodeIs(SC_SUCCESS)
        .taskIs(createdTask);
  }

  @Test(description = "Update task with valid fields and id")
  @Severity(CRITICAL)
  void updateTaskValid() {
    TaskGetDto createdTask = taskService.createTaskAsDto(getRandomTask());
    String newName = "update task name";
    taskService.updateTask(createdTask.getId(), newName).check()
        .statusCodeIs(SC_NO_CONTENT)
        .isUpdated(newName, createdTask.getId());
  }

  @Test(description = "Delete created task valid id")
  @Severity(CRITICAL)
  void deleteTaskValid() {
    TaskGetDto createdTask = taskService.createTaskAsDto(getRandomTask());
    taskService.deleteTask(createdTask.getId()).check()
        .isDeleted(createdTask.getId());
  }
}
