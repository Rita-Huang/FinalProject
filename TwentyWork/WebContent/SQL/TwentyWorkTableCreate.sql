use TwentyWork

DROP TABLE ShareFile;
CREATE TABLE ShareFile(
	fileId			int				IDENTITY(1,1)	PRIMARY KEY,
	fileName_		nvarchar(50)	not null,
	fileType		varchar(10)		not null,
	fileSize		int,
	updateTime		datetime,
	file_			varbinary(max),
	userId			int				not null,
	teamId			int				not null,
	upperFolderId	int				
	foreign key (upperFolderId) references ShareFile (fileId)
);

/*
DROP TABLE EMPLOYEES;
CREATE TABLE EMPLOYEES
    ( EMPLOYEE_ID    INT          CONSTRAINT emp_emp_id_pk PRIMARY KEY,
      FIRST_NAME     VARCHAR(20),
      LAST_NAME      VARCHAR(25)  CONSTRAINT emp_last_name_nn  NOT NULL,
      EMAIL          VARCHAR(25)  CONSTRAINT emp_email_nn  NOT NULL,
      PHONE_NUMBER   VARCHAR(20),
      HIRE_DATE      DATE	  CONSTRAINT emp_hire_date_nn  NOT NULL,
      JOB_ID         VARCHAR(10)  CONSTRAINT emp_job_nn  NOT NULL,
      SALARY         INT, 
      COMMISSION_PCT decimal(2,2) ,
      MANAGER_ID     INT,
      DEPARTMENT_ID  INT,
      CONSTRAINT     emp_salary_min  CHECK (salary > 0)     ,
      CONSTRAINT     emp_email_uk  UNIQUE (email) ,
   --   CONSTRAINT     emp_dept_fk  FOREIGN KEY (department_id) REFERENCES departments ,
   --   CONSTRAINT     emp_job_fk FOREIGN KEY (job_id) REFERENCES jobs (job_id) ,
      CONSTRAINT     emp_manager_fk FOREIGN KEY (manager_id) REFERENCES employees)
go
*/