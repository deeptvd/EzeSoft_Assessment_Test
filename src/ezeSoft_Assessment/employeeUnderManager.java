package ezeSoft_Assessment;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class employeeUnderManager {
	
	static Map<String,List<String>> result = new HashMap<String,List<String>>();
	
	public static void main(String args[]) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("Alex", "Sam");
		data.put("Alex", "Ed");
		data.put("Alex", "Martin");
		data.put("Ed", "Cran");
		data.put("Ed", "Kathy");
		data.put("Ed", "George");
		data.put("George", "Olivia");
		data.put("George", "Jerry");
		data.put("George", "Phil");
		
		populateResult(data);
		System.out.println("result " + result);	
	}
	
	private static void populateResult(Map<String, String> data)
    {
		
		Map<String, List<String>> mngrEmpMap = new HashMap<String, List<String>>();
		
		for (Map.Entry<String,String> entry: data.entrySet()) {
			String manager = entry.getKey();
			String employee = entry.getValue();
			
			 {
	                List<String> reportList = mngrEmpMap.get(manager);
	 
	                if (reportList == null)
	                    reportList = new ArrayList<String>();
	 
	                reportList.add(employee);
	                
	                mngrEmpMap.put(manager, reportList);
	            }
		}
		
		for (String manager: data.keySet())
            populateListUtil(manager, mngrEmpMap);
    }
	
private static ArrayList<String> populateListUtil(String manager, Map<String, List<String>> mngrEmpMap)
	 {
	
		ArrayList<String> empList = new ArrayList<String>(); //Assume a manager can have 50 employees at max
		
		//Check if the given employee is not a manager	
		if (!(mngrEmpMap.containsKey(manager)))
			{
				return null;
			}
			
			// this employee count has already been done by this
			// method, so avoid re-computation
			else if (result.containsKey(manager))
				empList.addAll(result.get(manager));
			
			else
			{
				List<String> directReportEmpList = mngrEmpMap.get(manager);
				for (String directReportEmp: directReportEmpList)
					empList.addAll(populateListUtil(directReportEmp, mngrEmpMap)); 
				
				result.put(manager, empList);
			}
			return empList;
		}
}
