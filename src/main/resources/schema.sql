CREATE TABLE IF NOT EXISTS admin (
                                     id BIGSERIAL PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );


INSERT INTO admin (username, password)
SELECT 'admin', '$2a$10$NZ6kYDU.tiRgO85TlyeNgOUJxN5kx/3ZjjvNmDmGcdsspvspgcGLG'
WHERE NOT EXISTS (SELECT 1 FROM admin WHERE username = 'admin');



