package api.services.assertions;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import api.dto.TaskCreateDto;
import api.dto.TaskGetDto;
import api.services.TaskService;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class TaskAssertions {
  private final Response response;

  public TaskAssertions(Response response) {
    this.response = response;
  }

  @Step("Assert status code of response")
  public TaskAssertions statusCodeIs(int statusCode) {
    response.then()
        .statusCode(statusCode);
    return this;
  }

  @Step("Assert that task was created")
  public TaskAssertions isCreated(TaskCreateDto task) {
    TaskGetDto getDto = response.as(TaskGetDto.class);
    assertEquals(getDto.getContent(), task.getContent(), "Wrong content of created task");
    assertEquals(getDto.getPriority(), task.getPriority(), "Wrong priority of created task");
    return this;
  }

  @Step("Assert that we get proper task")
  public TaskAssertions taskIs(TaskGetDto task) {
    assertEquals(response.as(TaskGetDto.class), task, "Wrong task returned");
    return this;
  }

  @Step("Assert that task was deleted")
  public TaskAssertions isDeleted(long id) {
    response.then()
        .body(equalTo(EMPTY));
    new TaskService().getTask(id).getResponse().then()
        .statusCode(SC_NOT_FOUND);
    return this;
  }

  @Step("Assert that task was updated")
  public TaskAssertions isUpdated(String newContent, long id) {
    response.then()
        .body(equalTo(EMPTY));
    TaskGetDto getDto = new TaskService().getTask(id).getResponse().as(TaskGetDto.class);
    assertEquals(getDto.getContent(), newContent, "Wrong content of updated task");
    return this;
  }
}
