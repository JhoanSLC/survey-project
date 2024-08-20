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

CREATE TABLE categoriesCatalog(
    id INT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP(6) DEFAULT NOW(),
    updatedAt TIMESTAMP(6) DEFAULT NOW(),
    name VARCHAR(255)
);

CREATE TABLE chapter(
    id INT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP(6) DEFAULT NOW(),
    surveyId INT,
    updatedAt TIMESTAMP(6) DEFAULT NOW(),
    chapterNumber VARCHAR(50),
    chapterTitle VARCHAR(50),
    CONSTRAINT Fk_ChapterSurvey FOREIGN KEY (survey_id) REFERENCES surveys(id)
)

CREATE TABLE questions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    chapterId INT,
    createdAt TIMESTAMP(6) DEFAULT NOW(),
    updatedAt TIMESTAMP(6) DEFAULT NOW(),
    questionNumber VARCHAR(10),
    responseType VARCHAR(20),
    commentQuestion TEXT,
    questionText TEXT,
    CONSTRAINT Fk_questionsChapter FOREIGN KEY (chapter_id) REFERENCES chapter(id)
);

CREATE TABLE responseOptions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    optionValue INT,
    categoryCatalogId INT,
    createdAt TIMESTAMP(6) DEFAULT NOW(),
    parentResponseId INT,
    questionId INT,
    updatedAt TIMESTAMP(6) DEFAULT NOW(),
    typeComponentHtml VARCHAR(30),
    commentResponse TEXT,
    optionText TEXT,
    CONSTRAINT Fk_responseCategory FOREIGN KsEY (categoryCatalog_id) REFERENCES categories_catalog(id),
    CONSTRAINT Fk_responseParent FOREIGN KEY (parentResponse_id) REFERENCES response_options(id),
    CONSTRAINT Fk_responseQuestion FOREIGN KEY (question_id) REFERENCES questions(id)
);

CREATE TABLE subResponseOptions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    subResponseNumber INT,
    createdAt TIMESTAMP(6) DEFAULT NOW(),
    responseOptionsId INT,
    updatedAt TIMESTAMP(6) DEFAULT NOW(),
    componentHtml VARCHAR(255),
    subResponseText VARCHAR(255),
    CONSTRAINT Fk_subResponseParent FOREIGN KEY (responseOptions_id) REFERENCES response_options(id)
);

CREATE TABLE response_question(
    id INT AUTO_INCREMENT PRIMARY KEY,
    responseId INT,
    subResponsesId INT,
    responseText VARCHAR(80),
    CONSTRAINT Fk_RQresponse FOREIGN KEY (response_id) REFERENCES response_options(id),
    CONSTRAINT Fk_RQsubResponse FOREIGN KEY (subResponses_id) REFERENCES subResponse_options(id)
);

CREATE TABLE roles(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
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
    CONSTRAINT Fk_URrole FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT Fk_URuser FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT Pk_UserRoles PRIMARY KEY (role_id,user_id)
);