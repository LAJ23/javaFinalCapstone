BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, flashcard, deck CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE deck (
	deck_id SERIAL,
 	name varchar(50) NOT NULL,
	high_score numeric(100) DEFAULT '0',
	color numeric(10) NOT NULL,
	creator_id integer NOT NULL, -- Change this to integer to match the users.user_id type
 	CONSTRAINT PK_deck PRIMARY KEY (deck_id),
	CONSTRAINT FK_deck_users FOREIGN KEY (creator_id) REFERENCES users(user_id)
);

CREATE TABLE flashcard (
	card_id SERIAL,
	deck_id integer,  -- Changed from numeric to integer to match deck.deck_id type
	question varchar(500) NOT NULL,
	answer varchar(2000) NOT NULL,
	CONSTRAINT PK_flashcard PRIMARY KEY (card_id),
	CONSTRAINT FK_flashcard_deck FOREIGN KEY (deck_id) REFERENCES deck(deck_id)
);

COMMIT TRANSACTION;
