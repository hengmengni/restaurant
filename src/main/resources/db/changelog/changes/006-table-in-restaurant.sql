--changeset h.mengni:006
CREATE TABLE public.table_in_restaurant(
    "id" INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL,
    "description" VARCHAR(255),
    "status" VARCHAR(50) NOT NULL,
    "create_date" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "create_by" VARCHAR NOT NULL
);