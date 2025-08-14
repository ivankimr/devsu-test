--
-- PostgreSQL database dump
--

-- Dumped from database version 13.18 (Ubuntu 13.18-1.pgdg22.04+1)
-- Dumped by pg_dump version 14.18 (Ubuntu 14.18-0ubuntu0.22.04.1)

-- Started on 2025-08-13 21:29:55 -05

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
-- TOC entry 3049 (class 1262 OID 89007)
-- Name: devsu_customer; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE devsu_customer WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C.UTF-8';


ALTER DATABASE devsu_customer OWNER TO postgres;

\connect devsu_customer

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
-- TOC entry 4 (class 2615 OID 89138)
-- Name: devsu; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA devsu;


ALTER SCHEMA devsu OWNER TO postgres;

--
-- TOC entry 2 (class 3079 OID 89139)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA devsu;


--
-- TOC entry 3050 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 89177)
-- Name: customer; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.customer (
    customer_id uuid DEFAULT devsu.uuid_generate_v4() NOT NULL,
    person_id uuid NOT NULL,
    password_hash character varying(255) NOT NULL,
    customer_status_id smallint NOT NULL
);


ALTER TABLE devsu.customer OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 89181)
-- Name: customer_status; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.customer_status (
    customer_status_id smallint NOT NULL,
    customer_status_description character varying(20) NOT NULL
);


ALTER TABLE devsu.customer_status OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 89184)
-- Name: customer_status_customer_status_id_seq; Type: SEQUENCE; Schema: devsu; Owner: postgres
--

ALTER TABLE devsu.customer_status ALTER COLUMN customer_status_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME devsu.customer_status_customer_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 204 (class 1259 OID 89186)
-- Name: gender; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.gender (
    gender_id smallint NOT NULL,
    gender_name character varying(20) NOT NULL
);


ALTER TABLE devsu.gender OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 89189)
-- Name: gender_gender_id_seq; Type: SEQUENCE; Schema: devsu; Owner: postgres
--

ALTER TABLE devsu.gender ALTER COLUMN gender_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME devsu.gender_gender_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 206 (class 1259 OID 89191)
-- Name: identification_type; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.identification_type (
    identification_type_id smallint NOT NULL,
    identification_type_name character varying(20) NOT NULL
);


ALTER TABLE devsu.identification_type OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 89194)
-- Name: identification_type_identification_type_id_seq; Type: SEQUENCE; Schema: devsu; Owner: postgres
--

ALTER TABLE devsu.identification_type ALTER COLUMN identification_type_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME devsu.identification_type_identification_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 208 (class 1259 OID 89196)
-- Name: person; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.person (
    person_id uuid DEFAULT devsu.uuid_generate_v4() NOT NULL,
    person_name character varying(100) NOT NULL,
    gender_id smallint NOT NULL,
    age smallint NOT NULL,
    identification_type_id smallint NOT NULL,
    identification_number character varying(30) NOT NULL,
    address character varying(300) NOT NULL,
    phone character varying(20) NOT NULL
);


ALTER TABLE devsu.person OWNER TO postgres;

--
-- TOC entry 3036 (class 0 OID 89177)
-- Dependencies: 201
-- Data for Name: customer; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.customer VALUES ('5ac73c2a-8b61-44ab-9ef5-134ab7ff9fdf', '8131154a-413f-4f9f-8da9-9146ba16d24a', 'hashTest123', 1);
INSERT INTO devsu.customer VALUES ('ca921dc3-bf83-4fa3-835e-b24246cbf048', 'f3130eb4-36b5-4b90-962e-a7aecc04f355', '1234', 2);
INSERT INTO devsu.customer VALUES ('3afffc0f-c565-4b06-8159-1e66e8dce10c', '541ca5d1-ac54-410e-8839-57ef2b72cf9c', '123456', 1);


--
-- TOC entry 3037 (class 0 OID 89181)
-- Dependencies: 202
-- Data for Name: customer_status; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.customer_status OVERRIDING SYSTEM VALUE VALUES (1, 'Activo');
INSERT INTO devsu.customer_status OVERRIDING SYSTEM VALUE VALUES (2, 'Inactivo');


--
-- TOC entry 3039 (class 0 OID 89186)
-- Dependencies: 204
-- Data for Name: gender; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.gender OVERRIDING SYSTEM VALUE VALUES (1, 'Masculino');
INSERT INTO devsu.gender OVERRIDING SYSTEM VALUE VALUES (2, 'Femenino');


--
-- TOC entry 3041 (class 0 OID 89191)
-- Dependencies: 206
-- Data for Name: identification_type; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.identification_type OVERRIDING SYSTEM VALUE VALUES (1, 'Cédula de Identidad');
INSERT INTO devsu.identification_type OVERRIDING SYSTEM VALUE VALUES (2, 'Cédula de ciudadanía');
INSERT INTO devsu.identification_type OVERRIDING SYSTEM VALUE VALUES (3, 'Pasaporte');


--
-- TOC entry 3043 (class 0 OID 89196)
-- Dependencies: 208
-- Data for Name: person; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.person VALUES ('4fc18699-7bb6-4824-9c40-5a57e4a8752b', 'Juan Pérez', 1, 35, 2, '1234567891', 'Av. Siempre Viva 742', '+593991234567');
INSERT INTO devsu.person VALUES ('8131154a-413f-4f9f-8da9-9146ba16d24a', 'Karate Test', 1, 25, 1, '1234567896', 'Calle Test 123', '0999000000');
INSERT INTO devsu.person VALUES ('f3130eb4-36b5-4b90-962e-a7aecc04f355', 'Iván Muñoz', 1, 36, 1, '1234567890', 'Av. Siempre Viva 742', '+593991234567');
INSERT INTO devsu.person VALUES ('541ca5d1-ac54-410e-8839-57ef2b72cf9c', 'Silvana Rivera', 2, 44, 1, '1708086739', 'San Carlos', '+593996219173');


--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 203
-- Name: customer_status_customer_status_id_seq; Type: SEQUENCE SET; Schema: devsu; Owner: postgres
--

SELECT pg_catalog.setval('devsu.customer_status_customer_status_id_seq', 2, true);


--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 205
-- Name: gender_gender_id_seq; Type: SEQUENCE SET; Schema: devsu; Owner: postgres
--

SELECT pg_catalog.setval('devsu.gender_gender_id_seq', 2, true);


--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 207
-- Name: identification_type_identification_type_id_seq; Type: SEQUENCE SET; Schema: devsu; Owner: postgres
--

SELECT pg_catalog.setval('devsu.identification_type_identification_type_id_seq', 3, true);


--
-- TOC entry 2891 (class 2606 OID 89211)
-- Name: customer customer_pk; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.customer
    ADD CONSTRAINT customer_pk PRIMARY KEY (customer_id);


--
-- TOC entry 2893 (class 2606 OID 89213)
-- Name: customer_status customer_status_pk; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.customer_status
    ADD CONSTRAINT customer_status_pk PRIMARY KEY (customer_status_id);


--
-- TOC entry 2895 (class 2606 OID 89215)
-- Name: gender gender_pk; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.gender
    ADD CONSTRAINT gender_pk PRIMARY KEY (gender_id);


--
-- TOC entry 2897 (class 2606 OID 89217)
-- Name: identification_type identification_type_pk; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.identification_type
    ADD CONSTRAINT identification_type_pk PRIMARY KEY (identification_type_id);


--
-- TOC entry 2899 (class 2606 OID 89219)
-- Name: person person_pk; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.person
    ADD CONSTRAINT person_pk PRIMARY KEY (person_id);


--
-- TOC entry 2901 (class 2606 OID 89221)
-- Name: person person_unique; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.person
    ADD CONSTRAINT person_unique UNIQUE (identification_number);


--
-- TOC entry 2902 (class 2606 OID 89247)
-- Name: customer customer_customer_status_fk; Type: FK CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.customer
    ADD CONSTRAINT customer_customer_status_fk FOREIGN KEY (customer_status_id) REFERENCES devsu.customer_status(customer_status_id);


--
-- TOC entry 2903 (class 2606 OID 89252)
-- Name: customer customer_person_fk; Type: FK CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.customer
    ADD CONSTRAINT customer_person_fk FOREIGN KEY (person_id) REFERENCES devsu.person(person_id);


--
-- TOC entry 2904 (class 2606 OID 89257)
-- Name: person person_gender_fk; Type: FK CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.person
    ADD CONSTRAINT person_gender_fk FOREIGN KEY (gender_id) REFERENCES devsu.gender(gender_id);


--
-- TOC entry 2905 (class 2606 OID 89262)
-- Name: person person_identification_type_fk; Type: FK CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.person
    ADD CONSTRAINT person_identification_type_fk FOREIGN KEY (identification_type_id) REFERENCES devsu.identification_type(identification_type_id);


-- Completed on 2025-08-13 21:30:15 -05

--
-- PostgreSQL database dump complete
--

