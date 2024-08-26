
INSERT INTO surveys (name, description) VALUES
('Family Background Survey', 'Survey aimed at gathering information on family background.'),
('General Health Survey', 'Survey to assess the overall health of respondents.'),
('Legal Survey', 'Survey on legal matters and background.');


INSERT INTO categoriesCatalog (name) VALUES
('Family'),
('Health'),
('Legal'),
('Background');

INSERT INTO chapter (surveyId, chapterNumber, chapterTitle) VALUES
(1, '1', 'Personal Information'),
(1, '2', 'Family Background'),
(2, '1', 'Current Health Status'),
(2, '2', 'Medical History'),
(3, '1', 'Legal Documentation'),
(3, '2', 'Legal Background');


INSERT INTO questions (chapterId, questionNumber, responseType, questionText, commentQuestion) VALUES
(1, '1.1', 'Text', 'What is your full name?', 'Basic personal information.'),
(1, '1.2', 'Text', 'What is your age?', 'Basic demographic information.'),
(2, '2.1', 'Multiple Choice', 'Do you have any family history of hereditary diseases?', 'This question seeks information about family health.'),
(3, '1.1', 'Multiple Choice', 'Do you currently have any health issues?', 'Assessing the current health status of the respondent.'),
(3, '1.2', 'Text', 'How often do you visit the doctor each year?', 'Measuring the frequency of medical visits.'),
(5, '1.1', 'Yes/No', 'Do you have any legal documentation related to assets?', 'Questions regarding possession of legal documents.'),
(5, '1.2', 'Text', 'What is your current legal situation?', 'Understanding if the respondent has any ongoing legal processes.');


INSERT INTO responseOptions (optionValue, categoryCatalogId, questionId, typeComponentHtml, optionText) VALUES
(1, 1, 3, 'RadioButton', 'Yes'),
(2, 1, 3, 'RadioButton', 'No');


INSERT INTO responseOptions (optionValue, categoryCatalogId, questionId, typeComponentHtml, optionText) VALUES
(1, 2, 4, 'RadioButton', 'Yes'),
(2, 2, 4, 'RadioButton', 'No');


INSERT INTO subResponseOptions (subResponseNumber, responseOptionsId, componentHtml, subResponseText) VALUES
(1, 1, 'TextBox', 'Please specify the hereditary disease'),
(2, 2, 'TextBox', 'If No, explain why you think there is no history');


INSERT INTO subResponseOptions (subResponseNumber, responseOptionsId, componentHtml, subResponseText) VALUES
(1, 3, 'TextBox', 'Please describe the health issue'),
(2, 4, 'TextBox', 'If No, specify your current health status');


INSERT INTO responseQuestion (responseId, subResponsesId, responseText) VALUES
(1, 1, 'Diabetes'),
(1, 1, 'Hypertension'),
(2, 2, 'No family history'),
(3, 1, 'Asthma'),
(3, 1, 'Chronic Pain'),
(4, 2, 'No health issues');

INSERT INTO roles(name) VALUES 
("common user"),
("admin");

INSERT INTO users(enabled,username,password) VALUES
(1,user,123),
(1,jhoan,123);

INSERT INTO usersRoles(roleId,userId) VALUES 
(1,1),
(2,2);