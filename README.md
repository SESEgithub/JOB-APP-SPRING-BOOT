
-- job-post entity
BEGIN;

-- CREATE TABLE "job_post" -------------------------------------
CREATE TABLE "public"."job_post" ( 
	"post_id" Integer NOT NULL,
	"req_experience" Integer NOT NULL,
	"post_description" Character Varying( 255 ),
	"post_profile" Character Varying( 255 ),
	"post_teck_stack" Character Varying( 255 )[],
	PRIMARY KEY ( "post_id" ) );
 ;
-- -------------------------------------------------------------

COMMIT;


-- users entity
BEGIN;

-- CREATE TABLE "users" ----------------------------------------
CREATE TABLE "public"."users" ( 
	"id" BigInt NOT NULL,
	"password" Character Varying( 255 ),
	"user_name" Character Varying( 255 ),
	PRIMARY KEY ( "id" ) );
 ;
-- -------------------------------------------------------------

COMMIT;
