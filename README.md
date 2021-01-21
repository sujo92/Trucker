
## Vehicles: 
microservice for adding new vehicles data.
### Endpoints:
- GET: /api/vehicle/{vin} -> get vehicles by vin
- GET: /api/vehicles -> get vehicles data sorted by timestamp
- POST: /api/vehicles -> add new vehicle data
- PUT: /api/vehicles -> update new vehicle data

## VehicleReading: 
microservice for capturing real time vehicle data and send alert if tire pressure low, gas low, high speed etc.
### Endpoints:
- GET: /vehicle/geoLocation/{vin} -> get vehicles by vin
- POST: /vehicle/reading -> save vehicl reading data in db

## VehicleAlerts:
microservice for reading alerts data through SQS and putting it in alerts table
### Endpoints:
- POST: /alerts/addAlert -> save alerts in db
- GET: /alerts/{vin} -> get alerts by vin
- GET: /alerts/high -> get high alerts for past 2 hours
