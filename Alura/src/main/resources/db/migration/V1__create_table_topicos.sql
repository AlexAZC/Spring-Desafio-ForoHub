create table topicos(

        id bigint generated by default as identity not null,
        mensaje varchar(500) not null,
        nombreCurso varchar(50) not null,
        titulo varchar(50) not null,
        fechaCreacion varchar(50) not null,
        activo boolean not null,

        primary key(id)

);