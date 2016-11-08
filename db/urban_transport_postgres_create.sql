CREATE TABLE "timetable" (
	"id" serial NOT NULL,
	"station_id" integer NOT NULL,
	"route_id" integer NOT NULL,
	"arrive_time" TIME NOT NULL,
	CONSTRAINT timetable_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "station" (
	"id" serial NOT NULL,
	"stop_id" integer NOT NULL,
	"sequence" integer NOT NULL,
	"route_id" integer NOT NULL,
	CONSTRAINT station_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "stop" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT stop_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "route" (
	"id" serial NOT NULL,
	"number" integer NOT NULL,
	CONSTRAINT route_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "transport" (
	"id" serial NOT NULL,
	"vehicle" character varying NOT NULL,
	"registration_number" character varying NOT NULL UNIQUE,
	"type" character varying NOT NULL,
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
	CONSTRAINT driver_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "timetable" ADD CONSTRAINT "timetable_fk0" FOREIGN KEY ("station_id") REFERENCES "station"("id");
ALTER TABLE "timetable" ADD CONSTRAINT "timetable_fk1" FOREIGN KEY ("route_id") REFERENCES "route"("id");

ALTER TABLE "station" ADD CONSTRAINT "station_fk0" FOREIGN KEY ("stop_id") REFERENCES "stop"("id");
ALTER TABLE "station" ADD CONSTRAINT "station_fk1" FOREIGN KEY ("route_id") REFERENCES "route"("id");



ALTER TABLE "transport" ADD CONSTRAINT "transport_fk0" FOREIGN KEY ("driver_id") REFERENCES "driver"("id");
ALTER TABLE "transport" ADD CONSTRAINT "transport_fk1" FOREIGN KEY ("route_id") REFERENCES "route"("id");


