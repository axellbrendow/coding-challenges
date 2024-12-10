# Client HTTP Specifications

### Referrals Endpoint

Use this endpoint to fetch referrals by date.

#### Request (Page 1)

```
GET referrals/2023-07-12
```

#### Response

```json
{
  "referrals": [
    {
      "name": "John S Doe",
      "balance": 100.5,
      "purchase_amount": 80,
      "fees": 20.5,
      "due_date": "20/01/2023",
      "reference": "joe-customer",
      "email_1": "john.s.doe@indebted.co",
      "email_2": "john.s.doe.backup@indebted.co",
      "address": "1 Paulista Avenue, Sao Paulo, 013001-012, Brazil"
    },
    {
      "name": "Olivia J Smith",
      "balance": 250.0,
      "purchase_amount": 200,
      "fees": 50.0,
      "due_date": "10/02/2023",
      "reference": "olivia-customer",
      "email_1": "olivia.j.smith@indebted.co",
      "address": "500 Boa Viagem Avenue, Unit 201, Recife, 51021-000, Brazil"
    }
  ],
  "next_page": "referrals/2023-07-12?page=2"
}
```

#### Request (Page 2)

```
GET referrals/2023-07-12?page=2
```

#### Response

```json
{
  "referrals": [
    {
      "name": "Ted Fred",
      "balance": 85.25,
      "purchase_amount": 75,
      "fees": 10.25,
      "due_date": "15/01/2023",
      "reference": "ted-fred-customer",
      "email_1": "ted.fred@indebted.co",
      "email_2": "ted.fred@indebted.co",
      "address": "1 Beira-Mar Avenue, Fortaleza, 60165-120, Brazil"
    }
  ]
}
```

Note that Ted's email is duplicated in the response.
