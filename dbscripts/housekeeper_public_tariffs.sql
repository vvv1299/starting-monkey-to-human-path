create table tariffs
(
    name varchar(50) not null
        constraint tariffs_pk
            primary key,
    cost numeric(10, 2)
);

alter table tariffs
    owner to postgres;

INSERT INTO public.tariffs (name, cost) VALUES ('Горячая вода', 1.20);
INSERT INTO public.tariffs (name, cost) VALUES ('Газ', 2.25);
INSERT INTO public.tariffs (name, cost) VALUES ('Электричество', 1.50);
INSERT INTO public.tariffs (name, cost) VALUES ('Холодная вода', 0.70);