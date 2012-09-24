package bieberfever.compositeservice;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompositeServiceTest {
  String testXMLTask = 
      "<task id=\"TaskTest\" " +
      		"name=\"test task 01\" " +
      		"date=\"24-09-2012\" " +
      		"status=\"pending\">" +
      " <description>Test task in XML</description>" +
      " <attendants>TestAttendee</attendants>" +
      " </task>";
  
//  @BeforeClass
//  public static void setUpClass() throws Exception {
//    
//  }

  @Test
  public void testGetAttendantTasks() {
    String result1 = null, result2 = null, result3 = null;
    result1 = CompositeService.getAttendantTasks("rao", 1);
    result1 = CompositeService.getAttendantTasks("rao", 2);
    result1 = CompositeService.getAttendantTasks("rao", 3);
    assertEquals(true, (result1 != null && !result1.isEmpty()));
    assertEquals(true, (result2 != null && !result2.isEmpty()));
    assertEquals(true, (result3 != null && !result3.isEmpty()));
    assertEquals(result1, result2);
    assertEquals(result2, result3);
  }

  @Test
  public void testCreateAndDeleteTask() {
    String result1 = null, result2 = null, result3 = null, empty = "";
    
    // Service 1
    CompositeService.createTask(testXMLTask, 1);
    result1 = CompositeService.getAttendantTasks("TestAttendee", 1);
    assertEquals(testXMLTask, result1);
    CompositeService.deleteTask("TaskTest", 1);
    assertEquals(empty, CompositeService.getAttendantTasks("TestAttendee", 1));
    
    // Service 2
    CompositeService.createTask(testXMLTask, 2);
    result2 = CompositeService.getAttendantTasks("TestAttendee", 2);
    assertEquals(testXMLTask, result2);
    CompositeService.deleteTask("TaskTest", 2);
    assertEquals(empty, CompositeService.getAttendantTasks("TestAttendee", 2));
    
    // Service 3
    CompositeService.createTask(testXMLTask, 3);
    result1 = CompositeService.getAttendantTasks("TestAttendee", 3);
    assertEquals(testXMLTask, result3);
    CompositeService.deleteTask("TaskTest", 3);
    assertEquals(empty, CompositeService.getAttendantTasks("TestAttendee", 3));
  }
}
