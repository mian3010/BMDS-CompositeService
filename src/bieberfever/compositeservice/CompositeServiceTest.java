package bieberfever.compositeservice;

import static org.junit.Assert.*;

import org.junit.Test;

// Test XML task
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

  /**
   * Get tasks for attendee "rao". 
   * Checks that resulting tasks are alike from all services.
   */
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

  /**
   * Test of SOAP service. Creates, gets and deletes a test task.
   */
  @Test
  public void testCreateAndDeleteTaskWithService1() {
    String result = null, empty = "";
    
    // Service 1
    CompositeService.createTask(testXMLTask, 1);
    result = CompositeService.getAttendantTasks("TestAttendee", 1);
    assertEquals(testXMLTask, result);
    CompositeService.deleteTask("TaskTest", 1);
    assertEquals(empty, CompositeService.getAttendantTasks("TestAttendee", 1));
  }
  
  /**
   * Test of REST service. Creates, gets and deletes a test task.
   */
  @Test
  public void testCreateAndDeleteTaskWithService2() {
    String result = null, empty = "";
    
    // Service 2
    CompositeService.createTask(testXMLTask, 2);
    result = CompositeService.getAttendantTasks("TestAttendee", 2);
    assertEquals(testXMLTask, result);
    CompositeService.deleteTask("TaskTest", 2);
    assertEquals(empty, CompositeService.getAttendantTasks("TestAttendee", 2));
  }
  
  /**
   * Test for third service option. Creates, gets and deletes a test task.
   */
  @Test
  public void testCreateAndDeleteTaskWithService3() {
    String result = null, empty = "";
    
    // Service 3
    CompositeService.createTask(testXMLTask, 3);
    result = CompositeService.getAttendantTasks("TestAttendee", 3);
    assertEquals(testXMLTask, result);
    CompositeService.deleteTask("TaskTest", 3);
    assertEquals(empty, CompositeService.getAttendantTasks("TestAttendee", 3));
  }
}
