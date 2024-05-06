--changeset h.mengni:003
CREATE TABLE public.category(
    "id" INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL
);
