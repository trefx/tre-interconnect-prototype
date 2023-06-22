# Network creation

podman network create lab
podman network create dmz
podman network create sde

# Play deployments

podman kube play --configmap demo-config.yml demo-lab.yml
podman kube play --configmap demo-config.yml demo-dmz.yml
podman kube play --configmap demo-config.yml demo-sde.yml

# Monitor logging output

podman pod logs -f demo-lab
podman pod logs -f demo-dmz
podman pod logs -f demo-sde

# Stop and remove deployments

podman kube down demo-lab.yml
podman kube down demo-dmz.yml
podman kube down demo-sde.yml

----

Webportal for Reseachers to submitting Analysis Requests, and view Analysis Responses - Port 8080.
Webportal for SDE Admins to check Analysis Requests and Analysis Responses - Port 8090.
