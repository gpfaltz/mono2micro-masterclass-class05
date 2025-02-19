# mono2micro-masterclass-class05
Monolithic Applcation to Micro Services Applications - Class 05/05

Main Objective: Application Containerization, Kubernetes Deployment in the Cloud and Virtual Thread Implemetation

Pre requistes:
JAVA 21 Installed
Docker  Installed
Openshift Sandbox Account
Openshift Command Line Interface Installed

Quarkus Extensions Used on Project:
RestEasy Reactive - quarkus-resteasy
RestEasy Reactive JSON-B - quarkus-resteasy-jsonb
Hibernate ORM - quarkus-hibernate-orm3
Hibernate ORM with Panache - quarkus-hibernate-orm-panache
JDBC Driver - PostgreSQL - quarkus-jdbc-postgresql
Lombok - 1.18.36 - org.projectlombok
RestEasy Reactive Client - quarkus-resteasy-client
RestEasy Reactive Client JSON-B - quarkus-resteasy-client-jsonb
SmallRye Health - quarkus-smallrye-health
SmallRye Fault Tolerance - quarkus-smallrye-fault-tolerance
Container Image Jib - quarkus-container-image-jib
Container Kubernetes - quarkus-kubernetes

Connect into Openshift Sendbox using the Openshift Command Line Interface
oc login --token=sha256~CBkgL_7QlZMY0VXqDdMpMIH4BOAMNe2JNbznKwN9Z9I --server=https://api.rm2.thpm.p1.openshiftapps.com:6443

Create Postgresql with YAML file
kubectl apply -f postgresql.yaml

Login into Docker Hub
docker login

Get Pods Informations
kubectl get pods

Package each Micro App into Kubernetes
./mvnw clean package -Dquarkus.kubernetes.deploy=true

Get Kubernetes Services Informations
kubectl get svc

Create Route to access Service
oc expose svc flight-app
oc expose svc hotel-app
oc expose svc travelorder-app

Get Routes Informations
kubectl get routes

Testing APP Access
curl flight-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/flight
curl hotel-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/hotel
curl trovelorder-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/travelorder

Looping TravelOrder Accesses
while true; do curl trovelorder-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/travelorder; sleep .3; done

Test Kubernetes Creating a new Flight
curl -d "{\"travelOrderId\": \"1\", \"sourceAirport\": \"GRU\", \"destinyAirport\": \"SDU\"}" -H "Content-Type: application/json" http://flight-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/flight

Test Kubernetes Creating a new Hotel
curl -d "{\"travelOrderId\": \"2\", \"nights\": \"10\"}" -H "Content-Type: application/json" http://hotel-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/hotel

Test Kubernetes Creating a new TravelOrder
curl -d "{\"sourceAirport\": \"GRU\", \"destinyAirport\": \"GIG\", \"nights\": \"5\"}" -H "Content-Type: application/json" http://trovelorder-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/travelorder
curl -d "{\"sourceAirport\": \"GRU\", \"destinyAirport\": \"SDU\", \"nights\": \"10\"}" -H "Content-Type: application/json" http://trovelorder-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/travelorder
curl -d "{\"sourceAirport\": \"GRU\", \"destinyAirport\": \"CWB\", \"nights\": \"15\"}" -H "Content-Type: application/json" http://trovelorder-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/travelorder
curl -d "{\"sourceAirport\": \"GRU\", \"destinyAirport\": \"FLN\", \"nights\": \"20\"}" -H "Content-Type: application/json" http://trovelorder-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/travelorder
curl -d "{\"sourceAirport\": \"GRU\", \"destinyAirport\": \"POA\", \"nights\": \"25\"}" -H "Content-Type: application/json" http://trovelorder-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/travelorder


Testing Healh Check end point:
http://flight-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/q/health
http://hotel-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/q/health
http://trovelorder-app-gpfaltz-dev.apps.rm2.thpm.p1.openshiftapps.com/q/health

Checking Virtual Thread is Working
Go into travelorder-app pod's log and check is printing into the log
Example
Thread[#20,executor-thread-1,5,main]


