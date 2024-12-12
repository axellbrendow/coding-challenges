-- works for mysql https://sqlfiddle.com/mysql/online-compiler?id=971977a4-5954-44a7-ba3a-52914e45d637

-- CREATING TABLES

DROP TABLE IF EXISTS parking_lots;
CREATE TABLE parking_lots (
  id SERIAL PRIMARY KEY,
  total_spots INT NOT NULL,
  hourly_price DECIMAL NOT NULL,
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS consumers;
CREATE TABLE consumers (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);

DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations (
  id SERIAL PRIMARY KEY,
  consumer_id BIGINT UNSIGNED NOT NULL,
  parking_lot_id BIGINT UNSIGNED NOT NULL,
  start_at TIMESTAMP NOT NULL,
  end_at TIMESTAMP NOT NULL,
  status ENUM('PENDING', 'RESERVED', 'CANCELED') DEFAULT 'PENDING',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  cancelled_at TIMESTAMP,
  cancelled_by INT,
  CONSTRAINT fk_reservation_consumer FOREIGN KEY (consumer_id) REFERENCES consumers(id),
  CONSTRAINT fk_reservation_parking_lot FOREIGN KEY (parking_lot_id) REFERENCES parking_lots(id)
);

DROP TABLE IF EXISTS files;
CREATE TABLE files (
  id SERIAL PRIMARY KEY,
  parking_lot_id BIGINT UNSIGNED NOT NULL,
  path VARCHAR(500) NOT NULL,
  mimetype VARCHAR(100) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_file_parking_lot FOREIGN KEY (parking_lot_id) REFERENCES parking_lots(id)
);

-- INSERTING VALUES

INSERT INTO parking_lots (total_spots, hourly_price, latitude, longitude) 
VALUES (1, 10.1, -37.23523, -111.72683);

INSERT INTO parking_lots (total_spots, hourly_price, latitude, longitude) 
VALUES (1, 11.1, -37.15734, -111.72683);

INSERT INTO consumers (name, email) VALUES ("consumer1", "consumer1@mail.com");
INSERT INTO consumers (name, email) VALUES ("consumer2", "consumer2@mail.com");

-- PROCEDURE TO RESERVE A SPOT

DELIMITER $$

DROP PROCEDURE IF EXISTS reserve_spot$$
CREATE PROCEDURE reserve_spot(
  consumer_id BIGINT UNSIGNED,
  parking_lot_id BIGINT UNSIGNED,
  start_at TIMESTAMP,
  end_at TIMESTAMP
)
this_procedure:BEGIN
  START TRANSACTION;
    SET @total_spots = 0;
    SET @conflicting_reservations = 0;
    SET @available_spots = 0;

    SELECT total_spots INTO @total_spots
    FROM parking_lots AS pl
    WHERE pl.id = parking_lot_id
    FOR UPDATE;

    SELECT COUNT(*) INTO @conflicting_reservations FROM reservations AS r
    WHERE r.parking_lot_id = parking_lot_id
      AND (
        (start_at BETWEEN r.start_at AND r.end_at)
        OR (end_at BETWEEN r.start_at AND r.end_at)
      );

    SET @available_spots = @total_spots - @conflicting_reservations;

    IF @available_spots = 0 THEN
      ROLLBACK;
      LEAVE this_procedure;
    END IF;

    INSERT INTO reservations (consumer_id, parking_lot_id, start_at, end_at)
    VALUES (consumer_id, parking_lot_id, start_at, end_at);
  COMMIT;
END;$$

DELIMITER ;

SET @consumer_id = 1;
SET @parking_lot_id = 1;
SET @start_at = DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 1 DAY);
SET @end_at = DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 2 DAY);
CALL reserve_spot(@consumer_id, @parking_lot_id, @start_at, @end_at);

SET @consumer_id = 2;
SET @parking_lot_id = 1;
SET @start_at = DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 1 DAY);
SET @end_at = DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 2 DAY);
CALL reserve_spot(@consumer_id, @parking_lot_id, @start_at, @end_at); -- should fail because all parking lots have only 1 spot

SET @consumer_id = 2;
SET @parking_lot_id = 2;
SET @start_at = DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 1 DAY);
SET @end_at = DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 2 DAY);
CALL reserve_spot(@consumer_id, @parking_lot_id, @start_at, @end_at);

-- SELECT * FROM parking_lots;
-- SELECT * FROM consumers;
SELECT * FROM reservations;
