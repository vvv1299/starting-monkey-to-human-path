create table registrations_tariffs
(
    id              serial        not null
        constraint "registrations-tariffs_pk"
            primary key,
    amount          numeric(5, 3) not null,
    registration_id integer
        constraint registration_id
            references registrations,
    tariff_name     varchar(50)
        constraint tariff_name
            references tariffs
);

alter table registrations_tariffs
    owner to postgres;

INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (55, 30.100, 20, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (56, 90.100, 20, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (57, 54.270, 20, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (5, 40.000, 1, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (58, 14.500, 20, 'Электричество');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (8, 20.000, 1, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (10, 90.000, 1, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (17, 10.000, 1, 'Электричество');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (18, 45.150, 3, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (19, 15.266, 3, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (20, 96.195, 3, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (21, 20.000, 3, 'Электричество');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (22, 90.000, 5, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (23, 10.526, 5, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (24, 45.150, 5, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (25, 61.672, 5, 'Электричество');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (26, 71.221, 7, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (27, 92.151, 7, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (28, 10.526, 7, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (29, 45.150, 7, 'Электричество');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (30, 61.672, 8, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (31, 71.221, 8, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (32, 90.000, 8, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (33, 10.666, 8, 'Электричество');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (34, 45.150, 10, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (35, 15.266, 10, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (36, 45.150, 10, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (37, 61.672, 10, 'Электричество');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (38, 71.221, 14, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (39, 90.000, 14, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (40, 15.266, 14, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (41, 96.195, 14, 'Электричество');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (42, 20.000, 15, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (43, 90.000, 15, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (44, 10.526, 15, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (45, 16.633, 15, 'Электричество');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (46, 10.000, 16, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (47, 45.150, 16, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (48, 15.266, 16, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (49, 96.195, 16, 'Электричество');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (50, 20.000, 17, 'Холодная вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (51, 90.000, 17, 'Горячая вода');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (52, 10.526, 17, 'Газ');
INSERT INTO public.registrations_tariffs (id, amount, registration_id, tariff_name) VALUES (53, 45.150, 17, 'Электричество');