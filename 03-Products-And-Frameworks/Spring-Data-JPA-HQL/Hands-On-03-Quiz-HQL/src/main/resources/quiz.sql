INSERT INTO quiz_option (question_id, label, option_text, correct) VALUES (1, 'A', 'class', TRUE);
INSERT INTO quiz_option (question_id, label, option_text, correct) VALUES (1, 'B', 'struct', FALSE);
INSERT INTO quiz_option (question_id, label, option_text, correct) VALUES (1, 'C', 'template', FALSE);

INSERT INTO quiz_option (question_id, label, option_text, correct) VALUES (2, 'A', '@Component', FALSE);
INSERT INTO quiz_option (question_id, label, option_text, correct) VALUES (2, 'B', '@SpringBootApplication', TRUE);
INSERT INTO quiz_option (question_id, label, option_text, correct) VALUES (2, 'C', '@Entity', FALSE);

INSERT INTO quiz_option (question_id, label, option_text, correct) VALUES (3, 'A', 'Database tables only', FALSE);
INSERT INTO quiz_option (question_id, label, option_text, correct) VALUES (3, 'B', 'Entity objects and their fields', TRUE);
INSERT INTO quiz_option (question_id, label, option_text, correct) VALUES (3, 'C', 'JDBC ResultSet rows', FALSE);

INSERT INTO attempt (user_id, score) VALUES (1, 2);

INSERT INTO attempt_question (attempt_id, question_id, display_order) VALUES (1, 1, 1);
INSERT INTO attempt_question (attempt_id, question_id, display_order) VALUES (1, 2, 2);
INSERT INTO attempt_question (attempt_id, question_id, display_order) VALUES (1, 3, 3);

INSERT INTO attempt_answer (attempt_question_id, selected_option_id) VALUES (1, 1);
INSERT INTO attempt_answer (attempt_question_id, selected_option_id) VALUES (2, 5);
INSERT INTO attempt_answer (attempt_question_id, selected_option_id) VALUES (3, 8);
