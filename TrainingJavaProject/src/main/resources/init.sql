-- Database
CREATE DATABASE strava_db;

-- Create the teams table
CREATE TABLE teams (
    team_id SERIAL PRIMARY KEY,
    team_name VARCHAR(255) NOT NULL,
    team_description TEXT
);

-- Create the athletes table
CREATE TABLE athletes (
    athlete_id SERIAL PRIMARY KEY,
    strava_id BIGINT UNIQUE NOT NULL,  -- Strava ID
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    username VARCHAR(100),
    profile_image_url VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the athlete_teams table to link athletes to teams (many-to-many)
CREATE TABLE athlete_teams (
    athlete_id INT REFERENCES athletes(athlete_id) ON DELETE CASCADE,
    team_id INT REFERENCES teams(team_id) ON DELETE CASCADE,
    PRIMARY KEY (athlete_id, team_id)
);

-- Create the activities table to store Strava activity data
CREATE TABLE activities (
    activity_id SERIAL PRIMARY KEY,
    strava_activity_id BIGINT UNIQUE NOT NULL,  -- Strava Activity ID
    athlete_id INT REFERENCES athletes(athlete_id) ON DELETE CASCADE,
    activity_type VARCHAR(50),  -- e.g., "Run", "Ride"
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    distance FLOAT,  -- Distance in meters
    duration INT,  -- Duration in seconds
    avg_speed FLOAT,  -- Average speed in meters per second
    elevation_gain FLOAT,  -- Elevation gain in meters
    calories INT,  -- Calories burned
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

