BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO deck (name, color, creator_id)
VALUES ('java', '1', 2);

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('1','What is a variable? Give an example', 'Variables are containers for storing data values. An example is a boolean variable named isAwake, whose value can be either true or false');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('1','What programming problem does a loop solve? Why would we use a loop?', 'A loop solves the problem of repeating tasks in code. We use a loop to perform an operation multiple times without writing the same code repeatedly. This makes our code more efficient, easier to read, and simpler to maintain.');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('1','What is an object? What is a class?','An object is an instance of a class. Its a data structure that contains data and methods. A class is a blueprint for creating objects. It defines the data and behavior that the created objects can have.');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('1','What is Encapsulation?','Encapsulation is a principle of object-oriented programming where the data (variables) and methods operating on that data are bundled together in a class. Its important because it hides the internal state of one object from others. This enhances security, prevents data corruption, and makes code easier to maintain and understand.');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('1','What is Inheritance?','Inheritance is a concept in object-oriented programming where a class can inherit properties and methods from another class. You would use inheritance to promote code reusability and create a logical, hierarchical structure for your classes. It helps in making code easier to manage, understand, and extend.');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('1','What is Polymorphism?','Polymorphism, a concept in object-oriented programming, allows one interface to represent different underlying forms. This could mean methods with the same name behaving differently based on their data types or number of parameters. Polymorphism is similar to Inheritance in that they both promote flexibility and extensibility in code. However, while Inheritance allows classes to inherit properties and methods from a parent class, Polymorphism allows methods to perform different actions based on the object they are acting upon.');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('1','What is an Interface, and why would you use one?','An Interface defines a contract for classes. It outlines specific methods or properties a class must implement. You use interfaces to enforce certain behaviors across different classes. This enables loose coupling, making your code more modular, adaptable, and testable.');

INSERT INTO deck (name, color, creator_id)
VALUES ('elementary', '2', 2);

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('2', 'How many States are in the United States?', '50');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('2', 'What is the biggest State in the United States?', 'ALASKA');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('2', 'What is the smallest State in the United States?', 'Rhode Island');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('2', 'What is the Capital of the United States?', 'Washington D.C.');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('2', 'How many permanently inhabited territories (not states) are part of the United States?', '5');

INSERT INTO deck (name, color, creator_id)
VALUES ('highschool', '3', 2);

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('3', 'How do we refer to the first ten amendments of the constitution?', 'The Bill of Rights');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('3', 'What are the three branches of Government?', 'Executive, Legislative, Judicial');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('3', 'What is the smallest State in the United States?', 'Rhode Island');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('3', 'Who was the second President of the United States?', 'John Adams');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('3', 'When was the USA founded?', 'July 4, 1776');


INSERT INTO deck (name, color, creator_id)
VALUES ('authors', '4', 2); 

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('4', 'Who wrote the book title "1984"?', 'George Orwell');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('4', 'What book opens with the line "It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness..."', 'A Tale of Two Cities');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('4', 'How many novels has author Stephen King published?', '65 Novels');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('4', 'Who is the main character in The Odyssey?', 'Odysseus');

INSERT INTO flashcard (deck_id, question, answer)
VALUES ('4', 'Who wrote the Odyssey?', 'Homer');





COMMIT TRANSACTION;

