CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    prompt VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS quiz_option (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_id BIGINT NOT NULL,
    label CHAR(1) NOT NULL,
    option_text VARCHAR(500) NOT NULL,
    correct BOOLEAN NOT NULL,
    CONSTRAINT fk_option_question FOREIGN KEY (question_id) REFERENCES question (id)
);

CREATE TABLE IF NOT EXISTS attempt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    score INT NOT NULL,
    CONSTRAINT fk_attempt_user FOREIGN KEY (user_id) REFERENCES app_user (id)
);

CREATE TABLE IF NOT EXISTS attempt_question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    attempt_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    display_order INT NOT NULL,
    CONSTRAINT fk_aq_attempt FOREIGN KEY (attempt_id) REFERENCES attempt (id),
    CONSTRAINT fk_aq_question FOREIGN KEY (question_id) REFERENCES question (id)
);

CREATE TABLE IF NOT EXISTS attempt_answer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    attempt_question_id BIGINT NOT NULL UNIQUE,
    selected_option_id BIGINT NOT NULL,
    CONSTRAINT fk_aa_aq FOREIGN KEY (attempt_question_id) REFERENCES attempt_question (id),
    CONSTRAINT fk_aa_option FOREIGN KEY (selected_option_id) REFERENCES quiz_option (id)
);
