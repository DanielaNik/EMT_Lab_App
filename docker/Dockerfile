FROM postgres:latest
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=0707veles
ENV POSTGRES_DB=emt_lab
COPY init.sql /docker-entrypoint-initdb.d/
EXPOSE 5432
