use TwentyWork
DROP TABLE TeamUser;
DROP TABLE ShareFile;

DROP TABLE Memember;
CREATE TABLE Memember(
	userId		int				IDENTITY(1,1) PRIMARY KEY,
	username	nvarchar(35)	not null,
	email		varchar(35)		not null,
	password_	varchar(35)		not null,
	birth		date,
	userImage	image,
	cellphone	varchar(15),
	phone		varchar(15)
);
INSERT INTO Memember VALUES('Group1-Mark','group1.mark@gmail.com','m',null,null,null,null);--1
INSERT INTO Memember VALUES('Group1-Peter','group1.peter@gmail.com','p',null,null,null,null);--2
INSERT INTO Memember VALUES('Group1-John','group1.john@gmail.com','j',null,null,null,null);--3
INSERT INTO Memember VALUES('Group1-Tinasha','group1.tinasha@gmail.com','t',null,null,null,null);--4
INSERT INTO Memember VALUES('Group1-Regard','group1.regard@gmail.com','r',null,null,null,null);--5

INSERT INTO Memember VALUES('Group2 Boss','Group2.Boss@gmail.com','b',null,null,null,null);--6
INSERT INTO Memember VALUES('Group2 Candy','Group2.Candy@gmail.com','c',null,null,null,null);--7
INSERT INTO Memember VALUES('Group2 Danny','Group2.Danny@gmail.com','d',null,null,null,null);--8
INSERT INTO Memember VALUES('Group2 Eric','Group2.Eric@gmail.com','e',null,null,null,null);--9

INSERT INTO Memember VALUES('Group3-Jojo','Group3.Jojo@gmail.com','j',null,null,null,null);--10


DROP TABLE Team;
CREATE TABLE Team(
	teamId		int				IDENTITY(1,1) PRIMARY KEY,
	teamName	nvarchar(20)	not null,
	teamImage	image
);
INSERT INTO Team VALUES('Group1',null);
INSERT INTO Team VALUES('Group2',null);
INSERT INTO Team VALUES('Group3',null);


CREATE TABLE TeamUser(
	userId		int,
	teamId		int,
	post		varchar(10),
	depart		varchar(10),
	extension	varchar(15),
	activeDate	date			not null,
	rights		int				not null,
	CONSTRAINT FK_TeamUser_userId  foreign key (userId) references Memember (userId) ,
	CONSTRAINT FK_TeamUser_teamId  foreign key (teamId) references Team (teamId) ,
	CONSTRAINT PK_TeamUser PRIMARY KEY (userId,teamId)
);
INSERT INTO TeamUser VALUES(1,1,null,null,null,'2015-12-15',1);
INSERT INTO TeamUser VALUES(2,1,null,null,null,'2015-12-15',1);
INSERT INTO TeamUser VALUES(3,1,null,null,null,'2015-12-15',2);
INSERT INTO TeamUser VALUES(4,1,null,null,null,'2015-12-15',2);
INSERT INTO TeamUser VALUES(5,1,null,null,null,'2015-12-15',2);

INSERT INTO TeamUser VALUES(1,2,null,null,null,'2015-12-15',1);
INSERT INTO TeamUser VALUES(6,2,null,null,null,'2015-12-16',1);
INSERT INTO TeamUser VALUES(7,2,null,null,null,'2015-12-16',2);
INSERT INTO TeamUser VALUES(8,2,null,null,null,'2015-12-16',2);
INSERT INTO TeamUser VALUES(9,2,null,null,null,'2015-12-16',2);

INSERT INTO TeamUser VALUES(10,3,null,null,null,'2015-12-16',1);



CREATE TABLE ShareFile(
	fileId			int				IDENTITY(1,1)	PRIMARY KEY,
	fileName_		nvarchar(50)	not null,
	fileType		varchar(10)		not null,
	fileSize		int,
	updateTime		datetime,
	userId			int,				
	teamId			int,			
	upperFolderId	int,				
	CONSTRAINT FK_ShareFile_upperFolderId	foreign key (upperFolderId) references ShareFile (fileId) ,
	CONSTRAINT FK_ShareFile_userId			foreign key (userId)		references Memember (userId) ,
	CONSTRAINT FK_ShareFile_teamId			foreign key (teamId)		references Team (teamId) 
);
 INSERT INTO ShareFile VALUES ( 'WebApp根目錄' , '資料夾' ,null ,null ,null,null, null);
 INSERT INTO ShareFile VALUES ( 'Group1根目錄' , '資料夾' ,null ,null ,1,1, 1);
 INSERT INTO ShareFile VALUES ( 'Group2根目錄' , '資料夾' ,null ,null ,1,2, 1);
 INSERT INTO ShareFile VALUES ( 'Group2-1' , '資料夾' ,null ,null ,1,2, 3);
 INSERT INTO ShareFile VALUES ( 'Group1-1' , '資料夾' ,null ,null ,2,1, 2);
 INSERT INTO ShareFile VALUES ( 'Group1-2' , '資料夾' ,null ,null ,3,1, 2);
 INSERT INTO ShareFile VALUES ( 'Group1-3' , '資料夾' ,null ,null ,3,1, 2);
 INSERT INTO ShareFile VALUES ( 'Group2-1-1' , '資料夾' ,null ,null ,1,2, 4);
 INSERT INTO ShareFile VALUES ( 'Group2-1-2' , '資料夾' ,null ,null ,6,2, 4);
 INSERT INTO ShareFile VALUES ( 'Group1-1-1' , '資料夾' ,null ,null ,3,1, 5);--10
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,1, 2);
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,2,1, 2);
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,3,1, 2);
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,4,1, 6)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,5,1, 6)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,1, 7)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,2,1, 7)--17


 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,2, 4)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,6,2, 4)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,7,2, 8)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,8,2, 9)--21
 INSERT INTO ShareFile VALUES ( 'Group3根目錄' , '資料夾' ,null ,null ,10,3, 1);

 SELECT * FROM ShareFile
 GO

   DROP PROCEDURE [dbo].[gen_folder_tree]
GO
 create procedure gen_folder_tree ( @v_teamId  int)
  AS
  BEGIN
	 WITH FileTree (fileId, fileName_,fileType,fileSize, teamId,upperFolderId , fileLevel)AS 
	(
		SELECT fileId, fileName_,fileType,fileSize, teamId,upperFolderId , 1 as level
		FROM   ShareFile
		WHERE  fileSize IS NULL
		AND upperFolderId=1 --IS NULL
		AND teamId =@v_teamId

		UNION ALL

		SELECT shr.fileId , shr.fileName_ ,shr.fileType, shr.fileSize,shr.teamId, shr.upperFolderId, tree.fileLevel + 1
		FROM ShareFile shr INNER JOIN FileTree tree
		ON shr.upperFolderId  = tree.fileId 
		WHERE  shr.fileSize IS NULL 
	)
	SELECT * FROM FileTree 
	order by fileLevel 
  END;
  GO
 -- exec gen_folder_tree 1
 
  DROP PROCEDURE [dbo].[find_file_by_fileName]
GO
 create procedure find_file_by_fileName ( @v_fileId  int,@v_queryString nvarchar(50))
  AS
  BEGIN
 WITH findFile (fileId, fileName_,fileType,fileSize,updateTime,userId, teamId,upperFolderId , fileLevel)AS 
	(
		SELECT fileId, fileName_,fileType,fileSize,updateTime,userId, teamId,upperFolderId , 1 as level
		FROM   ShareFile
		WHERE  fileId=@v_fileId

		UNION ALL

		SELECT shr.fileId , shr.fileName_ , shr.fileType , shr.fileSize , shr.updateTime , shr.userId,shr.teamId, shr.upperFolderId, tree.fileLevel + 1
		FROM ShareFile shr INNER JOIN findFile tree
		ON shr.upperFolderId  = tree.fileId 
		
	)
	SELECT * FROM findFile
	WHERE  fileName_ LIKE '%'+@v_queryString+'%' 
	and fileLevel>1
	order by fileLevel 
 END;
 GO
 -- exec find_file_by_fileName 2,'g'

 DROP PROCEDURE [dbo].[find_delete_files]
GO
 create procedure find_delete_files ( @v_fileId  int)
  AS
  BEGIN
 WITH findFile (fileId, fileLevel)AS 
	(
		SELECT fileId , 1 as level
		FROM   ShareFile
		WHERE  fileId= @v_fileId

		UNION ALL

		SELECT shr.fileId , tree.fileLevel + 1 
		FROM ShareFile shr INNER JOIN findFile tree
		ON shr.upperFolderId  = tree.fileId 
		
	)
	DELETE ShareFile 
	WHERE fileId IN(
	SELECT fileId FROM findFile
	)
 END;
 GO
  --exec find_delete_files 3