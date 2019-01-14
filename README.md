# Back End Coding Challenge

Write a program that reads the included file of addresses and finds the geolocation of each address.  

Use https://maps.googleapis.com/maps/api/geocode/json?address=<address string> 

*Note that this free api is subject to rate limiting. You will need to handle this with a maximum number of retries being 5. If an address cannot be geolocated or maximum number of retries has exceeded, set the status to "NOT_FOUND"
**You may notice that we do not include an API Key for Google Maps. Google severely rate limits this api call if no api key is added. This is intended as the point of this assignment is to handle rate limiting.

The application should output a JSONArray of JSONObjects. 

Example output
```
[{
  "address": "address",
  "status": "FOUND"
  "location": {
    "lat": 1234.56,
    "lng": 1234.56
  }
}]
```
**You may use a JSON library but otherwise stick to what comes with Java JDK

We leave the implementation open ended so you can work comfortably using tech you are used to.
