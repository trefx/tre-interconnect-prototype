podman network create lab
podman network create dmz
podman network create sde

podman play kube --network lab demo-lab.yml
podman play kube --network dmz demo-dmz.yml
podman play kube --network sde demo-sde.yml

----

Webportal for Reseachers to submitting Analysis Requests, and view Analysis Responses - Port 8080.
