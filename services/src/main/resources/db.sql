CREATE TABLE "timetable" (
	"id" serial NOT NULL,
	"arrival_time" TIME NOT NULL UNIQUE,
	CONSTRAINT timetable_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "route_station" (
	"id" serial NOT NULL,
	"stop_id" integer NOT NULL,
	"transport_id" integer NOT NULL,
	"sequence" integer NOT NULL,
	"timetable_id" integer NOT NULL,
	CONSTRAINT route_station_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "stop" (
	"id" serial NOT NULL,
	"name_en" character varying NOT NULL UNIQUE,
	"name_ru" character varying NOT NULL,
	CONSTRAINT stop_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "transport" (
	"id" serial NOT NULL,
	"vehicle_type" character varying NOT NULL,
	"route_number" integer NOT NULL,
	"route_name" character varying NOT NULL,
	CONSTRAINT transport_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "driver" (
	"id" serial NOT NULL,
	"first_name" character varying,
	"last_name" character varying,
	"birthday" DATE NOT NULL,
	"transport_id" integer,
	CONSTRAINT driver_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "logs" (
	"event_date" character varying(100) NOT NULL,
	"level" character varying(200) NOT NULL,
	"logger" character varying(500) NOT NULL,
	"message" character varying(100) NOT NULL,
	"exception" character varying(500) NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "app_user" (
	"id" serial NOT NULL,
	"username" character varying NOT NULL UNIQUE,
	"password" character varying NOT NULL,
	"email" character varying NOT NULL,
	"role_id" integer NOT NULL,
	CONSTRAINT app_user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "role" (
	"id" serial NOT NULL,
	"role" character varying NOT NULL,
	CONSTRAINT role_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);




ALTER TABLE "route_station" ADD CONSTRAINT "route_station_fk0" FOREIGN KEY ("stop_id") REFERENCES "stop"("id");
ALTER TABLE "route_station" ADD CONSTRAINT "route_station_fk1" FOREIGN KEY ("transport_id") REFERENCES "transport"("id");
ALTER TABLE "route_station" ADD CONSTRAINT "route_station_fk2" FOREIGN KEY ("timetable_id") REFERENCES "timetable"("id");



ALTER TABLE "driver" ADD CONSTRAINT "driver_fk0" FOREIGN KEY ("transport_id") REFERENCES "transport"("id");


ALTER TABLE "app_user" ADD CONSTRAINT "app_user_fk0" FOREIGN KEY ("role_id") REFERENCES "role"("id");

