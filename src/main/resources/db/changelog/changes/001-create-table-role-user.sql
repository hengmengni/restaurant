--changeset h.mengni:001
CREATE TABLE public.role_user(
    "id" INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL
);