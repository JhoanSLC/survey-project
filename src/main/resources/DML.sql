

INSERT INTO surveys (name, description) VALUES
('Customer Satisfaction Survey', 'Survey to gauge customer satisfaction with products and services.'),
('Healthcare Services Survey', 'Survey to assess the quality of healthcare services.'),
('Housing Preferences Survey', 'Survey to collect data on housing preferences and requirements.'),
('Career Development Survey', 'Survey focused on career growth and development opportunities.'),
('Internet Usage Survey', 'Survey to understand internet usage patterns and habits.'),
('Personal Finance Survey', 'Survey on personal finance management and budgeting.'),
('Local Government Services Survey', 'Survey to evaluate satisfaction with local government services.'),
('Family Lifestyle Survey', 'Survey to gather information about family lifestyles and activities.'),
('Educational Attainment Survey', 'Survey to assess educational achievements and qualifications.'),
('Technology Adoption Survey', 'Survey to understand the adoption of new technologies.');



-- Insert into categoriesCatalog
INSERT INTO categoriesCatalog (name) VALUES
('Customer Service'),
('Healthcare'),
('Housing'),
('Career'),
('Internet'),
('Finance'),
('Government'),
('Lifestyle'),
('Education'),
('Technology');


-- Insert into chapter
INSERT INTO chapter (surveyId, chapterNumber, chapterTitle) VALUES
-- Customer Satisfaction Survey
(1, '1', 'Overall Experience'),
(1, '2', 'Product Feedback'),

-- Healthcare Services Survey
(2, '1', 'Service Quality'),
(2, '2', 'Facility Conditions'),

-- Housing Preferences Survey
(3, '1', 'Housing Preferences'),
(3, '2', 'Desired Features'),

-- Career Development Survey
(4, '1', 'Career Goals'),
(4, '2', 'Development Opportunities'),

-- Internet Usage Survey
(5, '1', 'Usage Habits'),
(5, '2', 'Online Activities'),

-- Personal Finance Survey
(6, '1', 'Budget Management'),
(6, '2', 'Financial Goals'),

-- Local Government Services Survey
(7, '1', 'Service Satisfaction'),
(7, '2', 'Improvement Suggestions'),

-- Family Lifestyle Survey
(8, '1', 'Family Activities'),
(8, '2', 'Lifestyle Choices'),

-- Educational Attainment Survey
(9, '1', 'Highest Degree'),
(9, '2', 'Additional Qualifications'),

-- Technology Adoption Survey
(10, '1', 'Technology Usage'),
(10, '2', 'Interest in Innovations');



-- Insert into questions
INSERT INTO questions (chapterId, questionNumber, responseType, questionText, commentQuestion) VALUES
-- Overall Experience (Customer Satisfaction Survey)
(1, '1.1', 'Text', 'How satisfied are you with our products?', 'Evaluating overall product satisfaction.'),
(1, '1.2', 'Text', 'What improvements would you suggest?', 'Gathering suggestions for product improvement.'),
-- Product Feedback (Customer Satisfaction Survey)
(2, '1.1', 'Multiple Choice', 'How would you rate the quality of our products?', 'Assessing product quality.'),
(2, '1.2', 'Text', 'Please provide specific feedback on the products.', 'Detailed product feedback.'),

-- Service Quality (Healthcare Services Survey)
(3, '1.1', 'Multiple Choice', 'How would you rate the quality of our healthcare services?', 'Assessing healthcare service quality.'),
(3, '1.2', 'Text', 'What specific issues have you encountered?', 'Identifying issues with healthcare services.'),
-- Facility Conditions (Healthcare Services Survey)
(4, '1.1', 'Multiple Choice', 'How would you rate the cleanliness of our facilities?', 'Assessing facility cleanliness.'),
(4, '1.2', 'Text', 'What improvements would you suggest for our facilities?', 'Gathering suggestions for facility improvements.'),

-- Housing Preferences (Housing Preferences Survey)
(5, '1.1', 'Multiple Choice', 'What type of housing are you interested in?', 'Understanding housing interests.'),
(5, '1.2', 'Text', 'What features are most important to you in a house?', 'Gathering preferences for housing features.'),
-- Desired Features (Housing Preferences Survey)
(6, '1.1', 'Text', 'Please specify the features you are looking for in a house.', 'Detailed description of desired features.'),
(6, '1.2', 'Text', 'Are there any additional features you would like to have?', 'Additional feature preferences.'),

-- Career Goals (Career Development Survey)
(7, '1.1', 'Text', 'What are your long-term career goals?', 'Understanding career aspirations.'),
(7, '1.2', 'Text', 'What motivates you to achieve these goals?', 'Motivations for career goals.'),
-- Development Opportunities (Career Development Survey)
(8, '1.1', 'Text', 'What kind of professional development opportunities are you seeking?', 'Identifying desired development opportunities.'),
(8, '1.2', 'Text', 'Have you attended any career development programs?', 'Gathering information on past development programs.'),

-- Usage Habits (Internet Usage Survey)
(9, '1.1', 'Multiple Choice', 'How often do you use the internet?', 'Measuring internet usage frequency.'),
(9, '1.2', 'Text', 'What are your primary online activities?', 'Understanding main internet activities.'),
-- Online Activities (Internet Usage Survey)
(10, '1.1', 'Checkbox', 'Which of the following online activities do you engage in regularly?', 'Identifying regular online activities.'),
(10, '1.2', 'Text', 'How has your internet usage changed over the past year?', 'Understanding changes in internet usage.'),

-- Budget Management (Personal Finance Survey)
(11, '1.1', 'Text', 'How do you manage your monthly budget?', 'Collecting data on budgeting methods.'),
(11, '1.2', 'Text', 'What tools or methods do you use for budgeting?', 'Details on budgeting tools and methods.'),
-- Financial Goals (Personal Finance Survey)
(12, '1.1', 'Text', 'What are your financial goals for the next 5 years?', 'Identifying long-term financial goals.'),
(12, '1.2', 'Text', 'What steps are you taking to achieve these goals?', 'Understanding steps taken towards financial goals.'),

-- Service Satisfaction (Local Government Services Survey)
(13, '1.1', 'Multiple Choice', 'How satisfied are you with local government services?', 'Assessing satisfaction with local services.'),
(13, '1.2', 'Text', 'What specific services are you most concerned about?', 'Identifying concerns with specific services.'),
-- Improvement Suggestions (Local Government Services Survey)
(14, '1.1', 'Text', 'What improvements would you like to see in local government services?', 'Gathering suggestions for service improvements.'),
(14, '1.2', 'Text', 'How would you prioritize these improvements?', 'Prioritizing suggested improvements.'),

-- Family Activities (Family Lifestyle Survey)
(15, '1.1', 'Text', 'What family activities do you enjoy?', 'Understanding family activity preferences.'),
(15, '1.2', 'Text', 'How often do you engage in these activities?', 'Frequency of family activities.'),
-- Lifestyle Choices (Family Lifestyle Survey)
(16, '1.1', 'Text', 'What are your family’s lifestyle choices?', 'Gathering information on lifestyle choices.'),
(16, '1.2', 'Text', 'How do these choices affect your daily life?', 'Impact of lifestyle choices on daily life.'),

-- Highest Degree (Educational Attainment Survey)
(17, '1.1', 'Text', 'What is your highest level of education?', 'Collecting information on educational attainment.'),
(17, '1.2', 'Text', 'Where did you obtain this degree?', 'Institution where the highest degree was obtained.'),
-- Additional Qualifications (Educational Attainment Survey)
(18, '1.1', 'Text', 'Do you have any additional qualifications or certifications?', 'Identifying additional qualifications.'),
(18, '1.2', 'Text', 'What are these qualifications?', 'Details of additional qualifications.'),

-- Technology Usage (Technology Adoption Survey)
(19, '1', 'Multiple Choice', 'Which technologies do you use daily?', 'Understanding daily technology use.'),
(19, '2', 'Text', 'How do you use these technologies in your daily life?', 'Application of technologies in daily life.'),
-- Interest in Innovations (Technology Adoption Survey)
(20, '1', 'Text', 'What new technologies are you interested in?', 'Gathering interest in emerging technologies.'),
(20, '2', 'Text', 'How do you keep up with new technology trends?', 'Methods for staying informed about new technologies.');



-- Insert into responseOptions
INSERT INTO responseOptions (optionValue, categoryCatalogId, questionId, typeComponentHtml, optionText) VALUES
-- Customer Satisfaction Survey
(1, 1, 1, 'RadioButton', 'Very Satisfied'),
(2, 1, 1, 'RadioButton', 'Satisfied'),
(3, 1, 1, 'RadioButton', 'Neutral'),
(4, 1, 1, 'RadioButton', 'Dissatisfied'),
(5, 1, 1, 'RadioButton', 'Very Dissatisfied'),

-- Healthcare Services Survey
(1, 2, 1, 'RadioButton', 'Excellent'),
(2, 2, 1, 'RadioButton', 'Good'),
(3, 2, 1, 'RadioButton', 'Average'),
(4, 2, 1, 'RadioButton', 'Poor'),
(5, 2, 1, 'RadioButton', 'Very Poor'),

-- Housing Preferences Survey
(1, 3, 1, 'Checkbox', 'Apartment'),
(2, 3, 1, 'Checkbox', 'House'),
(3, 3, 1, 'Checkbox', 'Condo'),
(4, 3, 1, 'Checkbox', 'Townhouse'),
(5, 3, 1, 'Checkbox', 'Other'),

-- Career Development Survey
(1, 4, 1, 'Text', 'Enter your career goals'),
(2, 4, 2, 'Text', 'Describe your development opportunities'),

-- Internet Usage Survey
(1, 5, 1, 'RadioButton', 'Daily'),
(2, 5, 1, 'RadioButton', 'Weekly'),
(3, 5, 1, 'RadioButton', 'Monthly'),
(4, 5, 1, 'RadioButton', 'Rarely'),

-- Personal Finance Survey
(1, 6, 1, 'Text', 'Describe your budget management strategies'),
(2, 6, 2, 'Text', 'List your financial goals'),

-- Local Government Services Survey
(1, 7, 1, 'RadioButton', 'Very Satisfied'),
(2, 7, 1, 'RadioButton', 'Satisfied'),
(3, 7, 1, 'RadioButton', 'Neutral'),
(4, 7, 1, 'RadioButton', 'Dissatisfied'),
(5, 7, 1, 'RadioButton', 'Very Dissatisfied'),

-- Family Lifestyle Survey
(1, 8, 1, 'Text', 'Enter your family activities'),
(2, 8, 2, 'Text', 'Describe your lifestyle choices'),

-- Educational Attainment Survey
(1, 9, 1, 'Text', 'Enter your highest degree'),
(2, 9, 2, 'Text', 'List additional qualifications'),

-- Technology Adoption Survey
(1, 10, 1, 'Checkbox', 'Smartphone'),
(2, 10, 1, 'Checkbox', 'Laptop'),
(3, 10, 1, 'Checkbox', 'Tablet'),
(4, 10, 1, 'Checkbox', 'Smartwatch'),
(5, 10, 1, 'Checkbox', 'Other');


INSERT INTO subResponseOptions (subResponseNumber, responseOptionsId, componentHtml, subResponseText) VALUES
(1, 1, 'TextBox', 'Please specify the hereditary disease'),
(2, 2, 'TextBox', 'If No, explain why you think there is no history'),
(1, 3, 'TextBox', 'Please specify your specialization.'),
(2, 4, 'TextBox', 'Please specify your field of study.'),
(1, 5, 'TextBox', 'List your certifications.'),
(2, 6, 'TextBox', 'If No, explain why you have not pursued certifications.'),
(1, 7, 'TextBox', 'Specify the percentage you save.'),
(2, 8, 'TextBox', 'Explain the reasons you do not save.');

INSERT INTO responseQuestion (responseId, subResponsesId, responseText) VALUES
(1, 1, 'Diabetes'),
(2, 1, 'Hypertension'),
(3, 2, 'No family history'),
(4, 1, 'Asthma'),
(5, 1, 'Chronic Pain'),
(6, 2, 'No health issues'),
(7, 1, 'Monthly'),
(8, 2, 'Yearly'),
(9, 1, 'High School'),
(10, 2, "Bachelor's Degree"),
(11, 3, "Master's Degree"),
(12, 1, 'Project Management'),
(13, 2, 'Software Engineering'),
(14, 1, 'Very Satisfied'),
(15, 2, 'Neutral'),
(16, 1, '8'),
(17, 2, '5'),
(18, 1, "Master's Degree"),
(19, 2, "Bachelor's Degree"),
(20, 1, '10% savings monthly'),
(21, 2, 'Lack of sufficient income');


INSERT INTO roles(name) VALUES 
("common user"),
("admin");

INSERT INTO users(enabled,username,password) VALUES
(1,"user",123),
(1,"admin",123);

INSERT INTO usersRoles(roleId,userId) VALUES 
(1,1),
(2,2);