# Response

```json

{
    "links": {
        "atual": "http://localhost:8080/api/albums-pageable?page=0&size=2&sort=title,asc",
        "primeira": "http://localhost:8080/api/albums-pageable?page=0&size=2&sort=title,asc",
        "anterior": null,
        "proxima": "http://localhost:8080/api/albums-pageable?page=1&size=2&sort=title,asc",
        "ultima": "http://localhost:8080/api/albums-pageable?page=4&size=2&sort=title,asc"
    },
    "data": [
        {
            "id": 1,
            "title": "Top Hits Vol 1",
            "description": "Top hits vol 1. description",
            "releaseDate": "10-03-1981",
            "actors": [
                {
                    "id": 1,
                    "firstName": "John",
                    "lastName": "Doe"
                }
            ]
        },
        {
            "id": 10,
            "title": "Top Hits Vol 10",
            "description": "Top hits vol 10. description",
            "releaseDate": "10-03-1990",
            "actors": [
                {
                    "id": 5,
                    "firstName": "Pauline",
                    "lastName": "Rios"
                }
            ]
        }
    ]
}
```
