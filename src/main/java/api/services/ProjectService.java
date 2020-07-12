package api.services;

import static api.services.uri.ProjectServiceURI.DELETE;
import static api.services.uri.ProjectServiceURI.GET_ALL;
import static api.services.uri.ProjectServiceURI.GET_ONE;
import static api.services.uri.ProjectServiceURI.POST;
import static api.services.uri.ProjectServiceURI.UPDATE;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import api.dto.ProjectCreateDto;
import api.dto.ProjectGetDto;
import api.services.assertions.ProjectAssertions;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Getter;

@Getter
public class ProjectService extends CommonService {
  private Response response;

  @Step("Send POST request to project service to create new project")
  public ProjectService createProject(ProjectCreateDto project) {
    response = postNoParams(POST, new Gson().toJson(project));
    return this;
  }

  @Step("Send GET request to project service to create new project")
  public ProjectGetDto createProjectAsDto(ProjectCreateDto project) {
    response = postNoParams(POST, new Gson().toJson(project));
    return response.as(ProjectGetDto.class);
  }

  @Step("Send POST request to project service to update created project")
  public ProjectService updateProject(long id, String updatedName) {
    response = postNoParams(format(UPDATE, id), new Gson()
        .toJson(new ProjectCreateDto().setName(updatedName)));
    return this;
  }

  @Step("Send GET request to project service to get one project")
  public ProjectService getProject(long id) {
    response = getNoParams(format(GET_ONE, id), EMPTY);
    return this;
  }

  @Step("Send GET request to project service to get all created projects")
  public ProjectService getAllProjects() {
    response = getNoParams(GET_ALL, EMPTY);
    return this;
  }

  @Step("Send DELETE request to project service to delete created project")
  public ProjectService deleteProject(long id) {
    response = deleteNoParams(format(DELETE, id), EMPTY);
    return this;
  }

  public ProjectAssertions check() {
    return new ProjectAssertions(response);
  }
}
