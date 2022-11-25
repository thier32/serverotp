docker build -t otp_repository . -f build.Dockerfile
docker build -t otp .
docker image rm otp_repository
docker run -dp 8012:8080 otp