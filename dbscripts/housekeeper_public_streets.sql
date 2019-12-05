create table streets
(
    id   serial      not null
        constraint streets_pk
            primary key,
    name varchar(50) not null
);

alter table streets
    owner to postgres;

INSERT INTO public.streets (id, name) VALUES (1, 'Ново-Садовая');
INSERT INTO public.streets (id, name) VALUES (2, 'Московское шоссе');
INSERT INTO public.streets (id, name) VALUES (3, 'Советской Армии');
INSERT INTO public.streets (id, name) VALUES (4, 'Проспект Кирова');
INSERT INTO public.streets (id, name) VALUES (5, 'Революционная');