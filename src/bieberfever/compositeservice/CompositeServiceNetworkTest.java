package bieberfever.compositeservice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.junit.BeforeClass;
import org.junit.Test;

// Test XML task
public class CompositeServiceNetworkTest {
  String testXMLTask = 
      "<task id=\"TaskTest\" " +
      		"name=\"test task 01\" " +
      		"date=\"24-09-2012\" " +
      		"status=\"pending\">" +
      "<description>Test task in XML</description>" +
      "<attendants>TestAttendee</attendants>" +
      "</task>";
  QName service;
  QName port;
  String endpointAddress;
  Service webservice;
  String serviceNs = "http://CompositeService.itu.dk";
  String serviceUri = "http://localhost:8085/CompositeService";
  
  public CompositeServiceNetworkTest() {
    service = new QName(serviceNs, "CompositeService");
    port =  new QName(serviceNs, "CompositeServicePort");
    endpointAddress = serviceUri;
    webservice = Service.create(service);
    webservice.addPort(port, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
  }
  public String invokeServiceMethod(String methodName, String[] args) {
    try {
      MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
      SOAPMessage request = factory.createMessage();
      SOAPPart soap = request.getSOAPPart();
      SOAPEnvelope envelope = soap.getEnvelope();
      SOAPBody body = envelope.getBody();
      SOAPElement content = body.addBodyElement(new QName(serviceNs, methodName, "itu"));
      int c = 0;
      for (String arg : args) {
        SOAPElement name;
        name = content.addChildElement("arg"+c++);
        name.setTextContent(arg);
      }
      Dispatch<SOAPMessage> dispatch =
      webservice.createDispatch(port, SOAPMessage.class,
      Service.Mode.MESSAGE);
      SOAPMessage response = dispatch.invoke(request);
      String text = response.getSOAPBody().getTextContent();
      return text;
    }  catch (SOAPException e) {
      throw new RuntimeException(e);
    }
  }
  @BeforeClass
  public static void setUpClass() throws Exception {
    ServiceWrapper.main(null);
  }

  /**
   * Assert that getAttendantTasks returns a value when it is called with
   * option 1.
   */
  @Test
  public void testGetAttendantTasksOption1() {
    String[] args = {"rao", "1"};
	  String result = invokeServiceMethod("getAttendantTasks", args);
	  System.out.println(result);
    assertTrue(result.contains("rao"));
  }
  
  /**
   * Assert that getAttendantTasks returns a value when it is called with
   * option 2.
   */
  @Test
  public void testGetAttendantTasksOption2() {
    String[] args = {"rao", "2"};
	  String result = invokeServiceMethod("getAttendantTasks", args);
    assertTrue(result.contains("rao"));
  }
  
  /**
   * Assert that getAttendantTasks returns a value when it is called with
   * option 3.
   */
  @Test
  public void testGetAttendantTasksOption3() {
    String[] args = {"rao", "3"};
	  String result = invokeServiceMethod("getAttendantTasks", args);
    assertTrue(result.contains("rao"));
  }

  /**
   * Test of SOAP service. Creates and gets a test task.
   */
  @Test
  public void testCreateTaskWithService1() {
    String result = null;
    
    // Service 1
    String[] args1 = {testXMLTask, "1"};
    invokeServiceMethod("createTask", args1);
    String[] args2 = {"TestAttendee", "1"};
    result = invokeServiceMethod("getAttendantTasks", args2);
    TaskList taskList = JaxbUtils.xmlToTaskList(result);
    assertTrue(taskList.list.contains(JaxbUtils.xmlToTask(testXMLTask)));
  }
  
  /**
   * Test of REST service. Creates and gets a test task.
   */
  @Test
  public void testCreateTaskWithService2() {
    String result = null;
    
    // Service 2
    String[] args1 = {testXMLTask, "2"};
    invokeServiceMethod("createTask", args1);
    String[] args2 = {"TestAttendee", "2"};
    result = invokeServiceMethod("getAttendantTasks", args2);
    TaskList taskList = JaxbUtils.xmlToTaskList(result);
    assertTrue(taskList.list.contains(JaxbUtils.xmlToTask(testXMLTask)));
  }
  
  /**
   * Test for third service option. Creates and gets a test task.
   */
  @Test
  public void testCreateTaskWithService3() {
    String result = null;
    
    // Service 3
    String[] args1 = {testXMLTask, "3"};
    invokeServiceMethod("createTask", args1);
    String[] args2 = {"TestAttendee", "3"};
    result = invokeServiceMethod("getAttendantTasks", args2);
    TaskList taskList = JaxbUtils.xmlToTaskList(result);
    assertTrue(taskList.list.contains(JaxbUtils.xmlToTask(testXMLTask)));
  }
  
  /**
   * Test of SOAP service. Creates and gets a test task.
   */
  @Test
  public void testCreateTaskWithService1HashCheck() {
    String result = null;
    boolean pass = false;
    Task testTask = JaxbUtils.xmlToTask(testXMLTask);
    
    // Service 1
    String[] args1 = {testXMLTask, "1"};
    invokeServiceMethod("createTask", args1);
    String[] args2 = {"TestAttendee", "1"};
    result = invokeServiceMethod("getAttendantTasks", args2);
    TaskList taskList = JaxbUtils.xmlToTaskList(result);
    Iterator<Task> it = taskList.list.iterator();
    Task task = null;
    while(it.hasNext()){
      task = it.next();
      if(task.equals(testTask)){
        pass = true;
        break;
      }
    }
    assertTrue(pass);
  }
  
  /**
   * Test of REST service. Creates and gets a test task.
   */
  @Test
  public void testCreateTaskWithService2HashCheck() {
    String result = null;
    boolean pass = false;
    Task testTask = JaxbUtils.xmlToTask(testXMLTask);
    
    // Service 2
    String[] args1 = {testXMLTask, "2"};
    invokeServiceMethod("createTask", args1);
    String[] args2 = {"TestAttendee", "2"};
    result = invokeServiceMethod("getAttendantTasks", args2);
    TaskList taskList = JaxbUtils.xmlToTaskList(result);
    Iterator<Task> it = taskList.list.iterator();
    Task task = null;
    while(it.hasNext()){
      task = it.next();
      if(task.equals(testTask)){
        pass = true;
        break;
      }
    }
    assertTrue(pass);
  }
  
  /**
   * Test for third service option. Creates and gets a test task.
   */
  @Test
  public void testCreateTaskWithService3HashCheck() {
    String result = null;
    boolean pass = false;
    Task testTask = JaxbUtils.xmlToTask(testXMLTask);
    
    // Service 3
    String[] args1 = {testXMLTask, "3"};
    invokeServiceMethod("createTask", args1);
    String[] args2 = {"TestAttendee", "3"};
    result = invokeServiceMethod("getAttendantTasks", args2);
    TaskList taskList = JaxbUtils.xmlToTaskList(result);
    Iterator<Task> it = taskList.list.iterator();
    Task task = null;
    while(it.hasNext()){
      task = it.next();
      if(task.equals(testTask)){
        pass = true;
        break;
      }
    }
    assertTrue(pass);
  }
  
  /**
   * Delete a test task using SOAP.
   */
  @Test
  public void testDeleteTaskService1(){
    String[] args1 = {"TestAttendee", "1"};
    boolean taskExists = invokeServiceMethod("getAttendantTasks", args1).contains("TaskTest");
    if(!taskExists){
      String[] args2 = {testXMLTask, "1"};
      invokeServiceMethod("createTask", args2);
    }
    String[] args3 = {"TaskTest", "1"};
    invokeServiceMethod("deleteTask", args3);
    String[] args4 = {"TestAttendee", "1"};
    assertFalse(invokeServiceMethod("getAttendantTasks", args4).contains("TaskTest"));
  }
  
  /**
   * Delete a test task using REST.
   */
  @Test
  public void testDeleteTaskService2(){
    String[] args1 = {"TestAttendee", "2"};
    boolean taskExists = invokeServiceMethod("getAttendantTasks", args1).contains("TaskTest");
    if(!taskExists){
      String[] args2 = {testXMLTask, "2"};
      invokeServiceMethod("createTask", args2);
    }
    String[] args3 = {"TaskTest", "2"};
    invokeServiceMethod("deleteTask", args3);
    String[] args4 = {"TestAttendee", "2"};
    assertFalse(invokeServiceMethod("getAttendantTasks", args4).contains("TaskTest"));
  }
  
  /**
   * Test for third service option. Delete a test task.
   */
  @Test
  public void testDeleteTaskService3(){
    String[] args1 = {"TestAttendee", "3"};
    boolean taskExists = invokeServiceMethod("getAttendantTasks", args1).contains("TaskTest");
    if(!taskExists){
      String[] args2 = {testXMLTask, "3"};
      invokeServiceMethod("createTask", args2);
    }
    String[] args3 = {"TaskTest", "3"};
    invokeServiceMethod("deleteTask", args3);
    String[] args4 = {"TestAttendee", "3"};
    assertFalse(invokeServiceMethod("getAttendantTasks", args4).contains("TaskTest"));
  }
}
