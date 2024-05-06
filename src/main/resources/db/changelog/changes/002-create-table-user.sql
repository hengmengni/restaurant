--changeset h.mengni:002
CREATE TABLE public.user(
    "id" INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL,
    "username" VARCHAR(50) NOT NUll UNIQUE,
    "email" VARCHAR(50) NOT NULL UNIQUE,
    "gender" VARCHAR(10) NOT NULL,
    "profile" VARCHAR NOT NULL,
    "role_user_id" INTEGER,
    FOREIGN KEY ("role_user_id") REFERENCES public.role_user("id")
);