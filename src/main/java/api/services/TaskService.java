package api.services;

import static api.data.TaskGenerator.getRandomTask;
import static api.services.uri.TaskServiceURI.CREATE;
import static api.services.uri.TaskServiceURI.DELETE;
import static api.services.uri.TaskServiceURI.GET_ALL;
import static api.services.uri.TaskServiceURI.GET_ONE;
import static api.services.uri.TaskServiceURI.UPDATE;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import api.dto.TaskCreateDto;
import api.dto.TaskGetDto;
import api.services.assertions.TaskAssertions;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Getter;

@Getter
public class TaskService extends CommonService {
  private Response response;

  @Step("Send POST request to task service to create task")
  public TaskService createTask(TaskCreateDto task) {
    response = postNoParams(CREATE, new Gson().toJson(task));
    return this;
  }

  @Step("Send POST request to task service to create task")
  public TaskGetDto createTaskAsDto(TaskCreateDto task) {
    response = postNoParams(CREATE, new Gson().toJson(task));
    return response.as(TaskGetDto.class);
  }

  @Step("Send GET request to task service to get created task")
  public TaskService getTask(long id) {
    response = getNoParams(format(GET_ONE, id), EMPTY);
    return this;
  }

  @Step("Send GET request to task service to get all created task")
  public TaskService getAllTasks() {
    response = getNoParams(format(GET_ALL), EMPTY);
    return this;
  }

  @Step("Send UPDATE request to task service to update created task")
  public TaskService updateTask(long id, String newName) {
    response = postNoParams(format(UPDATE, id), new Gson()
        .toJson(getRandomTask().setContent(newName)));
    return this;
  }

  @Step("Send DELETE request to task service to delete created task")
  public TaskService deleteTask(long id) {
    response = deleteNoParams(format(DELETE, id), EMPTY);
    return this;
  }

  public TaskAssertions check() {
    return new TaskAssertions(response);
  }
}
