CREATE TABLE FIDO_APP (
	ID NUMBER(30,0) NOT NULL PRIMARY KEY,
	USER_ID NUMBER(30,0),
	ORGANIZATION_ID NUMBER(30,0),
	CREATED_DATE DATE,
	DESCRIPTION VARCHAR2(4000 CHAR),
	STATUS_ID NUMBER(30,0),
  PRODUCT_TYPE_ID NUMBER(30,0),
	STATUS_CHANGE_DATE DATE,
	CLIENT_NAME VARCHAR2(1200 CHAR),
	CLIENT_OKPO VARCHAR2(600 CHAR),
	CONTACT_PHONE VARCHAR2(600 CHAR),
	CREDIT_AMOUNT INTEGER,
	COMMENTS VARCHAR2(1200 CHAR),
	QUESTIONNAIRE CLOB
);
COMMENT ON TABLE FIDO_APP IS 'ЗАЯВКИ НА КРЕДИТ';
ALTER TABLE FIDO_APP ADD CONSTRAINT PK_FIDO_APP PRIMARY KEY (ID);
ALTER TABLE FIDO_APP ADD CONSTRAINT FK_FIDO_ORGANIZATION FOREIGN KEY (ORGANIZATION_ID) REFERENCES ORGANIZATION_(ORGANIZATIONID);
ALTER TABLE FIDO_APP ADD CONSTRAINT FK_FIDO_APP_STATUS FOREIGN KEY (STATUS_ID) REFERENCES FIDO_APP_STATUS(ID);
ALTER TABLE FIDO_APP ADD CONSTRAINT FK_FIDO_APP_USER FOREIGN KEY (USER_ID) REFERENCES USER_(USERID);;
/


CREATE TABLE FIDO_APP_STATUS (
	ID NUMBER(30,0) NOT NULL PRIMARY KEY,
	CODE VARCHAR2(75 CHAR),
	NAME VARCHAR2(800 CHAR),
	DESCRIPTION VARCHAR2(1200 CHAR)
);
COMMENT ON TABLE FIDO_APP_STATUS IS 'СТАТУСЫ ЗАЯВКИ';
ALTER TABLE FIDO_APP_STATUS ADD CONSTRAINT PK_FIDO_APP_STATUS PRIMARY KEY (ID);
CREATE UNIQUE INDEX UQ_FIDO_APP_STATUS ON FIDO_APP_STATUS (CODE);
/

CREATE TABLE FIDO_PRODUCT_TYPE (
	ID NUMBER(30,0) NOT NULL PRIMARY KEY,
	CODE VARCHAR2(75 CHAR),
	NAME VARCHAR2(800 CHAR),
	DESCRIPTION VARCHAR2(1200 CHAR),
	STATUS NUMBER(1,0),
	ORGANIZATION_ID NUMBER(30,0),
	TEMPLATE_ID NUMBER(30,0)
);
COMMENT ON TABLE FIDO_PRODUCT_TYPE IS 'КРЕДИТНЫЕ ПРОДУКТЫ И ПОДВЯЗАННЫЕ ШАБЛОНЫ';
ALTER TABLE FIDO_PRODUCT_TYPE ADD CONSTRAINT PK_FIDO_PRODUCT_TYPE PRIMARY KEY (ID);
CREATE UNIQUE INDEX UQ_FIDO_PRODUCT_TYPE ON FIDO_PRODUCT_TYPE (ORGANIZATION_ID, CODE);
ALTER TABLE FIDO_PRODUCT_TYPE ADD CONSTRAINT FIDO_PROD_TYPE_ORGANIZ FOREIGN KEY (ORGANIZATION_ID) REFERENCES ORGANIZATION_(ORGANIZATIONID);
ALTER TABLE FIDO_PRODUCT_TYPE ADD CONSTRAINT FIDO_PROD_TYPE_ORGANIZ FOREIGN KEY (TEMPLATE_ID) REFERENCES DDMTEMPLATE(TEMPLATEID);
/

CREATE TABLE FIDO_DICT
(
  DICT_CODE  VARCHAR2(50),
  ID         VARCHAR2(50),
  PARENT_ID  VARCHAR2(50),
  PARENT2_ID VARCHAR2(50),
  NAME       VARCHAR2(3000),
  SORT       NUMBER(30)
);
COMMENT ON TABLE FIDO_DICT IS 'КЭШ СПРАВОЧНИКОВ';
CREATE INDEX INDX_FIDO_DICT_PRNT ON FIDO_DICT (DICT_CODE, PARENT_ID, PARENT2_ID);
CREATE UNIQUE INDEX UQ_FIDO_DICT ON FIDO_DICT (DICT_CODE, ID, PARENT_ID);

-- TABLE FOR DIVISIONS/BRANCHES
CREATE TABLE FIDO_BRANCH
(
  ID 			VARCHAR2(30),
  BRANCH_NUM	VARCHAR2(128),
  NAME			VARCHAR2(1024),
  SCHEDULE		VARCHAR2(1024),
  PHONES		VARCHAR2(128),
  FACE_LOCATION	VARCHAR2(1024),
  ZIP_CODE    	VARCHAR2(128),
  COUNTRY     	VARCHAR2(128),
  REGION      	VARCHAR2(128),
  DISTRICT    	VARCHAR2(128),
  CITYTYPE    	VARCHAR2(128),
  CITY        	VARCHAR2(128),
  STREET_TYPE 	VARCHAR2(128),
  STREET      	VARCHAR2(128),
  HOUSE       	VARCHAR2(128),
  BLOCK		  	VARCHAR2(128),
  APARTMENT   	VARCHAR2(128),
  LATITUDE		VARCHAR2(30),
  LONGITUDE		VARCHAR2(30),
  BRANCH_TYPE	VARCHAR2(30) DEFAULT 'DIVISION', -- 'DIVISION' - ОТДЕЛЕНИЕ ,'ATM' - БАНКОМАТ
  COMMENTS 		VARCHAR2(3000)
);

COMMENT ON TABLE FIDO_BRANCH IS 'ОТДЕЛЕНИЯ/БАНКОМАТЫ';
ALTER TABLE FIDO_BRANCH ADD CONSTRAINT PK_FIDO_BRANCH PRIMARY KEY (ID);
ALTER TABLE FIDO_BRANCH ADD CONSTRAINT CKC_FIDO_BRANCH_BRANCH_TYPE  CHECK (BRANCH_TYPE IN ('DIVISION','ATM'));