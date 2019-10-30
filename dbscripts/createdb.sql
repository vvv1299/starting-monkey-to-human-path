--
-- PostgreSQL database dump
--

-- Dumped from database version 11.3
-- Dumped by pg_dump version 11.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: buildings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.buildings (
    id integer NOT NULL,
    number integer NOT NULL,
    street_id integer
);


ALTER TABLE public.buildings OWNER TO postgres;

--
-- Name: buildings_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.buildings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.buildings_id_seq OWNER TO postgres;

--
-- Name: buildings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.buildings_id_seq OWNED BY public.buildings.id;


--
-- Name: flats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flats (
    id integer NOT NULL,
    number integer NOT NULL,
    building_id integer
);


ALTER TABLE public.flats OWNER TO postgres;

--
-- Name: flats_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.flats_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.flats_id_seq OWNER TO postgres;

--
-- Name: flats_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.flats_id_seq OWNED BY public.flats.id;


--
-- Name: registrations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.registrations (
    id integer NOT NULL,
    date date NOT NULL,
    flat_id integer
);


ALTER TABLE public.registrations OWNER TO postgres;

--
-- Name: registrations-tariffs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."registrations-tariffs" (
    id integer NOT NULL,
    amount numeric(5,3) NOT NULL,
    registration_id integer,
    tariff_name character varying(50)
);


ALTER TABLE public.registrations_tariffs OWNER TO postgres;

--
-- Name: registrations-tariffs_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."registrations-tariffs_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."registrations-tariffs_id_seq" OWNER TO postgres;

--
-- Name: registrations-tariffs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."registrations-tariffs_id_seq" OWNED BY public.registrations_tariffs.id;


--
-- Name: registrations_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.registrations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.registrations_id_seq OWNER TO postgres;

--
-- Name: registrations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.registrations_id_seq OWNED BY public.registrations.id;


--
-- Name: streets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.streets (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.streets OWNER TO postgres;

--
-- Name: streets_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.streets_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.streets_id_seq OWNER TO postgres;

--
-- Name: streets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.streets_id_seq OWNED BY public.streets.id;


--
-- Name: tariffs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tariffs (
    name character varying(50) NOT NULL,
    cost numeric(10,2)
);


ALTER TABLE public.tariffs OWNER TO postgres;

--
-- Name: buildings id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings ALTER COLUMN id SET DEFAULT nextval('public.buildings_id_seq'::regclass);


--
-- Name: flats id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats ALTER COLUMN id SET DEFAULT nextval('public.flats_id_seq'::regclass);


--
-- Name: registrations id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrations ALTER COLUMN id SET DEFAULT nextval('public.registrations_id_seq'::regclass);


--
-- Name: registrations-tariffs id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrations_tariffs ALTER COLUMN id SET DEFAULT nextval('public."registrations-tariffs_id_seq"'::regclass);


--
-- Name: streets id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.streets ALTER COLUMN id SET DEFAULT nextval('public.streets_id_seq'::regclass);


--
-- Data for Name: buildings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.buildings (id, number, street_id) FROM stdin;
1	14	1
2	22	1
3	145	2
4	228	3
5	1	4
6	54	5
\.


--
-- Data for Name: flats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flats (id, number, building_id) FROM stdin;
1	43	1
2	40	1
3	12	2
4	18	2
5	122	3
6	136	3
7	27	4
8	54	4
9	66	5
10	35	5
11	215	6
12	256	6
\.


--
-- Data for Name: registrations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.registrations (id, date, flat_id) FROM stdin;
1	2019-10-01	1
3	2019-10-10	2
4	2019-05-01	3
5	2019-06-20	4
7	2019-11-15	5
8	2019-04-01	6
10	2019-01-30	7
14	2019-10-01	9
15	2019-03-12	10
16	2019-06-18	11
17	2019-06-28	12
\.


--
-- Data for Name: registrations-tariffs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.registrations_tariffs (id, amount, registration_id, tariff_name) FROM stdin;
5	40.000	1	Холодная вода
8	20.000	1	Горячая вода
10	90.000	1	Газ
17	10.000	1	Электричество
18	45.150	3	Холодная вода
19	15.266	3	Горячая вода
20	96.195	3	Газ
21	20.000	3	Электричество
22	90.000	5	Холодная вода
23	10.526	5	Горячая вода
24	45.150	5	Газ
25	61.672	5	Электричество
26	71.221	7	Холодная вода
27	92.151	7	Горячая вода
28	10.526	7	Газ
29	45.150	7	Электричество
30	61.672	8	Холодная вода
31	71.221	8	Горячая вода
32	90.000	8	Газ
33	10.666	8	Электричество
34	45.150	10	Холодная вода
35	15.266	10	Горячая вода
36	45.150	10	Газ
37	61.672	10	Электричество
38	71.221	14	Холодная вода
39	90.000	14	Горячая вода
40	15.266	14	Газ
41	96.195	14	Электричество
42	20.000	15	Холодная вода
43	90.000	15	Горячая вода
44	10.526	15	Газ
45	16.633	15	Электричество
46	10.000	16	Холодная вода
47	45.150	16	Горячая вода
48	15.266	16	Газ
49	96.195	16	Электричество
50	20.000	17	Холодная вода
51	90.000	17	Горячая вода
52	10.526	17	Газ
53	45.150	17	Электричество
\.


--
-- Data for Name: streets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.streets (id, name) FROM stdin;
1	Ново-Садовая
2	Московское шоссе
3	Советской Армии
4	Проспект Кирова
5	Революционная
\.


--
-- Data for Name: tariffs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tariffs (name, cost) FROM stdin;
Горячая вода	1.20
Холодная вода	0.65
Газ	2.25
Электричество	3.10
\.


--
-- Name: buildings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.buildings_id_seq', 6, true);


--
-- Name: flats_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.flats_id_seq', 12, true);


--
-- Name: registrations-tariffs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."registrations-tariffs_id_seq"', 53, true);


--
-- Name: registrations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.registrations_id_seq', 17, true);


--
-- Name: streets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.streets_id_seq', 5, true);


--
-- Name: buildings buildings_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings
    ADD CONSTRAINT buildings_pk PRIMARY KEY (id);


--
-- Name: flats flats_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats
    ADD CONSTRAINT flats_pk PRIMARY KEY (id);


--
-- Name: registrations-tariffs registrations-tariffs_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrations_tariffs
    ADD CONSTRAINT "registrations-tariffs_pk" PRIMARY KEY (id);


--
-- Name: registrations registrations_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrations
    ADD CONSTRAINT registrations_pk PRIMARY KEY (id);


--
-- Name: streets streets_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.streets
    ADD CONSTRAINT streets_pk PRIMARY KEY (id);


--
-- Name: tariffs tariffs_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tariffs
    ADD CONSTRAINT tariffs_pk PRIMARY KEY (name);


--
-- Name: flats building_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats
    ADD CONSTRAINT building_id FOREIGN KEY (building_id) REFERENCES public.buildings(id);


--
-- Name: registrations flat_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrations
    ADD CONSTRAINT flat_id FOREIGN KEY (flat_id) REFERENCES public.flats(id);


--
-- Name: registrations-tariffs registration_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrations_tariffs
    ADD CONSTRAINT registration_id FOREIGN KEY (registration_id) REFERENCES public.registrations(id);


--
-- Name: buildings street; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings
    ADD CONSTRAINT street FOREIGN KEY (street_id) REFERENCES public.streets(id);


--
-- Name: registrations-tariffs tariff_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrations_tariffs
    ADD CONSTRAINT tariff_name FOREIGN KEY (tariff_name) REFERENCES public.tariffs(name);


--
-- PostgreSQL database dump complete
--

