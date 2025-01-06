-- Create a new database
CREATE DATABASE users_management;

-- Create the 'users' table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,  -- Store hashed passwords, not plain text
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the 'teams' table
CREATE TABLE teams (
    team_id SERIAL PRIMARY KEY,
    team_name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create a junction table for the many-to-many relationship between users and teams
CREATE TABLE user_teams (
    user_team_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    team_id INT REFERENCES teams(team_id) ON DELETE CASCADE,
    role VARCHAR(50),  -- Role of the user in the team (e.g., 'member', 'admin')
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_id, team_id)  -- Prevent duplicate entries for the same user-team combination
);

-- Optional: Add indexes for better performance on the join table
CREATE INDEX idx_user_id ON user_teams(user_id);
CREATE INDEX idx_team_id ON user_teams(team_id);

-- Insert a new user
INSERT INTO users (username, email, password_hash, first_name, last_name)
VALUES ('john_doe', 'john@example.com', 'hashed_password_here', 'John', 'Doe');

-- Insert a new team
INSERT INTO teams (team_name, description)
VALUES ('Team Alpha', 'A description of Team Alpha');

-- Add a user to a team
INSERT INTO user_teams (user_id, team_id, role)
VALUES (1, 1, 'admin');

--- Update database: Add column phone_number
ALTER TABLE user
ADD COLUMN phone_number VARCHAR(15) UNIQUE;