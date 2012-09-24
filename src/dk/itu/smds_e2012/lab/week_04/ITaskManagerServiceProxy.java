package dk.itu.smds_e2012.lab.week_04;

public class ITaskManagerServiceProxy implements dk.itu.smds_e2012.lab.week_04.ITaskManagerService {
  private String _endpoint = null;
  private dk.itu.smds_e2012.lab.week_04.ITaskManagerService iTaskManagerService = null;
  
  public ITaskManagerServiceProxy() {
    _initITaskManagerServiceProxy();
  }
  
  public ITaskManagerServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initITaskManagerServiceProxy();
  }
  
  private void _initITaskManagerServiceProxy() {
    try {
      iTaskManagerService = (new org.tempuri.TaskManagerServiceLocator()).getBasicHttpBinding_ITaskManagerService();
      if (iTaskManagerService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iTaskManagerService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iTaskManagerService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iTaskManagerService != null)
      ((javax.xml.rpc.Stub)iTaskManagerService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public dk.itu.smds_e2012.lab.week_04.ITaskManagerService getITaskManagerService() {
    if (iTaskManagerService == null)
      _initITaskManagerServiceProxy();
    return iTaskManagerService;
  }
  
  public java.lang.String getAllTasks() throws java.rmi.RemoteException{
    if (iTaskManagerService == null)
      _initITaskManagerServiceProxy();
    return iTaskManagerService.getAllTasks();
  }
  
  public java.lang.String getAttendantTasks(java.lang.String attendantId) throws java.rmi.RemoteException{
    if (iTaskManagerService == null)
      _initITaskManagerServiceProxy();
    return iTaskManagerService.getAttendantTasks(attendantId);
  }
  
  public java.lang.String getTask(java.lang.String taskId) throws java.rmi.RemoteException{
    if (iTaskManagerService == null)
      _initITaskManagerServiceProxy();
    return iTaskManagerService.getTask(taskId);
  }
  
  public java.lang.String createTask(java.lang.String taskXml) throws java.rmi.RemoteException{
    if (iTaskManagerService == null)
      _initITaskManagerServiceProxy();
    return iTaskManagerService.createTask(taskXml);
  }
  
  public java.lang.String deleteTask(java.lang.String taskId) throws java.rmi.RemoteException{
    if (iTaskManagerService == null)
      _initITaskManagerServiceProxy();
    return iTaskManagerService.deleteTask(taskId);
  }
  
  
}