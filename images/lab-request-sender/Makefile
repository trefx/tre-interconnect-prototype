#
# Copyright (c) 2023, TRE-FX Consortium Members
# Copyright (c) 2023-2024, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
#

include ../settings.mk
include ../build.mk

QUARKUS = quarkus
MVN     = mvn
PODMAN  = podman

IMAGE_NAME = $(IMAGE_REPO)/lab-request-sender
# IMAGE_TAG from settings.mk

all:
	$(QUARKUS) build
	$(PODMAN) build -t $(IMAGE_NAME):$(IMAGE_TAG) -f src/main/docker/Dockerfile.jvm .

push: all
	$(PODMAN) image push $(IMAGE_NAME):$(IMAGE_TAG)

clean:
	$(MVN) clean

purge: clean
	$(PODMAN) $(POCMAN_IMAGE_RM) $(IMAGE_NAME):$(IMAGE_TAG)
