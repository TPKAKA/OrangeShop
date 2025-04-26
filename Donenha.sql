--CREATE DATABASE DONENHA 

--Use DONENHA

CREATE TABLE userrole (
    roleid INT NOT NULL IDENTITY(1,1),
    [name] VARCHAR(150) NOT NULL DEFAULT 'null',
    PRIMARY KEY (roleid)
);
CREATE TABLE [user] (
    userid INT NOT NULL IDENTITY(1,1),
    roleId INT NOT NULL,
    email VARCHAR(50) NOT NULL DEFAULT 'null',
    facebook VARCHAR(50) NOT NULL DEFAULT 'null' ,
    [password] VARCHAR(150) NOT NULL DEFAULT 'null',
    phone VARCHAR(30)  NULL ,
    fullname NVARCHAR(50) NOT NULL DEFAULT 'null',
    [image] NVARCHAR(500) NULL,
    dob DATE NULL,
    [address] NVARCHAR(500) NOT NULL DEFAULT 'null',
    addressId INT NOT NULL DEFAULT 'null',
    gmailid VARCHAR(80) NOT NULL DEFAULT 'null',
    facebookid VARCHAR(80) NOT NULL DEFAULT 'null',
    [status] TINYINT  NULL ,
    codeverify VARCHAR(15) NOT NULL DEFAULT 'null',
    bio NVARCHAR(MAX) NOT NULL DEFAULT 'null',
    PRIMARY KEY (userid),
    FOREIGN KEY (roleId) REFERENCES userrole (roleid)
);

CREATE TABLE NewsCategory (
    NGroupId INT NOT NULL IDENTITY(1,1),
    [name] NVARCHAR(300) NOT NULL,
    PRIMARY KEY (NGroupId)
);

CREATE TABLE News (
    ID INT NOT NULL IDENTITY(1,1),
    Userid INT NOT NULL,
    content NVARCHAR(MAX) NOT NULL,
	newsname NVARCHAR(200),
    [image] NVARCHAR(500) NOT NULL,
    groupId INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (Userid) REFERENCES [user](userid), 
    FOREIGN KEY (groupId) REFERENCES NewsCategory(NGroupId)
);

CREATE TABLE Customers (
    customerid INT NOT NULL IDENTITY(1,1),
    accountid INT NOT NULL,
    review NVARCHAR(MAX) NOT NULL,
    PRIMARY KEY (customerid),
    FOREIGN KEY (accountid) REFERENCES [user] (userid)
);


CREATE TABLE Payments (
    paymentid INT NOT NULL IDENTITY(1,1),
    customerid INT NOT NULL,
    paymentmethod VARCHAR(50) NOT NULL,
    PRIMARY KEY (paymentid),
    FOREIGN KEY (customerid) REFERENCES Customers (customerid)
);

CREATE TABLE Orders (
    orderid INT NOT NULL IDENTITY(1,1),
    customerid INT NOT NULL,
    orderdate DATE NOT NULL,
    totalamount DECIMAL(10, 2) NOT NULL,
    paymentid INT NOT NULL,
    [status] TINYINT NOT NULL,
    PRIMARY KEY (orderid),
    FOREIGN KEY (customerid) REFERENCES Customers (customerid),
    FOREIGN KEY (paymentid) REFERENCES Payments (paymentid)
);
CREATE TABLE ShipmentUnits (
    id INT NOT NULL IDENTITY(1,1),
    [Name] NVARCHAR(50) NOT NULL,
    Hotline INT NOT NULL,
    OrderID INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (OrderID) REFERENCES Orders (orderid)
);
CREATE TABLE Categories (
    categoryid INT NOT NULL IDENTITY(1,1),
    categoryname NVARCHAR(50) NOT NULL,
    PRIMARY KEY (categoryid)
);

CREATE TABLE Products (
    productid INT NOT NULL IDENTITY(1,1),
    [name] NVARCHAR(50) NOT NULL,
    [description] NVARCHAR(MAX) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    categoryid INT NOT NULL,
    PRIMARY KEY (productid),
    FOREIGN KEY (categoryid) REFERENCES Categories (categoryid)
);
CREATE TABLE imglink(
imgid INT NOT NULL IDENTITY(1,1),
imglink  NVARCHAR(50),
productid INT NOT NULL,
PRIMARY KEY (imgid),
 FOREIGN KEY (productid) REFERENCES Products( productid)
)

CREATE TABLE Orderitems (
    orderitemid INT NOT NULL IDENTITY(1,1),
    orderid INT NOT NULL,
    foodid INT NOT NULL,
    quantity INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
PRIMARY KEY (orderitemid),
    FOREIGN KEY (orderid) REFERENCES Orders (orderid),
    FOREIGN KEY (foodid) REFERENCES Products (productid)
);
CREATE TABLE ShopInfomation(
id INT NOT NULL IDENTITY(1,1),
Address   NVARCHAR(200),
Phone Varchar(500),
mail varchar (50),
[time] NVARCHAR(200),
userID INT NOT NULL ,
PRIMARY KEY (id),
 FOREIGN KEY (userID) REFERENCES [user](userid)
)