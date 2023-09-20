# Setup networking addresses

Edit "demo-config.yml" to set "lab_amqpbroker_host", "dmz_amqpbroker_host", "sde_amqpbroker_host" and "anal_ds_amqpbroker_host" to the primary IP address of host machine.

# Setup data volumes for anal demo (anal-data directory)

tar tf anal-data_2023-09-05.tar.gz

# Play deployments

podman kube play --configmap demo-config.yml demo-lab.yml
podman kube play --configmap demo-config.yml demo-dmz.yml
podman kube play --configmap demo-config.yml demo-sde.yml
podman kube play --configmap demo-config.yml demo-anal-datashield.yml

# Monitor logging output

podman pod logs -f demo-lab
podman pod logs -f demo-dmz
podman pod logs -f demo-sde
podman pod logs -f demo-anal-datashield

# Stop and remove deployments

podman kube down demo-lab.yml
podman kube down demo-dmz.yml
podman kube down demo-sde.yml
podman kube down demo-anal-datashield.yml

----

Webportal for Reseachers to submitting Analysis Requests, and view Analysis Responses - Port 8080.
Webportal for SDE Admins to check Analysis Requests and Analysis Responses - Port 8090.

To run webportals in interactive development mode:
ng serve --disable-host-check --host 0.0.0.0 --port 4200
