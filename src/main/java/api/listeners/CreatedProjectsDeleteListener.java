package api.listeners;

import api.services.ProjectService;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;

public class CreatedProjectsDeleteListener implements ITestListener {
  @Override
  public void onFinish(ITestContext context) {
    ProjectService projectService = new ProjectService();
    List<Object> id = projectService.getAllProjects().getResponse()
        .jsonPath()
        .getList("id");
    id.forEach(e -> projectService.deleteProject(Long.parseLong(e.toString())));
  }
}
