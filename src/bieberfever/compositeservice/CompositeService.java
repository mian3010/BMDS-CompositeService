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
				String restTasks = getRestAttendantTasks(attendantId);
				String soapTasks = getSoapAttendantTasks(attendantId);
				if(!restTasks.equals(soapTasks))
					throw new IllegalStateException("Rest and Soap services returned different results! Server out of sync.");
				else return restTasks;
		}
	}
	
	/**
	 * Niclas
	 * @param attendantId
	 * @return
	 */
	private static String getRestAttendantTasks(String attendantId) {
		
	}
	
	/**
	 * Michael
	 * @param attendantId
	 * @return
	 */
	private static String getSoapAttendantTasks(String attendantId) {
		
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
	 * Niclas
	 * @param taskXml
	 */
	private static void createRestTask(String taskXml) {
		
	}
	
	/**
	 * Michael
	 * @param taskXml
	 */
	private static void createSoapTask(String taskXml) {
		
	}
	
	/**
	 * Niels
	 * @param task_id
	 * @param option
	 */
	public static void deleteTask(String task_id, int options) {
		if(options < 1 || options > 3) throw new IllegalArgumentException("Uh-oh - only numbers between 1 and 3 are allowed in CompositeService methods.");
		
		switch(options) {
			case(1):
				deleteSoapTask(task_id);
				break;
			case(2):
				deleteRestTask(task_id);
				break;
			default:
				deleteSoapTask(task_id);
				deleteRestTask(task_id);
				break;
		}
	}
	
	/**
	 * Niclas
	 * @param task_id
	 */
	private static void deleteRestTask(String task_id) {
		
	}
	
	/**
	 * Michael
	 * @param task_id
	 */
	private static void deleteSoapTask(String task_id) {
		
	}

}
