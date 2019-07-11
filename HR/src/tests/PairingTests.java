package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import classes.*;

class PairingTests extends Mockito {
	private DBManager manager = mock(DBManager.class);
	private Pairing pairing = new Pairing(manager);
	private Map<Integer , Vacancy> vacancies = new HashMap<Integer, Vacancy>();
	private Map<Integer , List<Language>> vac_languages = new HashMap<Integer, List<Language>>();
	private Map<Integer , List<String>> vac_tags= new HashMap<Integer, List<String>>();
	private Map<Integer , List<Employee>> vac_apps= new HashMap<Integer, List<Employee>>();
	
	private Map<Integer , Employee> employees = new HashMap<Integer, Employee>();
	private Map<Integer , List<String> > employee_tags = new HashMap<Integer, List<String>>();
	private Map<Integer , List<Language> > employee_lans = new HashMap<Integer, List<Language>>();
	private Map<Integer , List<EmployeeEducation> > employee_edu = new HashMap<Integer, List<EmployeeEducation>>();	
	private Map<Integer , List<WorkExperience> > employee_exp = new HashMap<Integer, List<WorkExperience>>();
	
    public void beforeEverything() {
		
//		List<EmployeeEducation> edu = manager.getEducation(employeeId);
		final ArgumentCaptor<Integer> edu_argument = ArgumentCaptor.forClass(Integer.class);
		when(manager.getEducation(edu_argument.capture())).
				thenAnswer(new Answer() {
					@Override
					public Object answer(InvocationOnMock arg0) throws Throwable {
						return employee_edu.get(edu_argument.getValue());
					}
				});
		
//		Employee employee = manager.getEmployee(employeeId);
		final ArgumentCaptor<Integer> emp_argument = ArgumentCaptor.forClass(Integer.class);
		when(manager.getEmployee(emp_argument.capture())).
				thenAnswer(new Answer() {
					@Override
					public Object answer(InvocationOnMock arg0) throws Throwable {
						return employees.get(emp_argument.getValue());
					}
				});
		
//		List<String> tags = manager.getEmployeeTags(employeeId);
		final ArgumentCaptor<Integer> emp_tags_argument = ArgumentCaptor.forClass(Integer.class);
		when(manager.getEmployeeTags(emp_tags_argument.capture())).
				thenAnswer(new Answer() {
					@Override
					public Object answer(InvocationOnMock arg0) throws Throwable {
						return employee_tags.get(emp_tags_argument.getValue());
					}
				});
		
		//manager.getWorkExps(employeeId);
		final ArgumentCaptor<Integer> emp_exp_argument = ArgumentCaptor.forClass(Integer.class);
		when(manager.getWorkExps(emp_exp_argument.capture())).
				thenAnswer(new Answer() {
					@Override
					public Object answer(InvocationOnMock arg0) throws Throwable {
						return employee_exp.get(emp_exp_argument.getValue());
					}
				});

		
//		List<Language> lan = manager.getEmployeeLanguages(employeeId);
		final ArgumentCaptor<Integer> emp_lans_argument  = ArgumentCaptor.forClass(Integer.class);
		when(manager.getEmployeeLanguages(emp_lans_argument.capture())).
				thenAnswer(new Answer() {
					@Override
					public Object answer(InvocationOnMock arg0) throws Throwable {
						return employee_lans.get(emp_lans_argument.getValue());
					}
				});
			
//		List<Vacancy> all = manager.getVacancies();
		when(manager.getVacancies()).thenReturn(new ArrayList<Vacancy>(vacancies.values()));
		
		
//		Vacancy vacancy = manager.getVacancy(vacancyId);
		final ArgumentCaptor<Integer> vac_argument = ArgumentCaptor.forClass(Integer.class);
		when(manager.getVacancy(vac_argument.capture())).
				thenAnswer(new Answer() {
					@Override
					public Object answer(InvocationOnMock arg0) throws Throwable {
						return vacancies.get(vac_argument.getValue());
					}
				});
		
		//manager.getVacancyTags(arg0.getId());
		final ArgumentCaptor<Integer> vac_tags_argument = ArgumentCaptor.forClass(Integer.class);
		when(manager.getVacancyTags(vac_tags_argument.capture())).
				thenAnswer(new Answer() {
					@Override
					public Object answer(InvocationOnMock arg0) throws Throwable {
						return vac_tags.get(vac_tags_argument.getValue());
					}
				});
		
		
		//List<Employee> applicants =  manager.getVacancyApplicants(vacancyId);
		final ArgumentCaptor<Integer> vac_apps_argument = ArgumentCaptor.forClass(Integer.class);
		when(manager.getVacancyApplicants(vac_apps_argument.capture())).
				thenAnswer(new Answer() {
					@Override
					public Object answer(InvocationOnMock arg0) throws Throwable {
						return vac_apps.get(vac_tags_argument.getValue());
					}
				});

		final ArgumentCaptor<Integer> req_argument = ArgumentCaptor.forClass(Integer.class);
		//List<Language> lan = manager.getRequirementLanguages(vacancyId);
		when(manager.getRequirementLanguages(req_argument.capture())).
				thenAnswer(new Answer() {
					@Override
					public Object answer(InvocationOnMock arg0) throws Throwable {
						return vac_languages.get(req_argument.getValue());
					}
				});		
	}
	
	public void clearAll() {
		vacancies.clear();
		vac_languages.clear();
		vac_tags.clear();
		vac_apps.clear();
		employees.clear();
		employee_edu.clear();
		employee_lans.clear();
		employee_tags.clear();
	}
	
	@Test
	void getVacanciesTest1() throws ParseException {
		//clearAll();
		Account account = new Account(0, null, "", "", "");
		Employee emp = new Employee(account, null);
		employees.put(0, emp);
		
		//experience
		List<WorkExperience> exp = new ArrayList<WorkExperience>();
		String pattern = "yyyy-MM-dd";

		SimpleDateFormat d = new SimpleDateFormat(pattern);
		WorkExperience w1 =
		new WorkExperience(0, java.sql.Date.valueOf("2010-02-10"), java.sql.Date.valueOf("2012-02-10"), null, "prof1", null, null, null, null);
		WorkExperience w2 = 
		new WorkExperience(0, java.sql.Date.valueOf("2008-02-10"), java.sql.Date.valueOf("2010-02-10"), null, "prof1", null, null, null, null);
		WorkExperience w3 = 
		new WorkExperience(0, java.sql.Date.valueOf("2012-10-10"), java.sql.Date.valueOf("2014-10-10"), null, "prof2", null, null, null, null);
		WorkExperience w4 = 
		new WorkExperience(0, java.sql.Date.valueOf("2012-10-10"), java.sql.Date.valueOf("2013-10-10"), null, "prof3", null, null, null, null);
		exp.add(w1);
		exp.add(w2);
		exp.add(w3);
		exp.add(w4);
		employee_exp.put(0, exp);
		//education
		List<EmployeeEducation> edu = new ArrayList<EmployeeEducation>();
		EmployeeEducation e1 = 
				new EmployeeEducation(0, null, null, null,null, "prof1", "prof2", "degree1", 0.0);
		
		EmployeeEducation e2 = 
				new EmployeeEducation(0, null, null, null,null, "prof2", "prof3", "degree2", 0.0);

		EmployeeEducation e3 = 
				new EmployeeEducation(0, null, null, null,null, "prof2", "prof5", "degree3", 0.0);
		
		EmployeeEducation e4 = 
				new EmployeeEducation(0, null, null, null,null, "prof4", "prof6", "degree1", 0.0);
		
		edu.add(e1);
		edu.add(e2);
		edu.add(e3);
		edu.add(e4);
		employee_edu.put(0 ,  edu);
		//add language
		List<Language> lan = new ArrayList<Language>();
		Language l1 = new Language(0, "lan1", null, null);
		Language l2 = new Language(0, "lan2", null, null);
		Language l3 = new Language(0, "lan3", null, null);
		Language l4 = new Language(0, "lan4", null, null);
		lan.add(l1);
		lan.add(l2);
		lan.add(l3);
		lan.add(l4);
		employee_lans.put(0 , lan);
		//add tags
		List<String> tags = new ArrayList<String>();
		String t1 = "t1";
		String t2 = "t2";
		String t3 = "t3";
		String t4 = "t4";
		tags.add(t1);
		tags.add(t2);
		tags.add(t3);
		tags.add(t4);
		employee_tags.put(0 , tags);
		
		Requirement r1 = new Requirement("", 3, "degree1", "prof1");
		Vacancy v1 = new Vacancy(0, "", "", "", "", 0, r1, null, null);
		l1 = new Language(0, "lan1", null, null);
		l2 = new Language(0, "lan2", null, null);
		vac_languages.put( 0 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		vac_tags.put(0 , new ArrayList<String>(Arrays.asList(t1 , t2)));
		
		Requirement r2 = new Requirement("", 3, "degree2", "prof1");
		Vacancy v2 = new Vacancy(1 , "", "", "", "", 0, r2, null, null);
		l1 = new Language(0, "lan3", null, null);
		l2 = new Language(0, "lan2", null, null);
		vac_languages.put(1 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		vac_tags.put(1 , new ArrayList<String>(Arrays.asList(t3 , t2))); 
		
		Requirement r3 = new Requirement("", 3, "degree1", "prof3");
		Vacancy v3 = new Vacancy(2, "", "", "", "", 0, r3, null, null);
		l1 = new Language(0, "lan1", null, null);
		vac_languages.put(2 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		vac_tags.put(2 , new ArrayList<String>(Arrays.asList(t3 , t2))); 
		
		Requirement r4 = new Requirement("", 3, "degree3", "prof1");
		Vacancy v4 = new Vacancy(3, "", "", "", "", 0, r4, null, null);
		l1 = new Language(0, "lan3", null, null);
		l2 = new Language(0, "lan2", null, null);
		l3 = new Language(0, "lan2", null, null);
		vac_languages.put(3 , new ArrayList<Language>(Arrays.asList(l1 , l2 , l3)));
		vac_tags.put(3 , new ArrayList<String>(Arrays.asList(t3 , t2 , t4 , t1))); 
		
		Requirement r5 = new Requirement("", 3, "degree1", "prof2");
		Vacancy v5 = new Vacancy(4, "", "", "", "", 0, r5, null, null);
		l1 = new Language(0, "lan1", null, null);
		l2 = new Language(0, "lan2", null, null);
		vac_languages.put(4 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		vac_tags.put(4 , new ArrayList<String>(Arrays.asList(t1 , t2 , t3))); 
		
		Requirement r6 = new Requirement("", 3, "degree1", "prof1");
		Vacancy v6 = new Vacancy(5, "", "", "", "", 0, r6, null, null);
		l1 = new Language(0, "lan3", null, null);
		l2 = new Language(0, "lan2", null, null);
		vac_languages.put(5 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		vac_tags.put(5 , new ArrayList<String>(Arrays.asList(t1 , t4))); 
		
		Requirement r7 = new Requirement("", 3, "degree1", "prof1");
		Vacancy v7 = new Vacancy(6, "", "", "", "", 0, r7, null, null);
		l1 = new Language(0, "lan3", null, null);
		l2 = new Language(0, "lan2", null, null);
		vac_languages.put(6 , new ArrayList<Language>(Arrays.asList(l4 , l3)));
		vac_tags.put(6 , new ArrayList<String>(Arrays.asList(t1 , t2 , t3))); 
		
		Requirement r8 = new Requirement("", 3, "degree1", "prof2");
		Vacancy v8 = new Vacancy(7 , "", "", "", "", 0, r8, null, null);
		vac_languages.put(7 , new ArrayList<Language>());
		vac_tags.put(7 , new ArrayList<String>()); 
		
		Requirement r9 = new Requirement("", 3, "degree1", "prof7");
		Vacancy v9 = new Vacancy(8 , "", "", "", "", 0, r9, null, null);
		vac_languages.put(8 , new ArrayList<Language>());
		vac_tags.put(8 , new ArrayList<String>());
		
		Requirement r10 = new Requirement("", 3, "degree1", "prof1");
		Vacancy v10 = new Vacancy(9 , "", "", "", "", 0, r10, null, null);
		vac_languages.put(9 , new ArrayList<Language>(Arrays.asList(l1)));
		vac_tags.put(9 , new ArrayList<String>(Arrays.asList(t1 , t2))); 
		
		Requirement r11 = new Requirement("", 3, "degree1", "prof1");
		Vacancy v11 = new Vacancy(10 , "", "", "", "", 0, r11, null, null);
		vac_languages.put(10 , new ArrayList<Language>());
		vac_tags.put(10 , new ArrayList<String>(Arrays.asList(t1 , t2))); 
		
		Requirement r12 = new Requirement("", 3, "degree1", "prof1");
		Vacancy v12 = new Vacancy(11, "", "", "", "", 0, r12, null, null);
		vac_languages.put(11 , new ArrayList<Language>(Arrays.asList()));
		vac_tags.put(11 , new ArrayList<String>(Arrays.asList(t1 , t2))); 
		
		Requirement r13 = new Requirement("", 3, "degree1", "prof1");
		Vacancy v13 = new Vacancy(12 , "", "", "", "", 0, r13, null, null);
		vac_languages.put(12 , new ArrayList<Language>(Arrays.asList(l1)));
		vac_tags.put(12 , new ArrayList<String>(Arrays.asList(t1 , t2))); 
		
		vacancies.put(0, v1);
		vacancies.put(1, v2);
		vacancies.put(2, v3);
		vacancies.put(3, v4);
		vacancies.put(4, v5);
		vacancies.put(5, v6);
		vacancies.put(6, v7);
		vacancies.put(7, v8);
		vacancies.put(8, v9);
		vacancies.put(9, v10);
		vacancies.put(10, v11);
		vacancies.put(11, v12);
		vacancies.put(12, v13);
		beforeEverything();
		
		List<Vacancy> res = new ArrayList<Vacancy>(Arrays.asList(v9 , v3 , v2 , v4 , v8 , v5 , v11 ,
					v12 , v10 , v13 , v1 , v6 , v7));
		List<Vacancy> rs = pairing.getVacancies(0);
		
		assertEquals(res, rs);
		
	}
	
	
	
	@Test
	public void getEmoloyeesTest1() {
		clearAll();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat d = new SimpleDateFormat(pattern);
		Language l1 = new Language(0, "lan1", null, null);
		Language l2 = new Language(0, "lan2", null, null);
		Language l3 = new Language(0, "lan3", null, null);
		Language l4 = new Language(0, "lan4", null, null);
		Language l5 = new Language(0, "lan5", null, null);
		Language l6 = new Language(0, "lan6", null, null);
		String t1 = "t1";
		String t2 = "t2";
		String t3 = "t3";
		String t4 = "t4";
		String t5 = "t4";
		String t6 = "t4";
		WorkExperience w;
		EmployeeEducation ed0 , ed1;
		
		Requirement r = new Requirement("", 3, "degree1", "prof1");
		Vacancy v = new Vacancy(0, "", "", "", "", 0, r, null, null);
		vac_languages.put(0 , new ArrayList<Language>(Arrays.asList(l1 , l2 , l3 , l4 , l5 , l6)));
		vac_tags.put(0 , new ArrayList<String>(Arrays.asList(t1 , t2 , t3 , t4 , t5 , t6)));
		vacancies.put(0 , v);
		
		Account a0 = new Account(0, null, null, null, null);
		Employee e0 = new Employee(a0, null);
		w = experience("prof1" , "2010-02-10" , "2012-02-10");
		ed1 = education("prof1" , "prof2" , "degree1");
		employee_exp.put(0, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(0 , new ArrayList<Language>(Arrays.asList(l1)));
		employee_edu.put(0 , new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(0 , new ArrayList<String>(Arrays.asList(t1)));
		
		Account a1 = new Account(1, null, null, null, null);
		Employee e1 = new Employee(a1, null);
		w = experience("prof1" , "2011-02-10" , "2012-02-10");
		ed1 = education("prof2" , "prof2" , "degree1");
		employee_exp.put(1, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(1 , new ArrayList<Language>(Arrays.asList(l1)));
		employee_edu.put(1 , new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(1 , new ArrayList<String>(Arrays.asList(t1)));
		
		Account a2 = new Account(2, null, null, null, null);
		Employee e2 = new Employee(a2, null);
		w = experience("prof1" , "2010-02-10" , "2012-02-10");
		ed1 = education("prof1" , "prof2" , "degree1");
		employee_exp.put(2, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(2 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		employee_edu.put(2, new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(2 , new ArrayList<String>(Arrays.asList(t1)));
		
		Account a3 = new Account(3, null, null, null, null);
		Employee e3 = new Employee(a3, null);
		w = experience("prof1" , "2010-02-10" , "2012-02-10");
		ed1 = education("prof1" , "prof2" , "degree1");
		employee_exp.put(3, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(3 , new ArrayList<Language>(Arrays.asList(l1)));
		employee_edu.put(3, new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(3 , new ArrayList<String>(Arrays.asList(t1 , t2)));
		
		Account a4 = new Account(4, null, null, null, null);
		Employee e4 = new Employee(a4, null);
		w = experience("prof1" , "2010-02-10" , "2012-02-10");
		ed1 = education("prof1" , "prof1" , "degree1");
		employee_exp.put(4, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(4 , new ArrayList<Language>(Arrays.asList(l1)));
		employee_edu.put(4 , new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(4 , new ArrayList<String>(Arrays.asList(t1 , t2)));
		
		Account a5 = new Account(5, null, null, null, null);
		Employee e5 = new Employee(a5, null);
		w = experience("prof1" , "2010-02-10" , "2012-02-10");
		ed1 = education("prof2" , "prof1" , "degree1");
		employee_exp.put(5, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(5 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		employee_edu.put(5, new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(5 , new ArrayList<String>(Arrays.asList(t1 , t2)));
		
		Account a6 = new Account(6, null, null, null, null);
		Employee e6 = new Employee(a6, null);
		w = experience("prof1" , "2010-02-10" , "2012-02-10");
		ed1 = education("prof1" , "prof2" , "degree1");
		employee_exp.put(6, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(6 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		employee_edu.put(6 , new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(6 , new ArrayList<String>(Arrays.asList(t1 , t2)));
		
		Account a7 = new Account(7, null, null, null, null);
		Employee e7 = new Employee(a7, null);
		w = experience("prof1" , "2009-02-10" , "2012-02-10");
		ed1 = education("prof2" , "prof1" , "degree1");
		employee_exp.put(7, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(7 , new ArrayList<Language>(Arrays.asList(l1)));
		employee_edu.put(7, new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(7 , new ArrayList<String>(Arrays.asList(t1 , t2)));
		
		Account a8 = new Account(8, null, null, null, null);
		Employee e8 = new Employee(a8, null);
		w = experience("prof1" , "2010-02-10" , "2012-02-10");
		ed1 = education("prof2" , "prof2" , "degree1");
		employee_exp.put(8, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(8 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		employee_edu.put(8 , new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(8 , new ArrayList<String>(Arrays.asList(t1)));
		
		Account a9 = new Account(9, null, null, null, null);
		Employee e9 = new Employee(a9, null);
		w = experience("prof1" , "2011-02-10" , "2012-02-10");
		ed1 = education("prof2" , "prof2" , "degree1");
		employee_exp.put(9, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(9 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		employee_edu.put(9 , new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(9 , new ArrayList<String>(Arrays.asList(t1)));
		
		Account a10 = new Account(10, null, null, null, null);
		Employee e10 = new Employee(a10, null);
		w = experience("prof1" , "2011-02-10" , "2012-02-10");
		ed1 = education("prof2" , "prof2" , "degree1");
		employee_exp.put(10, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(10 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		employee_edu.put(10 , new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(10 , new ArrayList<String>(Arrays.asList()));
		
		Account a11 = new Account(11, null, null, null, null);
		Employee e11 = new Employee(a11, null);
		w = experience("prof1" , "2011-02-10" , "2012-02-10");
		ed1 = education("prof2" , "prof2" , "degree1");
		employee_exp.put(11, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(11 , new ArrayList<Language>(Arrays.asList(l1)));
		employee_edu.put(11 , new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(11 , new ArrayList<String>(Arrays.asList()));
		
		Account a12 = new Account(12, null, null, null, null);
		Employee e12 = new Employee(a12, null);
		w = experience("prof1" , "2011-02-10" , "2012-02-10");
		ed1 = education("prof2" , "prof2" , "degree1");
		employee_exp.put(12, new ArrayList<WorkExperience>(Arrays.asList(w)));
		employee_lans.put(12 , new ArrayList<Language>(Arrays.asList(l1 , l2)));
		employee_edu.put(12 , new ArrayList<EmployeeEducation>(Arrays.asList(ed1)));
		employee_tags.put(12 , new ArrayList<String>(Arrays.asList()));
		
		vac_apps.put(0 , new ArrayList<Employee>(Arrays.asList(e0 , e1 , e2 , e3 , 
				e4 , e5 , e6 , e7 , e8 , e9 , e10 , e11 , e12)));
		beforeEverything();
		List<Employee> rs = pairing.getEmoloyees(0);
//		for(Employee e : rs) {
//			System.out.println(e.getId());
//		}
		List<Employee> res = new ArrayList<>(Arrays.asList(e11 , e10 , e12 , e1 , e9 , e8 , e5 , e7,
					e0 , e2 , e3 , e6, e4));
		assertEquals(res, rs);
		
	}
	
	private EmployeeEducation education(String major_prof , String minor_prof , String degree) {
		return new EmployeeEducation(0, null, null, null,null, major_prof, minor_prof, degree, 0.0);
	}
	
	private WorkExperience experience(String prof , String startDate , String endDate) {
		return new WorkExperience(0, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), null, prof, null, null, null, null);
	}
}
