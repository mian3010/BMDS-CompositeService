package bieberfever.compositeservice;

import java.rmi.RemoteException;

import dk.itu.smds_e2012.lab.week_04.ITaskManagerService;
import dk.itu.smds_e2012.lab.week_04.ITaskManagerServiceProxy;

public class SoapUtils {
  private static String serviceNs = "http://itu.dk/smds-e2012/lab/week-04/";
  private static String serviceUri = "http://trustcare.itu.dk/task-manager-soap/TaskManagerService.svc";

  public static String getAllTasks() {
    ITaskManagerService service = new ITaskManagerServiceProxy();
    try {
      return service.getAllTasks();
    } catch (RemoteException e) {
      throw new IllegalStateException("OH NO SHIT HAPPENED", e);
    }
  }

  public static String getAttendantTasks(String attendantId) {
    ITaskManagerService service = new ITaskManagerServiceProxy();
    try {
      return service.getAttendantTasks(attendantId);
    } catch (RemoteException e) {
      throw new IllegalStateException("OH NO SHIT HAPPENED", e);
    }
  }

  public static void createTask(String taskXml) {
    ITaskManagerService service = new ITaskManagerServiceProxy();
    try {
      service.createTask(taskXml);
    } catch (RemoteException e) {
      throw new IllegalStateException("OH NO SHIT HAPPENED", e);
    }
  }

  public static void deleteTask(String taskId) {
    ITaskManagerService service = new ITaskManagerServiceProxy();
    try {
      service.deleteTask(taskId);
    } catch (RemoteException e) {
      throw new IllegalStateException("OH NO SHIT HAPPENED", e);
    }
  }
}