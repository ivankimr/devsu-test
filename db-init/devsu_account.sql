--
-- PostgreSQL database dump
--

-- Dumped from database version 13.18 (Ubuntu 13.18-1.pgdg22.04+1)
-- Dumped by pg_dump version 14.18 (Ubuntu 14.18-0ubuntu0.22.04.1)

-- Started on 2025-08-13 21:30:32 -05

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
-- TOC entry 3060 (class 1262 OID 89008)
-- Name: devsu_account; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE devsu_account WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C.UTF-8';


ALTER DATABASE devsu_account OWNER TO postgres;

\connect devsu_account

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
-- TOC entry 4 (class 2615 OID 89009)
-- Name: devsu; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA devsu;


ALTER SCHEMA devsu OWNER TO postgres;

--
-- TOC entry 2 (class 3079 OID 89010)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA devsu;


--
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 89021)
-- Name: account; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.account (
    account_id uuid DEFAULT devsu.uuid_generate_v4() NOT NULL,
    customer_id uuid NOT NULL,
    account_number character varying(34) NOT NULL,
    account_type_id smallint NOT NULL,
    initial_balance numeric(18,2) DEFAULT 0.00 NOT NULL,
    account_status_id smallint NOT NULL
);


ALTER TABLE devsu.account OWNER TO postgres;

--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN account.account_number; Type: COMMENT; Schema: devsu; Owner: postgres
--

COMMENT ON COLUMN devsu.account.account_number IS 'Standard IBAN Length: 34';


--
-- TOC entry 202 (class 1259 OID 89026)
-- Name: account_status; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.account_status (
    account_status_id smallint NOT NULL,
    account_status_description character varying(20) NOT NULL
);


ALTER TABLE devsu.account_status OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 89029)
-- Name: account_status_account_status_id_seq; Type: SEQUENCE; Schema: devsu; Owner: postgres
--

ALTER TABLE devsu.account_status ALTER COLUMN account_status_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME devsu.account_status_account_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 204 (class 1259 OID 89031)
-- Name: account_transaction; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.account_transaction (
    account_transaction_id uuid DEFAULT devsu.uuid_generate_v4() NOT NULL,
    account_id uuid NOT NULL,
    account_transaction_type_id smallint NOT NULL,
    transaction_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    transaction_amount numeric(18,2) NOT NULL,
    account_balance numeric(18,2) NOT NULL
);


ALTER TABLE devsu.account_transaction OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 89036)
-- Name: account_transaction_type; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.account_transaction_type (
    account_transaction_type_id smallint NOT NULL,
    account_transaction_type_description character varying(100) NOT NULL,
    balance_operation smallint DEFAULT 0 NOT NULL,
    CONSTRAINT account_transaction_type_balance_operation_check CHECK ((balance_operation = ANY (ARRAY['-1'::integer, 0, 1])))
);


ALTER TABLE devsu.account_transaction_type OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 89041)
-- Name: account_transaction_type_account_transaction_type_id_seq; Type: SEQUENCE; Schema: devsu; Owner: postgres
--

ALTER TABLE devsu.account_transaction_type ALTER COLUMN account_transaction_type_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME devsu.account_transaction_type_account_transaction_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 207 (class 1259 OID 89043)
-- Name: account_type; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.account_type (
    account_type_id smallint NOT NULL,
    account_type_description character varying(20) NOT NULL
);


ALTER TABLE devsu.account_type OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 89046)
-- Name: account_type_account_type_id_seq; Type: SEQUENCE; Schema: devsu; Owner: postgres
--

ALTER TABLE devsu.account_type ALTER COLUMN account_type_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME devsu.account_type_account_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 209 (class 1259 OID 89267)
-- Name: customer_projection; Type: TABLE; Schema: devsu; Owner: postgres
--

CREATE TABLE devsu.customer_projection (
    customer_id uuid NOT NULL,
    person_name character varying(100) NOT NULL,
    source_event_id smallint NOT NULL,
    source_event_type character varying(80) NOT NULL,
    received_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE devsu.customer_projection OWNER TO postgres;

--
-- TOC entry 3046 (class 0 OID 89021)
-- Dependencies: 201
-- Data for Name: account; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.account VALUES ('06e1a1e8-e28c-492d-b9ee-643237df5be1', 'ca921dc3-bf83-4fa3-835e-b24246cbf048', '00123456789', 1, 1500.75, 1);
INSERT INTO devsu.account VALUES ('7d4c40f5-3c50-421e-aca3-b712a2ad4232', 'ca921dc3-bf83-4fa3-835e-b24246cbf048', '00555123456', 2, 200.00, 1);
INSERT INTO devsu.account VALUES ('174d6854-dd7a-4878-a54e-438c231ab2f2', '3afffc0f-c565-4b06-8159-1e66e8dce10c', '00555123488', 1, 225.00, 1);


--
-- TOC entry 3047 (class 0 OID 89026)
-- Dependencies: 202
-- Data for Name: account_status; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.account_status OVERRIDING SYSTEM VALUE VALUES (1, 'Activa');
INSERT INTO devsu.account_status OVERRIDING SYSTEM VALUE VALUES (2, 'Inactiva');
INSERT INTO devsu.account_status OVERRIDING SYSTEM VALUE VALUES (3, 'Bloqueada');
INSERT INTO devsu.account_status OVERRIDING SYSTEM VALUE VALUES (4, 'Suspendida');
INSERT INTO devsu.account_status OVERRIDING SYSTEM VALUE VALUES (5, 'Activación pendiente');
INSERT INTO devsu.account_status OVERRIDING SYSTEM VALUE VALUES (6, 'Cierre pendiente');


--
-- TOC entry 3049 (class 0 OID 89031)
-- Dependencies: 204
-- Data for Name: account_transaction; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.account_transaction VALUES ('d7d40347-d646-4e9b-9039-c0fe5d003393', '06e1a1e8-e28c-492d-b9ee-643237df5be1', 1, '2025-08-11 08:29:59.958776-05', 250.75, 1751.50);
INSERT INTO devsu.account_transaction VALUES ('341f5dd3-87d5-48d8-8d85-c12dae372322', '06e1a1e8-e28c-492d-b9ee-643237df5be1', 2, '2025-08-11 08:31:06.651329-05', 30.00, 1721.50);
INSERT INTO devsu.account_transaction VALUES ('9c3d6d8d-b63d-4c8d-9654-e6dcf2ff865c', '06e1a1e8-e28c-492d-b9ee-643237df5be1', 2, '2025-08-11 08:31:36.168593-05', 30.00, 1691.50);
INSERT INTO devsu.account_transaction VALUES ('3566d4b2-e7eb-47b1-bcf8-eed9fcd2fc35', '06e1a1e8-e28c-492d-b9ee-643237df5be1', 2, '2025-08-11 08:32:10.708658-05', 1691.50, 0.00);
INSERT INTO devsu.account_transaction VALUES ('01405b37-78c9-44a7-8856-1ea7cae02c2f', '06e1a1e8-e28c-492d-b9ee-643237df5be1', 1, '2025-08-11 08:32:31.691219-05', 150.25, 150.25);
INSERT INTO devsu.account_transaction VALUES ('99cf6eff-3894-42aa-9833-7d18ded56d85', '06e1a1e8-e28c-492d-b9ee-643237df5be1', 8, '2025-08-11 08:41:40.029933-05', 0.36, 149.89);
INSERT INTO devsu.account_transaction VALUES ('7527ff81-a38d-43d9-99c8-f21832be5be4', '7d4c40f5-3c50-421e-aca3-b712a2ad4232', 1, '2025-08-11 12:09:17.343302-05', 52.28, 252.28);
INSERT INTO devsu.account_transaction VALUES ('b2ca8d4a-ec93-4ca3-88b2-71382bd5aef4', '7d4c40f5-3c50-421e-aca3-b712a2ad4232', 1, '2025-08-11 23:41:37.550318-05', 152.28, 404.56);
INSERT INTO devsu.account_transaction VALUES ('b750359e-296b-4b16-b741-d4c52d05cb61', '174d6854-dd7a-4878-a54e-438c231ab2f2', 1, '2025-08-13 19:05:16.672039-05', 152.28, 377.28);


--
-- TOC entry 3050 (class 0 OID 89036)
-- Dependencies: 205
-- Data for Name: account_transaction_type; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.account_transaction_type OVERRIDING SYSTEM VALUE VALUES (6, 'Reverso', 0);
INSERT INTO devsu.account_transaction_type OVERRIDING SYSTEM VALUE VALUES (1, 'Depósito', 1);
INSERT INTO devsu.account_transaction_type OVERRIDING SYSTEM VALUE VALUES (2, 'Retiro', -1);
INSERT INTO devsu.account_transaction_type OVERRIDING SYSTEM VALUE VALUES (3, 'Transferencia Depósito', 1);
INSERT INTO devsu.account_transaction_type OVERRIDING SYSTEM VALUE VALUES (4, 'Transferencia Retiro', -1);
INSERT INTO devsu.account_transaction_type OVERRIDING SYSTEM VALUE VALUES (5, 'Pago servicios', -1);
INSERT INTO devsu.account_transaction_type OVERRIDING SYSTEM VALUE VALUES (7, 'Interes', 1);
INSERT INTO devsu.account_transaction_type OVERRIDING SYSTEM VALUE VALUES (9, 'IVA Comisión', -1);
INSERT INTO devsu.account_transaction_type OVERRIDING SYSTEM VALUE VALUES (8, 'Comisión', -1);


--
-- TOC entry 3052 (class 0 OID 89043)
-- Dependencies: 207
-- Data for Name: account_type; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.account_type OVERRIDING SYSTEM VALUE VALUES (1, 'Ahorros');
INSERT INTO devsu.account_type OVERRIDING SYSTEM VALUE VALUES (2, 'Corriente');


--
-- TOC entry 3054 (class 0 OID 89267)
-- Dependencies: 209
-- Data for Name: customer_projection; Type: TABLE DATA; Schema: devsu; Owner: postgres
--

INSERT INTO devsu.customer_projection VALUES ('ca921dc3-bf83-4fa3-835e-b24246cbf048', 'Iván Muñoz', 1, 'customer.insupd', '2025-08-11 18:24:35.375405-05');
INSERT INTO devsu.customer_projection VALUES ('3afffc0f-c565-4b06-8159-1e66e8dce10c', 'Silvana Rivera', 1, 'customer.insupd', '2025-08-13 19:02:48.61967-05');


--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 203
-- Name: account_status_account_status_id_seq; Type: SEQUENCE SET; Schema: devsu; Owner: postgres
--

SELECT pg_catalog.setval('devsu.account_status_account_status_id_seq', 6, true);


--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 206
-- Name: account_transaction_type_account_transaction_type_id_seq; Type: SEQUENCE SET; Schema: devsu; Owner: postgres
--

SELECT pg_catalog.setval('devsu.account_transaction_type_account_transaction_type_id_seq', 9, true);


--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 208
-- Name: account_type_account_type_id_seq; Type: SEQUENCE SET; Schema: devsu; Owner: postgres
--

SELECT pg_catalog.setval('devsu.account_type_account_type_id_seq', 2, true);


--
-- TOC entry 2900 (class 2606 OID 89072)
-- Name: account account_pk; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.account
    ADD CONSTRAINT account_pk PRIMARY KEY (account_id);


--
-- TOC entry 2902 (class 2606 OID 89074)
-- Name: account_status account_status_pk; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.account_status
    ADD CONSTRAINT account_status_pk PRIMARY KEY (account_status_id);


--
-- TOC entry 2904 (class 2606 OID 89076)
-- Name: account_transaction account_transaction_pk; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.account_transaction
    ADD CONSTRAINT account_transaction_pk PRIMARY KEY (account_transaction_id);


--
-- TOC entry 2906 (class 2606 OID 89078)
-- Name: account_transaction_type account_transaction_type_pk; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.account_transaction_type
    ADD CONSTRAINT account_transaction_type_pk PRIMARY KEY (account_transaction_type_id);


--
-- TOC entry 2908 (class 2606 OID 89080)
-- Name: account_type account_type_pk; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.account_type
    ADD CONSTRAINT account_type_pk PRIMARY KEY (account_type_id);


--
-- TOC entry 2910 (class 2606 OID 89272)
-- Name: customer_projection customer_projection_pkey; Type: CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.customer_projection
    ADD CONSTRAINT customer_projection_pkey PRIMARY KEY (customer_id);


--
-- TOC entry 2911 (class 2606 OID 89093)
-- Name: account account_account_status_fk; Type: FK CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.account
    ADD CONSTRAINT account_account_status_fk FOREIGN KEY (account_status_id) REFERENCES devsu.account_status(account_status_id);


--
-- TOC entry 2912 (class 2606 OID 89098)
-- Name: account account_account_type_fk; Type: FK CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.account
    ADD CONSTRAINT account_account_type_fk FOREIGN KEY (account_type_id) REFERENCES devsu.account_type(account_type_id);


--
-- TOC entry 2913 (class 2606 OID 89278)
-- Name: account account_customer_projection_fk; Type: FK CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.account
    ADD CONSTRAINT account_customer_projection_fk FOREIGN KEY (customer_id) REFERENCES devsu.customer_projection(customer_id);


--
-- TOC entry 2914 (class 2606 OID 89108)
-- Name: account_transaction account_transaction_account_fk; Type: FK CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.account_transaction
    ADD CONSTRAINT account_transaction_account_fk FOREIGN KEY (account_id) REFERENCES devsu.account(account_id);


--
-- TOC entry 2915 (class 2606 OID 89113)
-- Name: account_transaction account_transaction_account_transaction_type_fk; Type: FK CONSTRAINT; Schema: devsu; Owner: postgres
--

ALTER TABLE ONLY devsu.account_transaction
    ADD CONSTRAINT account_transaction_account_transaction_type_fk FOREIGN KEY (account_transaction_type_id) REFERENCES devsu.account_transaction_type(account_transaction_type_id);


-- Completed on 2025-08-13 21:30:53 -05

--
-- PostgreSQL database dump complete
--

