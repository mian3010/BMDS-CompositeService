package bieberfever.compositeservice;

public class CompositeService {

	/**
	 * Niels
	 * @param attendantId
	 * @param options
	 * @return
	 */
	public static String getAttendantTasks(String attendantId, int options) {
		if(options < 1 || options > 3) throw new IllegalArgumentException("Uh-oh - only numbers between 1 and 3 are allowed in CompositeService methods.");
		
		switch(options) {
			case(1):
				return getSoapAttendantTasks(attendantId);
			case(2):
				return getRestAttendantTasks(attendantId);
			default:
				//Get tasks from both services
				TaskList restTasks = JaxbUtils.xmlToTaskList(getRestAttendantTasks(attendantId));
				TaskList soapTasks = JaxbUtils.xmlToTaskList(getSoapAttendantTasks(attendantId));
				//Remove duplicates
				for (Task t : restTasks.list) {
					if (soapTasks.list.contains(t)) {
						soapTasks.list.remove(t);
					}
				}
				//merge and return xml
				restTasks.list.addAll(soapTasks.list);
				return JaxbUtils.taskListToXml(restTasks);
		}
	}
	
	/**
	 * Get a task based on an attendant ID
	 * @param attendantId The ID of the attendant
	 * @return A task matching the attendant ID
	 */
	private static String getRestAttendantTasks(String attendantId) {
		return RestUtils.doRestCall("http://trustcare.itu.dk/task-manager-rest/tasks/attendant/"+ attendantId, "GET", null);
	}
	
	/**
	 * Michael
	 * @param attendantId
	 * @return
	 */
	private static String getSoapAttendantTasks(String attendantId) {
		return SoapUtils.getAttendantTasks(attendantId);
	}
	
	/**
	 * Niels
	 * @param taskXml
	 * @param options
	 */
	public static void createTask(String taskXml, int options) {
		if(options < 1 || options > 3) throw new IllegalArgumentException("Uh-oh - only numbers between 1 and 3 are allowed in CompositeService methods.");
		
		switch(options) {
			case(1):
				createSoapTask(taskXml);
				break;
			case(2):
				createRestTask(taskXml);
				break;
			default:
				createSoapTask(taskXml);
				createRestTask(taskXml);
				break;
		}
	}
	
	/**
	 * Creates a task based on the given XML
	 * @param taskXml The XML to create the task from
	 */
	private static void createRestTask(String taskXml) {
		System.out.println(RestUtils.doRestCall("http://trustcare.itu.dk/task-manager-rest/tasks/createtask", "POST", taskXml));
	}
	
	/**
	 * Michael
	 * @param taskXml
	 */
	private static void createSoapTask(String taskXml) {
		SoapUtils.createTask(taskXml);
	}
	
	/**
	 * Niels
	 * @param task_id
	 * @param option
	 */
	public static void deleteTask(String taskId, int options) {
		if(options < 1 || options > 3) throw new IllegalArgumentException("Uh-oh - only numbers between 1 and 3 are allowed in CompositeService methods.");
		
		switch(options) {
			case(1):
				deleteSoapTask(taskId);
				break;
			case(2):
				deleteRestTask(taskId);
				break;
			default:
				deleteSoapTask(taskId);
				deleteRestTask(taskId);
				break;
		}
	}
	
	/**
	 * Deletes a task in the REST service based on a taskId
	 * @param taskId The taskId of the Task to delete
	 */
	private static void deleteRestTask(String taskId) {
		RestUtils.doRestCall("http://trustcare.itu.dk/task-manager-rest/tasks/DeleteTask?taskId=" + taskId, "DELETE", null);
	}
	
	/**
	 * Michael
	 * @param task_id
	 */
	private static void deleteSoapTask(String taskId) {
		SoapUtils.deleteTask(taskId);
	}
}