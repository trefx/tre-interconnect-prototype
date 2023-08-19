#
# Copyright (c) 2023, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
#

PODMAN  = podman

IMAGE_NAME = docker.io/risbic/anal-datashield-armadillo
IMAGE_TAG  = latest

all:
	$(PODMAN) build -t $(IMAGE_NAME):$(IMAGE_TAG) -f Containerfile .

push: all
	$(PODMAN) image push $(IMAGE_NAME):$(IMAGE_TAG)

clean:

purge: clean
	$(PODMAN) image rm -i $(IMAGE_NAME):$(IMAGE_TAG)
