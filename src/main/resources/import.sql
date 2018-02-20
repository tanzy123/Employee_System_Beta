
INSERT INTO company (companyPrimaryId, companyAddress, companyEmail, companyName, companyReferenceNumber, companyWebsite,contactNumber,turnover)
VALUES ('1','Singapore','company_Singapore@company.com','Company_One','111111','www.Company_One.com','66635553','10');

INSERT INTO company (companyPrimaryId, companyAddress, companyEmail, companyName, companyReferenceNumber, companyWebsite,contactNumber,turnover)
VALUES ('2','Malaysia','company_Malaysia@company.com','Company_Two','222222','www.Company_Two.com','01222355112','20');

INSERT INTO company (companyPrimaryId, companyAddress, companyEmail, companyName, companyReferenceNumber, companyWebsite,contactNumber,turnover)
VALUES ('3','China','company_China@company.com','Company_Three','333333','www.Company_Three.com','43139234912','30');

INSERT INTO company (companyPrimaryId, companyAddress, companyEmail, companyName, companyReferenceNumber, companyWebsite,contactNumber,turnover)
VALUES ('4','United States','company_UnitedStates@company.com','Company_Four','444444','www.Company_Four','122432941139','40');

INSERT INTO company (companyPrimaryId, companyAddress, companyEmail, companyName, companyReferenceNumber, companyWebsite,contactNumber,turnover)
VALUES ('5','Russia','company_Russia@company.com','Company_Five','555555','www.Company_Five','21211371631','50');



INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('454','111111','IT');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('523','111111','HR');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('6454','111111','SALES');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('2349','222222','IT');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('845','222222','HR');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('7435','222222','SALES');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('1322','333333','IT');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('81','333333','HR');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('94','333333','SALES');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('150','444444','IT');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('121','444444','HR');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('112','444444','SALES');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('163','555555','IT');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('164','555555','HR');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('125','555555','SALES');




INSERT INTO requirement(requirementId,applicationRef,requirement,sequence,status,statusUpdateDate,userName)Values
('35','1234','Insufficient Funds','1','PENDING','10/07/17','T2234');


INSERT INTO requirement(requirementId,applicationRef,requirement,sequence,status,statusUpdateDate,userName)Values
('36','1234','Insufficient Funds','2','PENDING','10/07/17','T2235');

INSERT INTO requirement(requirementId,applicationRef,requirement,sequence,status,statusUpdateDate,userName)Values
('37','1234','Insufficient Funds','3','PENDING','10/07/17','T2236');




INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('38','111111','Director');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('39','111111','Manager');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('40','111111','Associate');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('41','222222','Director');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('42','222222','Manager');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('43','222222','Associate');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('44','333333','Director');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('45','333333','Manager');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('46','333333','Associate');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('47','444444','Director');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('48','444444','Manager');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('49','444444','Associate');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('50','555555','Director');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('51','555555','Manager');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('52','555555','Associate');



INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('30','Utilities','111111');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('31','HR','111111');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('32','Utilities','222222');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('33','HR','222222');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('34','Utilities','333333');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('35','HR','333333');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('66','Food','333333');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('36','Utilities','444444');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('37','HR','444444');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('38','Utilities','555555');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('39','HR','555555');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('69','Food','555555');





INSERT INTO application(applicationId,POC,applicationDate,applicationRef,applicationStatus,companyReferenceNumber,modifiedDate,remarks,vendorPeriod,vendorReferenceNumber,categoryId)Values
('30','Jason','2017-12-01','AAA1234','VETTING','111111','2018-01-14','No remarks',1,'555555','30');



INSERT INTO application(applicationId,POC,applicationDate,applicationRef,applicationStatus,companyReferenceNumber,modifiedDate,remarks,vendorPeriod,vendorReferenceNumber,categoryId)Values
('31','Jeremy','2017-12-01','AAA1235','PENDING','222222','2018-02-14','No remarks',1,'555555','33');


INSERT INTO application(applicationId,POC,applicationDate,applicationRef,applicationStatus,companyReferenceNumber,modifiedDate,remarks,vendorPeriod,vendorReferenceNumber,categoryId)Values
('32','Jonathan','2017-12-01','AAA1236','PENDING','333333','2018-01-18','No remarks',1,'222222','66');



INSERT INTO application(applicationId,POC,applicationDate,applicationRef,applicationStatus,companyReferenceNumber,modifiedDate,remarks,vendorPeriod,vendorReferenceNumber,categoryId)Values
('33','Cheryl','2017-12-18','AAA1237','VETTING','444444','2018-01-19','No remarks',1,'333333','36');


INSERT INTO application(applicationId,POC,applicationDate,applicationRef,applicationStatus,companyReferenceNumber,modifiedDate,remarks,vendorPeriod,vendorReferenceNumber,categoryId)Values
('34','Susan','2017-12-19','AAA1238','PENDING','555555','2018-01-21','No remarks',1,'222222','39');






INSERT INTO companyvendorrelationship(relationshipId,companyReferenceNumber,vendorReferenceNumber)VALUES
(11,'111111','333333');

INSERT INTO companyvendorrelationship(relationshipId,companyReferenceNumber,vendorReferenceNumber)VALUES
(12,'222222','111111');


INSERT INTO companyvendorrelationship(relationshipId,companyReferenceNumber,vendorReferenceNumber)VALUES
(13,'333333','222222');

INSERT INTO companyvendorrelationship(relationshipId,companyReferenceNumber,vendorReferenceNumber)VALUES
(14,'111111','222222');


INSERT INTO companyvendorrelationship(relationshipId,companyReferenceNumber,vendorReferenceNumber)VALUES
(15,'111111','444444');




INSERT INTO vendorreference(referenceId,applicationRef,companyAddress,companyName,contactPerson,emailAddress,endDate,PhoneNumber,startDate)VALUES
(28,'AAA1234','Singapore','Company_One','Robert','robert1234@rob.com','2018-01-14','63363112','2018-04-02');


INSERT INTO vendorreference(referenceId,applicationRef,companyAddress,companyName,contactPerson,emailAddress,endDate,PhoneNumber,startDate)VALUES
(29,'AAA1235','Malaysia','Company_Two','Roger','roger123@rog.com','2018-01-14','+01214139120','2018-04-02');


INSERT INTO vendorreference(referenceId,applicationRef,companyAddress,companyName,contactPerson,emailAddress,endDate,PhoneNumber,startDate)VALUES
(30,'AAA1236','China','Company_Three','Ram','ram113@ram.com','2018-01-14','+1213131010310','2018-04-02');

INSERT INTO vendorreference(referenceId,applicationRef,companyAddress,companyName,contactPerson,emailAddress,endDate,PhoneNumber,startDate)VALUES
(31,'AAA1237','United States','Company_Four','Ray','ray123@ram.com','2018-01-14','+1213113413','2018-04-02');


INSERT INTO vendorreference(referenceId,applicationRef,companyAddress,companyName,contactPerson,emailAddress,endDate,PhoneNumber,startDate)VALUES
(32,'AAA1238','Russia','Company_Five','Rey','rey100@rey.com','2018-01-14','+44454525654','2018-04-02');




INSERT INTO employeeaccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,contactNumber,employeeEmail,employeeId,logInType,departmentId,roleId)
VALUES('1','111111',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2','1234','company_One','66635553','employee_Singapore@company.com','14231','EMPLOYEE','454','38');

INSERT INTO employeeaccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,contactNumber,employeeEmail,employeeId,logInType,departmentId,roleId)
VALUES('2','222222',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2','4567','company_Two','666411331','company_Malaysia@company.com','134491','EMPLOYEE','2349','42');

INSERT INTO employeeaccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,contactNumber,employeeEmail,employeeId,logInType,departmentId,roleId)
VALUES('3','333333',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2','7890','company_Three','5643352442','company_China@company.com','123112','EMPLOYEE','94','45');


INSERT INTO employeeaccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,contactNumber,employeeEmail,employeeId,logInType,departmentId,roleId)
VALUES('4','444444',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2','0123','company_Four','54634242423','company_UnitedStates@company.com','423131','EMPLOYEE','112','47');

INSERT INTO employeeaccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,contactNumber,employeeEmail,employeeId,logInType,departmentId,roleId)
VALUES('5','555555',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2','1123','company_Five','313113121','company_Russia@company.com','121123','EMPLOYEE','125','52');




INSERT INTO companyadministratoraccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,logInType)VALUES
(1212,'111111',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2',1234,'companyOne','COMPANY_ADMINISTRATOR');

INSERT INTO companyadministratoraccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,logInType)VALUES
(2123,'222222',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2',4567,'companyTwo','COMPANY_ADMINISTRATOR');

INSERT INTO companyadministratoraccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,logInType)VALUES
(3123,'333333',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2',7890,'companyThree','COMPANY_ADMINISTRATOR');

INSERT INTO companyadministratoraccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,logInType)VALUES
(4134,'444444',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2',0123,'companyFour','COMPANY_ADMINISTRATOR');

INSERT INTO companyadministratoraccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,logInType)VALUES
(5234,'555555',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2',0012,'companyFive','COMPANY_ADMINISTRATOR');




