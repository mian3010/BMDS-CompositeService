package bieberfever.compositeservice;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class SoapUtils {
  private static String serviceNs = "http://itu.dk/smds-e2012/lab/week-04/";
  private static String serviceUri = "http://trustcare.itu.dk/task-manager-soap/TaskManagerService.svc";

  public static String doSoapCall(String soapMethod, String[] params) {
    SoapUtils utils = new SoapUtils();
    // Initialise SOAP
    QName service = new QName(serviceNs, "TaskManagerService");
    QName port = new QName(serviceNs, "BasicHttpBinding_ITaskManagerService");
    Service webService = Service.create(service);
    webService.addPort(port, SOAPBinding.SOAP11HTTP_BINDING, serviceUri);
    
    try {
      // Create the request to send to server
      MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
      
      SOAPMessage message = factory.createMessage();
      SOAPPart soap = message.getSOAPPart();
      SOAPHeader header = message.getSOAPHeader();
      //header.detachNode();
      
      SOAPEnvelope envelope = soap.getEnvelope();
//      envelope.setAttribute("namespace",utils.serviceNs);
      
      Name headername = envelope.createName("SOAPAction");
      SOAPHeaderElement soapaction = header.addHeaderElement(headername);
      soapaction.setActor("Hello World");
      soapaction.setMustUnderstand(true);
      
      SOAPBody body = envelope.getBody();
      QName bodyName = new QName(utils.serviceNs, soapMethod);
      SOAPElement bodyElement = body.addBodyElement(bodyName);
      
      SOAPElement symbol = bodyElement.addChildElement("arg0");
      symbol.setTextContent("tch-01");
      
      //SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
      
      //SOAPMessage response = connection.call(message, utils.serviceUri);
      
      //connection.close();
      
      //SOAPBody responseBody = response.getSOAPBody();
      
      //SOAPBodyElement responseElement = (SOAPBodyElement)responseBody.getChildElements().next();
      //SOAPElement returnElement = (SOAPElement)responseElement.getChildElements().next();
      
//      if(responseBody.getFault() != null) { //-- If response has any fault.
//          System.out.println(returnElement.getValue()+"\n"+responseBody.getFault().getFaultString());
//      }  else  {
//          System.out.println(returnElement.getValue()+"\n");
//      }


      
      
//      MimeHeaders mimeHeader = request.getMimeHeaders(); 
//      mimeHeader.setHeader("SOAPAction", utils.serviceUri);
//      SOAPPart soap = request.getSOAPPart();
//      SOAPEnvelope envelope = soap.getEnvelope();
//      SOAPBody body = envelope.getBody();
//      SOAPElement content = body.addBodyElement(new QName(utils.serviceNs,soapMethod));
//
//      SOAPElement name;
//      int argNum = 0;
//      for (String param : params) {
//        name = content.addChildElement("arg" + argNum++);
//        name.setTextContent(param);
//      }

      Utils.print(message);

      // Send request to server
      Dispatch<SOAPMessage> dispatch = webService.createDispatch(port,SOAPMessage.class, Service.Mode.MESSAGE);
      SOAPMessage response = dispatch.invoke(message);
      String text = response.getSOAPBody().getTextContent();
      
      Utils.print(response);
//      
//      System.out.println(text);
    } catch (SOAPException e) {
      System.out.println(e);
      e.printStackTrace();
    }

    return "";
  }

  public static void main(String[] args) {
    String[] params = {};
    doSoapCall("GetTask", params);
  }
}
