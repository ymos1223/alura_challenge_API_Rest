CREATE TABLE topicos (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) UNIQUE NOT NULL,
    mensaje VARCHAR(250) UNIQUE NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    estatus VARCHAR(50) not null,
    id_autor INT NOT NULL,
    id_curso INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_curso) REFERENCES cursos(id)
);