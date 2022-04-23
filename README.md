# Java-SimpanPinjam

1. GET USERS
METHOD : GET
ENDPOINT : http://localhost:8080/api/alami/v1/users
RESPONSE :
{
    "response_code": "200",
    "response_message": "Success",
    "data": [
        {
            "id": "0120220101000000001",
            "name": "WAWAN SETIAWAN",
            "date_of_birth": "10 Jan 1990",
            "address": "KOMPLEKS ASIA SERASI NO 100"
        },
        {
            "id": "0120220101000000002",
            "name": "TEGUH SUDIBYANTORO",
            "date_of_birth": "10 Feb 1991",
            "address": "JALAN PAMEKARAN NO 99"
        },
        {
            "id": "0120220101000000003",
            "name": "JOKO WIDODO",
            "date_of_birth": "10 Mar 1992",
            "address": "DUSUN PISANG RT 10 RW 20"
        }
    ]
}

2. GET TRANSACTIONS BY USER ID
METHOD : GET
ENDPOINT : http://localhost:8080/api/alami/v1/transactions/0120220101000000001
RESPONSE :
{
    "response_code": "200",
    "response_message": "Success",
    "data": [
        {
            "id": "0220220101000000001",
            "user_id": "0120220101000000001",
            "user_name": "WAWAN SETIAWAN",
            "type": "PENYERAHAN DANA",
            "currency": "IDR",
            "amount": "1000000",
            "date": "17 Aug 2020"
        },
        {
            "id": "0220220101000000005",
            "user_id": "0120220101000000001",
            "user_name": "WAWAN SETIAWAN",
            "type": "PENYERAHAN DANA",
            "currency": "IDR",
            "amount": "5000000",
            "date": "01 Dec 2020"
        }
    ]
}

3. GET TRANSACTIONS BY DATE
METHOD : GET
ENDPOINT : http://localhost:8080/api/alami/v1/transactions/from=18082020/to=23042022
RESPONSE :
{
    "response_code": "200",
    "response_message": "Success",
    "data": [
        {
            "id": "0220220101000000002",
            "user_id": "0120220101000000002",
            "user_name": "TEGUH SUDIBYANTORO",
            "type": "PENYERAHAN DANA",
            "currency": "IDR",
            "amount": "5000000",
            "date": "18 Aug 2020"
        },
        {
            "id": "0220220101000000003",
            "user_id": "0120220101000000003",
            "user_name": "JOKO WIDODO",
            "type": "PEMINJAMAN DANA",
            "currency": "IDR",
            "amount": "2000000",
            "date": "30 Sep 2020"
        },
        {
            "id": "0220220101000000004",
            "user_id": "0120220101000000003",
            "user_name": "JOKO WIDODO",
            "type": "PENGEMBALIAN DANA",
            "currency": "IDR",
            "amount": "1000000",
            "date": "10 Nov 2020"
        },
        {
            "id": "0220220101000000005",
            "user_id": "0120220101000000001",
            "user_name": "WAWAN SETIAWAN",
            "type": "PENYERAHAN DANA",
            "currency": "IDR",
            "amount": "5000000",
            "date": "01 Dec 2020"
        },
        {
            "id": "0220220101000000006",
            "user_id": "0120220101000000002",
            "user_name": "TEGUH SUDIBYANTORO",
            "type": "PENGAMBILAN DANA",
            "currency": "IDR",
            "amount": "2000000",
            "date": "01 Dec 2020"
        }
    ]
}

4. ADD USER
METHOD : POST
ENDPOINT : http://localhost:8080/api/alami/v1/users
REQUEST :
{
    "name": "FATHUR RAHMAN JAMIL",
    "date_of_birth": "27061997",
    "address": "JALAN BALADEWA UTARA NO 8"
}
RESPONSE :
{
    "response_code": "201",
    "response_message": "Success",
    "data": {
        "id": "0120220423221433488",
        "name": "FATHUR RAHMAN JAMIL",
        "date_of_birth": "27 Jun 1997",
        "address": "JALAN BALADEWA UTARA NO 8"
    }
}

6. ADD TRANSACTION
METHOD : POST
ENDPOINT : http://localhost:8080/api/alami/v1/transactions
REQUEST :
{
    "user_id": "0120220423215649226",
    "type": "PENYERAHAN DANA",
    "amount": "2000000"
}
RESPONSE :
{
    "response_code": "201",
    "response_message": "Success",
    "data": {
        "id": "0220220423221521186",
        "user_id": "0120220423221433488",
        "user_name": "FATHUR RAHMAN JAMIL",
        "type": "PENYERAHAN DANA",
        "currency": "IDR",
        "amount": "2000000",
        "date": "23 Apr 2022"
    }
}
