DROP DATABASE IF EXISTS surveyCampus;
CREATE DATABASE surveyCampus;
USE surveyCampus;

CREATE TABLE surveys(
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    description VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE categoriesCatalog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    name VARCHAR(255)
);

CREATE TABLE chapter(
    id INT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    surveyId INT,
	updatedAt TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    chapterNumber VARCHAR(50),
    chapterTitle VARCHAR(50),
    CONSTRAINT Fk_ChapterSurvey FOREIGN KEY (surveyId) REFERENCES surveys(id)
);

CREATE TABLE questions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    chapterId INT,
    createdAt TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    questionNumber VARCHAR(10),
    responseType VARCHAR(20),
    commentQuestion TEXT,
    questionText TEXT,
    CONSTRAINT Fk_questionsChapter FOREIGN KEY (chapterId) REFERENCES chapter(id)
);

CREATE TABLE responseOptions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    optionValue INT,
    categoryCatalogId INT,
    createdAt TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    parentResponseId INT,
    questionId INT,
    updatedAt TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    typeComponentHtml VARCHAR(30),
    commentResponse TEXT,
    optionText TEXT,
    CONSTRAINT Fk_responseCategory FOREIGN KEY (categoryCatalogId) REFERENCES categoriesCatalog(id),
    CONSTRAINT Fk_responseParent FOREIGN KEY (parentResponseId) REFERENCES responseOptions(id),
    CONSTRAINT Fk_responseQuestion FOREIGN KEY (questionId) REFERENCES questions(id)
);

CREATE TABLE subResponseOptions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    subResponseNumber INT,
    createdAt TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    responseOptionsId INT,
    updatedAt TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    componentHtml VARCHAR(255),
    subResponseText VARCHAR(255),
    CONSTRAINT Fk_subResponseParent FOREIGN KEY (responseOptionsId) REFERENCES responseOptions(id)
);

CREATE TABLE response_question(
    id INT AUTO_INCREMENT PRIMARY KEY,
    responseId INT,
    subResponsesId INT,
    responseText VARCHAR(80),
    CONSTRAINT Fk_RQresponse FOREIGN KEY (responseId) REFERENCES responseOptions(id),
    CONSTRAINT Fk_RQsubResponse FOREIGN KEY (subResponsesId) REFERENCES subResponseOptions(id)
);

CREATE TABLE roles(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    enabled BOOLEAN,
    username VARCHAR(12),
    password VARCHAR(255)
);

CREATE TABLE usersRoles(
    roleId INT,
    userId INT,
    CONSTRAINT Fk_URrole FOREIGN KEY (roleId) REFERENCES roles(id),
    CONSTRAINT Fk_URuser FOREIGN KEY (userId) REFERENCES users(id),
    CONSTRAINT Pk_UserRoles PRIMARY KEY (roleId,userId)
);