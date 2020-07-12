package api.services.assertions;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import api.dto.ProjectCreateDto;
import api.dto.ProjectGetDto;
import api.services.ProjectService;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ProjectAssertions {
  private final Response response;

  public ProjectAssertions(Response response) {
    this.response = response;
  }

  @Step("Assert status code of response")
  public ProjectAssertions statusCodeIs(int statusCode) {
    response.then()
        .statusCode(statusCode);
    return this;
  }

  @Step("Assert that project was created properly")
  public ProjectAssertions isCreated(ProjectCreateDto project) {
    assertEquals(response.as(ProjectGetDto.class).getName(), project.getName(),
        "Wrong name of crated project");
    return this;
  }

  @Step("Assert that we got proper project")
  public ProjectAssertions projectIs(ProjectGetDto createdProject) {
    assertEquals(response.as(ProjectGetDto.class), createdProject);
    return this;
  }

  @Step("Assert that project was updated")
  public ProjectAssertions isUpdated(String newName, long id) {
    response.then()
        .body(equalTo(EMPTY));
    ProjectGetDto getDto = new ProjectService().getProject(id).getResponse().as(ProjectGetDto.class);
    assertEquals(getDto.getName(), newName, "Wrong name of updated project");
    return this;
  }

  @Step("Assert that project was deleted")
  public ProjectAssertions isDeleted(long id) {
    response.then()
        .body(equalTo(EMPTY));
    new ProjectService().getProject(id).getResponse().then()
        .statusCode(SC_NOT_FOUND);
    return this;
  }
}
