package classes;

import java.util.*;

/**
 * The Class Pairing.
 */
public class Pairing {
	
	/** The manager. */
	private DBManager manager;
	
	/** The max number of applicants. */
	private final int MAX_NUMBER_OF_APPLICANTS = 18;
	
	/** The max number of vacancies. */
	private final int MAX_NUMBER_OF_VACANCIES = 30;

	
	/**
	 * Instantiates a new pairing.
	 *
	 * @param manager the manager
	 */
	public Pairing(DBManager manager) {
		this.manager = manager;
	}
	
	/**
	 * Gets the vacancies.
	 *
	 * @param employeeId the employee id
	 * @return the best vacancies suitable for employee
	 * generated with a special algrithm
	 */
	public List<Vacancy> getVacancies(int employeeId){
		Employee employee = manager.getEmployee(employeeId);
		List<String> tags = manager.getEmployeeTags(employeeId);
		List<Language> lan = manager.getEmployeeLanguages(employeeId);
		List<Vacancy> all = manager.getVacancies();
		
		all.sort(new Comparator<Vacancy>() {
			@Override
			public int compare(Vacancy arg0, Vacancy arg1) {
				Requirement req0 = arg0.getReq();
				Requirement req1 = arg1.getReq();
				
				boolean firstMajor = hasMajor(employee.getId(), req0.getProfession(), req0.getDegree());
				boolean secondMajor = hasMajor(employee.getId(), req1.getProfession(), req1.getDegree());
				if(firstMajor && !secondMajor) return 1;
				if(!firstMajor && secondMajor) return -1;
				
				boolean firstMinor = hasMinor(employee.getId(), req0.getProfession(), req0.getDegree());
				boolean secondMinor = hasMinor(employee.getId(), req1.getProfession(), req1.getDegree());
				
				if(firstMinor && !secondMinor) return 1;
				if(!firstMinor && secondMinor) return -1;
				
				int firstExp = getDaysOfExp(employee.getId(), req0.getProfession());
				int seconfExp = getDaysOfExp(employee.getId(), req1.getProfession());
				if(firstExp > seconfExp) return 1;
				if(firstExp < seconfExp) return -1;
				
				int firstCommon = getCommon(tags , manager.getVacancyTags(arg0.getId()));
				int secondCommon = getCommon(tags , manager.getVacancyTags(arg1.getId()));
				if(firstCommon > secondCommon) return 1;
				if(firstCommon < secondCommon) return -1;
				
				int firstLanCommon = getCommon(values(lan), values(manager.getRequirementLanguages(arg0.getId())));
				int secondLanCommon = getCommon(values(lan), values(manager.getRequirementLanguages(arg1.getId())));
				if(firstLanCommon > secondLanCommon) return 1;
				if(firstLanCommon < secondLanCommon) return -1;

				return 0;
			}		
		});
		List<Vacancy> res = all.subList(0, Math.min(all.size(), MAX_NUMBER_OF_VACANCIES));
		Collections.reverse(res);
		return res;
	}
	
	/**
	 * Gets the emoloyees.
	 *
	 * @param vacancyId the vacancy id
	 * @return best employees suitable for vacancy
	 * generated with our special algrithm
	 */
	public List<Employee> getEmoloyees(int vacancyId){
		Vacancy vacancy = manager.getVacancy(vacancyId);
		Requirement req = vacancy.getReq();
		List<Language> lan = manager.getRequirementLanguages(vacancyId);
		List<String> tags = manager.getVacancyTags(vacancyId);
		List<Employee> applicants =  manager.getVacancyApplicants(vacancyId);
		
		applicants.sort(new Comparator<Employee>() {
			@Override
			public int compare(Employee arg0, Employee arg1) {
				boolean firstMajor = hasMajor(arg0.getId(), req.getProfession(), req.getDegree());
				boolean secondMajor = hasMajor(arg1.getId(), req.getProfession(), req.getDegree());
				if(firstMajor && !secondMajor) return 1;
				if(!firstMajor && secondMajor) return -1;
				
				boolean firstMinor = hasMinor(arg0.getId(), req.getProfession(), req.getDegree());
				boolean secondMinor = hasMinor(arg1.getId(), req.getProfession(), req.getDegree());
				if(firstMinor && !secondMinor) return 1;
				if(!firstMinor && secondMinor) return -1;
				
				int firstExp = getDaysOfExp(arg0.getId(), req.getProfession());
				int seconfExp = getDaysOfExp(arg1.getId(), req.getProfession());
				if(firstExp > seconfExp) return 1;
				if(firstExp < seconfExp) return -1;
				
				int firstCommon = getCommon(tags , manager.getEmployeeTags(arg0.getId()));
				int secondCommon = getCommon(tags , manager.getEmployeeTags(arg1.getId()));
				if(firstCommon > secondCommon) return 1;
				if(firstCommon < secondCommon) return -1;
				
				int firstLanCommon = getCommon(values(lan), values(manager.getEmployeeLanguages(arg0.getId())));
				int secondLanCommon = getCommon(values(lan), values(manager.getEmployeeLanguages(arg1.getId())));
				if(firstLanCommon > secondLanCommon) return 1;
				if(firstLanCommon < secondLanCommon) return -1;
				
				return 0;
			}		
		});
		List<Employee> res = applicants.subList(0, Math.min(applicants.size(), MAX_NUMBER_OF_APPLICANTS));
		Collections.reverse(res);
		return res;
	}
	
	/**
	 * Values.
	 *
	 * @param List of languages
	 * @return List og Languages values in strings
	 */
	private List<String> values(List<Language> lan){
		List<String> res = new ArrayList<String>();
		for(Language l : lan) {
			res.add(l.getLanguage());
		}
		return res;
	}
	
	/**
	 * Gets the common.
	 *
	 * @param tags the vacancy tags
	 * @param employeeTags the employee tags
	 * @return common tags between this two
	 */
	private int getCommon(List<String> tags, List<String> employeeTags) {
		int res = 0;
		for(String s: tags) {
			if(employeeTags.contains(s)) res++;
		}
		return res;
	}
	
	/**
	 * Checks for minor.
	 *
	 * @param employeeId the employee id
	 * @param profession the profession
	 * @param degree the degree
	 * @return true, if employees has minor education in this profession with this degree
	 */
	private boolean hasMinor(int employeeId, String profession, String degree) {
		List<EmployeeEducation> edu = manager.getEducation(employeeId);
		for(EmployeeEducation e : edu) {
			if(e.getMinor().equals(profession) && e.getDegree().equals(degree)) return true;
		}
		return false;
	}
	
	/**
	 * Checks for major.
	 *
	 * @param employeeId the employee id
	 * @param profession the profession
	 * @param degree the degree
	 * @return true, if if employees has major education in this profession with this degree
	 */
	private boolean hasMajor(int employeeId, String profession, String degree) {
		List<EmployeeEducation> edu = manager.getEducation(employeeId);
		for(EmployeeEducation e : edu) {
			if(e.getMajor().equals(profession) && e.getDegree().equals(degree)) return true;
		}
		return false;
	}

	/**
	 * Gets the days of experience
	 *
	 * @param employeeId the employee id
	 * @param proff the profession
	 * @return the days of experience
	 */
	private int getDaysOfExp(int employeeId , String proff) {
		List<WorkExperience> exp = manager.getWorkExps(employeeId);
		int sum = 0;
		for(WorkExperience e : exp) {
			if(proff.equals(e.getProfession()))
					sum += MyDateFormatter.daysBetween(e.getStartDate() , e.getEndDate());
		}
		return sum;
	}
}