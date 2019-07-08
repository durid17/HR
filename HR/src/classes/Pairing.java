package classes;

import java.util.*;

public class Pairing {
	private DBManager manager;
	
	public Pairing(DBManager manager) {
		this.manager = manager;
	}
	
	public List<Vacancy> getVacancies(int employeeId){
		Employee employee = manager.getEmployee(employeeId);
		EmployeeProfile prof = employee.getProfile();
		List<String> tags = manager.getEmployeeTags(employeeId);
		List<Language> lan = manager.getEmployeeLanguages(employeeId);
		List<Vacancy> all = manager.getVacancies();
		List<Vacancy> res = new ArrayList<>();
		for(Vacancy vacancy : all) {
			Requirement req = vacancy.getReq();
			int years = getYearsOfExp(employee.getId() , req.getProfession());
			if(years < req.getYearsOfExp()) continue;
			if(!prof.getAddress().equals(req.getLocation())) continue;
			if(!Qualified(employee.getId() , req.getProfession() , req.getDegree())) continue;
			res.add(vacancy);
		}	
		
		res.sort(new Comparator<Vacancy>() {
			@Override
			public int compare(Vacancy arg0, Vacancy arg1) {
				Requirement req0 = arg0.getReq();
				Requirement req1 = arg1.getReq();

				boolean firstMajor = hasMajor(employee.getId(), req0.getProfession(), req0.getDegree());
				boolean secondMajor = hasMajor(employee.getId(), req1.getProfession(), req1.getDegree());
				if(firstMajor && ! secondMajor) return 1;
				if(!firstMajor && secondMajor) return -1;
				
				boolean firstMinor = hasMajor(employee.getId(), req0.getProfession(), req0.getDegree());
				boolean secondMinor = hasMajor(employee.getId(), req1.getProfession(), req1.getDegree());
				if(firstMinor && !secondMinor) return 1;
				if(!firstMinor && secondMinor) return -1;
				
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
		
		return res;
	}
	
	public List<Employee> getEmoloyees(int vacancyId){
		Vacancy vacancy = manager.getVacancy(vacancyId);
		Requirement req = vacancy.getReq();
		List<Language> lan = manager.getRequirementLanguages(vacancyId);
		List<String> tags = manager.getVacancyTags(vacancyId);
		List<Employee> applicants =  manager.getVacancyApplicants(vacancyId);
		List<Employee> res = new ArrayList<>();
		for(Employee employee : applicants) {
			int years = getYearsOfExp(employee.getId() , req.getProfession());
			EmployeeProfile prof = employee.getProfile();
			if(years < req.getYearsOfExp()) continue;
			if(!prof.getAddress().equals(req.getLocation())) continue;
			if(!Qualified(employee.getId() , req.getProfession() , req.getDegree())) continue;
			res.add(employee);
		}	
		
		res.sort(new Comparator<Employee>() {
			@Override
			public int compare(Employee arg0, Employee arg1) {
				boolean firstMajor = hasMajor(arg0.getId(), req.getProfession(), req.getDegree());
				boolean secondMajor = hasMajor(arg1.getId(), req.getProfession(), req.getDegree());
				if(firstMajor && ! secondMajor) return 1;
				if(!firstMajor && secondMajor) return -1;
				
				boolean firstMinor = hasMajor(arg0.getId(), req.getProfession(), req.getDegree());
				boolean secondMinor = hasMajor(arg1.getId(), req.getProfession(), req.getDegree());
				if(firstMinor && !secondMinor) return 1;
				if(!firstMinor && secondMinor) return -1;
				
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
		
		return res;
	}
	
	private List<String> values(List<Language> lan){
		List<String> res = new ArrayList<String>();
		for(Language l : lan) {
			res.add(l.getLanguage());
		}
		return res;
	}
	
	private int getCommon(List<String> tags, List<String> employeeTags) {
		int res = 0;
		for(String s: tags) {
			if(employeeTags.contains(s)) res++;
		}
		return res;
	}
	
	private boolean hasMinor(int employeeId, String profession, String degree) {
		List<EmployeeEducation> edu = manager.getEducation(employeeId);
		for(EmployeeEducation e : edu) {
			if(e.getMinor().equals(profession) && e.getDegree().equals(degree)) return true;
		}
		return false;
	}
	
	private boolean hasMajor(int employeeId, String profession, String degree) {
		List<EmployeeEducation> edu = manager.getEducation(employeeId);
		for(EmployeeEducation e : edu) {
			if(e.getMajor().equals(profession) && e.getDegree().equals(degree)) return true;
		}
		return false;
	}

	private boolean Qualified(int employeeId, String profession, String degree) {
		return hasMajor(employeeId, profession, degree) || hasMinor(employeeId, profession, degree);
	}

	private int getYearsOfExp(int employeeId , String proff) {
		List<WorkExperience> exp = manager.getWorkExps(employeeId);
		int sum = 0;
		for(WorkExperience e : exp) {
			if(proff.equals(e.getProfession()))
					sum += MyDateFormatter.yearsBetween(e.getStartDate() , e.getEndDate());
		}
		return sum;
	}
}

