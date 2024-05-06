--changeset h.mengni:005
CREATE TABLE public.order_item(
    "id" INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "menu_id" INTEGER NOT NUll,
    "qty" INTEGER NOT NULL DEFAULT 0,
    "price" FLOAT NOT NULL DEFAULT 0,
    FOREIGN KEY ("menu_id") REFERENCES public.menu("id")
);