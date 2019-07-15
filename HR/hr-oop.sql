USE oop;

DROP TABLE IF EXISTS notification_types;
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS vacancy_tags;
DROP TABLE IF EXISTS employee_tags;
DROP TABLE IF EXISTS default_tags;
DROP TABLE IF EXISTS followers;
DROP TABLE IF EXISTS applicants;
DROP TABLE IF EXISTS requirement_languages;
DROP TABLE IF EXISTS languages;
DROP TABLE IF EXISTS vacancies;
DROP TABLE IF EXISTS experiences;
DROP TABLE IF EXISTS education;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS default_languages;
DROP TABLE IF EXISTS default_qualities;
DROP TABLE IF EXISTS degrees;
DROP TABLE IF EXISTS institution_types;
DROP TABLE IF EXISTS default_locations;
DROP TABLE IF EXISTS professions;

CREATE TABLE accounts (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    registration_date TIMESTAMP DEFAULT NOW(),
    username VARCHAR(64) UNIQUE,
    password_hash VARCHAR(64),
    account_type VARCHAR(64) NOT NULL
);

CREATE TABLE companies (
	id INTEGER PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
	essence VARCHAR(50), /*                                         */
    logo VARCHAR(255),
    founded_date DATE,
    phone VARCHAR(255),
    email VARCHAR(255),
    location VARCHAR(255),
    
	FOREIGN KEY (id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE employees (
	id INTEGER PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    gender VARCHAR(255),
    birth_date DATE,
    major_profession VARCHAR(255),
    minor_profession VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    location VARCHAR(255),
    description TEXT,
    profile_picture VARCHAR(255),
    isWorking bool,	
    
	FOREIGN KEY (id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE experiences (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	employee_id INTEGER NOT NULL,
	company_name VARCHAR(255) NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	position VARCHAR(255) NOT NULL,
    profession VARCHAR(255) NOT NULL,
	job_description TEXT,
    emp_type VARCHAR(50) NOT NULL,
    achievement VARCHAR(50) NOT NULL,
    
	FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

CREATE TABLE education (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	employee_id INTEGER NOT NULL,
    educational_institution VARCHAR(50) NOT NULL,
	educational_institution_name VARCHAR(255) NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE,
    major VARCHAR(255) NOT NULL,
    minor VARCHAR(255),
    degree VARCHAR(50) NOT NULL,
    grade DOUBLE NOT NULL,
    
	FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

CREATE TABLE languages (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    employee_id INTEGER NOT NULL,
    language VARCHAR(50) NOT NULL,
    quality VARCHAR(50) NOT NULL,
    certificate VARCHAR(255),
    
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

CREATE TABLE vacancies (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    company_id INTEGER NOT NULL,
    heading VARCHAR(50),
    description TEXT,
    creation_date TIMESTAMP DEFAULT NOW(),
    expiry_date DATE NOT NULL,
    emp_type VARCHAR(50) NOT NULL, /* part-time, full-time */
    qualification_1 VARCHAR(255), /*                                         */
    qualification_2 VARCHAR(255), /*                                         */
    qualification_3 VARCHAR(255), /*                                         */
    profession VARCHAR(50),
    position VARCHAR(50) NOT NULL,
    years_of_experience INTEGER,
    location VARCHAR(50),
    degree VARCHAR(50),
    
	FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE
);
    
CREATE TABLE requirement_languages (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    vacancy_id INTEGER NOT NULL,
    language VARCHAR(50),
    quality VARCHAR(50),
    
    FOREIGN KEY (vacancy_id) REFERENCES vacancies(id) ON DELETE CASCADE
);

CREATE TABLE followers (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    employee_id INTEGER NOT NULL,
    company_id INTEGER NOT NULL,
    
	FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
	FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE
);

CREATE TABLE applicants (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    vacancy_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    
    FOREIGN KEY (vacancy_id) REFERENCES vacancies(id) ON DELETE CASCADE,
	FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

CREATE TABLE default_tags (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	tag VARCHAR(50) UNIQUE
);

CREATE TABLE vacancy_tags (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    tag VARCHAR(50) NOT NULL,
    vacancy_id INTEGER NOT NULL,
    
	FOREIGN KEY (vacancy_id) REFERENCES vacancies(id) ON DELETE CASCADE
);

CREATE TABLE employee_tags (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    tag VARCHAR(50) NOT NULL,
    employee_id INTEGER NOT NULL,

	FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

CREATE TABLE notifications (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    notif_type INTEGER NOT NULL,
    company_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    time_stamp TIMESTAMP DEFAULT NOW(),
    
	FOREIGN KEY (company_id) REFERENCES companies(id),
	FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE notification_types (
	id INTEGER PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE default_languages (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    language VARCHAR(250) UNIQUE
);

CREATE TABLE default_qualities (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    quality VARCHAR(50) UNIQUE
);

CREATE TABLE degrees (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    degree VARCHAR(50) UNIQUE
);

CREATE TABLE default_locations (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR (50) UNIQUE
);
    
CREATE TABLE institution_types (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    institution_type VARCHAR(50) UNIQUE
);

CREATE TABLE professions (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    profession VARCHAR(50)
);


INSERT INTO default_tags (tag) VALUES ("Finances");
INSERT INTO default_tags (tag) VALUES ("Marketing");
INSERT INTO default_tags (tag) VALUES ("Operations management");
INSERT INTO default_tags (tag) VALUES ("IT");
INSERT INTO default_tags (tag) VALUES ("Computer science");
INSERT INTO default_tags (tag) VALUES ("Engineering");
INSERT INTO default_tags (tag) VALUES ("International relations");
INSERT INTO default_tags (tag) VALUES ("Jurisprudence");
INSERT INTO default_tags (tag) VALUES ("Physics");
INSERT INTO default_tags (tag) VALUES ("Graphics design");
INSERT INTO default_tags (tag) VALUES ("Paintings");
INSERT INTO default_tags (tag) VALUES ("Architecture");
INSERT INTO default_tags (tag) VALUES ("Economics");
INSERT INTO default_tags (tag) VALUES ("Social science");
INSERT INTO default_tags (tag) VALUES ("Agronomy");
INSERT INTO default_tags (tag) VALUES ("Viticulture and winemaking");
INSERT INTO default_tags (tag) VALUES ("Natural sciences");
INSERT INTO default_tags (tag) VALUES ("Forestry");
INSERT INTO default_tags (tag) VALUES ("Auto industry");
INSERT INTO default_tags (tag) VALUES ("Investments");
INSERT INTO default_tags (tag) VALUES ("Construction");
INSERT INTO default_tags (tag) VALUES ("Telecommunications");
INSERT INTO default_tags (tag) VALUES ("Aviation");
INSERT INTO default_tags (tag) VALUES ("Consulting");
INSERT INTO default_tags (tag) VALUES ("Human resource management");
INSERT INTO default_tags (tag) VALUES ("Medical");
INSERT INTO default_tags (tag) VALUES ("Education");
INSERT INTO default_tags (tag) VALUES ("Food technology");
INSERT INTO default_tags (tag) VALUES ("Music");
INSERT INTO default_tags (tag) VALUES ("Natural resources");
INSERT INTO default_tags (tag) VALUES ("Sport");

INSERT INTO professions (profession) VALUES ("Accountant");
INSERT INTO professions (profession) VALUES ("Actor");
INSERT INTO professions (profession) VALUES ("Air traffic controller");
INSERT INTO professions (profession) VALUES ("Architect");
INSERT INTO professions (profession) VALUES ("Artist");
INSERT INTO professions (profession) VALUES ("Attorney");
INSERT INTO professions (profession) VALUES ("Banker");
INSERT INTO professions (profession) VALUES ("Bartender");
INSERT INTO professions (profession) VALUES ("Barber");
INSERT INTO professions (profession) VALUES ("Bookkeeper");
INSERT INTO professions (profession) VALUES ("Builder");
INSERT INTO professions (profession) VALUES ("Butcher");
INSERT INTO professions (profession) VALUES ("Carpenter");
INSERT INTO professions (profession) VALUES ("Cashier");
INSERT INTO professions (profession) VALUES ("Chef");
INSERT INTO professions (profession) VALUES ("Coach");
INSERT INTO professions (profession) VALUES ("Dentist");
INSERT INTO professions (profession) VALUES ("Designer");
INSERT INTO professions (profession) VALUES ("Developer");
INSERT INTO professions (profession) VALUES ("Dietician");
INSERT INTO professions (profession) VALUES ("Doctor");
INSERT INTO professions (profession) VALUES ("Economist");
INSERT INTO professions (profession) VALUES ("Editor");
INSERT INTO professions (profession) VALUES ("Electrician");
INSERT INTO professions (profession) VALUES ("Engineer");
INSERT INTO professions (profession) VALUES ("Farmer");
INSERT INTO professions (profession) VALUES ("Producer");
INSERT INTO professions (profession) VALUES ("Filmmaker");
INSERT INTO professions (profession) VALUES ("Fisherman");
INSERT INTO professions (profession) VALUES ("Flight attendant");
INSERT INTO professions (profession) VALUES ("Jeweler");
INSERT INTO professions (profession) VALUES ("Judge");
INSERT INTO professions (profession) VALUES ("Lawyer");
INSERT INTO professions (profession) VALUES ("Mechanic");
INSERT INTO professions (profession) VALUES ("Musician");
INSERT INTO professions (profession) VALUES ("Nurse");
INSERT INTO professions (profession) VALUES ("Optician");
INSERT INTO professions (profession) VALUES ("Painter");
INSERT INTO professions (profession) VALUES ("Pharmacist");
INSERT INTO professions (profession) VALUES ("Photographer");
INSERT INTO professions (profession) VALUES ("Physician");
INSERT INTO professions (profession) VALUES ("Teaching assistant");
INSERT INTO professions (profession) VALUES ("Pilot");
INSERT INTO professions (profession) VALUES ("Plumber");
INSERT INTO professions (profession) VALUES ("Police officer");
INSERT INTO professions (profession) VALUES ("Politian");
INSERT INTO professions (profession) VALUES ("Proffesor");
INSERT INTO professions (profession) VALUES ("Programmer");
INSERT INTO professions (profession) VALUES ("Psychologist");
INSERT INTO professions (profession) VALUES ("Receptionist");
INSERT INTO professions (profession) VALUES ("Salesman");
INSERT INTO professions (profession) VALUES ("Secretary");
INSERT INTO professions (profession) VALUES ("Singer");
INSERT INTO professions (profession) VALUES ("Surgeon");
INSERT INTO professions (profession) VALUES ("Teacher");
INSERT INTO professions (profession) VALUES ("Lecturer");
INSERT INTO professions (profession) VALUES ("Therapist");
INSERT INTO professions (profession) VALUES ("Pediatrician");
INSERT INTO professions (profession) VALUES ("Translator");
INSERT INTO professions (profession) VALUES ("Undertaker");
INSERT INTO professions (profession) VALUES ("Veterinarian");
INSERT INTO professions (profession) VALUES ("Graphic designer");
INSERT INTO professions (profession) VALUES ("Waiter");
INSERT INTO professions (profession) VALUES ("Writer");
INSERT INTO professions (profession) VALUES ("Cleaner");
INSERT INTO professions (profession) VALUES ("Copywriter");
INSERT INTO professions (profession) VALUES ("Creative director");

    
INSERT INTO default_locations (city) VALUES ("Mestia");
INSERT INTO default_locations (city) VALUES ("Tsalenjikha");
INSERT INTO default_locations (city) VALUES ("Chkhorotsku");
INSERT INTO default_locations (city) VALUES ("Martvili");
INSERT INTO default_locations (city) VALUES ("Zugdidi");
INSERT INTO default_locations (city) VALUES ("Khobi");
INSERT INTO default_locations (city) VALUES ("Senaki");
INSERT INTO default_locations (city) VALUES ("Abasha");
INSERT INTO default_locations (city) VALUES ("Lanchkhuti");
INSERT INTO default_locations (city) VALUES ("Ozurgeti");
INSERT INTO default_locations (city) VALUES ("Chokatauri");
INSERT INTO default_locations (city) VALUES ("Khelvachauri");
INSERT INTO default_locations (city) VALUES ("Kobuleti");
INSERT INTO default_locations (city) VALUES ("Keda");
INSERT INTO default_locations (city) VALUES ("Shuakhevi");
INSERT INTO default_locations (city) VALUES ("Khulo");
INSERT INTO default_locations (city) VALUES ("Lentekhi");
INSERT INTO default_locations (city) VALUES ("Tsageri");
INSERT INTO default_locations (city) VALUES ("Ambrolauri");
INSERT INTO default_locations (city) VALUES ("Oni");
INSERT INTO default_locations (city) VALUES ("Khashuri");
INSERT INTO default_locations (city) VALUES ("Kareli");
INSERT INTO default_locations (city) VALUES ("Gori");
INSERT INTO default_locations (city) VALUES ("Kaspi");
INSERT INTO default_locations (city) VALUES ("Java");
INSERT INTO default_locations (city) VALUES ("Stepanwminda");
INSERT INTO default_locations (city) VALUES ("Dusheti");
INSERT INTO default_locations (city) VALUES ("Akhalgori");
INSERT INTO default_locations (city) VALUES ("Tianeti");
INSERT INTO default_locations (city) VALUES ("Mtskheta");
INSERT INTO default_locations (city) VALUES ("Dedoplistskaro");
INSERT INTO default_locations (city) VALUES ("Signagi");
INSERT INTO default_locations (city) VALUES ("Sagarejo");
INSERT INTO default_locations (city) VALUES ("Gurjaani");
INSERT INTO default_locations (city) VALUES ("Lagodekhi");
INSERT INTO default_locations (city) VALUES ("Kvareli");
INSERT INTO default_locations (city) VALUES ("Telavi");
INSERT INTO default_locations (city) VALUES ("Akhmeta");
INSERT INTO default_locations (city) VALUES ("Sachkhere");
INSERT INTO default_locations (city) VALUES ("Kharagauli");
INSERT INTO default_locations (city) VALUES ("Chiatura");
INSERT INTO default_locations (city) VALUES ("Zestafoni");
INSERT INTO default_locations (city) VALUES ("Terjola");
INSERT INTO default_locations (city) VALUES ("Tkibuli");
INSERT INTO default_locations (city) VALUES ("Bagdati");
INSERT INTO default_locations (city) VALUES ("Vani");
INSERT INTO default_locations (city) VALUES ("Samtredia");
INSERT INTO default_locations (city) VALUES ("Tskaltubo");
INSERT INTO default_locations (city) VALUES ("Khoni");
INSERT INTO default_locations (city) VALUES ("Ninotsminda");
INSERT INTO default_locations (city) VALUES ("Akhalqalaqi");
INSERT INTO default_locations (city) VALUES ("Aspindza");
INSERT INTO default_locations (city) VALUES ("Borjomi");
INSERT INTO default_locations (city) VALUES ("Akhaltsikhe");
INSERT INTO default_locations (city) VALUES ("Adigeni");
INSERT INTO default_locations (city) VALUES ("Marneuli");
INSERT INTO default_locations (city) VALUES ("Gardabani");
INSERT INTO default_locations (city) VALUES ("Bolnisi");
INSERT INTO default_locations (city) VALUES ("Tetritskaro");
INSERT INTO default_locations (city) VALUES ("Dmanisi");
INSERT INTO default_locations (city) VALUES ("Tsalka");
INSERT INTO default_locations (city) VALUES ("Tbilisi");
INSERT INTO default_locations (city) VALUES ("Qutaisi");
INSERT INTO default_locations (city) VALUES ("Batumi");
INSERT INTO default_locations (city) VALUES ("Foti");
INSERT INTO default_locations (city) VALUES ("Rustavi");

INSERT INTO degrees (degree) VALUES ("Bachelor");
INSERT INTO degrees (degree) VALUES ("Associate");
INSERT INTO degrees (degree) VALUES ("Master");
INSERT INTO degrees (degree) VALUES ("Ph.D");
INSERT INTO degrees (degree) VALUES ("Pursuing Degree");

INSERT INTO institution_types (institution_type) VALUES ("School");
INSERT INTO institution_types (institution_type) VALUES ("University");
INSERT INTO institution_types (institution_type) VALUES ("College");
INSERT INTO institution_types (institution_type) VALUES ("Vocational School");
INSERT INTO institution_types (institution_type) VALUES ("Academy");

INSERT INTO default_qualities (quality) VALUES ("Bad");
INSERT INTO default_qualities (quality) VALUES ("Average");
INSERT INTO default_qualities (quality) VALUES ("Well");
INSERT INTO default_qualities (quality) VALUES ("Excellent");

INSERT INTO default_languages (language) VALUES ("Georgia");
INSERT INTO default_languages (language) VALUES ("Russian");
INSERT INTO default_languages (language) VALUES ("English");
INSERT INTO default_languages (language) VALUES ("French");
INSERT INTO default_languages (language) VALUES ("Italian");
INSERT INTO default_languages (language) VALUES ("German");
INSERT INTO default_languages (language) VALUES ("Spanish");
INSERT INTO default_languages (language) VALUES ("Chinese");
INSERT INTO default_languages (language) VALUES ("Turkish");
INSERT INTO default_languages (language) VALUES ("Japanese");
INSERT INTO default_languages (language) VALUES ("Kazakh");
INSERT INTO default_languages (language) VALUES ("Armenian");
INSERT INTO default_languages (language) VALUES ("Azerbaijani");
INSERT INTO default_languages (language) VALUES ("Korean");
INSERT INTO default_languages (language) VALUES ("Arabic");

INSERT INTO accounts (username, password_hash, account_type) VALUES ("user1", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Employee");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user2", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Employee");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user3", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Employee");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user4", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Employee");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user5", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Employee");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user6", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Employee");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user7", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Employee");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user8", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Employee");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user9", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Company");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user10", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Company");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user11", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Company");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user12", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Company");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user13", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Company");
INSERT INTO accounts (username, password_hash, account_type) VALUES ("user14", "14427c2bf6d1e9abe854993d4f5ed1c1f3fb7896", "Company");



INSERT INTO employees VALUES ((SELECT id FROM accounts WHERE username = "user1"), "John", "James", "Male", STR_TO_DATE('01/5/1973','%d/%m/%Y'), 
			"Developer", "Phsychologist", "599131415", "john.james@gmail.com", 
            "Tbilisi", "I am a highly competent IT professional with a proven track record in designing websites, networking and managing databases. I have strong technical skills as well as excellent interpersonal skills, enabling me to interact with a wide range of clients. I am eager to be challenged in order to grow and further improve my IT skills. My greatest passion is in life is using my technical know-how to benefit other people and organisations.", "../Images/avatar.png", false);
INSERT INTO employees VALUES ((SELECT id FROM accounts WHERE username = "user2"), "Mark", "Robertson", "Male", STR_TO_DATE('04/9/1980','%d/%m/%Y'), 
			"Developer", "Lecturer", "599111015", "mark.robertson@gmail.com", 
            "Tbilisi", "I am a highly competent IT professional with a proven track record in designing websites, networking and managing databases. I have strong technical skills as well as excellent interpersonal skills, enabling me to interact with a wide range of clients. I am eager to be challenged in order to grow and further improve my IT skills. My greatest passion is in life is using my technical know-how to benefit other people and organisations.", "../Images/avatar.png", false);
INSERT INTO employees VALUES ((SELECT id FROM accounts WHERE username = "user3"), "Albert", "Knopfler", "Male", STR_TO_DATE('02/9/1963','%d/%m/%Y'), 
			"Teacher", "Lecturer", "597552951", "albert.knopfler@gmail.com", 
            "Tbilisi", "I am a loyal, talented and caring teacher who loves making a difference in the lives of young children. I am open-minded, patient and supportive towards other people, especially towards children or those who suffer from disabilities. In my previous role as a primary school teacher, I have taught and nurtured more than 100 pupils from all backgrounds, successfully preparing them for the next stages of their young lives.", "../Images/avatar.png", true);
INSERT INTO employees VALUES ((SELECT id FROM accounts WHERE username = "user4"), "Mary", "Simpson", "Female", STR_TO_DATE('08/7/1989','%d/%m/%Y'), 
			"Lecturer", "Developer", "593058950", "mary.simpson@gmail.com", 
            "Gori", "I am a loyal, talented and caring teacher who loves making a difference in the lives of young children. I am open-minded, patient and supportive towards other people, especially towards children or those who suffer from disabilities. In my previous role as a primary school teacher, I have taught and nurtured more than 100 pupils from all backgrounds, successfully preparing them for the next stages of their young lives.", "../Images/avatar.png", true);
INSERT INTO employees VALUES ((SELECT id FROM accounts WHERE username = "user5"), "Maggie", "Hart", "Female", STR_TO_DATE('19/2/1990','%d/%m/%Y'), 
			"Chef", "Farmer", "593261665", "maggie.hart@gmail.com", 
            "Gori", "I am an enthusiastic, hard-working and disciplined Catering Assistant with excellent track-record in working in the food industry. I am a friendly individual with a great passion for food; I enjoy socialising with people, and my exceptional customer service skills have earned me the award of “Employee of the Month” twice in a row.", "../Images/avatar.png", false);
INSERT INTO employees VALUES ((SELECT id FROM accounts WHERE username = "user6"), "Rose", "Beckham", "Female", STR_TO_DATE('20/8/1994','%d/%m/%Y'), 
			"Chef", "Farmer", "593526767", "rose.beckham@gmail.com", 
            "Batumi", "I am an enthusiastic, hard-working and disciplined Catering Assistant with excellent track-record in working in the food industry. I am a friendly individual with a great passion for food; I enjoy socialising with people, and my exceptional customer service skills have earned me the award of “Employee of the Month” twice in a row.", "../Images/avatar.png", false);
INSERT INTO employees VALUES ((SELECT id FROM accounts WHERE username = "user7"), "Robert", "Fisher", "Male", STR_TO_DATE('10/8/1991','%d/%m/%Y'), 
			"Waiter", "Chef", "593627797", "robert.fisher@gmail.com", 
            "Batumi", "I am an enthusiastic, hard-working and disciplined Waiter with excellent track-record in working in the food industry. I am a friendly individual with a great passion for food; I enjoy socialising with people, and my exceptional customer service skills have earned me the award of “Employee of the Month” twice in a row.", "../Images/avatar.png", false);
INSERT INTO employees VALUES ((SELECT id FROM accounts WHERE username = "user8"), "Ellie", "Thomas", "Female", STR_TO_DATE('20/10/1982','%d/%m/%Y'), 
			"Chef", "Waiter", "593526767", "ellie.thomas@gmail.com", 
            "Batumi", "I am an enthusiastic, hard-working and disciplined Chef with excellent track-record in working in the food industry. I am a friendly individual with a great passion for food; I enjoy socialising with people, and my exceptional customer service skills have earned me the award of “Employee of the Month” twice in a row.", "../Images/avatar.png", false);

INSERT INTO companies VALUES ((SELECT id FROM accounts WHERE username = "user9"), "Free University of Tbilisi", "The university was founded by The Knowledge Fund (KF) in 2007, which is a non-profit, charity organization founded by Kakha Bendukidze. KF is the largest endowment in higher education in Georgia.The Fund's purpose is to ensure provision of world quality higher education to Georgia's young generation and encourage high quality research in the country.",
			"University", "../Images/freeuni.png", STR_TO_DATE('11/8/2007','%d/%m/%Y'), "+995 322 20 09 01", "info@freeuni.edu.ge", "Tbilisi");
INSERT INTO companies VALUES ((SELECT id FROM accounts WHERE username = "user10"), "Burger Bar", "Simply the best burgers in Tbilisi.",
			"Restaurant", "../Images/burger.jpg", STR_TO_DATE('01/9/2014','%d/%m/%Y'), "+995 598 93 20 10", "info@burgerbar.ge", "Tbilisi");
INSERT INTO companies VALUES ((SELECT id FROM accounts WHERE username = "user11"), "TV Shop", "We are the ones who sold Ultimate Chopper and Flavour Wave.",
			"Online Shop", "../Images/tv.png", STR_TO_DATE('08/12/2004','%d/%m/%Y'), "+995 322 11 10 00", "info@tvshop.ge", "Tbilisi");
INSERT INTO companies VALUES ((SELECT id FROM accounts WHERE username = "user12"), "Tbilisi City Hall", "City Hall of Tbilisi.",
			"Local Authority", "../Images/compImage.png", STR_TO_DATE('28/02/1918','%d/%m/%Y'), "+995 322 72 22 22", "info@tbilisi.gov.ge", "Tbilisi");
INSERT INTO companies VALUES ((SELECT id FROM accounts WHERE username = "user13"), "Facebook", "The world's most popular social networking web site, Facebook enables users to connect with friends and family by sharing status updates, personal photos and other items of interest. Founder Mark Zuckerberg created Facebook in 2004 while a student at Harvard, designing the site as a means for other university students to communicate and to socialize online.",
			"Social Network", "../Images/fb.png", STR_TO_DATE('04/7/2004','%d/%m/%Y'), "+995 322 55 66 77", "info@facebook.com", "Gori");
INSERT INTO companies VALUES ((SELECT id FROM accounts WHERE username = "user14"), "Google", "Google was founded by Larry Page and Sergey Brin while they were students at Stanford University. The company was officially launched in September 1998 in a friend’s garage. In one of the most anticipated Initial Public Offerings (IPO) Google raised $1.67 billion in August of 2004. Today, Google has over 12,000 employees in offices throughout the world.",
			"Search Engine", "../Images/google.jpg", STR_TO_DATE('07/10/1998','%d/%m/%Y'), "+995 322 51 91 00", "info@google.com", "Gori");

INSERT INTO vacancies (company_id, heading, description, expiry_date, emp_type, qualification_1, qualification_2, qualification_3, profession, position, years_of_experience, location, degree) VALUES (14, "Software Engineer", "Google's software engineers develop the next-generation technologies that change how billions of users connect, explore, and interact with information and one another. Our products need to handle information at massive scale, and extend well beyond web search. We're looking for engineers who bring fresh ideas from all areas, including information retrieval, distributed computing, large-scale system design, networking and data storage, security, artificial intelligence, natural language processing, UI design and mobile; the list goes on and is growing every day. As a software engineer, you will work on a specific project critical to Google’s needs with opportunities to switch teams and projects as you and our fast-paced business grow and evolve. We need our engineers to be versatile, display leadership qualities and be enthusiastic to take on new problems across the full-stack as we continue to push technology forward.", 
						DATE("2019-09-15"), "Full-time", "BS degree in Computer Science, similar technical field of study or equivalent practical experience.", 
                        "Software development experience in one or more general purpose programming languages.", 
                        "Working proficiency and communication skills in verbal and written English.",
                        "Engineer", "Engineer", 1, "Tbilisi", "Bachelor");
INSERT INTO vacancies (company_id, heading, description, expiry_date, emp_type, qualification_1, qualification_2, qualification_3, profession, position, years_of_experience, location, degree) VALUES (14, "Telecommunications", "The Google Cloud Platform team helps customers transform and evolve their business through the use of Google’s global network, web-scale data centers and software infrastructure. As part of an entrepreneurial team in this rapidly growing business, you will help shape the future of businesses of all sizes use technology to connect with customers, employees and partners. As an Enterprise Account Manager, you'll advocate for the Cloud Platform, promoting technology for business innovation. You'll introduce Google Cloud Platform to our customers, drive awareness in the developer and startup community and leverage and build the Google Cloud Platform partner ecosystem.", 
						DATE("2019-10-15"), "Part-time", "BS degree in Computer Science, similar technical field of study or equivalent practical experience.", 
                        "Software development experience in one or more general purpose programming languages.",
                        "Working proficiency and communication skills in verbal and written English.", "Engineer", "Engineer", 1, "Batumi", "Bachelor");
INSERT INTO vacancies (company_id, heading, description, expiry_date, emp_type, qualification_1, qualification_2, qualification_3, profession, position, years_of_experience, location, degree) VALUES (13, "Software Engineering Intern", "Much did had call new drew that kept. Limits expect wonder law she. Now has you views woman noisy match money rooms. To up remark it eldest length oh passed. Off because yet mistake feeling has men. Consulted disposing to moonlight ye extremity. Engage piqued in on coming. ", 
						DATE("2019-11-09"), "Internship", "BS degree in Computer Science, similar technical field of study or equivalent practical experience.",
                        "Software development experience in one or more general purpose programming languages.", 
                        "Working proficiency and communication skills in verbal and written English.", 
                        "Engineer", "Engineer", 1, "Gori", "Bachelor");
INSERT INTO vacancies (company_id, heading, description, expiry_date, emp_type, qualification_1, qualification_2, qualification_3, profession, position, years_of_experience, location, degree) VALUES (13, "Chef", "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends.", 
						DATE("2019-11-21"), "Full-time", "Motivational Management Style.", "Fast-Paced Decision Making.", "Culinary Expertise.", "Chef", "Chef", 2, "Rustavi", "Bachelor");
INSERT INTO vacancies (company_id, heading, description, expiry_date, emp_type, qualification_1, qualification_2, qualification_3, profession, position, years_of_experience, location, degree) VALUES (9, "Looking for Artificial Intelligence Lecturer", "You disposal strongly quitting his endeavor two settling him. Manners ham him hearted hundred expense. Get open game him what hour more part. Adapted as smiling of females oh me journey exposed concern. Met come add cold calm rose mile what. Tiled manor court at built by place fanny. Discretion at be an so decisively especially. Exeter itself object matter if on mr in.", 
						DATE("2019-09-17"), "Full-time", "BS degree in Computer Science, similar technical field of study or equivalent practical experience.", 
                        "Software development experience in one or more general purpose programming languages.", 
                        "Working proficiency and communication skills in verbal and written English.", "Lecturer", "Artificial Intelligence Lecturer", 3, "Tbilisi", "Master");
INSERT INTO vacancies (company_id, heading, description, expiry_date, emp_type, qualification_1, qualification_2, qualification_3, profession, position, years_of_experience, location, degree) VALUES (9, "Looking for Teaching Assistant", "Unpleasant nor diminution excellence apartments imprudence the met new. Draw part them he an to he roof only. Music leave say doors him. Tore bred form if sigh case as do. Staying he no looking if do opinion. Sentiments way understood end partiality and his.", 
						DATE("2019-09-29"), "Full-time", "BS degree in Computer Science, similar technical field of study or equivalent practical experience.", 
                        "Software development experience in one or more general purpose programming languages.",
                        "Working proficiency and communication skills in verbal and written English.", "Developer", "Teaching assistant of Software Engineering class", 2, "Tbilisi", "Bachelor");
INSERT INTO vacancies (company_id, heading, description, expiry_date, emp_type, qualification_1, qualification_2, qualification_3, profession, position, years_of_experience, location, degree) VALUES (9, "Looking for Web Developer", "Unpleasant nor diminution excellence apartments imprudence the met new. Draw part them he an to he roof only. Music leave say doors him. Tore bred form if sigh case as do. Staying he no looking if do opinion. Sentiments way understood end partiality and his.", 
						DATE("2019-11-16"), "Full-time", "HTML, CSS, Javascript, JSP", 
                        "Web development experience in one or more general purpose programming languages.",
                        "Working proficiency and communication skills in verbal and written Georgian.", "Developer", "Web Developer for renewed EMIS", 2, "Tbilisi", "Bachelor");

SELECT * FROM accounts;
SELECT * FROM employees;
SELECT * FROM companies;
SELECT * FROM vacancies;
SELECT * FROM followers;
SELECT * FROM applicants;
SELECT * FROM default_tags;
SELECT * FROM vacancy_tags;
SELECT * FROM employee_tags;
SELECT * FROM default_qualities;
SELECT * FROM degrees;
SELECT * FROM institution_types;
SELECT * FROM professions;
SELECT * FROM default_locations;
SELECT * FROM default_languages;
SELECT * FROM languages;
SELECT * FROM requirement_languages;
SELECT * FROM experiences;
SELECT * FROM education;
