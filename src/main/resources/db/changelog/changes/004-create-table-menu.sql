--changeset h.mengni:004
CREATE TABLE public.menu(
    "id" INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL,
    "price" FLOAT NOT NULL DEFAULT 0,
    "duration" INTEGER NOT NULL DEFAULT 0,
    "category_id" INTEGER NOT NULL,
    "image" VARCHAR NOT NULL,
    FOREIGN KEY ("category_id") REFERENCES public.category("id")
);