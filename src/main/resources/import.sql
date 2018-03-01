
INSERT INTO company (companyPrimaryId, companyAddress, companyEmail, companyName, companyReferenceNumber, companyWebsite,contactNumber,turnover)
VALUES ('5432','Singapore','company_Singapore@company.com','Company_One','111111','www.Company_One.com','66635553','10');

INSERT INTO company (companyPrimaryId, companyAddress, companyEmail, companyName, companyReferenceNumber, companyWebsite,contactNumber,turnover)
VALUES ('3425','Malaysia','company_Malaysia@company.com','Company_Two','222222','www.Company_Two.com','01222355112','20');

INSERT INTO company (companyPrimaryId, companyAddress, companyEmail, companyName, companyReferenceNumber, companyWebsite,contactNumber,turnover)
VALUES ('5646','China','company_China@company.com','Company_Three','333333','www.Company_Three.com','43139234912','30');

INSERT INTO company (companyPrimaryId, companyAddress, companyEmail, companyName, companyReferenceNumber, companyWebsite,contactNumber,turnover)
VALUES ('67876','United States','company_UnitedStates@company.com','Company_Four','444444','www.Company_Four','122432941139','40');

INSERT INTO company (companyPrimaryId, companyAddress, companyEmail, companyName, companyReferenceNumber, companyWebsite,contactNumber,turnover)
VALUES ('34535','Russia','company_Russia@company.com','Company_Five','555555','www.Company_Five','21211371631','50');



INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('452344','111111','IT');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('523423','111111','HR');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('645454','111111','SALES');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('2343549','222222','IT');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('84435','222222','HR');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('7432345','222222','SALES');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('123424322','333333','IT');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('82343241','333333','HR');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('9342434','333333','SALES');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('1556750','444444','IT');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('1567621','444444','HR');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('1143432','444444','SALES');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('1633433','555555','IT');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('1623444','555555','HR');

INSERT INTO department(departmentId,companyReferenceNumber,departmentName)
VALUES('1234435','555555','SALES');




INSERT INTO requirement(requirementId,applicationRef,requirement,sequence,status,statusUpdateDate,userName)Values
('33245','1234','Insufficient Funds','1','PENDING','10/07/17','T2234');


INSERT INTO requirement(requirementId,applicationRef,requirement,sequence,status,statusUpdateDate,userName)Values
('32346','1234','Insufficient Funds','2','PENDING','10/07/17','T2235');

INSERT INTO requirement(requirementId,applicationRef,requirement,sequence,status,statusUpdateDate,userName)Values
('32347','1234','Insufficient Funds','3','PENDING','10/07/17','T2236');




INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('33248','111111','Director');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('32349','111111','Manager');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('23440','111111','Associate');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('42341','222222','Director');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('42342','222222','Manager');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('4234233','222222','Associate');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('324244','333333','Director');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('43245','333333','Manager');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('42346','333333','Associate');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('42347','444444','Director');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('42348','444444','Manager');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('49654','444444','Associate');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('50456','555555','Director');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('53241','555555','Manager');

INSERT INTO role(roleId,companyReferenceNumber,role)
VALUES('52342','555555','Associate');



INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('345320','Utilities','111111');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('323451','HR','111111');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('3234522','Utilities','222222');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('323453','HR','222222');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('34232','Utilities','333333');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('35234','HR','333333');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('62346','Food','333333');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('34536','Utilities','444444');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('34567','HR','444444');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('35468','Utilities','555555');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('32349','HR','555555');

INSERT INTO category(categoryId,categoryName,companyReferenceNumber)Values
('69654','Food','555555');





INSERT INTO application(applicationId,POC,applicationDate,applicationRef,applicationStatus,companyReferenceNumber,modifiedDate,remarks,vendorPeriod,vendorReferenceNumber,categoryId)Values
('305465','Jason','2017-12-01','AAA1234','VETTING','111111','2018-01-14','No remarks',1,'555555','323451');



INSERT INTO application(applicationId,POC,applicationDate,applicationRef,applicationStatus,companyReferenceNumber,modifiedDate,remarks,vendorPeriod,vendorReferenceNumber,categoryId)Values
('313454','Jeremy','2017-12-01','AAA1235','PENDING','222222','2018-02-14','No remarks',1,'555555','3234522');


INSERT INTO application(applicationId,POC,applicationDate,applicationRef,applicationStatus,companyReferenceNumber,modifiedDate,remarks,vendorPeriod,vendorReferenceNumber,categoryId)Values
('3234543','Jonathan','2017-12-01','AAA1236','PENDING','333333','2018-01-18','No remarks',1,'222222','62346');



INSERT INTO application(applicationId,POC,applicationDate,applicationRef,applicationStatus,companyReferenceNumber,modifiedDate,remarks,vendorPeriod,vendorReferenceNumber,categoryId)Values
('312333','Cheryl','2017-12-18','AAA1237','VETTING','444444','2018-01-19','No remarks',1,'333333','34567');


INSERT INTO application(applicationId,POC,applicationDate,applicationRef,applicationStatus,companyReferenceNumber,modifiedDate,remarks,vendorPeriod,vendorReferenceNumber,categoryId)Values
('3423454','Susan','2017-12-19','AAA1238','PENDING','555555','2018-01-21','No remarks',1,'222222','69654');






INSERT INTO companyvendorrelationship(relationshipId,companyReferenceNumber,vendorReferenceNumber)VALUES
(564,'111111','333333');

INSERT INTO companyvendorrelationship(relationshipId,companyReferenceNumber,vendorReferenceNumber)VALUES
(45654,'222222','111111');


INSERT INTO companyvendorrelationship(relationshipId,companyReferenceNumber,vendorReferenceNumber)VALUES
(234234,'333333','222222');

INSERT INTO companyvendorrelationship(relationshipId,companyReferenceNumber,vendorReferenceNumber)VALUES
(32423,'111111','222222');


INSERT INTO companyvendorrelationship(relationshipId,companyReferenceNumber,vendorReferenceNumber)VALUES
(21323,'111111','444444');




INSERT INTO vendorreference(referenceId,applicationRef,companyAddress,companyName,contactPerson,emailAddress,endDate,PhoneNumber,startDate)VALUES
(54654,'AAA1234','Singapore','Company_One','Robert','robert1234@rob.com','2018-01-14','63363112','2018-04-02');


INSERT INTO vendorreference(referenceId,applicationRef,companyAddress,companyName,contactPerson,emailAddress,endDate,PhoneNumber,startDate)VALUES
(456454,'AAA1235','Malaysia','Company_Two','Roger','roger123@rog.com','2018-01-14','+01214139120','2018-04-02');


INSERT INTO vendorreference(referenceId,applicationRef,companyAddress,companyName,contactPerson,emailAddress,endDate,PhoneNumber,startDate)VALUES
(456254,'AAA1236','China','Company_Three','Ram','ram113@ram.com','2018-01-14','+1213131010310','2018-04-02');

INSERT INTO vendorreference(referenceId,applicationRef,companyAddress,companyName,contactPerson,emailAddress,endDate,PhoneNumber,startDate)VALUES
(312234,'AAA1237','United States','Company_Four','Ray','ray123@ram.com','2018-01-14','+1213113413','2018-04-02');


INSERT INTO vendorreference(referenceId,applicationRef,companyAddress,companyName,contactPerson,emailAddress,endDate,PhoneNumber,startDate)VALUES
(234333,'AAA1238','Russia','Company_Five','Rey','rey100@rey.com','2018-01-14','+44454525654','2018-04-02');




INSERT INTO employeeaccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,employeeName,employeeEmail,employeeId,logInType,departmentId,roleId)
VALUES('1234345','111111',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2','1234','company_One','66635553','employee_Singapore@company.com','14231','EMPLOYEE','523423','32349');

INSERT INTO employeeaccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,employeeName,employeeEmail,employeeId,logInType,departmentId,roleId)
VALUES('4532232','222222',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2','4567','company_Two','666411331','company_Malaysia@company.com','134491','EMPLOYEE','2343549','42341');

INSERT INTO employeeaccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,employeeName,employeeEmail,employeeId,logInType,departmentId,roleId)
VALUES('1232163','333333',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2','7890','company_Three','5643352442','company_China@company.com','123112','EMPLOYEE','123424322','42346');


INSERT INTO employeeaccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,employeeName,employeeEmail,employeeId,logInType,departmentId,roleId)
VALUES('1232123','444444',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2','0123','company_Four','54634242423','company_UnitedStates@company.com','423131','EMPLOYEE','1143432','49654');

INSERT INTO employeeaccount(accountId,companyReferenceNumber,isLogin,isValidated,password,token,userName,employeeName,employeeEmail,employeeId,logInType,departmentId,roleId)
VALUES('231332','555555',0,1,'$2a$10$ACmX1/kJJZLEkg3R3UY4mu50dQFJHRVJraWDJpQx7YuQyGC36A9q2','1123','company_Five','313113121','company_Russia@company.com','121123','EMPLOYEE','1633433','50456');




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




