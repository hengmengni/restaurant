--changeset h.mengni:008
CREATE TABLE public.invoive(
    "id" INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "order_id" INTEGER NOT NULL,
    "amount" FLOAT NOT NULL DEFAULT 0,
    "discount" FLOAT NOT NULL ,
    "create_date" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "create_by" VARCHAR NOT NULL,
    FOREIGN KEY ("order_id") REFERENCES public.order_restaurant("id")
);