# Design a Parking Lot system

## Requirements

Business owner:
- CRUD parking lot
- See all reservations per parking lot
- Set and modify hourly prices
- Cancel reservations
- Add photos and documents per parking lot
- View metrics per parking lot and aggregated for all parking lots
- Set price per spot and per hour (follow up question)

Consumer:
- Search for available spots in all parking lots filtering by start and end time
- Reserve a spot passing start and end time
- See history and upcoming reservations
- Cancel reservations

## Data model

### Main types of primary keys:

```
id SERIAL PRIMARY KEY

id UUID PRIMARY KEY

// ULID (Universally Unique Lexicographically Sortable Identifiers)
// Note that CHAR(26) indicates that all values will have exactly 26 characters
id CHAR(26) PRIMARY KEY

// Snowflake IDs 64-bit |1 sign bit + 41 bits for timestamp + 10 bits for machine id + 12 bits for sequence number|
id BIGINT PRIMARY KEY
```

### Entities

Inside the file `system-design.sql`.

## API Design

### Get reservations history

```
GET /v1/reservations
GET consumerId from the authentication token
QUERY PARAMS {
  start_at (optional) (example: "2024-12-09T12:00:00-03:00")
  end_at (optional) (example: "2024-12-10T12:00:00-03:00")
}
RESPONSE {
  id
  consumerId
  parkingLotId
  startAt
  endAt
  status
  createdAt
  cancelledAt
  cancelledBy
}[]
```

### Reserve a spot

```
POST /v1/reservations
GET consumerId from the authentication token
REQUEST {
  parkingLotId
  startAt
  endAt
}
RESPONSE {
  id
  consumerId
  parkingLotId
  startAt
  endAt
  status
  createdAt
  cancelledAt
  cancelledBy
}
```

### Get available spots in a parking lot

```
GET /v1/parking_lots/:parking_lot_id/spots
QUERY PARAMS {
  start_at (example: "2024-12-09T12:00:00-03:00")
  end_at (example: "2024-12-10T12:00:00-03:00")
  show_reserved (optional, default false)
}
RESPONSE {
  ...spot
}[]
```

## Write an SQL query to reserve a spot

Take a look at the procedure called `reserve_spot` inside the file `system-design.sql`.

## Write an SQL query to search for available spots in all parking lots

TODO
