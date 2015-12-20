use TwentyWork

DROP TABLE ShareFile;
CREATE TABLE ShareFile(
	fileId			int				IDENTITY(1,1)	PRIMARY KEY,
	fileName_		nvarchar(50)	not null,
	fileType		varchar(10)		not null,
	fileSize		int,
	updateTime		datetime,
	userId			int,				
	teamId			int,			
	upperFolderId	int,				
	CONSTRAINT FK_Products_upperFolderId  foreign key (upperFolderId) references ShareFile (fileId) 
);
 INSERT INTO ShareFile VALUES ( 'WebApp根目錄' , '資料夾' ,null ,null ,null,null, null);
 INSERT INTO ShareFile VALUES ( 'Group1根目錄' , '資料夾' ,null ,null ,1,1, 1);
 INSERT INTO ShareFile VALUES ( 'Group2根目錄' , '資料夾' ,null ,null ,1,2, 1);
 INSERT INTO ShareFile VALUES ( 'Group2-1' , '資料夾' ,null ,null ,1,2, 3);
 INSERT INTO ShareFile VALUES ( 'Group1-1' , '資料夾' ,null ,null ,2,1, 2);
 INSERT INTO ShareFile VALUES ( 'Group1-2' , '資料夾' ,null ,null ,3,1, 2);
 INSERT INTO ShareFile VALUES ( 'Group1-3' , '資料夾' ,null ,null ,3,1, 2);
 INSERT INTO ShareFile VALUES ( 'Group2-1-1' , '資料夾' ,null ,null ,1,2, 4);
 INSERT INTO ShareFile VALUES ( 'Group2-1-2' , '資料夾' ,null ,null ,2,2, 4);
 INSERT INTO ShareFile VALUES ( 'Group1-1-1' , '資料夾' ,null ,null ,3,1, 5);--10
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,1, 2);
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,1, 2);
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,1, 2);
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,1, 6)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,1, 6)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,1, 7)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,1, 7)--17


 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,2, 4)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,2, 4)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,2, 8)
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,1,2, 9)--21
 INSERT INTO ShareFile VALUES ( 'Group3根目錄' , '資料夾' ,null ,null ,1,3, 1);

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