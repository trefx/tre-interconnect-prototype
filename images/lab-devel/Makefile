#
# Copyright (c) 2023, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
#

QUARKUS = quarkus
MVN     = mvn
PODMAN  = podman

IMAGE_NAME = docker.io/risbic/lab-devel
IMAGE_TAG  = latest

all:
	$(QUARKUS) build
	$(PODMAN) build -t $(IMAGE_NAME):$(IMAGE_TAG) -f src/main/docker/Dockerfile.jvm .

push: all
	$(PODMAN) image push $(IMAGE_NAME):$(IMAGE_TAG)

clean:
	$(MVN) clean

purge: clean
	$(PODMAN) image rmi $(IMAGE_NAME):$(IMAGE_TAG)
