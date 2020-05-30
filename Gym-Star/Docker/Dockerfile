# Derived from official mysql image (our base image)

FROM mysql:5.7

# Add a database

ENV MYSQL_DATABASE firstdatabase
ENV MYSQL_ROOT_PASSWORD YourPassword

# Add database data during initialization

ADD DatabaseData.sql /docker-entrypoint-initdb.d
