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

CREATE TABLE categories_catalog(
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    name VARCHAR(255)
);

CREATE TABLE chapter(
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP(6),
    survey_id INT,
    updated_at TIMESTAMP(6),
    chapter_number VARCHAR(50),
    chapter_title VARCHAR(50),
    CONSTRAINT Fk_ChapterSurvey FOREIGN KEY (survey_id) REFERENCES surveys(id)
)

CREATE TABLE questions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    chapter_id INT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    question_number VARCHAR(10),
    response_type VARCHAR(20),
    comment_question TEXT,
    question_text TEXT,
    CONSTRAINT Fk_questionsChapter FOREIGN KEY (chapter_id) REFERENCES chapter(id)
);

CREATE TABLE response_options(
    id INT AUTO_INCREMENT PRIMARY KEY,
    option_value INT,
    categoryCatalog_id INT,
    created_at TIMESTAMP(6),
    parentResponse_id INT,
    question_id INT,
    updated_at TIMESTAMP(6),
    typeComponentHtml VARCHAR(30),
    comment_response TEXT,
    option_text TEXT,
    CONSTRAINT Fk_responseCategory FOREIGN KEY (categoryCatalog_id) REFERENCES categories_catalog(id),
    CONSTRAINT Fk_responseParent FOREIGN KEY (parentResponse_id) REFERENCES response_options(id),
    CONSTRAINT Fk_responseQuestion FOREIGN KEY (question_id) REFERENCES questions(id)
);

CREATE TABLE subResponse_options(
    id INT AUTO_INCREMENT PRIMARY KEY,
    subResponse_number INT,
    created_at TIMESTAMP(6),
    responseOptions_id INT,
    updated_at TIMESTAMP(6),
    component_html VARCHAR(255),
    subResponse_text VARCHAR(255),
    CONSTRAINT Fk_subResponseParent FOREIGN KEY (responseOptions_id) REFERENCES response_options(id)
);

CREATE TABLE response_question(
    id INT AUTO_INCREMENT PRIMARY KEY,
    response_id INT,
    subResponses_id INT,
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

CREATE TABLE users_roles(
    role_id INT,
    user_id INT,
    CONSTRAINT Fk_URrole FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT Fk_URuser FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT Pk_UserRoles PRIMARY KEY (role_id,user_id)
);