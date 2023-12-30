#
# Copyright (c) 2023, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
#

include ../settings.mk

QUARKUS = quarkus
MVN     = mvn
PODMAN  = podman

IMAGE_NAME = $(IMAGE_REPO)/sde-devel
# IMAGE_TAG from settings.mk

all:
	$(QUARKUS) build
	$(PODMAN) build -t $(IMAGE_NAME):$(IMAGE_TAG) -f src/main/docker/Dockerfile.jvm .

push: all
	$(PODMAN) image push $(IMAGE_NAME):$(IMAGE_TAG)

clean:
	$(MVN) clean

purge: clean
	$(PODMAN) image rm -i $(IMAGE_NAME):$(IMAGE_TAG)
