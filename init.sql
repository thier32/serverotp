CREATE USER postgres with encrypted password 'admin';
CREATE DATABASE otp_db;
GRANT ALL PRIVILEGES ON DATABASE otp_db TO postgres;