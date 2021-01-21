
- Vehicles: microservice for adding new vehicles data.
Endpoints:
/api/vehicle/{vin} -> get vehicles by vin
/api/vehicles -> get vehicles data sorted by timestamp
/api/vehicles -> add/update new vehicle data

- VehicleReading: microservice for capturing real time vehicle data and send alert if tire pressure low, gas low, high speed etc.
Endpoints:
/vehicle/geoLocation/{vin} -> get vehicles by vin
/vehicle/reading -> save vehicl reading data in db

- VehicleAlerts:
microservice for reading alerts data through SQS and putting it in alerts table
Endpoints:
/alerts/addAlert -> save alerts in db
/alerts/{vin} -> get alerts by vin
/alerts/high -> get high alerts for past 2 hours
