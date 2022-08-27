/*
TABLE Department
*/
CREATE TABLE Department (
    ID int IDENTITY(1,1) NOT NULL,
    DepartName NVARCHAR(8),
    IsDeleted bit NOT NULL DEFAULT 0,
    [Timestamp] datetime NOT NULL DEFAULT GETDATE(),
    CONSTRAINT PK_Department PRIMARY KEY (ID)
);
SELECT * FROM MstEventType
/*EventType*/
CREATE TABLE MstEventType (
    ID int IDENTITY(1,1) NOT NULL,
    EventType NVARCHAR(200),
    IsDeleted bit NOT NULL DEFAULT 0,
    [Timestamp] datetime NOT NULL DEFAULT GETDATE(),
    CONSTRAINT PK_MstEventType PRIMARY KEY (ID)
);

INSERT INTO MstEventType VALUES('Team building',DEFAULT,DEFAULT);
INSERT INTO MstEventType VALUES('Travel',DEFAULT,DEFAULT);
INSERT INTO MstEventType VALUES('Training',DEFAULT,DEFAULT);

 
 

/*
TABLE Users
+ DepartID: ID của bảng Department
+ IsDeleted : 0 : Chưa xoá / 1 : Xoá (Mặc định là 0)
*/
CREATE TABLE Users (
    ID int IDENTITY(1,1) NOT NULL,
    Username VARCHAR(8) unique NOT NULL,
    Fullname NVARCHAR(32) NOT NULL,
    DepartID int NOT NULL,
	Email VARCHAR(128) unique,	
	[Password] VARCHAR(128)	NOT NULL,
	IsDeleted BIT NOT NULL DEFAULT 0,
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),
    CONSTRAINT PK_Users PRIMARY KEY (ID),
);


CREATE TABLE Events (
    ID int IDENTITY(1,1) NOT NULL,
    EventName NVARCHAR(250) NOT NULL,
    EventType int NOT NULL,			/*1: Team building, 2: Travel, 3: Training*/
    EventImage VARCHAR(150),		
	Creator int	NOT NULL,			/*Người tạo event*/
	[Description] NVARCHAR(500),	/*Mô tả event*/
	[Status] int NOT NULL,          /*1: Opening / 2: Closed*/
	OptionType int	NOT NULL,		/*1: Chọn 1 / 2 : Chọn nhiều*/
	Place NVARCHAR(50),				/*Địa điểm tổ chức*/
	StartDate DATE,				/*Ngày bắt đầu*/
	StartHour TIME,				/*Giờ bắt đầu*/
	EndDate DATE,				/*Ngày kết thúc*/
	EndHour TIME,				/*Giờ kết thúc*/
	IsDeleted bit NOT NULL DEFAULT 0,		/*0 : Chưa xoá / 1 : Xoá (Mặc định là 0)*/
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),
	CONSTRAINT PK_Events PRIMARY KEY (ID),
);



CREATE TABLE RegistEvents (
    ID int IDENTITY(1,1) NOT NULL,
	Username VARCHAR(8) NOT NULL,	/*Username user vote*/
    EventID int	NOT NULL,			/*Event ID*/
	IsJoined BIT NOT NULL,			/*0: không join, 1: có join*/
	VoteOptionID int,		 
	Voted bit NOT NULL DEFAULT 0,				/*0 : Không chọn / 1 : Chọn*/
	AttachedPersonAdult int,
	AttachedPersonChild int,
	IsDeleted bit NOT NULL DEFAULT 0,		
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),	
	CONSTRAINT PK_RegistEvents PRIMARY KEY (ID),

);

SELECT * FROM Events;
SELECT * FROM VoteOption
 

CREATE TABLE VoteOption (
    ID int IDENTITY(1,1) NOT NULL,
    EventID int	NOT NULL,
    StartDate DATETIME,		/*Ngày tổ chức event*/
	Place NVARCHAR(128),		/*Địa điểm tổ chức*/
	OptionImage VARCHAR(150),			
	IsDeleted bit NOT NULL DEFAULT 0,	/*0 : Chưa xoá / 1 : Xoá (Mặc định là 0)*/	
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),
	CONSTRAINT PK_VoteOption PRIMARY KEY (ID),
);







/*Department*/
INSERT INTO Department VALUES ('DEV 01',DEFAULT,DEFAULT);
INSERT INTO Department VALUES ('DEV 02',DEFAULT,DEFAULT);
INSERT INTO Department VALUES ('DEV 03',DEFAULT,DEFAULT);
INSERT INTO Department VALUES ('DEV 04',DEFAULT,DEFAULT);

SELECT * FROM Department;


/*Users*/
INSERT INTO Users VALUES ('NgocNB1',N'Nguyen Ba Ngoc1',1,'ngocmeu20001@gmail.com','abcd1234',DEFAULT,DEFAULT);
INSERT INTO Users VALUES ('NgocNB2',N'Nguyen Ba Ngoc2',2,'ngocmeu20002@gmail.com','abcd1234',DEFAULT,DEFAULT);
INSERT INTO Users VALUES ('NgocNB3',N'Nguyen Ba Ngoc3',3,'ngocmeu20003@gmail.com','abcd1234',DEFAULT,DEFAULT);
INSERT INTO Users VALUES ('NgocNB4',N'Nguyen Ba Ngoc4',1,'ngocmeu20004@gmail.com','abcd1234',DEFAULT,DEFAULT);
INSERT INTO Users VALUES ('NgocNB5',N'Nguyen Ba Ngoc5',2,'ngocmeu20005@gmail.com','abcd1234',DEFAULT,DEFAULT);
INSERT INTO Users VALUES ('NgocNB6',N'Nguyen Ba Ngoc5',4,'ngocmeu20006@gmail.com','abcd1234',DEFAULT,DEFAULT);

SELECT * FROM Department;


/*Events*/
INSERT INTO Events VALUES (N'Liên hoan intern ship',1,NULL,1,N'Nguyen Ba Ngoc1 description',
						1,1,N'Bia Thu Hằng - Nguyễn Phong Sắc',
						CAST(dateadd(DD,+0,getdate()) AS DATE),(CAST('00:01:15' AS TIME)),
						CAST(dateadd(DD,+1,getdate()) AS DATE),(CAST('10:03:15' AS TIME)),
						DEFAULT,DEFAULT);

INSERT INTO Events VALUES (N'Du lịch công ty',2,NULL,2,N'Nguyen Ba Ngoc2 description',
						1,2,N'Bãi biển cát bà',
						CAST('12/01/2019' as date),CAST('2pm' AS time),
						CAST('12/01/2019' as date),CAST('2pm' AS time),
						DEFAULT,DEFAULT);
SELECT * FROM Events;
 
 

 /* insert voteOption */
INSERT INTO VoteOption (EventID, StartDate, Place, OptionImage, IsDeleted, [Timestamp])
VALUES (2,CAST('2022-12-21 14:30' AS DATETIME) + 2,N'Quán ăn 2.1',NULL,DEFAULT,DEFAULT);

INSERT INTO VoteOption (EventID, StartDate, Place, OptionImage, IsDeleted, [Timestamp])
VALUES (2,CAST('2022-11-21 14:30' AS DATETIME) + 1,N'Quán ăn 2.2',NULL,DEFAULT,DEFAULT);

INSERT INTO VoteOption (EventID, StartDate, Place, OptionImage, IsDeleted, [Timestamp])
VALUES (2,CAST('2022-10-21 14:30' AS DATETIME) + 1,N'Quán ăn 2.3',NULL,DEFAULT,DEFAULT);

SELECT * FROM VoteOption;
 DROP TABLE VoteOption



 
/*sự kiện 1 loại 1 option, có join,mang đi 6ng lớn,voted 1, 5 trẻ em */
INSERT INTO RegistEvents(Username, EventID, IsJoined, VoteOptionID, Voted, AttachedPersonAdult,
AttachedPersonChild,IsDeleted)
VALUES ('NgocNB1',1,1,
NULL,1,
6,5,DEFAULT);

/*sự kiện 1 loại 1 option, không join,voted mặc định 0,nên không dc mang theo ai */
INSERT INTO RegistEvents (Username, EventID, IsJoined, VoteOptionID, Voted, AttachedPersonAdult,
AttachedPersonChild,IsDeleted)
VALUES ('NgocNB2',1,0,
NULL,DEFAULT,
NULL,NULL,DEFAULT);

/*sự kiện 1 loại 1 option, có join,có voted là đi, nên mang đi 8ng lớn, 5 trẻ em */
INSERT INTO RegistEvents (Username, EventID, IsJoined, VoteOptionID, Voted, AttachedPersonAdult,
AttachedPersonChild,IsDeleted)
VALUES ('NgocNB3',1,1,
NULL,1,
8,5,DEFAULT);



/* option type là 2, VoteOptionID 1, voted 1 có đi,nên được mang theo người lớn trẻ em */
INSERT INTO RegistEvents (Username, EventID, IsJoined, VoteOptionID, Voted, AttachedPersonAdult,
AttachedPersonChild,IsDeleted)
VALUES ('NgocNB4',2,1,
1,1,
9,5,DEFAULT);

/* option type là 2, VoteOptionID 1,có join, voted 0 nên không đi, nên không được amng theo người có đi */
INSERT INTO RegistEvents (Username, EventID, IsJoined, VoteOptionID, Voted, AttachedPersonAdult,
AttachedPersonChild,IsDeleted)
VALUES ('NgocNB5',2,1,
2,0,
NULL,NULL,DEFAULT);

/* option type là 2, VoteOptionID 2, voted 1 nên  đi, được mang 8 ng lớn 5 trẻ em */
INSERT INTO RegistEvents (Username, EventID, IsJoined, VoteOptionID, Voted, AttachedPersonAdult,
AttachedPersonChild,IsDeleted)
VALUES ('NgocNB6',2,1,
2,1,
8,5,DEFAULT);


/* kết luân có NgocNB1,NgocNB3, vừa join vừa vote đi
	+ NgocNB2 không join nê vote mặc định là 0

	+ NgocNB4 chọn loại option type là 2, có join, có chọn đi là 1
	+ NgocNB5 có join, nhưng voted 0 , nên số ng mang đi là 0
	+ NgocNB6, Có join, option type là 2, VoteOptionID 2, voted 1 nên  đi, được mang 8 ng lớn 5 trẻ em
		Tổng có 5 join 1 không, 1 ko join    */

 
 
 SELECT * FROM VoteOption
 SELECT * FROM Events
SELECT * FROM RegistEvents AS R WHERE R.EventID=1
SELECT * FROM Users
 
 /*lấy số người join và không join*/
SELECT COUNT(*) AS Joined FROM RegistEvents AS R WHERE R.IsJoined=1 AND  Events.ID=1;
SELECT COUNT(*) AS Joined FROM RegistEvents AS R WHERE R.IsJoined=0;
 


(SELECT COUNT(*) FROM RegistEvents AS R WHERE R.IsJoined=1 AND R.EventID=1) AS Joined;
(SELECT COUNT(*) FROM RegistEvents AS R WHERE R.IsJoined=0 AND R.EventID=0) AS 'Not Joined';

/* lấy Event name,Description,Time,Place,Department,Creator,Status */
 SELECT E.ID, E.EventName,E.[Description],E.Place,E.[Status],E.EventType AS EventTypeID,
 E.StartDate,E.StartHour,E.EndDate,E.EndHour,
convert(varchar, E.StartDate) + ' ' + convert(varchar(5),E.StartHour)+' ~ '+convert(varchar, E.EndDate) + ' ' + convert(varchar(5),E.EndHour) AS [Time],
(SELECT COUNT(*) FROM RegistEvents AS R 
	WHERE R.EventID=E.ID AND R.IsJoined=1) AS Joined, 
(SELECT COUNT(*) FROM RegistEvents AS R 
	WHERE R.EventID=E.ID AND R.IsJoined=0) AS 'NotJoined',
(SELECT U.Username FROM Users AS U WHERE U.ID = E.Creator) AS Creator,
 (SELECT D.DepartName FROM Users AS U,Department as D WHERE U.ID = E.Creator AND U.DepartID=D.ID) AS DepartName,
  (SELECT D.ID AS DepartID FROM Users AS U,Department as D WHERE U.ID = E.Creator AND U.DepartID=D.ID) AS DepartName,
 (SELECT M.EventType FROM MstEventType AS M WHERE M.ID=E.EventType) AS EventType
		FROM Department AS D 
	INNER JOIN Users AS U
		ON D.ID = U.DepartID
	INNER JOIN RegistEvents AS R
		ON R.Username = U.Username
	INNER JOIN [Events] AS E
		ON E.ID = R.EventID
		GROUP BY  E.EventName,E.[Description],E.ID,
		E.Creator,Place,E.[Status], E.StartDate,E.EventType,
		E.StartHour,E.EndDate,E.EndHour,E.ID;



	SELECT * FROM Users;
	SELECT * FROM Events;


 
 
 








select ((SELECT 
convert(varchar, E.StartDate) + ' ' + convert(varchar(5),
E.StartHour)+' ~ '+convert(varchar, E.EndDate) + ' ' + convert(varchar(5),E.EndHour)
FROM [Events] AS E WHERE E.ID=1
)) AS [Time] FROM [Events] AS E






  SELECT (E.StartDate +' '+ E.StartHour+'~~'+E.EndDate +' '+ E.EndHour)  AS [Time]
  FROM [Events] AS E

/* lấy thời gian*/
SELECT 
convert(varchar, E.StartDate) + ' ' + convert(varchar(5),
E.StartHour)+' ~ '+convert(varchar, E.EndDate) + ' ' + convert(varchar(5),E.EndHour)
AS [Time]
FROM [Events] AS E

CREATE TABLE RegistEvents (
    ID int IDENTITY(1,1) NOT NULL,
	Username VARCHAR(8) NOT NULL,	/*Username user vote*/
    EventID int	NOT NULL,			/*Event ID*/
	IsJoined BIT NOT NULL,			/*0: không join, 1: có join*/
	VoteOptionID int,		 
	Voted bit NOT NULL DEFAULT 0,				/*0 : Không chọn / 1 : Chọn*/
	AttachedPersonAdult int,
	AttachedPersonChild int,
	IsDeleted bit NOT NULL DEFAULT 0,		
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),	
	CONSTRAINT PK_RegistEvents PRIMARY KEY (ID),

);

 




 

CREATE TABLE VoteOption (
    ID int IDENTITY(1,1) NOT NULL,
    EventID int	NOT NULL,
    StartDate DATETIME,		/*Ngày tổ chức event*/
	Place NVARCHAR(128),		/*Địa điểm tổ chức*/
	OptionImage VARCHAR(150),			
	IsDeleted bit NOT NULL DEFAULT 0,	/*0 : Chưa xoá / 1 : Xoá (Mặc định là 0)*/	
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),
	CONSTRAINT PK_VoteOption PRIMARY KEY (ID),
);

DROP TABLE VoteOption











CREATE TABLE Events (
    ID int IDENTITY(1,1) NOT NULL,
    EventName NVARCHAR(250) NOT NULL,
    EventType int NOT NULL,			/*1: Team building, 2: Travel, 3: Training*/
    EventImage VARCHAR(150),		
	Creator int	NOT NULL,			/*Người tạo event*/
	[Description] NVARCHAR(500),	/*Mô tả event*/
	[Status] int NOT NULL,          /*1: Opening / 2: Closed*/
	OptionType int	NOT NULL,		/*1: Chọn 1 / 2 : Chọn nhiều*/
	Place NVARCHAR(50),				/*Địa điểm tổ chức*/
	StartDate DATE,				/*Ngày bắt đầu*/
	StartHour TIME,				/*Giờ bắt đầu*/
	EndDate DATE,				/*Ngày kết thúc*/
	EndHour TIME,				/*Giờ kết thúc*/
	IsDeleted bit NOT NULL DEFAULT 0,		/*0 : Chưa xoá / 1 : Xoá (Mặc định là 0)*/
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),
	CONSTRAINT PK_Events PRIMARY KEY (ID),
);

 












/*
TABLE Department
*/
CREATE TABLE Department (
    ID int IDENTITY(1,1) NOT NULL,
    DepartName NVARCHAR(8),
    IsDeleted bit NOT NULL DEFAULT 0,
    [Timestamp] datetime NOT NULL DEFAULT GETDATE(),
    CONSTRAINT PK_Department PRIMARY KEY (ID)
);

/*
TABLE Users
+ DepartID: ID của bảng Department
+ IsDeleted : 0 : Chưa xoá / 1 : Xoá (Mặc định là 0)
*/
CREATE TABLE Users (
    ID int IDENTITY(1,1) NOT NULL,
    Username VARCHAR(8) unique NOT NULL,
    Fullname NVARCHAR(32) NOT NULL,
    DepartID int NOT NULL,
	Email VARCHAR(128) unique,	
	[Password] VARCHAR(128)	NOT NULL,
	IsDeleted BIT NOT NULL DEFAULT 0,
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),
    CONSTRAINT PK_Users PRIMARY KEY (ID),
);


/*
TABLE Events
+ EventType: 1:Team building, 2: Travel, 3: Training
+ Creator : UserID
+ [Status]: 1: Opening / 2: Closed
+ OptionType: 1: Chọn 1 / 2 : Chọn nhiều
+ IsDeleted : 0 : Chưa xoá / 1 : Xoá (Mặc định là 0)

+ So nguoi da tham gia:?
+ So nguoi khong tham gia:?
*/

CREATE TABLE Events (
    ID int IDENTITY(1,1) NOT NULL,
    EventName NVARCHAR(250) NOT NULL,
    EventType int NOT NULL,			/*1: Team building, 2: Travel, 3: Training*/
    EventImage VARCHAR(150),		
	Creator int	NOT NULL,			/*Người tạo event*/
	[Description] NVARCHAR(500),	/*Mô tả event*/
	[Status] int NOT NULL,          /*1: Opening / 2: Closed*/
	OptionType int	NOT NULL,		/*1: Chọn 1 / 2 : Chọn nhiều*/
	Place NVARCHAR(50),				/*Địa điểm tổ chức*/
	StartDate DATE,				/*Ngày bắt đầu*/
	StartHour TIME,				/*Giờ bắt đầu*/
	EndDate DATE,				/*Ngày kết thúc*/
	EndHour TIME,				/*Giờ kết thúc*/
	IsDeleted bit NOT NULL DEFAULT 0,		/*0 : Chưa xoá / 1 : Xoá (Mặc định là 0)*/
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),
	CONSTRAINT PK_Events PRIMARY KEY (ID),
);




 
/*
TABLE VoteOption
+ StartDate : Ngày tổ chức event
+ Place : Địa điểm tổ chức
+ IsDeleted :  0 : Chưa xoá / 1 : Xoá (Mặc định là 0)
*/

CREATE TABLE VoteOption (
    ID int IDENTITY(1,1) NOT NULL,
    EventID int	NOT NULL,
    StartDate DATETIME NOT NULL,		/*Ngày tổ chức event*/
	Place NVARCHAR(128) NOT NULL,		/*Địa điểm tổ chức*/
	OptionImage VARCHAR(150),			
	IsDeleted bit NOT NULL DEFAULT 0,	/*0 : Chưa xoá / 1 : Xoá (Mặc định là 0)*/	
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),
	CONSTRAINT PK_VoteOption PRIMARY KEY (ID),
);

 	

  




/*
TABLE RegistEvents
+ Username: Người vote
+ VoteOptionID: Id của bảng VoteOption 
+ AttachedPersonAdult: Số lượng người lớn đi cùng
+ AttachedPersonChild: Số lượng trẻ em đi cùng 
+ IsJoined: 0: không join, 1: có join
+ VoteOptionID: ID bảng VoteOption
+ Voted: 0 : Không chọn / 1 : Chọn <thằng này chỉ có khi IsJoined = 1 
và VoteOptionID có giá trị>
+ khi mà join thì mới chọn option vote
+ IsDeleted 

*/

CREATE TABLE RegistEvents (
    ID int IDENTITY(1,1) NOT NULL,
	Username VARCHAR(8) NOT NULL,	/*Username user vote*/
    EventID int	NOT NULL,			/*Event ID*/
	IsJoined BIT NOT NULL,			/*0: không join, 1: có join*/
	VoteOptionID int,		 
	Voted bit NOT NULL DEFAULT 0,				/*0 : Không chọn / 1 : Chọn*/
	AttachedPersonAdult int,
	AttachedPersonChild int,
	IsDeleted bit NOT NULL DEFAULT 0,		
	[Timestamp] datetime NOT NULL DEFAULT GETDATE(),	
	CONSTRAINT PK_RegistEvents PRIMARY KEY (ID),

);

SELECT * FROM Users;
SELECT * FROM Department;
SELECT * FROM [Events];
SELECT * FROM RegistEvents;
DROP TABLE RegistEvents


 	


 SELECT U.ID  FROM Department AS D 
	INNER JOIN Users AS U
		ON D.ID = U.DepartID
	INNER JOIN RegistEvents AS R
		ON R.Username = U.Username
	INNER JOIN [Events] AS E
		ON E.ID = R.EventID
	INNER JOIN;
	
SELECT *,(SELECT COUNT(*) FROM [Events] AS E WHERE E.EventType=2) FROM Users	

SELECT *,
(SELECT E.ID ,COUNT(E.ID) AS Joined FROM RegistEvents  AS R WHERE
 R.IsJoined=1 AND R.EventID=E.ID
 GROUP BY E.ID)

FROM [Events] AS E	

select * from [Events]
 SELECT E.ID ,COUNT(E.ID) AS Joined FROM RegistEvents  AS R,[Events] AS E WHERE
 R.IsJoined=1 AND R.EventID=E.ID
 GROUP BY E.ID

 SELECT E.ID ,COUNT(E.ID) AS 'Not Joined' FROM RegistEvents  AS R,[Events] AS E WHERE
 R.IsJoined=0 AND R.EventID=E.ID
 GROUP BY E.ID


(SELECT COUNT(*) FROM RegistEvents AS R WHERE R.IsJoined=0 AND R.EventID=E.ID) AS 'NotJoined'






 -- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE  GetAllEventName

AS
BEGIN
 SELECT E.ID, E.EventName,E.[Description],E.Place,E.[Status],E.EventType AS EventTypeID,
 E.StartDate,E.StartHour,E.EndDate,E.EndHour,
convert(varchar, E.StartDate) + ' ' + convert(varchar(5),E.StartHour)+' ~ '+convert(varchar, E.EndDate) + ' ' + convert(varchar(5),E.EndHour) AS [Time],
(SELECT COUNT(*) FROM RegistEvents AS R 
	WHERE R.EventID=E.ID AND R.IsJoined=1) AS Joined, 
(SELECT COUNT(*) FROM RegistEvents AS R 
	WHERE R.EventID=E.ID AND R.IsJoined=0) AS 'NotJoined',
(SELECT U.Username FROM Users AS U WHERE U.ID = E.Creator) AS Creator,
 (SELECT D.DepartName FROM Users AS U,Department as D WHERE U.ID = E.Creator AND U.DepartID=D.ID) AS DepartName,
  (SELECT D.ID AS DepartID FROM Users AS U,Department as D WHERE U.ID = E.Creator AND U.DepartID=D.ID) AS DepartName,
 (SELECT M.EventType FROM MstEventType AS M WHERE M.ID=E.EventType) AS EventType
		FROM Department AS D 
	INNER JOIN Users AS U
		ON D.ID = U.DepartID
	INNER JOIN RegistEvents AS R
		ON R.Username = U.Username
	INNER JOIN [Events] AS E
		ON E.ID = R.EventID
		GROUP BY  E.EventName,E.[Description],E.ID,
		E.Creator,Place,E.[Status], E.StartDate,E.EventType,
		E.StartHour,E.EndDate,E.EndHour,E.ID;
END
GO


CREATE VIEW Eventlist AS

  SELECT E.ID, E.EventName,E.[Description],E.Place,E.[Status],E.EventType AS EventTypeID,
 E.StartDate,E.StartHour,E.EndDate,E.EndHour,
convert(varchar, E.StartDate) + ' ' + convert(varchar(5),E.StartHour)+' ~ '+convert(varchar, E.EndDate) + ' ' + convert(varchar(5),E.EndHour) AS [Time],
ISNULL(
	(SELECT DISTINCT COUNT(R.Username) FROM RegistEvents AS R inner join Users as U 
	on R.Username=U.Username WHERE R.EventID=E.ID AND R.IsJoined=1 group by R.Username, R.EventID), 0) AS Joined,
ISNULL(
	(SELECT DISTINCT COUNT(R.Username) FROM RegistEvents AS R inner join Users as U 
	on R.Username=U.Username where R.EventID=E.ID AND R.IsJoined=0 group by R.Username, R.EventID), 0) AS NotJoined,
(SELECT U.Username FROM Users AS U WHERE U.ID = E.Creator) AS Creator,
 (SELECT D.DepartName FROM Users AS U,Department as D WHERE U.ID = E.Creator AND U.DepartID=D.ID) AS DepartName,
  (SELECT D.ID AS DepartID FROM Users AS U,Department as D WHERE U.ID = E.Creator AND U.DepartID=D.ID) AS DepartID,
 (SELECT M.EventType FROM MstEventType AS M WHERE M.ID=E.EventType) AS EventType
		FROM Department AS D 
	INNER JOIN Users AS U
		ON D.ID = U.DepartID
	INNER JOIN RegistEvents AS R
		ON R.Username = U.Username
	INNER JOIN [Events] AS E
		ON E.ID = R.EventID
		GROUP BY  E.EventName,E.[Description],E.ID,
		E.Creator,Place,E.[Status], E.StartDate,E.EventType,
		E.StartHour,E.EndDate,E.EndHour,E.ID;



 


 SELECT * FROM Eventlist 

 


