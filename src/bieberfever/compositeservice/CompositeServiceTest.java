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
      "<description>Test task in XML</description>" +
      "<attendants>TestAttendee</attendants>" +
      "</task>";
  
//  @BeforeClass
//  public static void setUpClass() throws Exception {
//    
//  }

  /**
   * Assert that getAttendantTasks returns a value when it is called with
   * option 1.
   */
  @Test
  public void testGetAttendantTasksOption1() {
	  String result = CompositeService.getAttendantTasks("rao", 1);
	  System.out.println("Service 1 " + result);
	  assertNotNull(result);
	  assertTrue(!result.isEmpty());
  }
  
  /**
   * Assert that getAttendantTasks returns a value when it is called with
   * option 2.
   */
  @Test
  public void testGetAttendantTasksOption2() {
	  String result = CompositeService.getAttendantTasks("rao", 2);
	  System.out.println("Service 2 " + result);
	  assertNotNull(result);
	  assertTrue(!result.isEmpty());
  }
  
  /**
   * Assert that getAttendantTasks returns a value when it is called with
   * option 3.
   */
  @Test
  public void testGetAttendantTasksOption3() {
	  String result = CompositeService.getAttendantTasks("rao", 3);
	  System.out.println("Service 3 " + result);
	  assertNotNull(result);
	  assertTrue(!result.isEmpty());
  }

  /**
   * Test of SOAP service. Creates and gets a test task.
   */
  @Test
  public void testCreateTaskWithService1() {
    String result = null;
    
    // Service 1
    CompositeService.createTask(testXMLTask, 1);
    result = CompositeService.getAttendantTasks("TestAttendee", 1);
    assertEquals(testXMLTask, result);
  }
  
  /**
   * Test of REST service. Creates and gets a test task.
   */
  @Test
  public void testCreateTaskWithService2() {
    String result = null;
    
    // Service 2
    CompositeService.createTask(testXMLTask, 2);
    result = CompositeService.getAttendantTasks("TestAttendee", 2);
    assertTrue(result.contains(testXMLTask));
  }
  
  /**
   * Test for third service option. Creates and gets a test task.
   */
  @Test
  public void testCreateTaskWithService3() {
    String result = null;
    
    // Service 3
    CompositeService.createTask(testXMLTask, 3);
    result = CompositeService.getAttendantTasks("TestAttendee", 3);
    assertEquals(testXMLTask, result);
  }
  
  /**
   * Delete a test task using SOAP.
   */
  @Test
  public void testDeleteTaskService1(){
    boolean taskExists = CompositeService.getAttendantTasks("TestAttendee", 1).contains("TaskTest");
    if(!taskExists){
      CompositeService.createTask(testXMLTask, 1);
    }
    CompositeService.deleteTask("TaskTest", 1);
    assertFalse(CompositeService.getAttendantTasks("TestAttendee", 1).contains("TaskTest"));
  }
  
  /**
   * Delete a test task using REST.
   */
  @Test
  public void testDeleteTaskService2(){
    boolean taskExists = CompositeService.getAttendantTasks("TestAttendee", 2).contains("TaskTest");
    if(!taskExists){
      CompositeService.createTask(testXMLTask, 2);
    }
    CompositeService.deleteTask("TaskTest", 2);
    assertFalse(CompositeService.getAttendantTasks("TestAttendee", 2).contains("TaskTest"));
  }
  
  /**
   * Test for third service option. Delete a test task.
   */
  @Test
  public void testDeleteTaskService3(){
    boolean taskExists = CompositeService.getAttendantTasks("TestAttendee", 3).contains("TaskTest");
    if(!taskExists){
      CompositeService.createTask(testXMLTask, 3);
    }
    CompositeService.deleteTask("TaskTest", 3);
    assertFalse(CompositeService.getAttendantTasks("TestAttendee", 3).contains("TaskTest"));
  }
}
