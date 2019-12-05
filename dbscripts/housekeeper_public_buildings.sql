create table buildings
(
    id        serial  not null
        constraint buildings_pk
            primary key,
    number    integer not null,
    street_id integer
        constraint street
            references streets
);

alter table buildings
    owner to postgres;

INSERT INTO public.buildings (id, number, street_id) VALUES (1, 14, 1);
INSERT INTO public.buildings (id, number, street_id) VALUES (2, 22, 1);
INSERT INTO public.buildings (id, number, street_id) VALUES (3, 145, 2);
INSERT INTO public.buildings (id, number, street_id) VALUES (4, 228, 3);
INSERT INTO public.buildings (id, number, street_id) VALUES (5, 1, 4);
INSERT INTO public.buildings (id, number, street_id) VALUES (6, 54, 5);