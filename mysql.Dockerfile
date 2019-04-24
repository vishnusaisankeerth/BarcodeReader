FROM mysql:5.7

COPY student_dump.sql /docker-entrypoint-initdb.d/
