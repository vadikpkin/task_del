package api.listeners;

import api.services.TaskService;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;

public class CreatedTaskDeleteListener implements ITestListener {
  @Override
  public void onFinish(ITestContext context) {
    TaskService taskService = new TaskService();
    List<Object> id = taskService.getAllTasks().getResponse()
        .jsonPath()
        .getList("id");
    id.forEach(e -> taskService.deleteTask(Long.parseLong(e.toString())));
  }
}
