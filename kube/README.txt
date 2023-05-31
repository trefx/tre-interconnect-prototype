# Network creation

podman network create lab
podman network create dmz
podman network create sde

# Play deployments

podman play kube --network podman:ip=10.88.0.2 demo-lab.yml
podman play kube --network podman:ip=10.88.0.3 demo-dmz.yml
podman play kube --network podman:ip=10.88.0.4 demo-sde.yml

# Monitor logging output

podman pod logs -f demo-lab
podman pod logs -f demo-dmz
podman pod logs -f demo-sde

# Stop and remove deployments

podman pod stop demo-lab
podman pod rm demo-lab
podman pod stop demo-dmz
podman pod rm demo-dmz
podman pod stop demo-sde
podman pod rm demo-sde

----

Webportal for Reseachers to submitting Analysis Requests, and view Analysis Responses - Port 8080.
Webportal for SDE Admins to check Analysis Requests and Analysis Responses - Port 8082.
