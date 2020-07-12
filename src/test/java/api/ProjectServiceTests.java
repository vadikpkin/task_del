package api;

import static api.data.ProjectGenerator.getRandomProjectCreateDto;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.apache.hc.core5.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;

import api.dto.ProjectCreateDto;
import api.dto.ProjectGetDto;
import api.listeners.CreatedProjectsDeleteListener;
import api.services.ProjectService;
import io.qameta.allure.Severity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CreatedProjectsDeleteListener.class)
public class ProjectServiceTests {
  private ProjectService projectService;

  @BeforeClass
  public void init() {
    projectService = new ProjectService();
  }

  @Test(description = "Create new project valid fields")
  @Severity(CRITICAL)
  void createProjectValid() {
    ProjectCreateDto project = getRandomProjectCreateDto();
    projectService.createProject(project).check()
        .statusCodeIs(SC_SUCCESS)
        .isCreated(project);
  }

  @Test(description = "Get created project valid id")
  @Severity(CRITICAL)
  void getProjectValid() {
    ProjectGetDto createdProject = projectService.createProjectAsDto(getRandomProjectCreateDto());
    projectService.getProject(createdProject.getId()).check()
        .statusCodeIs(SC_SUCCESS)
        .projectIs(createdProject);
  }

  @Test(description = "Update created project valid fields")
  @Severity(CRITICAL)
  void updateProjectValid() {
    ProjectGetDto createdProject = projectService.createProjectAsDto(getRandomProjectCreateDto());
    String newName = "updated name";
    projectService.updateProject(createdProject.getId(), newName).check()
        .statusCodeIs(SC_NO_CONTENT)
        .isUpdated(newName, createdProject.getId());
  }

  @Test(description = "Delete created project valid id")
  @Severity(CRITICAL)
  void deleteProjectValid() {
    ProjectGetDto createdProject = projectService.createProjectAsDto(getRandomProjectCreateDto());
    projectService.deleteProject(createdProject.getId()).check()
        .statusCodeIs(SC_NO_CONTENT)
        .isDeleted(createdProject.getId());
  }
}
