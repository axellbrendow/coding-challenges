# InDebted HTTP Specifications

### Referrals Endpoint

Use this endpoint to ingest referrals from the client.

#### Request

```
POST /referrals
{
  Referrals: Array[
    Referral {
      CustomerName string
      CustomerReference string
      Balance int
      PurchaseAmount int
      Fees int
      DueDate string // (YYYY-MM-DD)
      Emails Array[string]
      Address string
    }
  ]
}
```

Sample

```json
{
  "Referrals": [
    {
      "CustomerName": "John S Doe",
      "CustomerReference": "joe-customer",
      "Balance": 10050,
      "PurchaseAmount": 8000,
      "Fees": 2050,
      "DueDate": "2023-01-20",
      "Emails": ["john.s.doe@indebted.co", "john.s.doe.backup@indebted.co"],
      "Address": "1 Paulista Avenue, Sao Paulo, 013001-012, Brazil"
    },
    {
      "CustomerName": "Olivia J Smith",
      "CustomerReference": "olivia-customer",
      "Balance": 25000,
      "PurchaseAmount": 20000,
      "Fees": 5000,
      "DueDate": "2023-02-10",
      "Emails": ["olivia.j.smith@indebted.co"],
      "Address": "500 Boa Viagem Avenue, Unit 201, Recife, 51021-000, Brazil"
    },
    {
      "CustomerName": "Ted Fred",
      "CustomerReference": "ted-fred-customer",
      "Balance": 8525,
      "PurchaseAmount": 7500,
      "Fees": 1025,
      "DueDate": "2023-01-15",
      "Emails": ["ted.fred@indebted.co"],
      "Address": "1 Beira-Mar Avenue, Fortaleza, 60165-120, Brazil"
    }
  ]
}
```
