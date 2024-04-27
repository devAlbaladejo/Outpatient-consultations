--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2024-01-07 13:24:39

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

DROP DATABASE cex;
--
-- TOC entry 3403 (class 1262 OID 57344)
-- Name: cex; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE cex WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';


ALTER DATABASE cex OWNER TO postgres;

\connect cex

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

--
-- TOC entry 216 (class 1259 OID 57354)
-- Name: seq_assistant; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_assistant
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;


ALTER TABLE public.seq_assistant OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 57355)
-- Name: assistant; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.assistant (
    id integer DEFAULT nextval('public.seq_assistant'::regclass) NOT NULL,
    name character varying NOT NULL,
    color character varying NOT NULL,
    active numeric(1,0) DEFAULT 1
);


ALTER TABLE public.assistant OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 57345)
-- Name: seq_diary; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_diary
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;


ALTER TABLE public.seq_diary OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 57346)
-- Name: diary; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.diary (
    id integer DEFAULT nextval('public.seq_diary'::regclass) NOT NULL,
    state character varying NOT NULL,
    color character varying NOT NULL
);


ALTER TABLE public.diary OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 57379)
-- Name: seq_doctor; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_doctor
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;


ALTER TABLE public.seq_doctor OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 57380)
-- Name: doctor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doctor (
    id integer DEFAULT nextval('public.seq_doctor'::regclass) NOT NULL,
    name character varying NOT NULL,
    speciality character varying NOT NULL,
    active numeric(1,0) DEFAULT 1
);


ALTER TABLE public.doctor OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 57393)
-- Name: seq_listing; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_listing
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;


ALTER TABLE public.seq_listing OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 57397)
-- Name: listing; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.listing (
    id integer DEFAULT nextval('public.seq_listing'::regclass) NOT NULL,
    doctor integer NOT NULL,
    doctor_comment character varying,
    assistant integer,
    assistant_comment character varying,
    room integer NOT NULL,
    date date NOT NULL,
    schedule character varying NOT NULL,
    diary integer
);


ALTER TABLE public.listing OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 98322)
-- Name: seq_role; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_role
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;


ALTER TABLE public.seq_role OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 98323)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer DEFAULT nextval('public.seq_role'::regclass) NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 57363)
-- Name: seq_room; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_room
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;


ALTER TABLE public.seq_room OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 57364)
-- Name: room; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.room (
    id integer DEFAULT nextval('public.seq_room'::regclass) NOT NULL,
    name character varying NOT NULL,
    active numeric(1,0) DEFAULT 1
);


ALTER TABLE public.room OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 81929)
-- Name: seq_user; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;


ALTER TABLE public.seq_user OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 57372)
-- Name: speciality; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.speciality (
    code character varying NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.speciality OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 81930)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer DEFAULT nextval('public.seq_user'::regclass) NOT NULL,
    email character varying NOT NULL,
    name character varying NOT NULL,
    password character varying NOT NULL,
    token character varying NOT NULL,
    role integer NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3386 (class 0 OID 57355)
-- Dependencies: 217
-- Data for Name: assistant; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.assistant (id, name, color, active) VALUES (1, 'Carla', '#c4be08', 1);
INSERT INTO public.assistant (id, name, color, active) VALUES (2, 'Noelia', '#b771b5', 1);
INSERT INTO public.assistant (id, name, color, active) VALUES (3, 'Vicente', '#10947e', 1);
INSERT INTO public.assistant (id, name, color, active) VALUES (4, 'Andrea', '#6f84ec', 1);
INSERT INTO public.assistant (id, name, color, active) VALUES (6, 'Luz', '#f4963e', 1);
INSERT INTO public.assistant (id, name, color, active) VALUES (5, 'Jorge', '#e8b5b5', 0);


--
-- TOC entry 3384 (class 0 OID 57346)
-- Dependencies: 215
-- Data for Name: diary; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.diary (id, state, color) VALUES (1, 'Blocked up', '#E74C3C');
INSERT INTO public.diary (id, state, color) VALUES (2, 'Confirmed', '#8DF786');
INSERT INTO public.diary (id, state, color) VALUES (3, 'Telephone', '#F39C12');


--
-- TOC entry 3391 (class 0 OID 57380)
-- Dependencies: 222
-- Data for Name: doctor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.doctor (id, name, speciality, active) VALUES (1, 'Dr. Sanchez', 'DIG', 1);
INSERT INTO public.doctor (id, name, speciality, active) VALUES (3, 'Dr. Garcia', 'END', 1);
INSERT INTO public.doctor (id, name, speciality, active) VALUES (2, 'Dra. López', 'GYN', 1);
INSERT INTO public.doctor (id, name, speciality, active) VALUES (4, 'Dra. Castaño', 'CAR', 1);
INSERT INTO public.doctor (id, name, speciality, active) VALUES (5, 'Dr. Blanco', 'URO', 1);
INSERT INTO public.doctor (id, name, speciality, active) VALUES (6, 'Dra. Campos', 'TRA', 1);
INSERT INTO public.doctor (id, name, speciality, active) VALUES (7, 'Dra. Morales', 'IMM', 1);
INSERT INTO public.doctor (id, name, speciality, active) VALUES (8, 'Dr. Hermoso', 'PED', 1);
INSERT INTO public.doctor (id, name, speciality, active) VALUES (9, 'Dra. Tamayo', 'NPS', 1);
INSERT INTO public.doctor (id, name, speciality, active) VALUES (10, 'Dra. Muñoz', 'ALG', 1);
INSERT INTO public.doctor (id, name, speciality, active) VALUES (11, 'Dr. Cortés', 'PRE', 0);


--
-- TOC entry 3393 (class 0 OID 57397)
-- Dependencies: 224
-- Data for Name: listing; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (1, 1, NULL, 4, NULL, 1, '2024-01-01', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (2, 10, NULL, NULL, NULL, 1, '2024-01-01', 'A', 1);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (3, 1, NULL, 3, NULL, 5, '2024-01-01', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (4, 10, NULL, 3, NULL, 10, '2024-01-01', 'M', NULL);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (5, 4, NULL, 1, NULL, 3, '2024-01-01', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (6, 4, NULL, 1, NULL, 3, '2024-01-01', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (7, 3, NULL, NULL, NULL, 7, '2024-01-01', 'M', 3);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (8, 3, NULL, NULL, NULL, 11, '2024-01-01', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (10, 2, NULL, 4, NULL, 6, '2024-01-01', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (9, 2, NULL, 2, NULL, 8, '2024-01-01', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (11, 5, NULL, NULL, NULL, 2, '2024-01-01', 'M', NULL);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (12, 6, NULL, 2, NULL, 2, '2024-01-01', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (13, 1, NULL, 4, NULL, 1, '2024-01-02', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (14, 10, NULL, NULL, NULL, 1, '2024-01-02', 'A', 1);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (15, 1, NULL, 3, NULL, 5, '2024-01-02', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (16, 10, NULL, 3, NULL, 10, '2024-01-02', 'M', NULL);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (17, 4, NULL, 1, NULL, 3, '2024-01-02', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (18, 4, NULL, 1, NULL, 3, '2024-01-02', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (19, 3, NULL, NULL, NULL, 7, '2024-01-02', 'M', 3);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (20, 3, NULL, NULL, NULL, 11, '2024-01-02', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (21, 2, NULL, 4, NULL, 6, '2024-01-02', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (22, 2, NULL, 2, NULL, 8, '2024-01-02', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (23, 5, NULL, NULL, NULL, 2, '2024-01-02', 'M', NULL);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (24, 6, NULL, 2, NULL, 2, '2024-01-02', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (25, 1, NULL, 4, NULL, 1, '2024-01-03', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (26, 10, NULL, NULL, NULL, 1, '2024-01-03', 'A', 1);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (27, 1, NULL, 3, NULL, 5, '2024-01-03', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (28, 10, NULL, 3, NULL, 10, '2024-01-03', 'M', NULL);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (29, 4, NULL, 1, NULL, 3, '2024-01-03', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (30, 4, NULL, 1, NULL, 3, '2024-01-03', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (31, 3, NULL, NULL, NULL, 7, '2024-01-03', 'M', 3);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (32, 3, NULL, NULL, NULL, 11, '2024-01-03', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (33, 2, NULL, 4, NULL, 6, '2024-01-03', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (34, 2, NULL, 2, NULL, 8, '2024-01-03', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (35, 5, NULL, NULL, NULL, 2, '2024-01-03', 'M', NULL);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (36, 6, NULL, 2, NULL, 2, '2024-01-03', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (37, 1, NULL, 4, NULL, 1, '2024-01-04', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (38, 10, NULL, NULL, NULL, 1, '2024-01-04', 'A', 1);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (39, 1, NULL, 3, NULL, 5, '2024-01-04', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (40, 10, NULL, 3, NULL, 10, '2024-01-04', 'M', NULL);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (41, 4, NULL, 1, NULL, 3, '2024-01-04', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (42, 4, NULL, 1, NULL, 3, '2024-01-04', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (43, 3, NULL, NULL, NULL, 7, '2024-01-04', 'M', 3);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (44, 3, NULL, NULL, NULL, 11, '2024-01-04', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (45, 2, NULL, 4, NULL, 6, '2024-01-04', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (46, 2, NULL, 2, NULL, 8, '2024-01-04', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (47, 5, NULL, NULL, NULL, 2, '2024-01-04', 'M', NULL);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (48, 6, NULL, 2, NULL, 2, '2024-01-04', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (49, 1, NULL, 4, NULL, 1, '2024-01-05', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (50, 10, NULL, NULL, NULL, 1, '2024-01-05', 'A', 1);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (51, 1, NULL, 3, NULL, 5, '2024-01-05', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (52, 10, NULL, 3, NULL, 10, '2024-01-05', 'M', NULL);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (53, 4, NULL, 1, NULL, 3, '2024-01-05', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (54, 4, NULL, 1, NULL, 3, '2024-01-05', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (55, 3, NULL, NULL, NULL, 7, '2024-01-05', 'M', 3);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (56, 3, NULL, NULL, NULL, 11, '2024-01-05', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (57, 2, NULL, 4, NULL, 6, '2024-01-05', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (58, 2, NULL, 2, NULL, 8, '2024-01-05', 'M', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (59, 5, NULL, NULL, NULL, 2, '2024-01-05', 'M', NULL);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (60, 6, NULL, 2, NULL, 2, '2024-01-05', 'A', 2);
INSERT INTO public.listing (id, doctor, doctor_comment, assistant, assistant_comment, room, date, schedule, diary) VALUES (61, 10, NULL, NULL, NULL, 1, '2024-01-08', 'M', 1);


--
-- TOC entry 3397 (class 0 OID 98323)
-- Dependencies: 228
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id, name) VALUES (1, 'supervisor');
INSERT INTO public.role (id, name) VALUES (2, 'assistant');


--
-- TOC entry 3388 (class 0 OID 57364)
-- Dependencies: 219
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.room (id, name, active) VALUES (1, '1', 1);
INSERT INTO public.room (id, name, active) VALUES (2, '2', 1);
INSERT INTO public.room (id, name, active) VALUES (3, '3', 1);
INSERT INTO public.room (id, name, active) VALUES (4, '4-5-6', 0);
INSERT INTO public.room (id, name, active) VALUES (5, '7', 1);
INSERT INTO public.room (id, name, active) VALUES (6, '8', 1);
INSERT INTO public.room (id, name, active) VALUES (7, '9', 1);
INSERT INTO public.room (id, name, active) VALUES (8, '10', 1);
INSERT INTO public.room (id, name, active) VALUES (9, '11', 1);
INSERT INTO public.room (id, name, active) VALUES (10, '12', 1);
INSERT INTO public.room (id, name, active) VALUES (11, '13-14', 1);
INSERT INTO public.room (id, name, active) VALUES (12, '15', 0);
INSERT INTO public.room (id, name, active) VALUES (13, '16', 1);
INSERT INTO public.room (id, name, active) VALUES (14, '17', 1);
INSERT INTO public.room (id, name, active) VALUES (15, '18', 1);
INSERT INTO public.room (id, name, active) VALUES (16, '19', 1);
INSERT INTO public.room (id, name, active) VALUES (17, '20', 1);


--
-- TOC entry 3389 (class 0 OID 57372)
-- Dependencies: 220
-- Data for Name: speciality; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.speciality (code, name) VALUES ('ALG', 'Allergy');
INSERT INTO public.speciality (code, name) VALUES ('ANS', 'Anesthesiology');
INSERT INTO public.speciality (code, name) VALUES ('CLA', 'Clinical analysis');
INSERT INTO public.speciality (code, name) VALUES ('AVS', 'Angiology / Vascular Surgery');
INSERT INTO public.speciality (code, name) VALUES ('CLB', 'Clinical Biochemistry');
INSERT INTO public.speciality (code, name) VALUES ('CAR', 'Cardiology');
INSERT INTO public.speciality (code, name) VALUES ('GDS', 'General and Digestive Surgery');
INSERT INTO public.speciality (code, name) VALUES ('MAS', 'Maxillofacial Surgery');
INSERT INTO public.speciality (code, name) VALUES ('PES', 'Pediatric Surgery');
INSERT INTO public.speciality (code, name) VALUES ('PLS', 'Plastic surgery');
INSERT INTO public.speciality (code, name) VALUES ('THS', 'Thoracic Surgery');
INSERT INTO public.speciality (code, name) VALUES ('DIG', 'Digestive');
INSERT INTO public.speciality (code, name) VALUES ('END', 'Endocrinology');
INSERT INTO public.speciality (code, name) VALUES ('NUR', 'Nursing');
INSERT INTO public.speciality (code, name) VALUES ('PHA', 'Pharmacy');
INSERT INTO public.speciality (code, name) VALUES ('PHY', 'Physiotherapy');
INSERT INTO public.speciality (code, name) VALUES ('GYN', 'Gynecology');
INSERT INTO public.speciality (code, name) VALUES ('HEM', 'Hematology');
INSERT INTO public.speciality (code, name) VALUES ('IMM', 'Immunology');
INSERT INTO public.speciality (code, name) VALUES ('LAB', 'Laboratory');
INSERT INTO public.speciality (code, name) VALUES ('COM', 'Community Medicine');
INSERT INTO public.speciality (code, name) VALUES ('AEM', 'Aesthetic Medicine');
INSERT INTO public.speciality (code, name) VALUES ('MIP', 'Microbiology and Parasitology');
INSERT INTO public.speciality (code, name) VALUES ('IRM', 'Internal Medicine');
INSERT INTO public.speciality (code, name) VALUES ('IVM', 'Intensive medicine');
INSERT INTO public.speciality (code, name) VALUES ('NUM', 'Nuclear medicine');
INSERT INTO public.speciality (code, name) VALUES ('NEP', 'Nephrology');
INSERT INTO public.speciality (code, name) VALUES ('NEO', 'Neonatology');
INSERT INTO public.speciality (code, name) VALUES ('CLN', 'Clinical Neurophysiology');
INSERT INTO public.speciality (code, name) VALUES ('PNE', 'Pneumology');
INSERT INTO public.speciality (code, name) VALUES ('NES', 'Neurosurgery');
INSERT INTO public.speciality (code, name) VALUES ('NEU', 'Neurology');
INSERT INTO public.speciality (code, name) VALUES ('NPS', 'Neuropsychology');
INSERT INTO public.speciality (code, name) VALUES ('OPH', 'Ophthalmology');
INSERT INTO public.speciality (code, name) VALUES ('MON', 'Medical Oncology');
INSERT INTO public.speciality (code, name) VALUES ('OTO', 'Otorhinolaryngology');
INSERT INTO public.speciality (code, name) VALUES ('PED', 'Pediatrics');
INSERT INTO public.speciality (code, name) VALUES ('PRE', 'Preanesthesia');
INSERT INTO public.speciality (code, name) VALUES ('PSG', 'Psychology');
INSERT INTO public.speciality (code, name) VALUES ('PST', 'Psychiatry');
INSERT INTO public.speciality (code, name) VALUES ('RAD', 'Radiology');
INSERT INTO public.speciality (code, name) VALUES ('REH', 'Rehabilitation');
INSERT INTO public.speciality (code, name) VALUES ('RHE', 'Rheumatology');
INSERT INTO public.speciality (code, name) VALUES ('TRA', 'Traumatology');
INSERT INTO public.speciality (code, name) VALUES ('EME', 'Emergencies');
INSERT INTO public.speciality (code, name) VALUES ('URO', 'Urology');
INSERT INTO public.speciality (code, name) VALUES ('PAA', 'Pathological anatomy');


--
-- TOC entry 3395 (class 0 OID 81930)
-- Dependencies: 226
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, email, name, password, token, role) VALUES (1, 'supervisor@cex.com', 'Ángel Albaladejo Flores', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3MDAyMjgwOTUsImV4cCI6MTczMTc2NDA5NSwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0.MEp4oFZ9w6tR7aElboeTza3is-KM7MbC1cGaqGF6qco', 1);
INSERT INTO public.users (id, email, name, password, token, role) VALUES (2, 'assistant@cex.com', 'Assistant CEX', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'zu07FniH-qzkg7dSoRv1ZGth6AQOHqNy2hTYkf4xXl5ScB2mwecStOCsuu49xrUDZoN2QYig5MN9hEoLUEn-iRL1VrMxAU382mlf-uSi5dOxB2C5yWYgVj62Gqg7P580e4CVkAzEMkP8AKopynofQg39ttGtU50pl5RJ5OX9qpprhX8Pvoz1Z8FHBv2sv8itrMp1I-k47zGpmFvFj6-AD7vnq8hWCSveU1zhvyoKjfmkEOP4IDZMPGBFCaP5LlENCja8yGrDbxzBAKVs0kXXRvPqBy38eEchMnhZzeCXJfsNuSUE4xAycGICJ2XXU8HHSZZPyKXmtxbFx2FWEhrG', 2);


--
-- TOC entry 3404 (class 0 OID 0)
-- Dependencies: 216
-- Name: seq_assistant; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_assistant', 6, true);


--
-- TOC entry 3405 (class 0 OID 0)
-- Dependencies: 214
-- Name: seq_diary; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_diary', 3, true);


--
-- TOC entry 3406 (class 0 OID 0)
-- Dependencies: 221
-- Name: seq_doctor; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_doctor', 11, true);


--
-- TOC entry 3407 (class 0 OID 0)
-- Dependencies: 223
-- Name: seq_listing; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_listing', 61, true);


--
-- TOC entry 3408 (class 0 OID 0)
-- Dependencies: 227
-- Name: seq_role; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_role', 2, true);


--
-- TOC entry 3409 (class 0 OID 0)
-- Dependencies: 218
-- Name: seq_room; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_room', 17, true);


--
-- TOC entry 3410 (class 0 OID 0)
-- Dependencies: 225
-- Name: seq_user; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_user', 2, true);


--
-- TOC entry 3220 (class 2606 OID 57362)
-- Name: assistant pk_assistant; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assistant
    ADD CONSTRAINT pk_assistant PRIMARY KEY (id);


--
-- TOC entry 3218 (class 2606 OID 57353)
-- Name: diary pk_diary; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.diary
    ADD CONSTRAINT pk_diary PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 57387)
-- Name: doctor pk_doctor; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT pk_doctor PRIMARY KEY (id);


--
-- TOC entry 3228 (class 2606 OID 57404)
-- Name: listing pk_list; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.listing
    ADD CONSTRAINT pk_list PRIMARY KEY (id);


--
-- TOC entry 3236 (class 2606 OID 98330)
-- Name: role pk_role; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT pk_role PRIMARY KEY (id);


--
-- TOC entry 3222 (class 2606 OID 57371)
-- Name: room pk_room; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT pk_room PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 57378)
-- Name: speciality pk_speciality; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.speciality
    ADD CONSTRAINT pk_speciality PRIMARY KEY (code);


--
-- TOC entry 3232 (class 2606 OID 81937)
-- Name: users pk_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT pk_user PRIMARY KEY (id);


--
-- TOC entry 3234 (class 2606 OID 90146)
-- Name: users uk_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_email UNIQUE (email);


--
-- TOC entry 3230 (class 2606 OID 57406)
-- Name: listing uk_list; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.listing
    ADD CONSTRAINT uk_list UNIQUE (room, date, schedule);


--
-- TOC entry 3238 (class 2606 OID 57407)
-- Name: listing fk_doctor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.listing
    ADD CONSTRAINT fk_doctor FOREIGN KEY (doctor) REFERENCES public.doctor(id);


--
-- TOC entry 3240 (class 2606 OID 98331)
-- Name: users fk_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_role FOREIGN KEY (role) REFERENCES public.role(id);


--
-- TOC entry 3239 (class 2606 OID 57417)
-- Name: listing fk_room; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.listing
    ADD CONSTRAINT fk_room FOREIGN KEY (room) REFERENCES public.room(id);


--
-- TOC entry 3237 (class 2606 OID 57388)
-- Name: doctor fk_speciality; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT fk_speciality FOREIGN KEY (speciality) REFERENCES public.speciality(code);


-- Completed on 2024-01-07 13:24:40

--
-- PostgreSQL database dump complete
--

