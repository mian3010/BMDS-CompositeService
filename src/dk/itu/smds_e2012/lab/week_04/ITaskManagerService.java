/**
 * ITaskManagerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package dk.itu.smds_e2012.lab.week_04;

public interface ITaskManagerService extends java.rmi.Remote {
    public java.lang.String getAllTasks() throws java.rmi.RemoteException;
    public java.lang.String getAttendantTasks(java.lang.String attendantId) throws java.rmi.RemoteException;
    public java.lang.String getTask(java.lang.String taskId) throws java.rmi.RemoteException;
    public java.lang.String createTask(java.lang.String taskXml) throws java.rmi.RemoteException;
    public java.lang.String deleteTask(java.lang.String taskId) throws java.rmi.RemoteException;
}
