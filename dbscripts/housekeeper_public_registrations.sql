create table registrations
(
    id      serial not null
        constraint registrations_pk
            primary key,
    date    date   not null,
    flat_id integer
        constraint flat_id
            references flats
);

alter table registrations
    owner to postgres;

INSERT INTO public.registrations (id, date, flat_id) VALUES (1, '2019-10-01', 1);
INSERT INTO public.registrations (id, date, flat_id) VALUES (3, '2019-10-10', 2);
INSERT INTO public.registrations (id, date, flat_id) VALUES (4, '2019-05-01', 3);
INSERT INTO public.registrations (id, date, flat_id) VALUES (5, '2019-06-20', 4);
INSERT INTO public.registrations (id, date, flat_id) VALUES (7, '2019-11-15', 5);
INSERT INTO public.registrations (id, date, flat_id) VALUES (8, '2019-04-01', 6);
INSERT INTO public.registrations (id, date, flat_id) VALUES (20, '2019-11-10', 12);
INSERT INTO public.registrations (id, date, flat_id) VALUES (10, '2019-01-30', 7);
INSERT INTO public.registrations (id, date, flat_id) VALUES (14, '2019-10-01', 9);
INSERT INTO public.registrations (id, date, flat_id) VALUES (15, '2019-03-12', 10);
INSERT INTO public.registrations (id, date, flat_id) VALUES (16, '2019-06-18', 11);
INSERT INTO public.registrations (id, date, flat_id) VALUES (17, '2019-06-28', 12);