CREATE TABLE IF NOT EXISTS news (
                      id serial PRIMARY KEY ,
                      date DATE NOT NULL ,
                      title VARCHAR(50),
                      text VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS comments (
                          id serial PRIMARY KEY ,
                          date DATE,
                          text VARCHAR(500) ,
                          username VARCHAR(50) ,
                          id_news INTEGER REFERENCES news(id)
);


