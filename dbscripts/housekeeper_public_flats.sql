create table flats
(
    id          serial  not null
        constraint flats_pk
            primary key,
    number      integer not null,
    building_id integer
        constraint building_id
            references buildings
);

alter table flats
    owner to postgres;

INSERT INTO public.flats (id, number, building_id) VALUES (1, 43, 1);
INSERT INTO public.flats (id, number, building_id) VALUES (2, 40, 1);
INSERT INTO public.flats (id, number, building_id) VALUES (3, 12, 2);
INSERT INTO public.flats (id, number, building_id) VALUES (4, 18, 2);
INSERT INTO public.flats (id, number, building_id) VALUES (5, 122, 3);
INSERT INTO public.flats (id, number, building_id) VALUES (6, 136, 3);
INSERT INTO public.flats (id, number, building_id) VALUES (7, 27, 4);
INSERT INTO public.flats (id, number, building_id) VALUES (8, 54, 4);
INSERT INTO public.flats (id, number, building_id) VALUES (9, 66, 5);
INSERT INTO public.flats (id, number, building_id) VALUES (10, 35, 5);
INSERT INTO public.flats (id, number, building_id) VALUES (11, 215, 6);
INSERT INTO public.flats (id, number, building_id) VALUES (12, 256, 6);