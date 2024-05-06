--changeset h.mengni:007
CREATE TABLE public.order_restaurant(
    "id" INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "table_id" INTEGER NOT NULL,
    "order_id" INTEGER NOT NULL,
    FOREIGN KEY ("table_id") REFERENCES public.table_in_restaurant("id"),
    FOREIGN KEY ("order_id") REFERENCES public.order_item("id")
);