CREATE TABLE "timetable" (
	"id" serial NOT NULL,
	"transport_stop_id" integer NOT NULL,
	"route_id" integer NOT NULL,
	"arrival_time" TIME NOT NULL,
	CONSTRAINT timetable_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "route_2_stop" (
	"id" serial NOT NULL,
	"route_id" integer NOT NULL,
	"transport_stop_id" integer NOT NULL,
	"sequence" integer NOT NULL,
	CONSTRAINT route_2_stop_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "transport_stop" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT transport_stop_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "route" (
	"id" serial NOT NULL,
	"number" integer NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT route_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "transport" (
	"id" serial NOT NULL,
	"vehicle_type" character varying NOT NULL,
	"driver_id" integer NOT NULL UNIQUE,
	"route_id" integer NOT NULL,
	CONSTRAINT transport_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "driver" (
	"id" serial NOT NULL,
	"first_name" character varying NOT NULL,
	"last_name" character varying NOT NULL,
	"birthday" DATE NOT NULL,
	"license_category" character varying NOT NULL,
	CONSTRAINT driver_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "timetable" ADD CONSTRAINT "timetable_fk0" FOREIGN KEY ("transport_stop_id") REFERENCES "transport_stop"("id");
ALTER TABLE "timetable" ADD CONSTRAINT "timetable_fk1" FOREIGN KEY ("route_id") REFERENCES "route"("id");

ALTER TABLE "route_2_stop" ADD CONSTRAINT "route_2_stop_fk0" FOREIGN KEY ("route_id") REFERENCES "route"("id");
ALTER TABLE "route_2_stop" ADD CONSTRAINT "route_2_stop_fk1" FOREIGN KEY ("transport_stop_id") REFERENCES "transport_stop"("id");



ALTER TABLE "transport" ADD CONSTRAINT "transport_fk0" FOREIGN KEY ("driver_id") REFERENCES "driver"("id");
ALTER TABLE "transport" ADD CONSTRAINT "transport_fk1" FOREIGN KEY ("route_id") REFERENCES "route"("id");

