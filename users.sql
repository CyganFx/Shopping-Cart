PGDMP                     	    x            users    11.7    11.7                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    24656    users    DATABASE     �   CREATE DATABASE users WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE users;
             postgres    false            �            1259    32848    images    TABLE     �   CREATE TABLE public.images (
    id integer,
    file_name character varying(256),
    path character varying(256),
    description character varying(256)
);
    DROP TABLE public.images;
       public         postgres    false            �            1259    41042    product    TABLE     �   CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(256),
    category text,
    price integer,
    image character varying(256)
);
    DROP TABLE public.product;
       public         postgres    false            �            1259    41040    product_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public       postgres    false    200                       0    0    product_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;
            public       postgres    false    199            �            1259    24659    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    first_name character varying(20),
    last_name character varying(20),
    job character varying(30),
    email character varying(30),
    password character varying(30)
);
    DROP TABLE public.users;
       public         postgres    false            �            1259    24657    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public       postgres    false    197                       0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
            public       postgres    false    196            �
           2604    41045 
   product id    DEFAULT     h   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    200    199    200            �
           2604    24662    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    197    196    197                      0    32848    images 
   TABLE DATA               B   COPY public.images (id, file_name, path, description) FROM stdin;
    public       postgres    false    198                    0    41042    product 
   TABLE DATA               C   COPY public.product (id, name, category, price, image) FROM stdin;
    public       postgres    false    200   �       
          0    24659    users 
   TABLE DATA               P   COPY public.users (id, first_name, last_name, job, email, password) FROM stdin;
    public       postgres    false    197   �                  0    0    product_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.product_id_seq', 9, true);
            public       postgres    false    199                       0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 3, true);
            public       postgres    false    196            �
           2606    41050    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public         postgres    false    200            �
           2606    24664    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public         postgres    false    197            �
           2606    32854    images images_id_fkey    FK CONSTRAINT     o   ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_id_fkey FOREIGN KEY (id) REFERENCES public.users(id);
 ?   ALTER TABLE ONLY public.images DROP CONSTRAINT images_id_fkey;
       public       postgres    false    2700    198    197               �   x����
�0���S�	�ћ�� P���֍�s��v�coΐ?�G�%�FC�X�;���$����t }�%!���ָ�\�U�^ѭ�Mb}g킴,�ٚdqB9�E>��Fq�t$��O�{�π�F����!E�J�a���U�ԡ����Y�ZǙ��GEϫ�B�Zu�         �   x�M�Q� ������]�4�E��HX��Y�|�6IY?gCSI��m]m>ъ�^N�t*z�(z�eҧ`\g �_��O�8.u��\	�@Q���a6앜�o�U�O���`�$����	������s��#�
�Α����%���(��-N
�B� �.`7      
   �   x�e�;�0��aVv"AKA�R��Y��-���M���"�<��)8�H	.l)�',k~��YA���q�D����tW�Q�Ln��L��'�V1��rt\�#��.�7U�a����4Yb���.d΋mo��*�3{�B�7��J�     