use ua_database;

DROP TABLE IF EXISTS Member_Skill;
DROP TABLE IF EXISTS Member_Info;
DROP TABLE IF EXISTS Skill;
DROP TABLE IF EXISTS Skills;



CREATE TABLE Member_Info(
    Member_ID INT(4) NOT NULL AUTO_INCREMENT, 
    First_Name NCHAR(20),
    Last_Name NCHAR(20),
    Email NCHAR(50),
    Phone NCHAR(15) NOT NULL,
    Biography NCHAR(200),
    Profile_Pic NCHAR(250),
    Location NCHAR(250),
    Firebase_ID NCHAR(30),
    PRIMARY KEY (Member_ID)
);

#INSERT INTO Staff (Member_ID, First_Name, Last_Name, Email, Phone, Biography, Profile_Pic, Location, Firebase_ID) VALUES ();

CREATE TABLE Skill(
    skill_id INT(4) NOT NULL AUTO_INCREMENT,  
    title NCHAR(20) NOT NULL,
    PRIMARY KEY (Skill_ID)
);

INSERT INTO Skill (title) VALUES 
    ('basketball'),
    ('soccer'),
    ('tennis'),
    ('swimming'),
    ('golf'),
    ('running'),
    ('biking'),
    ('yoga'),
    ('weightlifting'),
    ('dance'),
    ('boxing'),
    ('football'),
    ('baseball'),
    ('volleyball'),
    ('track_running'),
    ('track_throwing'),
    ('ultimate_frisbee'),
    ('disc_golf'),
    ('wrestling'),
    ('spikeball'),
    ('pickleball');

CREATE TABLE Member_Skill(
    Member_ID INT(4) NOT NULL,
    Skill_ID INT(4) NOT NULL,
    PRIMARY KEY (Member_ID, Skill_ID),
    CONSTRAINT fk_M_Member FOREIGN KEY (Member_ID) REFERENCES Member_Info(Member_ID) ON DELETE CASCADE,
    CONSTRAINT fk_M_Skill FOREIGN KEY (Skill_ID) REFERENCES Skill(Skill_ID) ON DELETE CASCADE
);

#INSERT INTO Staff (Member_ID, Skill_ID) VALUES ();