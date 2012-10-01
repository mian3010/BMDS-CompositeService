package bieberfever.compositeservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(name="CompositeService", targetNamespace = "http://CompositeService.itu.dk",serviceName="CompositeService")
public class ServiceWrapper {
  
  public String getAttendantTasks(String attendantId, int options) { System.out.println(attendantId); return CompositeService.getAttendantTasks(attendantId, options); }
  public void createTask(String taskXml, int options) { CompositeService.createTask(taskXml, options); }
  public void deleteTask(String taskId, int options) { CompositeService.deleteTask(taskId, options); }
  
  public static void main(String[] args) {
    Endpoint.publish("http://localhost:8085/CompositeService", new ServiceWrapper());
    System.out.println("Endpoint published");
  }

} 
